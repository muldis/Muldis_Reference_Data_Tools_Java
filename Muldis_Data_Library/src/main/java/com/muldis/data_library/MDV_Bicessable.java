package com.muldis.data_library;

public interface MDV_Bicessable<Specific_T>
    extends MDV_Orderable<Specific_T>, MDV_Successable<Specific_T>
{
    public abstract MDV_Bicessable<Specific_T> pred();
}
