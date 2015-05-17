package com.brianjmelton.stanley;

public class TestLongAccessor extends AbstractTest {

    public void testSetLong() throws Exception {
        final long expected = 1001l;
        proxy.setLong(expected);
        assertEquals(expected, sharedPreferences.getLong("Long", 0));
    }

    public void testSetLongWrapper() throws Exception {
        final Long expected = 35l;
        proxy.setLongWrapper(expected);
        assertEquals((long) expected, sharedPreferences.getLong("LongWrapper", 0l));
    }

    public void testSetLongNamedKey() throws Exception {
        final long expected = 12l;
        proxy.setLongNamedKey(expected);
        assertEquals(expected, sharedPreferences.getLong("key_long", 0));
    }

    public void testSetLongWrapperNamedKey() throws Exception {
        final Long expected = 1337l;
        proxy.setLongWrapperNamedKey(expected);
        assertEquals((long) expected, sharedPreferences.getLong("key_long_wrapper", 0));
    }

    public void testGetLong() throws Exception {
        final long expected = 12l;
        sharedPreferences.edit().putLong("Long", expected).commit();
        assertEquals(expected, proxy.getLong());
    }

    public void testGetLongWrapper() throws Exception {
        final Long expected = 10l;
        sharedPreferences.edit().putLong("LongWrapper", expected).commit();
        assertEquals(expected, proxy.getLongWrapper());
    }

    public void testGetLongNamedKey() throws Exception {
        final long expected = 13l;
        sharedPreferences.edit().putLong("key_long", expected).commit();
        assertEquals(expected, proxy.getLongNamedKey());
    }

    public void testGetLongWrapperNamedKey() throws Exception {
        final Long expected = 8l;
        sharedPreferences.edit().putLong("key_long_wrapper", expected).commit();
        assertEquals(expected, proxy.getLongWrapperNamedKey());
    }

    public void testGetLongProvidedDefault() throws Exception {
        assertEquals(5l, proxy.getLongProvidedDefault());
    }

    public void testGetLongWrapperProvidedDefault() throws Exception {
        assertEquals(6l, (long) proxy.getLongWrapperProvidedDefault());
    }

    public void testGetLongNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getLong("key_long_default", 7l),
                proxy.getLongNamedKeyProvidedDefault());
    }

    public void testGetLongWrapperNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getLong("key_long_wrapper_default", 8l),
                (long) proxy.getLongWrapperNamedKeyProvidedDefault());
    }

}
