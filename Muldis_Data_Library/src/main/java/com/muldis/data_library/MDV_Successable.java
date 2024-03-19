package com.muldis.data_library;

public interface MDV_Successable<Specific_T> extends MDV_Any
{
    public abstract MDV_Any asset();

    public default MDV_Successable<Specific_T> succ()
    {
        final MDV_Successable<Specific_T> topic = this;
        return topic.nth_succ(MDV_Integer.positive_one());
    }

    public abstract MDV_Successable<Specific_T> nth_succ(MDV_Integer topic_1);
}
