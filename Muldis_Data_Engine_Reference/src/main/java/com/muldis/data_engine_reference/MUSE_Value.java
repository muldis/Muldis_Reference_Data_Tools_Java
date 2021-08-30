package com.muldis.data_engine_reference;

import java.util.AbstractMap.SimpleImmutableEntry;

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

    public Object MUSE_export()
    {
        switch (this.memory_value.WKBT)
        {
            case MDL_Ignorance:
                return null;
            case MDL_False:
                return false;
            case MDL_True:
                return true;
            case MDL_Variable:
                return new SimpleImmutableEntry<String, Object>("New_Variable",
                    new MUSE_Value(this.MUSE_Machine, this.memory_value.MDL_Variable()));
            case MDL_External:
                return new SimpleImmutableEntry<String, Object>("New_External",
                    this.memory_value.MDL_External());
            default:
                throw new UnsupportedOperationException("Unhandled MUSE value type.");
        }
    }
}
