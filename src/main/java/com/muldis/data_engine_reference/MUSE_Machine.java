package com.muldis.data_engine_reference;

import java.util.AbstractMap.SimpleImmutableEntry;

public final class MUSE_Machine
{
    public final MUSE_Factory MUSE_Factory;

    Memory memory;
    // Executor executor;

    MUSE_Machine(final MUSE_Factory factory)
    {
        this.MUSE_Factory = factory;
        this.memory       = new Memory();
        // this.executor = this.memory.Executor;
    }

    public void provides_Muldis_Service_Protocol_Machine() {}

    public MUSE_Value MUSE_evaluate(final MUSE_Value function)
    {
        if (function == null)
        {
            throw new IllegalArgumentException("Argument \"function\" must not be null.");
        }
        // TODO: Actually do the work.
        return new MUSE_Value(this, this.memory.MDL_Ignorance);
    }

    public MUSE_Value MUSE_evaluate(final MUSE_Value function, final MUSE_Value args)
    {
        if (function == null)
        {
            throw new IllegalArgumentException("Argument \"function\" must not be null.");
        }
        if (args == null)
        {
            throw new IllegalArgumentException("Argument \"args\" must not be null.");
        }
        // TODO: Actually do the work.
        return new MUSE_Value(this, this.memory.MDL_Ignorance);
    }

    public void MUSE_perform(final MUSE_Value procedure)
    {
        if (procedure == null)
        {
            throw new IllegalArgumentException("Argument \"procedure\" must not be null.");
        }
        // TODO: Actually do the work.
    }

    public void MUSE_perform(final MUSE_Value procedure, final MUSE_Value args)
    {
        if (procedure == null)
        {
            throw new IllegalArgumentException("Argument \"procedure\" must not be null.");
        }
        if (args == null)
        {
            throw new IllegalArgumentException("Argument \"args\" must not be null.");
        }
        // TODO: Actually do the work.
    }

    public MUSE_Value MUSE_current(final MUSE_Value variable)
    {
        if (variable == null)
        {
            throw new IllegalArgumentException("Argument \"variable\" must not be null.");
        }
        return new MUSE_Value(this, variable.value.MDL_Variable());
    }

    public void MUSE_assign(final MUSE_Value variable, final MUSE_Value new_current)
    {
        if (variable == null)
        {
            throw new IllegalArgumentException("Argument \"variable\" must not be null.");
        }
        if (new_current == null)
        {
            throw new IllegalArgumentException("Argument \"new_current\" must not be null.");
        }
        variable.value.details = new_current.value;
    }

    public MUSE_Value MUSE_import(final Object value)
    {
        // TODO: Actually import the argument.
        return new MUSE_Value(this, this.memory.MDL_Ignorance);
    }

    public MUSE_Value MUSE_import_qualified(final SimpleImmutableEntry<String,Object> value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Argument \"value\" must not be null.");
        }
        // TODO: Actually import the argument.
        return new MUSE_Value(this, this.memory.MDL_Ignorance);
    }

    public Object MUSE_export(final MUSE_Value value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Argument \"value\" must not be null.");
        }
        // TODO: Actually export the argument.
        return null;
    }

    public SimpleImmutableEntry<String,Object> MUSE_export_qualified(final MUSE_Value value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Argument \"value\" must not be null.");
        }
        // TODO: Actually export the argument.
        return null;
    }
}
