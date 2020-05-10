package com.muldis.data_engine_reference.test.client;

import java.lang.reflect.InvocationTargetException;

public final class MUSE_Factory
{
    Object server_factory;

    MUSE_Factory(final Object server_factory)
    {
        this.server_factory = server_factory;
    }

    public void provides_Muldis_Service_Protocol_Factory() {}

    public MUSE_Machine new_MUSE_Machine(final Object requested_model_version)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object server_machine = this.server_factory.getClass()
            .getMethod("new_MUSE_Machine", Object.class)
            .invoke(this.server_factory, requested_model_version);
        return server_machine == null ? null : new MUSE_Machine(this, server_machine);
    }
}
