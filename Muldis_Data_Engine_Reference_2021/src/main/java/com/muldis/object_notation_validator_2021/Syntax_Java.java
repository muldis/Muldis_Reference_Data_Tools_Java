package com.muldis.object_notation_validator_2021;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.BitSet;

public final class Syntax_Java
{
    public Syntax_Java()
    {
    }

    public Boolean is_MUON_artifact(final Object value)
    {
        return this.MUON_Any(value);
    }

    private Boolean MUON_Any(final Object value)
    {
        if (!this.SYS_Pair_PS(value))
        {
            return this.SYS_Ignorance(value)
                || this.SYS_Boolean(value)
                || this.SYS_Integer(value)
                || this.SYS_Rational(value)
                || this.SYS_Bits(value)
                || this.SYS_Text(value)
                || this.SYS_Array(value)
                || this.MUSE_Value(value);
        }
        final String MUON_predicate = this.SYS_Pair_PS__SYS_predicate(value);
        final Object MUON_subject = this.SYS_Pair_PS__SYS_subject(value);
        switch (MUON_predicate)
        {
            case "Ignorance":
                return this.SYS_Ignorance(MUON_subject);
            case "Boolean":
                return this.SYS_Boolean(MUON_subject);
            case "Integer":
                return this.SYS_Integer(MUON_subject);
            case "Rational":
                // TODO: Tuple D1..D4 possreps for Rational.
                return this.SYS_Rational(MUON_subject);
            case "Bits":
                return this.SYS_Bits(MUON_subject);
            case "Blob":
                return this.SYS_Blob(MUON_subject);
            case "Text":
                return this.SYS_Text(MUON_subject);
            case "Pair":
                return this.SYS_Pair(MUON_subject)
                    && this.MUON_Any(this.SYS_Pair__SYS_this(MUON_subject))
                    && this.MUON_Any(this.SYS_Pair__SYS_that(MUON_subject));
            case "Array":
                return this.SYS_Array(MUON_subject);
            case "New_Variable":
                return this.MUSE_Value(MUON_subject);
            case "New_External":
                return true;
            default:
                return false;
        }
    }

    private Boolean SYS_Ignorance(final Object value)
    {
        return value == null;
    }

    private Boolean SYS_Boolean(final Object value)
    {
        return value instanceof Boolean;
    }

    private Boolean SYS_Integer(final Object value)
    {
        return value instanceof Integer
            || value instanceof Long
            || value instanceof BigInteger;
    }

    private Boolean SYS_Rational(final Object value)
    {
        if (value instanceof Float)
        {
            final Float v = (Float) value;
            return !v.isInfinite() && !v.isNaN();
        }
        if (value instanceof Double)
        {
            final Double v = (Double) value;
            return !v.isInfinite() && !v.isNaN();
        }
        return value instanceof BigDecimal;
    }

    private Boolean SYS_Bits(final Object value)
    {
        return value instanceof BitSet;
    }

    private Boolean SYS_Blob(final Object value)
    {
        return value instanceof byte[];
    }

    private Boolean SYS_Text(final Object value)
    {
        if (!(value instanceof String))
        {
            return false;
        }
        final String s = (String) value;
        for (@SuppressWarnings("modifiedcontrolvariable") int i = 0; i < s.length(); i++)
        {
            if (Character.isSurrogate(s.charAt(i)))
            {
                if ((i + 1) < s.length() && Character.isSurrogatePair(s.charAt(i), s.charAt(i + 1)))
                {
                    i++;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }

    private Boolean SYS_Pair(final Object value)
    {
        return value instanceof SimpleImmutableEntry;
    }

    private Object SYS_Pair__SYS_this(final Object value)
    {
        return ((SimpleImmutableEntry<?, ?>) value).getKey();
    }

    private Object SYS_Pair__SYS_that(final Object value)
    {
        return ((SimpleImmutableEntry<?, ?>) value).getValue();
    }

    private Boolean SYS_Array(final Object value)
    {
        return false;
    }

    private Boolean SYS_Pair_PS(final Object value)
    {
        return this.SYS_Pair(value) && this.SYS_Text(this.SYS_Pair__SYS_this(value));
    }

    private String SYS_Pair_PS__SYS_predicate(final Object value)
    {
        return (String) this.SYS_Pair__SYS_this(value);
    }

    private Object SYS_Pair_PS__SYS_subject(final Object value)
    {
        return this.SYS_Pair__SYS_that(value);
    }

    private Boolean MUSE_Value(final Object value)
    {
        try
        {
            // This will throw NoSuchMethodException if
            // the object doesn't declare it provides MUSE Value API.
            value.getClass().getMethod("provides_MuldService_Protocol_Value");
        }
        catch (final NoSuchMethodException e)
        {
            return false;
        }
        return true;
    }
}
