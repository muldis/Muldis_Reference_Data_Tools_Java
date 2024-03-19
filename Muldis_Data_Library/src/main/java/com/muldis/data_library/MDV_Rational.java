package com.muldis.data_library;

import java.math.BigInteger;

public final class MDV_Rational
    implements MDV_Orderable<MDV_Rational>, MDV_Numerical<MDV_Rational>
{
    private static final MDV_Rational __negative_one
        = new MDV_Rational(BigInteger.valueOf(-1), BigInteger.ONE);
    private static final MDV_Rational __zero
        = new MDV_Rational(BigInteger.ZERO, BigInteger.ONE);
    private static final MDV_Rational __positive_one
        = new MDV_Rational(BigInteger.ONE, BigInteger.ONE);

    // A value of the Java class BigInteger is immutable.
    // It should be safe to pass around without cloning.
    // The numerator/denominator pair is normalized/reduced/coprime,
    // and the denominator is positive.
    private final BigInteger __numerator;
    private final BigInteger __denominator;

    private MDV_Rational(final BigInteger numerator, final BigInteger denominator)
    {
        this.__numerator = numerator;
        this.__denominator = denominator;
    }

    @Override
    public int hashCode()
    {
        return this.__numerator.hashCode() ^ this.__denominator.hashCode();
    }

    @Override
    public String toString()
    {
        return Internal_Identity.Rational(this);
    }

    @Override
    public boolean equals(final Object obj)
    {
        return (obj instanceof MDV_Rational specific_obj)
            && this._same(specific_obj);
    }

    public static MDV_Rational from(
        final MDV_Integer numerator, final MDV_Integer denominator)
    {
        return MDV_Rational.from(
            numerator.as_BigInteger(), denominator.as_BigInteger());
    }

    public static MDV_Rational from(
        final BigInteger numerator, final BigInteger denominator)
    {
        if (denominator.equals(BigInteger.ZERO))
        {
            throw new ArithmeticException();
        }
        BigInteger result_n = numerator;
        BigInteger result_d = denominator;
        // Ensure denominator is positive.
        if (result_d.compareTo(BigInteger.ZERO) < 0)
        {
            result_n = result_n.negate();
            result_d = result_d.negate();
        }
        final BigInteger gcd = MDV_Integer._greatest_common_divisor(
            result_n, result_d);
        // Ensure numerator and denominator normalized/reduced/coprime.
        if (gcd.compareTo(BigInteger.ONE) > 0)
        {
            result_n = result_n.divide(gcd);
            result_d = result_d.divide(gcd);
        }
        return !result_d.equals(BigInteger.ONE) ? new MDV_Rational(result_n, result_d)
            : result_n.equals(BigInteger.ZERO) ? MDV_Rational.__zero
            : result_n.equals(BigInteger.ONE) ? MDV_Rational.__positive_one
            : result_n.equals(BigInteger.valueOf(-1)) ? MDV_Rational.__negative_one
            : new MDV_Rational(result_n, result_d);
    }

    public static MDV_Rational from(final long numerator, final long denominator)
    {
        return MDV_Rational.from(
            BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public static MDV_Rational from(final int numerator, final int denominator)
    {
        return MDV_Rational.from(
            BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public static MDV_Rational negative_one()
    {
        return MDV_Rational.__negative_one;
    }

    public static MDV_Rational zero_()
    {
        return MDV_Rational.__zero;
    }

    public static MDV_Rational positive_one()
    {
        return MDV_Rational.__positive_one;
    }

    public MDV_Integer numerator()
    {
        return MDV_Integer.from(this.__numerator);
    }

    public BigInteger numerator_as_BigInteger()
    {
        return this.__numerator;
    }

    public long numerator_as_long()
    {
        // This will throw a Java ArithmeticException if the value doesn't fit.
        return this.__numerator.longValueExact();
    }

    public int numerator_as_int()
    {
        // This will throw a Java ArithmeticException if the value doesn't fit.
        return this.__numerator.intValueExact();
    }

    public MDV_Integer denominator()
    {
        return MDV_Integer.from(this.__denominator);
    }

    public BigInteger denominator_as_BigInteger()
    {
        return this.__denominator;
    }

    public long denominator_as_long()
    {
        // This will throw a Java ArithmeticException if the value doesn't fit.
        return this.__denominator.longValueExact();
    }

    public int denominator_as_int()
    {
        // This will throw a Java ArithmeticException if the value doesn't fit.
        return this.__denominator.intValueExact();
    }

    public MDV_Boolean same(final MDV_Any topic_1)
    {
        final MDV_Rational topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_1 instanceof MDV_Rational specific_topic_1)
            ? MDV_Boolean.from(topic_0._same(specific_topic_1))
            : MDV_Boolean.false_();
    }

    private boolean _same(final MDV_Rational topic_1)
    {
        final MDV_Rational topic_0 = this;
        return (topic_0.__denominator.equals(topic_1.__denominator)
            && topic_0.__numerator.equals(topic_1.__numerator));
    }

    public MDV_Boolean in_order(final MDV_Orderable<MDV_Rational> topic_1)
    {
        final MDV_Rational topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic_0._in_order((MDV_Rational) topic_1));
    }

    private boolean _in_order(final MDV_Rational topic_1)
    {
        final MDV_Rational topic_0 = this;
        if (topic_0.__denominator.equals(topic_1.__denominator))
        {
            return (topic_0.__numerator.compareTo(topic_1.__numerator) <= 0);
        }
        // We need to compare the arguments in terms of their equivalent
        // fractions where the denominators are equal.
        final BigInteger common_d = MDV_Integer._least_common_multiple(
            topic_0.__denominator, topic_1.__denominator);
        return ((topic_0.__numerator.multiply(common_d.divide(topic_0.__denominator)))
            .compareTo(topic_1.__numerator.multiply(common_d.divide(topic_1.__denominator))) <= 0);
    }

    public MDV_Boolean so_zero()
    {
        final MDV_Rational topic = this;
        return MDV_Boolean.from(topic.__numerator.equals(BigInteger.ZERO));
    }

    public MDV_Numerical<MDV_Rational> zero()
    {
        return MDV_Rational.__zero;
    }
}
