package com.muldis.object_notation_processor_reference_util.processor;

import com.muldis.object_notation_processor_reference_util.Processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public final class Duplicate implements Processor
{
    public Duplicate()
    {
    }

    public byte[] process_blob_at_once(final byte[] blob_in)
    {
        return blob_in;
    }

    public void process_blob_stream(
        final InputStream blob_stream_in, final OutputStream blob_stream_out)
        throws IOException
    {
        // Note that InputStream.read()
        // returns one of 0..255 when there is another octet
        // and it returns -1 when there is none / the end of stream was passed.
        int octet_as_int = blob_stream_in.read();
        while (octet_as_int >= 0)
        {
            blob_stream_out.write(octet_as_int);
            octet_as_int = blob_stream_in.read();
        }
    }

    public String process_text_at_once(final String text_in)
    {
        return text_in;
    }

    public void process_text_stream(
        final InputStreamReader text_stream_in, final OutputStreamWriter text_stream_out)
        throws IOException
    {
        // Note that InputStreamReader.read()
        // returns one of 0..0xFFFF when there is another Java char
        // and it returns -1 when there is none / the end of stream was passed.
        int char_as_int = text_stream_in.read();
        while (char_as_int >= 0)
        {
            text_stream_out.write(char_as_int);
            char_as_int = text_stream_in.read();
        }
    }
}
