package com.muldis.object_notation_processor_reference_util.processor;

import com.muldis.object_notation_processor_reference.Repeatable_Octet_Input_Stream;
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
        final Repeatable_Octet_Input_Stream in
            = new Repeatable_Octet_Input_Stream(stream_in);
        int octet_as_int = in.read_octet();
        while (octet_as_int >= 0)
        {
            stream_out.write(octet_as_int);
            octet_as_int = in.read_octet();
        }
    }
}
