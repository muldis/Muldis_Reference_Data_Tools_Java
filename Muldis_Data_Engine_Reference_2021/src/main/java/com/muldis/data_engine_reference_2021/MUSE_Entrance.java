package com.muldis.data_engine_reference_2021;

import java.util.Arrays;

public final class MUSE_Entrance
{
    private static final String[] only_supported_MUSE_version
        = new String[] {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};

    public MUSE_Entrance()
    {
    }

    public void provides_Muldis_Service_Protocol_Entrance()
    {
    }

    public Boolean provides_MUSE_version(final Object requested_MUSE_version)
    {
        return requested_MUSE_version instanceof String[]
            && Arrays.equals((String[]) requested_MUSE_version, only_supported_MUSE_version);
    }

    public MUSE_Factory new_MUSE_Factory(final Object requested_MUSE_version)
    {
        return this.provides_MUSE_version(requested_MUSE_version) ? new MUSE_Factory() : null;
    }
}
