package com.muldis.data_engine_reference;

public final class MUSE_Value
{
    MUSE_Machine machine;
    MDL_Any      value;

    MUSE_Value() {}

    MUSE_Value init(final MUSE_Machine machine)
    {
        this.machine = machine;
        return this;
    }

    MUSE_Value init(final MUSE_Machine machine, final MDL_Any value)
    {
        this.machine = machine;
        this.value   = value;
        return this;
    }

    public String toString()
    {
        return this.value.toString();
    }

    public void provides_Muldis_Service_Protocol_Value() {}

    public MUSE_Machine MUSE_Machine()
    {
        return this.machine;
    }
}
