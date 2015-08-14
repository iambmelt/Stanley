package com.brianjmelton.stanley;

public class TestStringAccessor extends AbstractTest {

    public void testSetStringNull() throws Exception {
        final String expectedNull = null;
        proxy.setString(expectedNull);
        String result = sharedPreferences.getString("String", null);
        assertNull(result);
    }

    public void testSetString() throws Exception {
        final String expected = "test string";
        proxy.setString(expected);
        String result = sharedPreferences.getString("String", null);
        assertEquals(expected, result);
    }

    public void testGetString() throws Exception {
        final String expected = "trumpets";
        sharedPreferences.edit().putString("String", expected).commit();
        assertEquals(expected, proxy.getString());
    }

    public void testSetStringNamedKey() throws Exception {
        final String expected = "turtle";
        proxy.setStringNamedKey(expected);
        assertEquals(expected, sharedPreferences.getString("key_string", ""));
    }

    public void testGetStringNamedKey() throws Exception {
        final String expected = "pineapple";
        sharedPreferences.edit().putString("key_string", expected).commit();
        assertEquals(expected, proxy.getStringNamedKey());
    }

    public void testGetStringProvidedDefault() throws Exception {
        assertEquals("hello", proxy.getStringProvidedDefault());
    }

    public void testGetStringNamedKeyProvidedDefault() throws Exception {
        assertEquals(
                sharedPreferences.getString("key_string_default", "hola"),
                proxy.getStringNamedKeyProvidedDefault());
    }

}
