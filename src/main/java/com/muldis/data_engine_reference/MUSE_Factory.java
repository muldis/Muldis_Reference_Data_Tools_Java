package com.muldis.data_engine_reference;

import java.util.Arrays;

public final class MUSE_Factory
{
    private static final String[] only_supported_model_version
        = new String[] {"Muldis_Data_Language", "https://muldis.com", "0.300.0"};

    public final MUSE_Entrance MUSE_Entrance;

    MUSE_Factory(final MUSE_Entrance entrance)
    {
        this.MUSE_Entrance = entrance;
    }

    public void provides_Muldis_Service_Protocol_Factory() {}

    public MUSE_Machine new_MUSE_Machine(final Object requested_model_version)
    {
        return (requested_model_version instanceof String[]
                && Arrays.equals((String[]) requested_model_version, only_supported_model_version))
            ? new MUSE_Machine(this) : null;
    }
}
