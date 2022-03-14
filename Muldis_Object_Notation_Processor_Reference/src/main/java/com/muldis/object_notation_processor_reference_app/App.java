package com.muldis.object_notation_processor_reference_app;

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
            case analyze ->
            {
                task_analyze(app_args);
            }
            case validate ->
            {
                task_validate(app_args);
            }
            case format ->
            {
                task_format(app_args);
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

    private static void task_analyze(final Map<App_Arg_Names, String> app_args)
    {
        System.out.println("This is task analyze.");
    }

    private static void task_analyze_per_artifact()
    {
        System.out.println("This is task analyze.");
    }

    private static void task_validate(final Map<App_Arg_Names, String> app_args)
    {
        System.out.println("This is task validate.");
    }

    private static void task_format(final Map<App_Arg_Names, String> app_args)
    {
        System.out.println("This is task format.");
    }
}
