package com.muldis.object_notation_processor_reference_app;

import com.muldis.object_notation_processor_reference_util.processor.Analyze;
import com.muldis.object_notation_processor_reference_util.processor.Processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class App
{
    private enum App_Arg_Names
    {
        task,
        in,
        out,
        enc
    }

    private enum App_Tasks
    {
        version,
        help,
        analyze,
        validate,
        format,
        textify,
        untextify,
        blobify,
        unblobify
    }

    private App()
    {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(final String[] raw_app_args)
    {
        task_version();
        final Map<App_Arg_Names, String> app_args = normalize_app_args(raw_app_args);
        if (app_args.isEmpty())
        {
            System.out.println("Fatal: Task-naming primary app argument is missing.");
            task_help();
            return;
        }
        final App_Tasks task;
        try
        {
            task = App_Tasks.valueOf(app_args.get(App_Arg_Names.task));
        }
        catch (final IllegalArgumentException e)
        {
            // App_Tasks.valueOf() throws IllegalArgumentException if task arg doesn't match member.
            System.out.println("Fatal: Unrecognized task: " + app_args.get(App_Arg_Names.task));
            task_help();
            return;
        }
        switch (task)
        {
            case version ->
            {
                // Skip since app always does this when it starts.
            }
            case help ->
            {
                task_help();
            }
            case analyze, validate, format, textify, untextify, blobify, unblobify ->
            {
                generic_task_process(task, app_args);
            }
            default ->
            {
                task_help();
            }
        }
    }

    private static Map<App_Arg_Names, String> normalize_app_args(final String[] raw_app_args)
    {
        // The "task" arg is expected to be positional, and the others named.
        // A positional arg does NOT start with "--", a named looks like "--foo=bar".
        final Map<App_Arg_Names, String> app_args = new HashMap<>();
        if (raw_app_args.length > 0 && !raw_app_args[0].startsWith("--"))
        {
            for (int i = 1; i < raw_app_args.length; i++)
            {
                final String raw_app_arg = raw_app_args[i];
                if (raw_app_arg.startsWith("--") && raw_app_arg.contains("="))
                {
                    final int eq_pos = raw_app_arg.indexOf("=");
                    final String arg_name = raw_app_arg.substring(2, eq_pos);
                    try
                    {
                        app_args.put(App_Arg_Names.valueOf(arg_name),
                            raw_app_arg.substring(eq_pos + 1));
                    }
                    catch (final IllegalArgumentException e)
                    {
                        // App_Arg_Names.valueOf() throws IllegalArgumentException
                        // if arg name doesn't match member.
                        System.out.println("Warning: Ignoring unrecognized argument: " + arg_name);
                    }
                }
            }
            // Assign the positional "task" last so it takes precedence over any named one.
            app_args.put(App_Arg_Names.task, raw_app_args[0]);
        }
        return app_args;
    }

    private static void task_version()
    {
        System.out.println("This application is"
            + " com.muldis.object_notation_processor_reference_app.App version 0.1.");
    }

    private static void task_help()
    {
        final String APP_NAME = "sh muonp.sh";
        final String ARG_IN  = "--in=<input file or directory path>";
        final String ARG_OUT = "--out=<output file or directory path>";
        final String ARG_ENC = "--enc=<output character encoding name>";
        System.out.println(
            "Usage:\n"
                + "  " + APP_NAME + " version\n"
                + "  " + APP_NAME + " help\n"
                + "  " + APP_NAME + " analyze "   + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " validate "  + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " format "    + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " textify "   + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " untextify " + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " blobify "   + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " unblobify " + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
        );
    }

    private static void generic_task_process(
        final App_Tasks task, final Map<App_Arg_Names, String> app_args)
    {
        System.out.println("This is task " + task + ".");
        @SuppressWarnings("checkstyle:Indentation")
        final Processor processor = switch (task)
        {
            case analyze -> new Analyze();
            default -> throw new UnsupportedOperationException(
                "generic_task_process(): task " + task + " is not handled.");
        };
        if (!app_args.containsKey(App_Arg_Names.in))
        {
            System.out.println("Fatal: Task analyze: Missing argument: " + App_Arg_Names.in);
            return;
        }
        if (!app_args.containsKey(App_Arg_Names.out))
        {
            System.out.println("Fatal: Task analyze: Missing argument: " + App_Arg_Names.out);
            return;
        }
        final Path path_in = Path.of(app_args.get(App_Arg_Names.in)).toAbsolutePath();
        final Path path_out = Path.of(app_args.get(App_Arg_Names.out)).toAbsolutePath();
        process_file_or_dir_recursive(processor, path_in, path_out);
    }

    private static void process_file_or_dir_recursive(
        final Processor processor, final Path path_in, final Path path_out)
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
            System.out.println("Fatal: Can't input from file or dir at path " + path_in
                + " or can't output to new file or dir at path " + path_out
                + " because either those two paths are the same or one is contained in the other.");
            return;
        }
        // We intentionally do not support following symbolic links for safety/simplicity reasons.
        if (Files.isSymbolicLink(path_in))
        {
            System.out.println("Fatal: Can't input from file or dir at path " + path_in
                + " because that is a symbolic link and we don't support following those.");
            return;
        }
        if (Files.isSymbolicLink(path_out))
        {
            System.out.println("Fatal: Can't output to file or dir at path " + path_out
                + " because that is a symbolic link and we don't support following those.");
            return;
        }
        // Gracefully handle user-specified paths not existing, though we still have to later handle
        // the case of it disappearing between this check and when we actually try to read it.
        if (Files.notExists(path_in, LinkOption.NOFOLLOW_LINKS))
        {
            System.out.println("Fatal: Can't input from file or dir at path " + path_in
                + " because no file or dir exists there.");
            return;
        }
        // For safety we don't ever want to overwrite existing files or directories;
        // if one wants to re-run a processing task they must remove previous run's output first,
        // or include a date stamp in the output path that changes per run, or something.
        if (Files.exists(path_out, LinkOption.NOFOLLOW_LINKS))
        {
            System.out.println("Fatal: Can't output to new file or dir at path " + path_out
                + " because some other file or dir already exists there.");
            return;
        }
        // For simplicity or to allow flexibility, we ignore whether or not input files are
        // writable or executable or hidden, as that shouldn't matter to us, probably.
        if (Files.isRegularFile(path_in, LinkOption.NOFOLLOW_LINKS))
        {
            // Gracefully handle user-specified paths not being readable to us, we still have to
            // later handle the case of us having lost privilege when we actually try to read it.
            if (!Files.isReadable(path_in))
            {
                System.out.println("Fatal: Can't input from existing regular file at path "
                    + path_in + " because we lack read privileges for it.");
                return;
            }
            System.out.println("Starting the process from file at path "
                + path_in + " to file at path " + path_out);
            try (InputStream stream_in = Files.newInputStream(path_in);
                OutputStream stream_out = Files.newOutputStream(path_out))
            {
                processor.process(stream_in, stream_out);
            }
            catch (final IOException e)
            {
                System.out.println("Fatal: An IOException occurred while attempting to process"
                    + " from file at path " + path_in
                    + " to file at path " + path_out + "; details: " + e);
                return;
            }
            System.out.println("Finished the process from file at path "
                + path_in + " to file at path " + path_out);
            return;
        }
        if (Files.isDirectory(path_in, LinkOption.NOFOLLOW_LINKS))
        {
            System.out.println("TODO: Do the thing with directory from "
                + path_in + " to " + path_out);
            // Gracefully handle user-specified paths not being readable to us, we still have to
            // later handle the case of us having lost privilege when we actually try to read it.
            if (!Files.isReadable(path_in))
            {
                System.out.println("Fatal: Can't input from existing dir at path "
                    + path_in + " because we lack read privileges for it.");
                return;
            }
            return;
        }
        throw new UnsupportedOperationException(
            "process_file_or_dir_recursive(): unexpected situation; path_in " + path_in
                + " is neither a symbolic link or regular file or directory.");
    }
}
