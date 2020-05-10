package com.muldis.data_engine_reference;

import java.util.Arrays;

public final class MUSE_Entrance
{
    public MUSE_Entrance() {}

    public void provides_Muldis_Service_Protocol_Entrance() {}

    public MUSE_Factory new_MUSE_Factory(final Object requested_MUSE_version)
    {
        String[] only_supported_MUSE_version = new String[]
            {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};
        if (!(requested_MUSE_version instanceof String[])
            || !Arrays.equals((String[]) requested_MUSE_version, only_supported_MUSE_version))
        {
            // We don't support the requested specific MUSE version.
            return null;
        }
        // We support the requested specific MUSE version.
        return new MUSE_Factory(this);
    }
}
