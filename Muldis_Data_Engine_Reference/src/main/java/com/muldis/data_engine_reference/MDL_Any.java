package com.muldis.data_engine_reference;

final class MDL_Any
{
    final Memory memory;

    final Well_Known_Base_Type WKBT;

    /*
    Details of this MDL "value", in one of several possible
    specialized representation formats depending on the data type.
    Iff WKBT is MDL_Ignorance, this field holds a null.
    Iff WKBT is MDL_False, this field holds a null.
    Iff WKBT is MDL_True, this field holds a null.
    Iff WKBT is MDL_Variable, this field holds a MDL_Any.
        For a MDL_Variable, details holds its current_value.
        This can become a MDL_Variable_Struct if we want to store other things.
    Iff WKBT is MDL_External, this field holds an Object.
        The entity that is defined and managed externally to the Muldis
        Data Language environment, which the MDL_External value is an opaque
        and transient reference to.
    */
    Object details;

    MDL_Any(final Memory memory, final Well_Known_Base_Type WKBT, final Object details)
    {
        this.memory = memory;
        this.WKBT = WKBT;
        this.details = details;
    }

    public String toString()
    {
        return this.memory.MUON_Generator_Preview.MDL_Any_To_Preview_String(this);
    }

    MDL_Any MDL_Variable()
    {
        return (MDL_Any)details;
    }

    Object MDL_External()
    {
        return (Object)details;
    }
}
