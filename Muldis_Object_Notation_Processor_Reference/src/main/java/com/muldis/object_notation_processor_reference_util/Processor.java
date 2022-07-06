package com.muldis.object_notation_processor_reference_util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public interface Processor
{
    byte[] process_blob_at_once(byte[] blob_in);

    void process_blob_stream(InputStream blob_stream_in, OutputStream blob_stream_out)
        throws IOException;

    String process_text_at_once(String text_in);

    void process_text_stream(InputStreamReader text_stream_in, OutputStreamWriter text_stream_out)
        throws IOException;
}
