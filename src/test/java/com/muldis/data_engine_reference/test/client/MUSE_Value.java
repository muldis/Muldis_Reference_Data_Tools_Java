package com.muldis.data_engine_reference.test.client;

public final class MUSE_Value
{
    public final MUSE_Machine MUSE_Machine;

    Object server_value;

    MUSE_Value(final MUSE_Machine machine)
    {
        this.MUSE_Machine = machine;
    }

    MUSE_Value(final MUSE_Machine machine, final Object server_value)
    {
        this.MUSE_Machine = machine;
        this.server_value = server_value;
    }

    public String toString()
    {
        return this.server_value.toString();
    }

    public void provides_Muldis_Service_Protocol_Value() {}
}
