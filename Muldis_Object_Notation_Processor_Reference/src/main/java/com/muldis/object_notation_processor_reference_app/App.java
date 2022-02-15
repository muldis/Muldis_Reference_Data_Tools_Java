package com.muldis.object_notation_processor_reference_app;

import java.util.HashMap;
import java.util.Map;

public final class App
{
    private App()
    {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(final String[] raw_app_args)
    {
        final Map<String, String> app_args = normalize_app_args(raw_app_args);
        if (app_args.isEmpty())
        {
            task_help();
            return;
        }
        switch (app_args.get("task"))
        {
            case "version" ->
            {
                task_version();
            }
            case "analyze" ->
            {
                task_analyze(app_args);
            }
            case "validate" ->
            {
                task_validate(app_args);
            }
            case "format" ->
            {
                task_format(app_args);
            }
            default ->
            {
                task_help();
            }
        }
    }

    private static Map<String, String> normalize_app_args(final String[] raw_app_args)
    {
        final Map<String, String> app_args = new HashMap<>();
        if (raw_app_args.length > 0)
        {
            app_args.put("task", raw_app_args[0]);
            for (int i = 1; i < raw_app_args.length; i++)
            {
                final String raw_app_arg = raw_app_args[i];
                if (raw_app_arg.startsWith("--") && raw_app_arg.contains("="))
                {
                    final int eq_pos = raw_app_arg.indexOf("=");
                    app_args.put(raw_app_arg.substring(2, eq_pos),
                        raw_app_arg.substring(eq_pos + 1));
                }
            }
        }
        return app_args;
    }

    private static void task_help()
    {
        final String APP_NAME = "sh muonp.sh";
        final String ARG_IN  = "--in=<input file or directory path>";
        final String ARG_OUT = "--out=<output file or directory path>";
        final String ARG_ENC = "--enc=<output character encoding name>";
        System.out.println(
            "Usage:\n"
                + "  " + APP_NAME + " help\n"
                + "  " + APP_NAME + " version\n"
                + "  " + APP_NAME + " analyze "   + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " validate "  + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " format "    + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " textify "   + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " untextify " + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " blobify "   + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
                + "  " + APP_NAME + " unblobify " + ARG_IN + " " + ARG_OUT + " " + ARG_ENC + "\n"
        );
    }

    private static void task_version()
    {
        System.out.println("com.muldis.object_notation_processor_reference_app.App version 0.1");
    }

    private static void task_analyze(final Map<String, String> app_args)
    {
        System.out.println("This is task analyze.");
    }

    private static void task_validate(final Map<String, String> app_args)
    {
        System.out.println("This is task validate.");
    }

    private static void task_format(final Map<String, String> app_args)
    {
        System.out.println("This is task format.");
    }
}
