package com.muldis.data_library;

public interface MDV_Homogeneous<Specific_T> extends MDV_Any
{
    public abstract MDV_Boolean so_empty();

    public default MDV_Boolean not_empty()
    {
        final MDV_Homogeneous<Specific_T> topic = this;
        return topic.so_empty().not();
    }

    public abstract MDV_Homogeneous<Specific_T> empty();
}
