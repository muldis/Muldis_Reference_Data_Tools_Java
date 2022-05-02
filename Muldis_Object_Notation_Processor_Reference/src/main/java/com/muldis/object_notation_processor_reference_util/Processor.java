package com.muldis.object_notation_processor_reference_util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Processor
{
    void process(InputStream stream_in, OutputStream stream_out, String encoding)
        throws IOException;
}
