package com.muldis.data_engine_reference.test.client;

import java.lang.reflect.InvocationTargetException;

public final class MUSE_Entrance
{
    private MUSE_Entrance() {}

    public static MUSE_Factory new_MUSE_Factory(final String MUSE_Entrance_class_name)
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException
    {
        // Try and load the entrance class, or die.
        // Note, generally the class name needs to be assembly-qualified for
        // GetType() to find it; eg "Company.Project.Class,Company.Project" works.
        Class<?> server_entrance_class = Class.forName(MUSE_Entrance_class_name);
        if (server_entrance_class == null)
        {
            return null;
        }

        // Die unless the entrance class explicitly declares it implements MUSE.
        if (server_entrance_class.getMethod("provides_Muldis_Service_Protocol_Entrance") == null)
        {
            return null;
        }

        // Instantiate object of a Muldis Service Protocol entrance class.
        Object server_entrance = server_entrance_class.newInstance();

        // Request a factory object implementing a specific version of the
        // MUSE or what the entrance considers the next best fit version;
        // this would die if it thinks it can't satisfy an acceptable version.
        // We will use this for all the main work.
        String[] requested_MUSE_Version = new String[]
            {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};
        Object server_factory = server_entrance_class.getMethod("new_MUSE_Factory")
            .invoke(server_entrance, new Object[] { requested_MUSE_Version });
        if (server_factory == null)
        {
            return null;
        }

        return new MUSE_Factory(server_factory);
    }
}
