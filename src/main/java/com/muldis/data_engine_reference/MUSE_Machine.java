package com.muldis.data_engine_reference;

import java.util.AbstractMap.SimpleImmutableEntry;

public final class MUSE_Machine
{
    public final MUSE_Factory MUSE_Factory;

    Memory   memory;
    Executor executor;

    MUSE_Machine(final MUSE_Factory factory)
    {
        this.MUSE_Factory = factory;
        this.memory       = new Memory();
        this.executor     = this.memory.Executor;
    }

    public void provides_Muldis_Service_Protocol_Machine() {}

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
        return new MUSE_Value(this, this.executor.evaluates(function.value, args.value));
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
        this.executor.performs(procedure.value, args.value);
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
        return new MUSE_Value(this, import__tree(value));
    }

    public MUSE_Value MUSE_import_qualified(final SimpleImmutableEntry<String, Object> value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Argument \"value\" must not be null.");
        }
        return new MUSE_Value(this, import__tree_qualified(value));
    }

    private MDL_Any import__tree(final Object value)
    {
        if (value != null)
        {
            if (value instanceof MUSE_Value)
            {
                return ((MUSE_Value)value).value;
            }
            if (value instanceof SimpleImmutableEntry)
            {
                return import__tree_qualified((SimpleImmutableEntry<String, Object>)value);
            }
        }
        return import__tree_unqualified(value);
    }

    private MDL_Any import__tree_qualified(final SimpleImmutableEntry<String, Object> value)
    {
        Object v = value.getValue();
        switch (value.getKey())
        {
            case "Boolean":
                if (v instanceof Boolean)
                {
                    return this.memory.MDL_Boolean((Boolean)v);
                }
                break;
            case "New_Variable":
                if (v instanceof MUSE_Value)
                {
                    return this.memory.new_MDL_Variable(((MUSE_Value)v).value);
                }
                break;
            case "New_External":
                return this.memory.new_MDL_External(v);
            default:
                throw new UnsupportedOperationException("Unhandled MUSE value type.");
        }
        throw new UnsupportedOperationException("Unhandled MUSE value type.");
    }

    private MDL_Any import__tree_unqualified(final Object value)
    {
        if (value == null)
        {
            return this.memory.MDL_Ignorance;
        }
        if (value instanceof Boolean)
        {
            return this.memory.MDL_Boolean((Boolean)value);
        }
        throw new UnsupportedOperationException("Unhandled MUSE value type.");
    }

    public Object MUSE_export(final MUSE_Value value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Argument \"value\" must not be null.");
        }
        MDL_Any v = value.value;
        switch (v.WKBT)
        {
            case MDL_Ignorance:
                return null;
            case MDL_False:
                return false;
            case MDL_True:
                return true;
            case MDL_Variable:
                return new SimpleImmutableEntry<String, Object>("New_Variable",
                    new MUSE_Value(this, v.MDL_Variable()));
            case MDL_External:
                return new SimpleImmutableEntry<String, Object>("New_External", v.MDL_External());
            default:
                throw new UnsupportedOperationException("Unhandled MUSE value type.");
        }
    }

    public SimpleImmutableEntry<String, Object> MUSE_export_qualified(final MUSE_Value value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Argument \"value\" must not be null.");
        }
        MDL_Any v = value.value;
        switch (v.WKBT)
        {
            case MDL_Ignorance:
                return new SimpleImmutableEntry<String, Object>("Ignorance", null);
            case MDL_False:
                return new SimpleImmutableEntry<String, Object>("Boolean", false);
            case MDL_True:
                return new SimpleImmutableEntry<String, Object>("Boolean", true);
            case MDL_Variable:
                return new SimpleImmutableEntry<String, Object>("New_Variable",
                    new MUSE_Value(this, v.MDL_Variable()));
            case MDL_External:
                return new SimpleImmutableEntry<String, Object>("New_External", v.MDL_External());
            default:
                throw new UnsupportedOperationException("Unhandled MUSE value type.");
        }
    }
}
