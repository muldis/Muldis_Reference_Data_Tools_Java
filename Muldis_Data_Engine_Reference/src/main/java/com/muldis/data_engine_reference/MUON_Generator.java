package com.muldis.data_engine_reference;

abstract class MUON_Generator
{
    String Any_selector(final MDL_Any value, final String indent)
    {
        switch (value.WKBT)
        {
            case MDL_Ignorance:
                return "\\Ignorance\\";
            case MDL_False:
                return "False";
            case MDL_True:
                return "True";
            case MDL_Variable:
                // We display something useful for debugging purposes, but no
                // (transient) MDL_Variable can actually be rendered as MUON.
                return "\\New_Variable\\\"Some MDL_Variable value is here.\"";
            case MDL_External:
                // We display something useful for debugging purposes, but no
                // (transient) MDL_External can actually be rendered as MUON.
                return "\\New_External\\\"Some MDL_External value is here.\"";
            default:
                return "\\TODO_FIX_UN_HANDLED_TYPE\\\"" + value.WKBT.toString() + "\"";
        }
    }
}
