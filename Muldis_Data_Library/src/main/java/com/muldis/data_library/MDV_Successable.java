package com.muldis.data_library;

public interface MDV_Successable<Specific_T> extends MDV_Any
{
    public abstract MDV_Any asset();

    public abstract MDV_Successable<Specific_T> succ();
}
