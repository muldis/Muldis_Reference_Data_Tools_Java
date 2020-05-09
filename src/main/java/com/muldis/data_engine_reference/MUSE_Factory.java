package com.muldis.data_engine_reference;

public final class MUSE_Factory
{
    MUSE_Entrance entrance;

    MUSE_Factory init(MUSE_Entrance entrance)
    {
        this.entrance = entrance;
        return this;
    }

    public void provides_Muldis_Service_Protocol_Factory() {}

    public MUSE_Entrance MUSE_Entrance()
    {
        return this.entrance;
    }

    public MUSE_Machine new_MUSE_Machine(final Object requested_model_version)
    {
        // TODO: Check for and fail/return-null if: We don't support the requested specific model version.
        // We support the requested specific model version.
        return new MUSE_Machine().init(this);
    }
}
