package com.muldis.object_notation_processor_reference_util.processor;

import com.muldis.object_notation_processor_reference_util.Processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Duplicate implements Processor
{
    public Duplicate()
    {
    }

    public void process(final InputStream stream_in, final OutputStream stream_out)
        throws IOException
    {
        // Note that InputStream.read()
        // returns one of 0..255 when there is another octet
        // and it returns -1 when there is none / the end of stream was passed.
        int octet_as_int = stream_in.read();
        while (octet_as_int >= 0)
        {
            stream_out.write(octet_as_int);
            octet_as_int = stream_in.read();
        }
    }
}
