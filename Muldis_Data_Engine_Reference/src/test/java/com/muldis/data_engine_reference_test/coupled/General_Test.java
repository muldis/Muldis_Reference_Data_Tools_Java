package com.muldis.data_engine_reference.test.coupled;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.muldis.data_engine_reference.MUSE_Entrance;
import com.muldis.data_engine_reference.MUSE_Factory;
import com.muldis.data_engine_reference.MUSE_Machine;
import com.muldis.data_engine_reference.MUSE_Value;
import org.junit.jupiter.api.Test;
//import java.util.AbstractMap.SimpleImmutableEntry;

public final class General_Test
{
    private static final String[] requested_MUSE_version
        = new String[] {"Muldis_Service_Protocol", "https://muldis.com", "0.300.0"};

    private static final String[] requested_model_version
        = new String[] {"Muldis_Data_Language", "https://muldis.com", "0.300.0"};

    @Test
    public void should_just_work()
    {
        assertTrue(true);

        MUSE_Entrance entrance = new MUSE_Entrance();

        MUSE_Factory factory = entrance.new_MUSE_Factory(requested_MUSE_version);

        MUSE_Machine machine = factory.new_MUSE_Machine(requested_model_version);

        MUSE_Value expected_MDL_Ignorance = machine.MUSE_import(null);
        MUSE_Value expected_MDL_False = machine.MUSE_import(false);
        MUSE_Value expected_MDL_True = machine.MUSE_import(true);

        assertTrue(true);

        /*
        MUSE_Value sum = machine.MUSE_evaluate(
            machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
                "Attr_Name_List", new String[] {"foundation", "Integer_plus"})),
            machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
                "Tuple", new Object[] {27,39}))
        );
        MUSE_Value that = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Tuple", new Object[] {27,39}));
        MUSE_Value that_too = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Tuple", new Dictionary<String, Object>()
            {{"\u0014", 25}, {"aye", "zwei"}, {"some one", "other two"}}
        ));
        MUSE_Value the_other = machine.MUSE_import("Fr ⊂ ac 💩 ti ÷ on");
        MUSE_Value f0 = machine.MUSE_import(014.0M);
        MUSE_Value f1 = machine.MUSE_import(2.3M);
        MUSE_Value f2 = machine.MUSE_import(02340233.23402532000M);
        MUSE_Value f3 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(13,5)));
        MUSE_Value f4 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(27,6)));
        MUSE_Value f5 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(35,-41)));
        MUSE_Value f6 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(
                new BigInteger(-54235435432),new BigInteger(32543252))));
        MUSE_Value f7 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(26,13)));
        MUSE_Value f8 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(5,1)));
        MUSE_Value f9 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(5,-1)));
        */
    }
}
