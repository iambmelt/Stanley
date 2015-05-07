package com.brianjmelton.stanley;

public class TestIntAccessor extends AbstractTest {

    public void testSetInt() throws Exception {
        final int expected = 64;
        proxy.setInt(64);
        assertEquals(expected, sharedPreferences.getInt("Int", 0));
    }

    public void testSetIntWrapper() throws Exception {
        final Integer expected = 25;
        proxy.setIntWrapper(expected);
        assertEquals((int) expected, sharedPreferences.getInt("IntWrapper", 0));
    }

    public void testSetIntNamedKey() throws Exception {
        final int expected = 11;
        proxy.setIntNamedKey(expected);
        assertEquals(expected, sharedPreferences.getInt("key_int", 0));
    }

    public void testSetIntWrapperNamedKey() throws Exception {
        final Integer expected = 10;
        proxy.setIntWrapperNamedKey(expected);
        assertEquals((int) expected, sharedPreferences.getInt("key_int_wrapper", 0));
    }

    public void testGetInt() throws Exception {
        final int expected = 15;
        sharedPreferences.edit().putInt("Int", expected).commit();
        assertEquals(expected, proxy.getInt());
    }

    public void testGetIntWrapper() throws Exception {
        final Integer expected = 2;
        sharedPreferences.edit().putInt("IntWrapper", expected).commit();
        assertEquals(expected, proxy.getIntWrapper());
    }

    public void testGetIntNamedKey() throws Exception {
        final int expected = 3;
        sharedPreferences.edit().putInt("key_int", 3).commit();
        assertEquals(expected, proxy.getIntNamedKey());
    }

    public void testGetIntWrapperNamedKey() throws Exception {
        final Integer expected = 4;
        sharedPreferences.edit().putInt("key_int_wrapper", expected).commit();
        assertEquals(expected, proxy.getIntWrapperNamedKey());
    }

    public void testGetIntProvidedDefault() throws Exception {
        assertEquals(5, proxy.getIntProvidedDefault());
    }

    public void testGetIntWrapperProvidedDefault() throws Exception {
        assertEquals(Integer.valueOf(6), proxy.getIntWrapperProvidedDefault());
    }

    public void testGetIntNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getInt("key_int_default", 5),
                proxy.getIntNamedKeyProvidedDefault());
    }

    public void testGetIntWrapperNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getInt("key_int_wrapper_default", Integer.valueOf(7)),
                (int) proxy.getIntWrapperNamedKeyProvidedDefault());
    }

}
