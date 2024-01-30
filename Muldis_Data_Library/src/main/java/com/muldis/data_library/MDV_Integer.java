package com.muldis.data_library;

import java.math.BigInteger;

public final class MDV_Integer
    implements MDV_Bicessable<MDV_Integer>, MDV_Numerical<MDV_Integer>
{
    private static final MDV_Integer __negative_one
        = new MDV_Integer(BigInteger.valueOf(-1));
    private static final MDV_Integer __zero
        = new MDV_Integer(BigInteger.ZERO);
    private static final MDV_Integer __positive_one
        = new MDV_Integer(BigInteger.ONE);

    // A value of the Java class BigInteger is immutable.
    // It should be safe to pass around without cloning.
    private final BigInteger __as_BigInteger;

    private MDV_Integer(final BigInteger as_BigInteger)
    {
        this.__as_BigInteger = as_BigInteger;
    }

    @Override
    public int hashCode()
    {
        return this.__as_BigInteger.hashCode();
    }

    @Override
    public String toString()
    {
        return Internal_Identity.Integer(this);
    }

    @Override
    public boolean equals(final Object obj)
    {
        return (obj instanceof MDV_Integer specific_obj)
            && this._same(specific_obj);
    }

    public static MDV_Integer from(final BigInteger as_BigInteger)
    {
        return as_BigInteger.equals(BigInteger.ZERO) ? MDV_Integer.__zero
            : as_BigInteger.equals(BigInteger.ONE) ? MDV_Integer.__positive_one
            : as_BigInteger.equals(BigInteger.valueOf(-1)) ? MDV_Integer.__negative_one
            : new MDV_Integer(as_BigInteger);
    }

    public static MDV_Integer from(final long as_long)
    {
        return MDV_Integer.from(BigInteger.valueOf(as_long));
    }

    public static MDV_Integer from(final int as_int)
    {
        return MDV_Integer.from(BigInteger.valueOf(as_int));
    }

    public static MDV_Integer negative_one()
    {
        return MDV_Integer.__negative_one;
    }

    public static MDV_Integer zero_()
    {
        return MDV_Integer.__zero;
    }

    public static MDV_Integer positive_one()
    {
        return MDV_Integer.__positive_one;
    }

    public BigInteger as_BigInteger()
    {
        return this.__as_BigInteger;
    }

    public long as_long()
    {
        // This will throw a Java ArithmeticException if the value doesn't fit.
        return this.__as_BigInteger.longValueExact();
    }

    public int as_int()
    {
        // This will throw a Java ArithmeticException if the value doesn't fit.
        return this.__as_BigInteger.intValueExact();
    }

    public MDV_Boolean same(final MDV_Any topic_1)
    {
        final MDV_Integer topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_1 instanceof MDV_Integer specific_topic_1)
            ? MDV_Boolean.from(topic_0._same(specific_topic_1))
            : MDV_Boolean.false_();
    }

    private boolean _same(final MDV_Integer topic_1)
    {
        final MDV_Integer topic_0 = this;
        return topic_0.__as_BigInteger.equals(topic_1.__as_BigInteger);
    }

    public MDV_Boolean in_order(final MDV_Orderable<MDV_Integer> topic_1)
    {
        final MDV_Integer topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic_0._in_order((MDV_Integer) topic_1));
    }

    private boolean _in_order(final MDV_Integer topic_1)
    {
        final MDV_Integer topic_0 = this;
        return topic_0.__as_BigInteger.compareTo(topic_1.__as_BigInteger) <= 0;
    }

    public MDV_Any asset()
    {
        final MDV_Integer topic = this;
        return topic;
    }

    public MDV_Successable<MDV_Integer> succ()
    {
        final MDV_Integer topic = this;
        return MDV_Integer.from(topic.__as_BigInteger.add(BigInteger.ONE));
    }

    public MDV_Bicessable<MDV_Integer> pred()
    {
        final MDV_Integer topic = this;
        return MDV_Integer.from(topic.__as_BigInteger.subtract(BigInteger.ONE));
    }

    public MDV_Boolean so_zero()
    {
        final MDV_Integer topic = this;
        return MDV_Boolean.from(topic.__as_BigInteger.equals(BigInteger.ZERO));
    }

    public MDV_Numerical<MDV_Integer> zero()
    {
        return MDV_Integer.__zero;
    }

    public MDV_Integer greatest_common_divisor(final MDV_Integer topic_1)
    {
        final MDV_Integer topic_0 = this;
        return MDV_Integer.from(MDV_Integer._greatest_common_divisor(
            topic_0.__as_BigInteger, topic_1.__as_BigInteger));
    }

    static BigInteger _greatest_common_divisor(
        final BigInteger topic_0, final BigInteger topic_1)
    {
        // Note that greatest common divisor always has a non-negative result.
        return topic_0.gcd(topic_1);
    }

    public MDV_Integer least_common_multiple(final MDV_Integer topic_1)
    {
        final MDV_Integer topic_0 = this;
        return MDV_Integer.from(MDV_Integer._least_common_multiple(
            topic_0.__as_BigInteger, topic_1.__as_BigInteger));
    }

    static BigInteger _least_common_multiple(
        final BigInteger topic_0, final BigInteger topic_1)
    {
        // Note that least common multiple always has a non-negative result.
        if (topic_0.equals(BigInteger.ZERO) || topic_1.equals(BigInteger.ZERO))
        {
            return topic_0;
        }
        return topic_0.abs().multiply(
                topic_1.abs().divide(
                    topic_0.gcd(topic_1)
                )
            );
    }
}
