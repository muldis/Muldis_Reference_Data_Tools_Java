package com.muldis.data_library;

public interface MDV_Any
{
    public abstract MDV_Boolean same(final MDV_Any topic_1);

    public default MDV_Boolean not_same(final MDV_Any topic_1)
    {
        final MDV_Any topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return topic_0.same(topic_1).not();
    }

    public default MDV_Any coalesce(final MDV_Any topic_1)
    {
        final MDV_Any topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_0 instanceof MDV_Excuse) ? topic_1 : topic_0;
    }

    public default MDV_Any anticoalesce(final MDV_Any topic_1)
    {
        final MDV_Any topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_0 instanceof MDV_Excuse) ? topic_0 : topic_1;
    }
}
