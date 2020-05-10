package com.muldis.data_engine_reference;

import java.util.Arrays;

public final class MUSE_Factory
{
    MUSE_Entrance entrance;

    MUSE_Factory() {}

    MUSE_Factory init(final MUSE_Entrance entrance)
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
        String[] only_supported_model_version = new String[]
            {"Muldis_Data_Language", "https://muldis.com", "0.300.0"};
        if (!(requested_model_version instanceof String[])
            || !Arrays.equals((String[]) requested_model_version, only_supported_model_version))
        {
            // We don't support the requested specific model version.
            return null;
        }
        // We support the requested specific model version.
        return new MUSE_Machine().init(this);
    }
}
