package com.muldis.data_library;

public final class MDV_Ignorance implements MDV_Excuse
{
    private static final MDV_Ignorance __only = new MDV_Ignorance();

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return Internal_Identity.Ignorance();
    }

    @Override
    public boolean equals(final Object obj)
    {
        return obj instanceof MDV_Ignorance;
    }

    public static MDV_Ignorance only()
    {
        return MDV_Ignorance.__only;
    }

    public MDV_Boolean same(final MDV_Any topic_1)
    {
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic_1 instanceof MDV_Ignorance);
    }
}
