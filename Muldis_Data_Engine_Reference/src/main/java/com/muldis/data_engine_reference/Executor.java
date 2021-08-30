package com.muldis.data_engine_reference;

final class Executor
{
    final Memory Memory;

    Executor(final Memory memory)
    {
        this.Memory = memory;
    }

    MDL_Any evaluates(final MDL_Any function, final MDL_Any args)
    {
        Memory m = this.Memory;
        throw new UnsupportedOperationException("Unhandled MDL function.");
    }

    void performs(final MDL_Any procedure, final MDL_Any args)
    {
        Memory m = this.Memory;
        throw new UnsupportedOperationException("Unhandled MDL procedure.");
    }
}
