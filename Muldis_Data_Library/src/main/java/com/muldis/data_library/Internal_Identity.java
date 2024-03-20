package com.muldis.data_library;

import java.math.BigInteger;
import java.util.regex.Pattern;

final class Internal_Identity
{
    private Internal_Identity()
    {
    }

    static String Ignorance()
    {
        return "0iIGNORANCE";
    }

    static String Boolean(final MDV_Boolean topic)
    {
        return topic.as_boolean() ? "0bTRUE" : "0bFALSE";
    }

    static String Integer(final MDV_Integer topic)
    {
        return topic.as_BigInteger().toString();
    }

    static String Rational(final MDV_Rational topic)
    {
        // Iff the Rational value can be exactly expressed as a
        // non-terminating decimal (includes all Rational that can be
        // non-terminating binary/octal/hex), express in that format;
        // otherwise, express as a normalized/reduced/coprime numerator/denominator pair.
        if (Internal_Identity.is_terminating_decimal(topic))
        {
            final int dec_scale = Internal_Identity.decimal_denominator_scale(topic);
            if (dec_scale == 0)
            {
                // We have an integer expressed as a Rational.
                return topic.numerator_as_BigInteger().toString() + ".0";
            }
            final BigInteger dec_denominator = BigInteger.TEN.pow(dec_scale);
            final BigInteger dec_numerator = topic.numerator_as_BigInteger()
                .multiply(dec_denominator.divide(topic.denominator_as_BigInteger()));
            final String numerator_digits = dec_numerator.toString();
            final int left_size = numerator_digits.length() - dec_scale;
            return numerator_digits.substring(0, left_size)
                + "." + numerator_digits.substring(left_size);
        }
        return topic.numerator_as_BigInteger().toString()
            + "/" + topic.denominator_as_BigInteger().toString();
    }

    private static boolean is_terminating_decimal(final MDV_Rational topic)
    {
        boolean found_all_2_factors = false;
        boolean found_all_5_factors = false;
        BigInteger confirmed_quotient = topic.denominator_as_BigInteger();
        BigInteger attempt_quotient = BigInteger.ONE;
        BigInteger attempt_remainder = BigInteger.ZERO;
        while (!found_all_2_factors)
        {
            final BigInteger[] qr
                = confirmed_quotient.divideAndRemainder(BigInteger.TWO);
            attempt_quotient = qr[0];
            attempt_remainder = qr[1];
            if (attempt_remainder.compareTo(BigInteger.ZERO) > 0)
            {
                found_all_2_factors = true;
            }
            else
            {
                confirmed_quotient = attempt_quotient;
            }
        }
        while (!found_all_5_factors)
        {
            final BigInteger[] qr
                = confirmed_quotient.divideAndRemainder(BigInteger.valueOf(5));
            attempt_quotient = qr[0];
            attempt_remainder = qr[1];
            if (attempt_remainder.compareTo(BigInteger.ZERO) > 0)
            {
                found_all_5_factors = true;
            }
            else
            {
                confirmed_quotient = attempt_quotient;
            }
        }
        return confirmed_quotient.equals(BigInteger.ONE);
    }

    private static int decimal_denominator_scale(final MDV_Rational topic)
    {
        final BigInteger denominator = topic.denominator_as_BigInteger();
        for (int dec_scale = 0; dec_scale <= Integer.MAX_VALUE; dec_scale++)
        {
            // BigInteger.pow() can only take an int exponent anyway.
            if (BigInteger.TEN.pow(dec_scale).remainder(denominator)
                .equals(BigInteger.ZERO))
            {
                return dec_scale;
            }
        }
        // If somehow the denominator can be big enough that we'd actually get here.
        throw new ArithmeticException();
    }

    static String Text(final MDV_Text topic)
    {
        return Internal_Identity.quoted_char_seq(
            topic.code_point_members_as_String());
    }

    private static String quoted_char_seq(final String topic)
    {
        if (topic.isEmpty())
        {
            return "\"\"";
        }
        if (!Pattern.matches("[\u0000-\u001F\"\\\\`\u007F-\u009F]", topic))
        {
            return "\"" + topic + "\"";
        }
        final StringBuilder sb = new StringBuilder(topic.length());
        for (int i = 0; i < topic.length(); i++)
        {
            final int c = (int) topic.charAt(i);
            switch (c)
            {
                case 0x7:
                    sb.append("\\a");
                    break;
                case 0x8:
                    sb.append("\\b");
                    break;
                case 0x9:
                    sb.append("\\t");
                    break;
                case 0xA:
                    sb.append("\\n");
                    break;
                case 0xB:
                    sb.append("\\v");
                    break;
                case 0xC:
                    sb.append("\\f");
                    break;
                case 0xD:
                    sb.append("\\r");
                    break;
                case 0x1B:
                    sb.append("\\e");
                    break;
                case 0x22:
                    sb.append("\\q");
                    break;
                case 0x5C:
                    sb.append("\\k");
                    break;
                case 0x60:
                    sb.append("\\g");
                    break;
                default:
                    if (c <= 0x1F || (c >= 0x7F && c <= 0x9F))
                    {
                        sb.append("\\(" + ((char) c) + ")");
                    }
                    else
                    {
                        sb.append((char) c);
                    }
                    break;
            }
        }
        return "\"" + sb.toString() + "\"";
    }

    static String Name(final MDV_Name topic)
    {
        return ":" + Internal_Identity.Name_nonqualified(
            topic.code_point_members_as_String());
    }

    private static String Name_nonqualified(final String topic)
    {
        if (topic.length() == 1 && ((int) topic.charAt(0)) <= 0x1F)
        {
            // Format as a code-point-text.
            return "" + ((int) topic.charAt(0));
        }
        if (Pattern.matches("\\A[A-Za-z_][A-Za-z_0-9]*\\z", topic))
        {
            // Format as a nonquoted-alphanumeric-text.
            return topic;
        }
        // Else, format as a quoted text.
        return Internal_Identity.quoted_char_seq(topic);
    }
}
