package com.brianjmelton.stanley;

public class TestBooleanAccessor extends AbstractTest {

    public void testSetBoolean() throws Exception {
        final boolean expected = true;
        proxy.setBoolean(expected);
        assertTrue(sharedPreferences.getBoolean("Boolean", false));
    }

    public void testSetBooleanWrapper() throws Exception {
        final Boolean expected = Boolean.TRUE;
        proxy.setBooleanWrapper(expected);
        assertTrue(sharedPreferences.getBoolean("BooleanWrapper", false));
    }

    public void testSetBooleanNamedKey() throws Exception {
        final boolean expected = true;
        proxy.setBooleanNamedKey(expected);
        assertTrue(sharedPreferences.getBoolean("key_boolean", false));
    }

    public void testSetBooleanWrapperNamedKey() throws Exception {
        final Boolean expected = Boolean.TRUE;
        proxy.setBooleanWrapperNamedKey(expected);
        assertTrue(sharedPreferences.getBoolean("key_boolean_wrapper", false));
    }

    public void testGetBoolean() throws Exception {
        final boolean expected = true;
        sharedPreferences.edit().putBoolean("Boolean", expected).commit();
        assertTrue(proxy.getBoolean());
    }

    public void testGetBooleanWrapper() throws Exception {
        final Boolean expected = Boolean.TRUE;
        sharedPreferences.edit().putBoolean("BooleanWrapper", expected).commit();
        assertTrue(proxy.getBooleanWrapper());
    }

    public void testGetBooleanNamedKey() throws Exception {
        final boolean expected = true;
        sharedPreferences.edit().putBoolean("key_boolean", expected).commit();
        assertTrue(proxy.getBooleanNamedKey());
    }

    public void testGetBooleanWrapperNamedKey() throws Exception {
        final Boolean expected = Boolean.TRUE;
        sharedPreferences.edit().putBoolean("key_boolean_wrapper", expected).commit();
        assertTrue(proxy.getBooleanWrapperNamedKey());
    }

    public void testGetBooleanProvidedDefault() throws Exception {
        assertTrue(proxy.getBooleanProvidedDefault());
    }

    public void testGetBooleanWrapperProvidedDefault() throws Exception {
        assertTrue(proxy.getBooleanWrapperProvidedDefault());
    }

    public void testGetBooleanNamedKeyProvidedDefault() throws Exception {
        assertTrue(proxy.getBooleanNamedKeyProvidedDefault());
    }

    public void testGetBooleanWrapperNamedKeyProvidedDefault() throws Exception {
        assertTrue(proxy.getBooleanWrapperNamedKeyProvidedDefault());
    }

}
