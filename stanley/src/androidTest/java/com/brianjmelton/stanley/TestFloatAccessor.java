package com.brianjmelton.stanley;

public class TestFloatAccessor extends AbstractTest {

    public static final double DELTA = 0.0001;

    public void testSetFloat() throws Exception {
        final float expected = 1.35f;
        proxy.setFloat(expected);
        assertEquals(expected, sharedPreferences.getFloat("Float", 0), DELTA);
    }

    public void testSetFloatWrapper() throws Exception {
        final Float expected = 1.23456f;
        proxy.setFloatWrapper(expected);
        assertEquals(expected, sharedPreferences.getFloat("FloatWrapper", 0), DELTA);
    }

    public void testSetFloatNamedKey() throws Exception {
        final float expected = 2.123f;
        proxy.setFloatNamedKey(expected);
        assertEquals(expected, sharedPreferences.getFloat("key_float", 0), DELTA);
    }

    public void testSetFloatWrapperNamedKey() throws Exception {
        final Float expected = 2.9876f;
        proxy.setFloatWrapperNamedKey(expected);
        assertEquals(expected, sharedPreferences.getFloat("key_float_wrapper", 0), DELTA);
    }

    public void testGetFloat() throws Exception {
        final float expected = 0.0092f;
        sharedPreferences.edit().putFloat("Float", expected).commit();
        assertEquals(expected, proxy.getFloat(), DELTA);
    }

    public void testGetFloatWrapper() throws Exception {
        final Float expected = 7.004f;
        sharedPreferences.edit().putFloat("FloatWrapper", expected).commit();
        assertEquals(expected, proxy.getFloatWrapper(), DELTA);
    }

    public void testGetFloatNamedKey() throws Exception {
        final float expected = 45.8752f;
        sharedPreferences.edit().putFloat("key_float", expected).commit();
        assertEquals(expected, proxy.getFloatNamedKey(), DELTA);
    }

    public void testGetFloatWrapperNamedKey() throws Exception {
        final Float expected = 78.2360f;
        sharedPreferences.edit().putFloat("key_float_wrapper", expected).commit();
        assertEquals(expected, proxy.getFloatWrapperNamedKey(), DELTA);
    }

    public void testGetFloatProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getFloat("FloatProvidedDefault", 5.2f),
                proxy.getFloatProvidedDefault(), DELTA);
    }

    public void testGetFloatWrapperProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getFloat("FloatWrapperProvidedDefault", 7.65f),
                proxy.getFloatWrapperProvidedDefault(), DELTA);
    }

    public void testGetFloatNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getFloat("key_float_default", 5.5222f),
                proxy.getFloatNamedKeyProvidedDefault(), DELTA);
    }

    public void testGetFloatWrapperNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getFloat("key_float_wrapper_default", 19.6802f),
                proxy.getFloatWrapperNamedKeyProvidedDefault(), DELTA);
    }

}
