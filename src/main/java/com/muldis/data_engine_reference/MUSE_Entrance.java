package com.muldis.data_engine_reference;

public final class MUSE_Entrance
{
    public void provides_Muldis_Service_Protocol_Entrance() {}

    public MUSE_Factory new_MUSE_Factory(final Object requested_MUSE_version)
    {
        // TODO: Check for and fail/return-null if: We don't support the requested specific MUSE version.
        // We support the requested specific MUSE version.
        return new MUSE_Factory().init(this);
    }
}
