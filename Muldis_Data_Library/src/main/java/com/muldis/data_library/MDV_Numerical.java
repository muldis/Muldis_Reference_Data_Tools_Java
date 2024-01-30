package com.muldis.data_library;

public interface MDV_Numerical<Specific_T> extends MDV_Any
{
    public abstract MDV_Boolean so_zero();

    public default MDV_Boolean not_zero()
    {
        final MDV_Numerical<Specific_T> topic = this;
        return topic.so_zero().not();
    }

    public abstract MDV_Numerical<Specific_T> zero();
}
