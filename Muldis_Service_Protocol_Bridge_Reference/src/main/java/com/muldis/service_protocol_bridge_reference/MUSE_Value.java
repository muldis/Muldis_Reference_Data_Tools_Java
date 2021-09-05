package com.muldis.service_protocol_bridge_reference;

import java.lang.reflect.InvocationTargetException;

public final class MUSE_Value
{
    public final MUSE_Machine MUSE_Machine;

    @SuppressWarnings("checkstyle:VisibilityModifier")
    Object server_value;

    MUSE_Value(final MUSE_Machine machine, final Object server_value)
    {
        this.MUSE_Machine = machine;
        this.server_value = server_value;
    }

    public String toString()
    {
        return this.server_value.toString();
    }

    public void provides_Muldis_Service_Protocol_Value()
    {
    }

    public Object MUSE_export()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        return this.server_value.getClass()
            .getMethod("MUSE_export")
            .invoke(this.server_value);
    }
}
