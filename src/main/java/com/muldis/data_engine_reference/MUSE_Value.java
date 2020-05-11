package com.muldis.data_engine_reference;

public final class MUSE_Value
{
    public final MUSE_Machine MUSE_Machine;

    MDL_Any memory_value;

    MUSE_Value(final MUSE_Machine machine, final MDL_Any memory_value)
    {
        this.MUSE_Machine = machine;
        this.memory_value = memory_value;
    }

    public String toString()
    {
        return this.memory_value.toString();
    }

    public void provides_Muldis_Service_Protocol_Value() {}
}
