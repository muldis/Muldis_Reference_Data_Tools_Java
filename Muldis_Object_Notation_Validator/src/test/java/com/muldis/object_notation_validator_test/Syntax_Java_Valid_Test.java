package com.muldis.object_notation_validator_test;

import com.muldis.object_notation_validator.Syntax_Java;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.BitSet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class Syntax_Java_Valid_Test
{
    private static final Syntax_Java v = new Syntax_Java();

    public Syntax_Java_Valid_Test()
    {
    }

    @Test
    public void so_SYS_Pair_PS()
    {
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Ignorance", null)));
    }

    @Test
    public void so_Ignorance()
    {
        assertTrue(v.is_MUON_artifact(null));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Ignorance", null)));
    }

    @Test
    public void so_Boolean()
    {
        assertTrue(v.is_MUON_artifact(false));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", false)));

        assertTrue(v.is_MUON_artifact(true));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", true)));
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    public void so_Integer()
    {
        assertTrue(v.is_MUON_artifact(0));
        assertTrue(v.is_MUON_artifact(0L));
        assertTrue(v.is_MUON_artifact(BigInteger.ZERO));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", 0)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", 0L)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", BigInteger.ZERO)));

        assertTrue(v.is_MUON_artifact(1));
        assertTrue(v.is_MUON_artifact(1L));
        assertTrue(v.is_MUON_artifact(BigInteger.ONE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", 1)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", 1L)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", BigInteger.ONE)));

        assertTrue(v.is_MUON_artifact(Integer.MIN_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", Integer.MIN_VALUE)));

        assertTrue(v.is_MUON_artifact(Integer.MAX_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", Integer.MAX_VALUE)));

        assertTrue(v.is_MUON_artifact(Long.MIN_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", Long.MIN_VALUE)));

        assertTrue(v.is_MUON_artifact(Long.MAX_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", Long.MAX_VALUE)));
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    public void so_Fraction()
    {
        assertTrue(v.is_MUON_artifact(0f));
        assertTrue(v.is_MUON_artifact(0d));
        assertTrue(v.is_MUON_artifact(BigDecimal.ZERO));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Fraction", 0f)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Fraction", 0d)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", BigDecimal.ZERO)));

        assertTrue(v.is_MUON_artifact(1f));
        assertTrue(v.is_MUON_artifact(1d));
        assertTrue(v.is_MUON_artifact(BigDecimal.ONE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Fraction", 1f)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Fraction", 1d)));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", BigDecimal.ONE)));

        assertTrue(v.is_MUON_artifact(Float.MIN_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Float.MIN_VALUE)));

        assertTrue(v.is_MUON_artifact(Float.MAX_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Float.MAX_VALUE)));

        assertTrue(v.is_MUON_artifact(Double.MIN_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Double.MIN_VALUE)));

        assertTrue(v.is_MUON_artifact(Double.MAX_VALUE));
        assertTrue(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Double.MAX_VALUE)));

        // TODO: Tuple D1..D4 possreps for Fraction.
    }

    @Test
    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public void so_Bits()
    {
        final BitSet x = new BitSet();
    }

    @Test
    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public void so_Blob()
    {
        final byte[] x = new byte[] {};

        final byte[] y = new byte[] {(byte) 0xA7, 0x05, (byte) 0xE4, 0x16};

        final byte[] z = new byte[] {0b00101110, (byte) 0b10001011};
    }

    @Test
    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public void so_Text()
    {
        final String s1 = "";

        final String s2 = "Ceres";

        final String s3 = "サンプル";

        final String s4 = "This isn't not escaped.\n";

        final String s5 = "\u263A\u0041";

        final String s8 = "First Name";

        final String s10 = "\u0000";

        /* From a graduate student (in finals week), the following haiku: */
        final String s11 = "study, write, study,\n"
            + "do review (each word) if time.\n"
            + "close book. sleep? what's that?\n";
    }

    @Test
    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public void so_Nesting()
    {
        final Object a = new SimpleImmutableEntry<String, Object>("Nesting", "person");

        final Object b = new SimpleImmutableEntry<String, Object>("Nesting",
            new String[] {"person", "birth_date"});

        final Object c = new SimpleImmutableEntry<String, Object>("Nesting",
            new String[] {"person", "birth_date", "year"});

        final Object d = new SimpleImmutableEntry<String, Object>("Nesting",
            new String[] {"the_db", "stats", "samples by order"});
    }

    @Test
    public void so_Duo()
    {
    }

    @Test
    public void so_Lot()
    {
    }

    @Test
    public void so_Kit()
    {
    }
}

/*
        final MUSE_Value sum = machine.MUSE_evaluate(
            machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
                "Attr_Name_List", new String[] {"foundation", "Integer_plus"})),
            machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
                "Tuple", new Object[] {27,39}))
        );
        final MUSE_Value that = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Tuple", new Object[] {27,39}));
        final MUSE_Value that_too = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Tuple", new Dictionary<String, Object>()
            {{"\u0014", 25}, {"aye", "zwei"}, {"some one", "other two"}}
        ));
        final MUSE_Value the_other = machine.MUSE_import("Fr ⊂ ac 💩 ti ÷ on");
        final MUSE_Value f0 = machine.MUSE_import(014.0M);
        final MUSE_Value f1 = machine.MUSE_import(2.3M);
        final MUSE_Value f2 = machine.MUSE_import(02340233.23402532000M);
        final MUSE_Value f3 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(13,5)));
        final MUSE_Value f4 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(27,6)));
        final MUSE_Value f5 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(35,-41)));
        final MUSE_Value f6 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(
                new BigInteger(-54235435432),new BigInteger(32543252))));
        final MUSE_Value f7 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(26,13)));
        final MUSE_Value f8 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(5,1)));
        final MUSE_Value f9 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(5,-1)));
*/
