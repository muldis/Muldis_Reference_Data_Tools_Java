package com.muldis.data_engine_reference;

import com.muldis.object_notation_validator.Syntax_Java;

import java.util.AbstractMap.SimpleImmutableEntry;

public final class MUSE_Machine
{
    private static final Syntax_Java MUON_HD_validator = new Syntax_Java();

    public final MUSE_Factory MUSE_Factory;

    final Memory memory;
    final Executor executor;

    MUSE_Machine(final MUSE_Factory factory)
    {
        this.MUSE_Factory = factory;
        this.memory       = new Memory();
        this.executor     = this.memory.Executor;
    }

    public void provides_Muldis_Service_Protocol_Machine()
    {
    }

    public Boolean provides_MUSE_version(final Object requested_MUSE_version)
    {
        return this.MUSE_Factory.provides_MUSE_version(requested_MUSE_version);
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
        return new MUSE_Value(this,
            this.executor.evaluates(function.memory_value, args.memory_value));
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
        this.executor.performs(procedure.memory_value, args.memory_value);
    }

    public MUSE_Value MUSE_current(final MUSE_Value variable)
    {
        if (variable == null)
        {
            throw new IllegalArgumentException("Argument \"variable\" must not be null.");
        }
        return new MUSE_Value(this, variable.memory_value.MDL_Variable());
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
        variable.memory_value.details = new_current.memory_value;
    }

    public MUSE_Value MUSE_import(final Object value)
    {
        if (!MUON_HD_validator.is_MUON_artifact(value))
        {
            throw new IllegalArgumentException(
                "Argument \"value\" must be a valid MUON Syntax_Java artifact.");
        }
        return new MUSE_Value(this, import__tree(value));
    }

    private MDL_Any import__tree(final Object value)
    {
        if (!(value instanceof SimpleImmutableEntry
            && ((SimpleImmutableEntry) value).getKey() instanceof String))
        {
            if (value == null)
            {
                return this.memory.MDL_Ignorance;
            }
            if (value instanceof Boolean)
            {
                return this.memory.MDL_Boolean((Boolean) value);
            }
            if (value instanceof MUSE_Value)
            {
                return ((MUSE_Value) value).memory_value;
            }
            throw new UnsupportedOperationException("Unhandled MUSE value type.");
        }
        final SimpleImmutableEntry<String, Object> art = (SimpleImmutableEntry) value;
        final String MUON_predicate = art.getKey();
        final Object MUON_subject = art.getValue();
        switch (MUON_predicate)
        {
            case "Ignorance":
                return this.memory.MDL_Ignorance;
            case "Boolean":
                return this.memory.MDL_Boolean((Boolean) MUON_subject);
            case "New_Variable":
                if (MUON_subject instanceof MUSE_Value)
                {
                    return this.memory.new_MDL_Variable(((MUSE_Value) MUON_subject).memory_value);
                }
                break;
            case "New_External":
                return this.memory.new_MDL_External(MUON_subject);
            default:
                throw new UnsupportedOperationException("Unhandled MUSE value type.");
        }
        throw new UnsupportedOperationException("Unhandled MUSE value type.");
    }
}
