package com.muldis.data_engine_reference_2021;

final class Executor
{
    final Memory Memory;

    Executor(final Memory memory)
    {
        this.Memory = memory;
    }

    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    MDL_Any evaluates(final MDL_Any function, final MDL_Any args)
    {
        final Memory m = this.Memory;
        throw new UnsupportedOperationException("Unhandled MDL function.");
    }

    @SuppressWarnings("checkstyle:UnusedLocalVariable")
    void performs(final MDL_Any procedure, final MDL_Any args)
    {
        final Memory m = this.Memory;
        throw new UnsupportedOperationException("Unhandled MDL procedure.");
    }
}
