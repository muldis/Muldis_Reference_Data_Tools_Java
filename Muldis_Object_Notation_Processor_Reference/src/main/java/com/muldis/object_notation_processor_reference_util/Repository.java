package com.muldis.object_notation_processor_reference_util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class Repository
{
    private final Logger logger;

    public Repository(final Logger logger)
    {
        this.logger = logger;
    }

    // This method generally only will throw an exception if it was invoked with invalid arguments,
    // or if it seems to have a coding error from not anticipating a particular file type.
    // Otherwise, it returns true if it succeeded in processing its argument node,
    // and it returns false if it failed in processing its argument node.
    // This method implements a recursive operation over a regular file or a directory containing
    // either regular files or directories.  The method's "resume_when_failure" Boolean argument
    // determines whether the entire recursive operation is halted immediately if any individual
    // node fails to be processed (false argument), or if it attempts to keep going and process the
    // remainder of the nodes (true argument).
    @SuppressWarnings("checkstyle:MethodLength")
    public Boolean process_file_or_dir_recursive(
        final Processor processor,
        final Path path_in,
        final Path path_out,
        final Boolean each_file_all_at_once,
        final Boolean each_file_as_text,
        final Boolean resume_when_failure
    )
    {
        // We expect that whatever calls this routine has already normalized the paths to absolute.
        if (!path_in.isAbsolute())
        {
            throw new IllegalArgumentException("process_file_or_dir_recursive():"
                + " invalid argument path_in: is not absolute path: " + path_in);
        }
        if (!path_out.isAbsolute())
        {
            throw new IllegalArgumentException("process_file_or_dir_recursive():"
                + " invalid argument path_out: is not absolute path: " + path_out);
        }
        // For safety we want to ensure the input tree and output tree don't overlap.
        // Logically this also means that neither of them can be the filesystem root.
        if (path_in.startsWith(path_out) || path_out.startsWith(path_in))
        {
            this.logger.failure("Fatal: Can't input from file or dir at path " + path_in
                + " or can't output to new file or dir at path " + path_out
                + " because either those two paths are the same or one is contained in the other.");
            return false;
        }
        // We intentionally do not support following symbolic links for safety/simplicity reasons.
        if (Files.isSymbolicLink(path_in))
        {
            this.logger.failure("Fatal: Can't input from file or dir at path " + path_in
                + " because that is a symbolic link and we don't support following those.");
            return false;
        }
        if (Files.isSymbolicLink(path_out))
        {
            this.logger.failure("Fatal: Can't output to file or dir at path " + path_out
                + " because that is a symbolic link and we don't support following those.");
            return false;
        }
        // Gracefully handle user-specified paths not existing, though we still have to later handle
        // the case of it disappearing between this check and when we actually try to read it.
        if (Files.notExists(path_in, LinkOption.NOFOLLOW_LINKS))
        {
            this.logger.failure("Fatal: Can't input from file or dir at path " + path_in
                + " because no file or dir exists there.");
            return false;
        }
        // For safety we don't ever want to overwrite existing files or directories;
        // if one wants to re-run a processing task they must remove previous run's output first,
        // or include a date stamp in the output path that changes per run, or something.
        if (Files.exists(path_out, LinkOption.NOFOLLOW_LINKS))
        {
            this.logger.failure("Fatal: Can't output to new file or dir at path " + path_out
                + " because some other file or dir already exists there.");
            return false;
        }
        // For simplicity we require that the immediate parent dir of the named output path
        // already exists, and we will not be creating any nonexisting ancestor dirs.
        if (Files.notExists(path_out.getParent(), LinkOption.NOFOLLOW_LINKS))
        {
            this.logger.failure("Fatal: Can't output to new file or dir at path " + path_out
                + " because its parent dir " + path_out.getParent() + " doesn't exist.");
            return false;
        }
        // For simplicity or to allow flexibility, we ignore whether or not input files are
        // writable or executable or hidden, as that shouldn't matter to us, probably.
        if (Files.isRegularFile(path_in, LinkOption.NOFOLLOW_LINKS))
        {
            // Gracefully handle user-specified paths not being readable to us, we still have to
            // later handle the case of us having lost privilege when we actually try to read it.
            if (!Files.isReadable(path_in))
            {
                this.logger.failure("Fatal: Can't input from existing regular file at path "
                    + path_in + " because we lack read privileges for it.");
                return false;
            }
            this.logger.notice("Starting the process from file at path "
                + path_in + " to file at path " + path_out);
            // Open existing file for input in octet streaming mode, should fail if doesn't exist.
            // Open nonexisting file for output in octet streaming mode, fail if already exists.
            try (InputStream blob_stream_in = Files.newInputStream(path_in,
                    LinkOption.NOFOLLOW_LINKS, StandardOpenOption.READ);
                OutputStream blob_stream_out = Files.newOutputStream(path_out,
                    LinkOption.NOFOLLOW_LINKS,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW))
            {
                if (each_file_all_at_once)
                {
                    // Read entire input file from storage to memory first,
                    // then process it from memory to memory,
                    // then write entire output file from memory to storage.
                    // This will fail during the initial read for any file larger than about 1-2GB
                    // due to such being the limit of what a single Java byte[] or String can hold.
                    if (each_file_as_text)
                    {
                        this.logger.notice("Using method process_text_at_once().");
                        blob_stream_out.write(processor
                            .process_text_at_once(
                                new String(blob_stream_in.readAllBytes(), StandardCharsets.UTF_8))
                            .getBytes(StandardCharsets.UTF_8));
                    }
                    else
                    {
                        this.logger.notice("Using method process_blob_at_once().");
                        blob_stream_out.write(
                            processor.process_blob_at_once(blob_stream_in.readAllBytes()));
                    }
                }
                else
                {
                    // Read input file from storage to memory one octet or one Java char
                    // (up to 3 UTF-8 octets) at a time, each processor may or may not read entire
                    // input file before writing output file from memory to storage, each processor
                    // may or may not keep all internal working data for a task in memory at once.
                    // This will theoretically be able to handle files of any size, but actual
                    // limits would depend on the processor rather than Java's ability to store an
                    // entire file in a single byte[] or String value.
                    if (each_file_as_text)
                    {
                        try (InputStreamReader text_stream_in
                                = new InputStreamReader(blob_stream_in, StandardCharsets.UTF_8);
                            OutputStreamWriter text_stream_out
                                = new OutputStreamWriter(blob_stream_out, StandardCharsets.UTF_8))
                        {
                            this.logger.notice("Using method process_text_stream().");
                            // Note that InputStreamReader.read()
                            // returns one of 0..0xFFFF when there is another Java char
                            // and it returns -1 when there is none / the end of stream was passed.
                            processor.process_text_stream(text_stream_in, text_stream_out);
                        }
                        catch (final IOException e)
                        {
                            // So next level out of catch block can see and report on this failure.
                            throw e;
                        }
                    }
                    else
                    {
                        this.logger.notice("Using method process_blob_stream().");
                        // Note that InputStream.read()
                        // returns one of 0..255 when there is another octet
                        // and it returns -1 when there is none / the end of stream was passed.
                        processor.process_blob_stream(blob_stream_in, blob_stream_out);
                    }
                }
            }
            catch (final IOException e)
            {
                this.logger.failure("Fatal: An IOException occurred while attempting to process"
                    + " from file at path " + path_in
                    + " to file at path " + path_out + "; details: " + e);
                return false;
            }
            this.logger.notice("Finished the process from file at path "
                + path_in + " to file at path " + path_out);
            return true;
        }
        // For simplicity or to allow flexibility, we ignore whether or not input dirs are
        // writable or executable or hidden, as that shouldn't matter to us, probably.
        if (Files.isDirectory(path_in, LinkOption.NOFOLLOW_LINKS))
        {
            // Gracefully handle user-specified paths not being readable to us, we still have to
            // later handle the case of us having lost privilege when we actually try to read it.
            if (!Files.isReadable(path_in))
            {
                this.logger.failure("Fatal: Can't input from existing dir at path "
                    + path_in + " because we lack read privileges for it.");
                return false;
            }
            this.logger.notice("Starting the process from dir at path "
                + path_in + " to dir at path " + path_out);
            // Create nonexisting output directory, fail if already exists.
            try
            {
                Files.createDirectory(path_out);
            }
            catch (final IOException e)
            {
                this.logger.failure("Fatal: An IOException occurred while attempting to create"
                    + " output dir " + path_out + "; details: " + e);
                return false;
            }
            this.logger.notice("Created new empty output dir at path " + path_out);
            // Iterate children of input dir and make corresponding children of output dir.
            // Files.newDirectoryStream() does NOT include "." or ".." etc in child list.
            try (DirectoryStream<Path> dir_stream_in = Files.newDirectoryStream(path_in))
            {
                // Each child_path_in is a fully-qualified absolute path of the child already.
                for (final Path child_path_in: dir_stream_in)
                {
                    // Get the unqualified file name of the child, which is the last element.
                    final Path child_common_unqualified_path = child_path_in.getFileName();
                    // Append unqualified child file name to fully qualified path of output parent.
                    final Path child_path_out = path_out.resolve(child_common_unqualified_path);
                    // Recurse to actually process the child.
                    final Boolean was_child_success = process_file_or_dir_recursive(
                        processor, child_path_in, child_path_out,
                        each_file_all_at_once, each_file_as_text, resume_when_failure);
                    // If we should abort all processing as soon as any child node fails, do so.
                    if (!was_child_success && !resume_when_failure)
                    {
                        return false;
                    }
                    // Else if we should continue on with trying to process the rest of the child
                    // nodes regardless of whether the child we just tried had failed, then do so.
                }
            }
            catch (final IOException e)
            {
                this.logger.failure("Fatal: An IOException occurred while attempting to process"
                    + " from dir at path " + path_in
                    + " to dir at path " + path_out + "; details: " + e);
                return false;
            }
            this.logger.notice("Finished the process from dir at path "
                + path_in + " to dir at path " + path_out);
            return true;
        }
        throw new UnsupportedOperationException(
            "process_file_or_dir_recursive(): unexpected situation; path_in " + path_in
                + " is neither a symbolic link or regular file or directory.");
    }
}
