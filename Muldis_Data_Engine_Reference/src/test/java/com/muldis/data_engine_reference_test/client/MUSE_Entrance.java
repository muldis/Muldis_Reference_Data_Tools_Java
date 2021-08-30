package com.muldis.data_engine_reference_test.client;

import java.lang.reflect.InvocationTargetException;

public final class MUSE_Entrance
{
    private static final String[] requested_MUSE_version
        = new String[] {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};

    private MUSE_Entrance()
    {
    }

    public static MUSE_Factory new_MUSE_Factory(final String MUSE_Entrance_class_name)
        throws ClassNotFoundException, InstantiationException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        final Class<?> server_entrance_class = Class.forName(MUSE_Entrance_class_name);
        server_entrance_class.getMethod("provides_Muldis_Service_Protocol_Entrance");
        final Object server_entrance = server_entrance_class.newInstance();
        final Object server_factory = server_entrance_class
            .getMethod("new_MUSE_Factory", Object.class)
            .invoke(server_entrance, (Object) requested_MUSE_version);
        return server_factory == null ? null : new MUSE_Factory(server_factory);
    }
}
