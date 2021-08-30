package com.muldis.data_engine_reference_test.noncoupled;

import com.muldis.data_engine_reference_test.client.MUSE_Entrance;
import com.muldis.data_engine_reference_test.client.MUSE_Factory;
import com.muldis.data_engine_reference_test.client.MUSE_Machine;
import com.muldis.data_engine_reference_test.client.MUSE_Value;

import java.lang.reflect.InvocationTargetException;
//import java.util.AbstractMap.SimpleImmutableEntry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class General_Test
{
    private static final String[] requested_model_version
        = new String[] {"Muldis_Data_Language", "https://muldis.com", "0.300.0"};

    @Test
    public void should_just_work()
        throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException
    {
        assertTrue(true);

        final String MUSE_server_entrance_class_name
            = System.getProperty("MUSE_server_entrance_class_name");
        if (MUSE_server_entrance_class_name == null)
        {
            throw new IllegalStateException("Usage requires -DMUSE_server_entrance_class_name"
                + "=com.muldis.data_engine_reference.MUSE_Entrance or similar.");
        }

        final MUSE_Factory factory
            = MUSE_Entrance.new_MUSE_Factory(MUSE_server_entrance_class_name);

        final MUSE_Machine machine = factory.new_MUSE_Machine(requested_model_version);

        final MUSE_Value expected_MDL_Ignorance = machine.MUSE_import(null);
        final MUSE_Value expected_MDL_False = machine.MUSE_import(false);
        final MUSE_Value expected_MDL_True = machine.MUSE_import(true);

        assertTrue(true);

        /*
        final MUSE_Value sum = machine.MUSE_evaluate(
            machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
                "Attr_Name_List", new String[] {"foundation", "Integer_plus"})),
            machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
                "Tuple", new Object[] {27,39}))
        );
        final MUSE_Value that = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Tuple", new Object[] {27,39}));
        final MUSE_Value that_too = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Tuple", new Dictionary<String, Object>()
            {{"\u0014", 25}, {"aye", "zwei"}, {"some one", "other two"}}
        ));
        final MUSE_Value the_other = machine.MUSE_import("Fr ⊂ ac 💩 ti ÷ on");
        final MUSE_Value f0 = machine.MUSE_import(014.0M);
        final MUSE_Value f1 = machine.MUSE_import(2.3M);
        final MUSE_Value f2 = machine.MUSE_import(02340233.23402532000M);
        final MUSE_Value f3 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(13,5)));
        final MUSE_Value f4 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(27,6)));
        final MUSE_Value f5 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(35,-41)));
        final MUSE_Value f6 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(
                new BigInteger(-54235435432),new BigInteger(32543252))));
        final MUSE_Value f7 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(26,13)));
        final MUSE_Value f8 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(5,1)));
        final MUSE_Value f9 = machine.MUSE_import(new SimpleImmutableEntry<String, Object>(
            "Fraction", new SimpleImmutableEntry<Object, Object>(5,-1)));
        */
    }
}
