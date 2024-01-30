package com.muldis.data_library;

public final class MDV_Boolean implements MDV_Bicessable<MDV_Boolean>
{
    private static final MDV_Boolean __false = new MDV_Boolean(false);
    private static final MDV_Boolean __true = new MDV_Boolean(true);

    // A value of the Java primitive type Boolean is immutable.
    // It should be safe to pass around without cloning.
    private final boolean __as_boolean;

    private MDV_Boolean(final boolean as_boolean)
    {
        this.__as_boolean = as_boolean;
    }

    @Override
    public int hashCode()
    {
        return this.__as_boolean ? 1 : 0;
    }

    @Override
    public String toString()
    {
        return Internal_Identity.Boolean(this);
    }

    @Override
    public boolean equals(final Object obj)
    {
        return (obj instanceof MDV_Boolean specific_obj)
            && this._same(specific_obj);
    }

    public static MDV_Boolean from(final boolean as_boolean)
    {
        return as_boolean ? MDV_Boolean.__true : MDV_Boolean.__false;
    }

    public boolean as_boolean()
    {
        return this.__as_boolean;
    }

    public MDV_Boolean same(final MDV_Any topic_1)
    {
        final MDV_Boolean topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_1 instanceof MDV_Boolean specific_topic_1)
            ? MDV_Boolean.from(topic_0._same(specific_topic_1))
            : MDV_Boolean.__false;
    }

    private boolean _same(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return topic_0.__as_boolean == topic_1.__as_boolean;
    }

    public MDV_Boolean in_order(final MDV_Orderable<MDV_Boolean> topic_1)
    {
        final MDV_Boolean topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic_0._in_order((MDV_Boolean) topic_1));
    }

    private boolean _in_order(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return !topic_0.__as_boolean || topic_1.__as_boolean;
    }

    public MDV_Any asset()
    {
        final MDV_Boolean topic = this;
        return topic;
    }

    public MDV_Successable<MDV_Boolean> succ()
    {
        final MDV_Boolean topic = this;
        if (topic.__as_boolean)
        {
            // Alternate conceptually is After_All_Others.
            throw new UnsupportedOperationException();
        }
        return MDV_Boolean.true_();
    }

    public MDV_Bicessable<MDV_Boolean> pred()
    {
        final MDV_Boolean topic = this;
        if (!topic.__as_boolean)
        {
            // Alternate conceptually is Before_All_Others.
            throw new UnsupportedOperationException();
        }
        return MDV_Boolean.false_();
    }

    public static MDV_Boolean false_()
    {
        return MDV_Boolean.__false;
    }

    public static MDV_Boolean true_()
    {
        return MDV_Boolean.__true;
    }

    public MDV_Boolean not()
    {
        final MDV_Boolean topic = this;
        return MDV_Boolean.from(!topic.__as_boolean);
    }

    public MDV_Boolean and(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return MDV_Boolean.from(topic_0.__as_boolean && topic_1.__as_boolean);
    }

    public MDV_Boolean nand(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return MDV_Boolean.from(!topic_0.__as_boolean || !topic_1.__as_boolean);
    }

    public MDV_Boolean or(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return MDV_Boolean.from(topic_0.__as_boolean || topic_1.__as_boolean);
    }

    public MDV_Boolean nor(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return MDV_Boolean.from(!topic_0.__as_boolean && !topic_1.__as_boolean);
    }

    public MDV_Boolean xnor(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return MDV_Boolean.from(topic_0.__as_boolean == topic_1.__as_boolean);
    }

    public MDV_Boolean xor(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return MDV_Boolean.from(topic_0.__as_boolean != topic_1.__as_boolean);
    }

    public MDV_Boolean imp(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return topic_0.__as_boolean ? topic_1 : MDV_Boolean.__true;
    }

    public MDV_Boolean nimp(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return topic_0.imp(topic_1).not();
    }

    public MDV_Boolean if_(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return topic_1.imp(topic_0);
    }

    public MDV_Boolean nif(final MDV_Boolean topic_1)
    {
        final MDV_Boolean topic_0 = this;
        return topic_1.nimp(topic_0);
    }
}
