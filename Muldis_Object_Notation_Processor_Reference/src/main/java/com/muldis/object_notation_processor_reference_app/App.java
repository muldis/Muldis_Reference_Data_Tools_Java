package com.muldis.object_notation_processor_reference_app;

import com.muldis.object_notation_processor_reference_util.Logger;
import com.muldis.object_notation_processor_reference_util.Processor;
import com.muldis.object_notation_processor_reference_util.Repository;
import com.muldis.object_notation_processor_reference_util.processor.Duplicate;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class App
{
    private enum App_Arg_Name
    {
        task,
        verbose,
        resume,
        in,
        out,
    }

    private enum App_Task
    {
        version,
        help,
        duplicate,
        analyze,
        validate,
        format,
        textify,
        untextify,
        blobify,
        unblobify,
    }

    private App()
    {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(final String[] raw_app_args)
    {
        task_version();
        final Map<App_Arg_Name, String> app_args = normalized_app_args(raw_app_args);
        if (app_args.isEmpty())
        {
            System.out.println("Fatal: Task-naming primary app argument is missing.");
            task_help();
            return;
        }
        final App_Task task;
        try
        {
            task = App_Task.valueOf(app_args.get(App_Arg_Name.task));
        }
        catch (final IllegalArgumentException e)
        {
            // App_Task.valueOf() throws IllegalArgumentException if task arg doesn't match member.
            System.out.println("Fatal: Unrecognized task: " + app_args.get(App_Arg_Name.task));
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
            case duplicate, analyze, validate, format, textify, untextify, blobify, unblobify ->
            {
                generic_task_process(task, app_args);
            }
            default ->
            {
                task_help();
            }
        }
    }

    private static Map<App_Arg_Name, String> normalized_app_args(final String[] raw_app_args)
    {
        // The "task" arg is expected to be positional, and the others named.
        // A positional arg does NOT start with "--", a named looks like "--foo=bar" or "--foo".
        final Map<App_Arg_Name, String> app_args = new HashMap<>();
        if (raw_app_args.length > 0 && !raw_app_args[0].startsWith("--"))
        {
            for (int i = 1; i < raw_app_args.length; i++)
            {
                final String raw_app_arg = raw_app_args[i];
                if (raw_app_arg.startsWith("--"))
                {
                    final String arg_name;
                    final String arg_value;
                    if (raw_app_arg.contains("="))
                    {
                        // Named arg format "--foo=bar", the most generic format.
                        final int eq_pos = raw_app_arg.indexOf("=");
                        arg_name = raw_app_arg.substring(2, eq_pos);
                        arg_value = raw_app_arg.substring(eq_pos + 1);
                    }
                    else
                    {
                        // Named arg format "--foo", a Boolean where present is true, absent false.
                        arg_name = raw_app_arg.substring(2);
                        arg_value = null;
                    }
                    try
                    {
                        app_args.put(App_Arg_Name.valueOf(arg_name), arg_value);
                    }
                    catch (final IllegalArgumentException e)
                    {
                        // App_Arg_Name.valueOf() throws IllegalArgumentException
                        // if arg name doesn't match member.
                        System.out.println("Warning: Ignoring unrecognized argument: " + arg_name);
                    }
                }
            }
            // Assign the positional "task" last so it takes precedence over any named one.
            app_args.put(App_Arg_Name.task, raw_app_args[0]);
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
        System.out.println("Usage:");
        System.out.println("  " + APP_NAME + " version");
        System.out.println("  " + APP_NAME + " help");
        for (final String generic_task: List.of("duplicate", "analyze", "validate", "format",
            "textify", "untextify", "blobify", "unblobify"))
        {
            System.out.println("  " + APP_NAME + " " + generic_task
                + " [--verbose]"
                + " [--resume]"
                + " --in=<input file or directory path>"
                + " --out=<output file or directory path>"
            );
        }
    }

    private static void generic_task_process(
        final App_Task task, final Map<App_Arg_Name, String> app_args)
    {
        System.out.println("This is task " + task + ".");
        @SuppressWarnings("checkstyle:Indentation")
        final Processor processor = switch (task)
        {
            case duplicate -> new Duplicate();
            default -> throw new UnsupportedOperationException(
                "generic_task_process(): task " + task + " is not handled.");
        };
        final Boolean verbose = app_args.containsKey(App_Arg_Name.verbose);
        final Boolean resume_when_failure = app_args.containsKey(App_Arg_Name.resume);
        if (!app_args.containsKey(App_Arg_Name.in))
        {
            System.out.println("Fatal: Task " + task + ": Missing argument: " + App_Arg_Name.in);
            return;
        }
        if (!app_args.containsKey(App_Arg_Name.out))
        {
            System.out.println("Fatal: Task " + task + ": Missing argument: " + App_Arg_Name.out);
            return;
        }
        final Path path_in = Path.of(app_args.get(App_Arg_Name.in)).toAbsolutePath();
        final Path path_out = Path.of(app_args.get(App_Arg_Name.out)).toAbsolutePath();
        final Logger logger = new Logger(System.out, verbose ? System.out : null);
        final Repository repository = new Repository(logger);
        repository.process_file_or_dir_recursive(processor, path_in, path_out, resume_when_failure);
    }
}
