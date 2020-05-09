package com.muldis.data_engine_reference;

public final class MUSE_Machine
{
    MUSE_Factory factory;
    Memory       memory;
    // Executor    executor;

    MUSE_Machine init(final MUSE_Factory factory)
    {
        this.factory  = factory;
        this.memory   = new Memory();
        // this.executor = this.memory.Executor;
        return this;
    }

    public void provides_Muldis_Service_Protocol_Machine() {}

    public MUSE_Factory MUSE_Factory()
    {
        return this.factory;
    }

    public MUSE_Value MUSE_import(final Object value)
    {
        // TODO: Actually import the argument.
        return new MUSE_Value().init(this, this.memory.MDL_Ignorance);
    }
}
