package com.muldis.data_library;

public interface MDV_Bicessable<Specific_T>
    extends MDV_Orderable<Specific_T>, MDV_Successable<Specific_T>
{
    public default MDV_Bicessable<Specific_T> pred()
    {
        final MDV_Bicessable<Specific_T> topic = this;
        return topic.nth_pred(MDV_Integer.positive_one());
    }

    public abstract MDV_Bicessable<Specific_T> nth_pred(MDV_Integer topic_1);
}
