package com.muldis.data_engine_reference;

final class Memory
{
    final MDL_Any MDL_Ignorance;

    final MDL_Any MDL_False;
    final MDL_Any MDL_True;

    Memory()
    {
        MDL_Ignorance = new MDL_Any(this, Well_Known_Base_Type.MDL_Ignorance, null);

        MDL_False = new MDL_Any(this, Well_Known_Base_Type.MDL_False, null);
        MDL_True  = new MDL_Any(this, Well_Known_Base_Type.MDL_True, null);
    }

    MDL_Any MDL_Boolean(final Boolean value)
    {
        return value ? this.MDL_True : this.MDL_False;
    }

    MDL_Any new_MDL_Variable(final MDL_Any initial_current_value)
    {
        return new MDL_Any(this, Well_Known_Base_Type.MDL_Variable, initial_current_value);
    }

    MDL_Any new_MDL_External(final Object value)
    {
        return new MDL_Any(this, Well_Known_Base_Type.MDL_External, value);
    }
}
