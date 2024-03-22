package com.muldis.data_library;

public final class MDP_System
{
    private MDP_System()
    {
    }

    public static MDV_Boolean Any(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.true_();
    }

    public static MDV_Boolean None(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.false_();
    }

    public static MDV_Boolean same(final MDV_Any topic_0, final MDV_Any topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.same(topic_1);
    }

    public static MDV_Boolean not_same(final MDV_Any topic_0, final MDV_Any topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.not_same(topic_1);
    }

    public static MDV_Boolean Excuse(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Excuse);
    }

    public static MDV_Any coalesce(final MDV_Any topic_0, final MDV_Any topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.coalesce(topic_1);
    }

    public static MDV_Any anticoalesce(final MDV_Any topic_0, final MDV_Any topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.anticoalesce(topic_1);
    }

    public static MDV_Boolean Ignorance(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Ignorance);
    }

    public static MDV_Boolean Orderable(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Orderable);
    }

    public static MDV_Boolean in_order(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.in_order(topic_1);
    }

    public static MDV_Boolean before(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.before(topic_1);
    }

    public static MDV_Boolean after(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.after(topic_1);
    }

    public static MDV_Boolean before_or_same(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.before_or_same(topic_1);
    }

    public static MDV_Boolean after_or_same(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.after_or_same(topic_1);
    }

    public static MDV_Orderable<MDV_Any> min(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.min(topic_1);
    }

    public static MDV_Orderable<MDV_Any> max(
        final MDV_Orderable<MDV_Any> topic_0, final MDV_Orderable<MDV_Any> topic_1)
    {
        if (topic_0 == null || topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.max(topic_1);
    }

    public static MDV_Boolean Boolean(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Boolean);
    }

    public static MDV_Boolean false_()
    {
        return MDV_Boolean.false_();
    }

    public static MDV_Boolean true_()
    {
        return MDV_Boolean.true_();
    }

    public static MDV_Boolean not(final MDV_Boolean topic)
    {
        return topic.not();
    }

    public static MDV_Boolean and(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.and(topic_1);
    }

    public static MDV_Boolean nand(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.nand(topic_1);
    }

    public static MDV_Boolean or(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.or(topic_1);
    }

    public static MDV_Boolean nor(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.nor(topic_1);
    }

    public static MDV_Boolean xnor(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.xnor(topic_1);
    }

    public static MDV_Boolean xor(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.xor(topic_1);
    }

    public static MDV_Boolean imp(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.imp(topic_1);
    }

    public static MDV_Boolean nimp(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.nimp(topic_1);
    }

    public static MDV_Boolean if_(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.if_(topic_1);
    }

    public static MDV_Boolean nif(final MDV_Boolean topic_0, final MDV_Boolean topic_1)
    {
        return topic_0.nif(topic_1);
    }

    public static MDV_Boolean Numerical(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Numerical);
    }

    public static MDV_Boolean so_zero(final MDV_Numerical<MDV_Any> topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return topic.so_zero();
    }

    public static MDV_Boolean not_zero(final MDV_Numerical<MDV_Any> topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return topic.not_zero();
    }

    public static MDV_Numerical<MDV_Any> zero(final MDV_Numerical<MDV_Any> topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return topic.zero();
    }

    public static MDV_Boolean Integer(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Integer);
    }

    public static MDV_Integer greatest_common_divisor(
        final MDV_Integer topic_0, final MDV_Integer topic_1)
    {
        return topic_0.greatest_common_divisor(topic_1);
    }

    public static MDV_Integer least_common_multiple(
        final MDV_Integer topic_0, final MDV_Integer topic_1)
    {
        return topic_0.least_common_multiple(topic_1);
    }

    public static MDV_Boolean Rational(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Rational);
    }

    public static MDV_Boolean Homogeneous(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Homogeneous);
    }

    public static MDV_Boolean so_empty(final MDV_Homogeneous<MDV_Any> topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return topic.so_empty();
    }

    public static MDV_Boolean not_empty(final MDV_Homogeneous<MDV_Any> topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return topic.not_empty();
    }

    public static MDV_Homogeneous<MDV_Any> empty(final MDV_Homogeneous<MDV_Any> topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return topic.empty();
    }

    public static MDV_Boolean Positional(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Positional);
    }

    public static MDV_Boolean Text(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Text);
    }

    public static MDV_Boolean Name(final MDV_Any topic)
    {
        if (topic == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic instanceof MDV_Name);
    }
}
