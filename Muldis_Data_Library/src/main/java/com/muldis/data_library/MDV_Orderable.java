package com.muldis.data_library;

public interface MDV_Orderable<Specific_T> extends MDV_Any
{
    public abstract MDV_Boolean in_order(MDV_Orderable<Specific_T> topic_1);

    public default MDV_Boolean before(final MDV_Orderable<Specific_T> topic_1)
    {
        final MDV_Orderable<Specific_T> topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_1.in_order(topic_0).not();
    }

    public default MDV_Boolean after(final MDV_Orderable<Specific_T> topic_1)
    {
        final MDV_Orderable<Specific_T> topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.in_order(topic_1).not();
    }

    public default MDV_Boolean before_or_same(final MDV_Orderable<Specific_T> topic_1)
    {
        final MDV_Orderable<Specific_T> topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.in_order(topic_1);
    }

    public default MDV_Boolean after_or_same(final MDV_Orderable<Specific_T> topic_1)
    {
        final MDV_Orderable<Specific_T> topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_1.in_order(topic_0);
    }

    public default MDV_Orderable<Specific_T> min(final MDV_Orderable<Specific_T> topic_1)
    {
        final MDV_Orderable<Specific_T> topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.in_order(topic_1).as_boolean() ? topic_0 : topic_1;
    }

    public default MDV_Orderable<Specific_T> max(final MDV_Orderable<Specific_T> topic_1)
    {
        final MDV_Orderable<Specific_T> topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.in_order(topic_1).as_boolean() ? topic_1 : topic_0;
    }
}
