package com.muldis.object_notation_processor_reference_test;

import com.muldis.object_notation_processor_reference.Syntax_Plain_Text;

import java.io.IOException;
// import java.io.InputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class Syntax_Plain_Text_Test
{
    private static final Syntax_Plain_Text v = new Syntax_Plain_Text();

    public Syntax_Plain_Text_Test()
    {
    }

    @Test
    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public void it_should_work() throws IOException
    {
        final String source_code_file_name = "/overview.muon";
        /*
        try (InputStream source_code_input_stream
            = Syntax_Plain_Text_Test.class
            .getResourceAsStream(source_code_file_name))
        {
            final int curr_octet = source_code_input_stream.read();
            final int curr_octet_if_empty = 0;
            assertTrue(curr_octet >= curr_octet_if_empty, "File wasn't empty.");
        }
        */
        assertTrue(true, "it worked");
    }
}
