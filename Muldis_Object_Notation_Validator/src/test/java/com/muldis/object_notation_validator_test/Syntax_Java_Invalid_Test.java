package com.muldis.object_notation_validator_test;

import com.muldis.object_notation_validator.Syntax_Java;

import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap.SimpleImmutableEntry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class Syntax_Java_Invalid_Test
{
    private static final Syntax_Java v = new Syntax_Java();

    public Syntax_Java_Invalid_Test()
    {
    }

    @Test
    public void not_SYS_Pair_PS()
    {
        assertFalse(v.is_MUON_artifact(new SimpleEntry<String, Object>("Ignorance", null)));
    }

    @Test
    public void not_Ignorance()
    {
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Ignorance", false)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Ignorance", 0)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Ignorance", 0f)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Ignorance", "")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Ignorance", "null")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Ignorance", "false")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Ignorance", "0")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Ignorance", "0.0")));
    }

    @Test
    public void not_Boolean()
    {
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", null)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", 0)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", 0f)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", "")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Boolean", "null")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Boolean", "false")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", "0")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", "0.0")));

        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", 1)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", 1f)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Boolean", "true")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", "1")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Boolean", "1.0")));
    }

    @Test
    public void not_Integer()
    {
        assertFalse(v.is_MUON_artifact((byte) 0));
        assertFalse(v.is_MUON_artifact((short) 0));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", (byte) 0)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", (short) 0)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", null)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", false)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", 0f)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", "")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", "null")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", "false")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", "0")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", "0f")));

        assertFalse(v.is_MUON_artifact((byte) 1));
        assertFalse(v.is_MUON_artifact((short) 1));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", (byte) 1)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", (short) 1)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", true)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", 1f)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Integer", "true")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", "1")));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>("Integer", "1f")));
    }

    @Test
    public void not_Fraction()
    {
        assertFalse(v.is_MUON_artifact(Float.NEGATIVE_INFINITY));
        assertFalse(v.is_MUON_artifact(Double.NEGATIVE_INFINITY));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Float.NEGATIVE_INFINITY)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Double.NEGATIVE_INFINITY)));

        assertFalse(v.is_MUON_artifact(Float.POSITIVE_INFINITY));
        assertFalse(v.is_MUON_artifact(Double.POSITIVE_INFINITY));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Float.POSITIVE_INFINITY)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Double.POSITIVE_INFINITY)));

        assertFalse(v.is_MUON_artifact(Float.NaN));
        assertFalse(v.is_MUON_artifact(Double.NaN));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Float.NaN)));
        assertFalse(v.is_MUON_artifact(new SimpleImmutableEntry<String, Object>(
            "Fraction", Double.NaN)));
    }
}
