package com.muldis.service_protocol_bridge_reference;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class MUSE_Factory
{
    private static final String[] only_supported_MUSE_version
        = new String[] {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};

    final Object server_factory;

    public MUSE_Factory(final String MUSE_server_entrance_class_name)
        throws ClassNotFoundException, InstantiationException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        // This will throw ClassNotFoundException if
        // the Entrance class doesn't exist in the project.
        final Class<?> server_entrance_class = Class.forName(MUSE_server_entrance_class_name);
        // This will throw InstantiationException if
        // an Entrance object can't be instantiated.
        final Object server_entrance = server_entrance_class.newInstance();
        // This will throw NoSuchMethodException if
        // the Entrance object doesn't declare it provides MUSE Entrance API.
        server_entrance_class.getMethod("provides_Muldis_Service_Protocol_Entrance");
        // This will throw NoSuchMethodException if
        // the Entrance object doesn't provide a MUSE API method it is supposed to.
        // This will throw IllegalAccessException if we can't invoke that method.
        // This will throw InvocationTargetException if we can't invoke that method.
        this.server_factory = server_entrance_class
            .getMethod("new_MUSE_Factory", Object.class)
            .invoke(server_entrance, (Object) only_supported_MUSE_version);
        // This will throw NoSuchMethodException if
        // the Factory object doesn't declare it provides MUSE Factory API.
        server_factory.getClass().getMethod("provides_Muldis_Service_Protocol_Factory");
    }

    public void provides_Muldis_Service_Protocol_Factory()
    {
    }

    public Boolean provides_MUSE_version(final Object requested_MUSE_version)
    {
        return requested_MUSE_version instanceof String[]
            && Arrays.equals((String[]) requested_MUSE_version, only_supported_MUSE_version);
    }

    public MUSE_Machine new_MUSE_Machine(final Object requested_model_version)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        // This will throw NoSuchMethodException if
        // the Factory object doesn't provide a MUSE API method it is supposed to.
        // This will throw IllegalAccessException if we can't invoke that method.
        // This will throw InvocationTargetException if we can't invoke that method.
        final Object server_machine = this.server_factory.getClass()
            .getMethod("new_MUSE_Machine", Object.class)
            .invoke(this.server_factory, requested_model_version);
        // This will throw NoSuchMethodException if
        // the Factory object doesn't declare it provides MUSE Machine API.
        server_machine.getClass().getMethod("provides_Muldis_Service_Protocol_Machine");
        return new MUSE_Machine(this, server_machine);
    }
}
