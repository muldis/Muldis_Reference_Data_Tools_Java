package com.muldis.data_engine_reference;

public final class MUSE_Value
{
    public final MUSE_Machine MUSE_Machine;

    MDL_Any value;

    MUSE_Value(final MUSE_Machine machine)
    {
        this.MUSE_Machine = machine;
    }

    MUSE_Value(final MUSE_Machine machine, final MDL_Any value)
    {
        this.MUSE_Machine = machine;
        this.value        = value;
    }

    public String toString()
    {
        return this.value.toString();
    }

    public void provides_Muldis_Service_Protocol_Value() {}
}
