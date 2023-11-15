package com.muldis.data_engine_reference_2021;

import java.util.Arrays;

public final class MUSE_Factory
{
    private static final String[] only_supported_MUSE_version
        = new String[] {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};
    private static final String[] only_supported_model_version
        = new String[] {"Muldis_Data_Language", "https://muldis.com", "0.300.0"};

    public MUSE_Factory()
    {
    }

    public void provides_Muldis_Service_Protocol_Factory()
    {
    }

    public Boolean provides_MUSE_version(final Object requested_MUSE_version)
    {
        return requested_MUSE_version instanceof String[]
            && Arrays.equals((String[]) requested_MUSE_version, only_supported_MUSE_version);
    }

    public MUSE_Machine new_MUSE_Machine(final Object requested_model_version)
    {
        return (requested_model_version instanceof String[]
                && Arrays.equals((String[]) requested_model_version, only_supported_model_version))
            ? new MUSE_Machine(this) : null;
    }
}
