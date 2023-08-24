package com.muldis.object_notation_processor_reference_util;

import java.io.PrintStream;

public final class Logger
{
    private final PrintStream failure_stream;
    private final PrintStream notice_stream;

    public Logger(final PrintStream failure_stream, final PrintStream notice_stream)
    {
        this.failure_stream = failure_stream;
        this.notice_stream = notice_stream;
    }

    public void failure(final String message)
    {
        if (this.failure_stream != null)
        {
            this.failure_stream.println(message);
        }
    }

    public void notice(final String message)
    {
        if (this.notice_stream != null)
        {
            this.notice_stream.println(message);
        }
    }
}
