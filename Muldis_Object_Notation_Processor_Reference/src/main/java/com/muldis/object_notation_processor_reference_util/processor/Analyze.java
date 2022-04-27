package com.muldis.object_notation_processor_reference_util.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Analyze implements Processor
{
    public Analyze()
    {
    }

    public void process(final InputStream stream_in, final OutputStream stream_out)
        throws IOException
    {
        // InputStream.read() returns one of 0..255 when there is another octet
        // and it returns -1 when there is none / the end of the stream was passed.
        int octet = stream_in.read();
        while (octet >= 0)
        {
            stream_out.write(octet);
            octet = stream_in.read();
        }
    }
}
