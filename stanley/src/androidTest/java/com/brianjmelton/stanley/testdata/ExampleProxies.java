package com.brianjmelton.stanley.testdata;

import android.content.Context;

import com.brianjmelton.stanley.annot.Accessor;
import com.brianjmelton.stanley.annot.Proxy;

public class ExampleProxies {

    @Proxy(name = "ProxyWithName.file")
    public interface ProxyWithName {

        //
        // String
        //

        @Accessor
        void setString(String v);

        @Accessor
        String getString();

        @Accessor(key = "key_string")
        void setStringNamedKey(String string);

        @Accessor(key = "key_string")
        String getStringNamedKey();

        @Accessor(_String = "hello")
        String getStringProvidedDefault();

        @Accessor(key = "key_string_default", _String = "hola")
        String getStringNamedKeyProvidedDefault();

        //
        // Int
        //

        @Accessor
        void setInt(int v);

        @Accessor
        void setIntWrapper(Integer v);

        @Accessor(key = "key_int")
        void setIntNamedKey(int v);

        @Accessor(key = "key_int_wrapper")
        void setIntWrapperNamedKey(Integer v);

        @Accessor
        int getInt();

        @Accessor
        Integer getIntWrapper();

        @Accessor(key = "key_int")
        int getIntNamedKey();

        @Accessor(key = "key_int_wrapper")
        Integer getIntWrapperNamedKey();

        @Accessor(_int = 5)
        int getIntProvidedDefault();

        @Accessor(_int = 6)
        Integer getIntWrapperProvidedDefault();

        @Accessor(key = "key_int_default", _int = 5)
        int getIntNamedKeyProvidedDefault();

        @Accessor(key = "key_int_wrapper_default", _int = 7)
        Integer getIntWrapperNamedKeyProvidedDefault();

        //
        // Float
        //

        @Accessor
        void setFloat(float v);

        @Accessor
        void setFloatWrapper(Float v);

        @Accessor(key = "key_float")
        void setFloatNamedKey(float v);

        @Accessor(key = "key_float_wrapper")
        void setFloatWrapperNamedKey(Float v);

        @Accessor
        float getFloat();

        @Accessor
        Float getFloatWrapper();

        @Accessor(key = "key_float")
        float getFloatNamedKey();

        @Accessor(key = "key_float_wrapper")
        Float getFloatWrapperNamedKey();

        @Accessor(_float = 5.2f)
        float getFloatProvidedDefault();

        @Accessor(_float = 7.65f)
        Float getFloatWrapperProvidedDefault();

        @Accessor(key = "key_float_default", _float = 5.5222f)
        float getFloatNamedKeyProvidedDefault();

        @Accessor(key = "key_float_wrapper_default", _float = 19.6802f)
        Float getFloatWrapperNamedKeyProvidedDefault();

        //
        // Long
        //

        @Accessor
        void setLong(long v);

        @Accessor
        void setLongWrapper(Long v);

        @Accessor(key = "key_long")
        void setLongNamedKey(long v);

        @Accessor(key = "key_long_wrapper")
        void setLongWrapperNamedKey(Long v);

        @Accessor
        long getLong();

        @Accessor
        Long getLongWrapper();

        @Accessor(key = "key_long")
        long getLongNamedKey();

        @Accessor(key = "key_long_wrapper")
        Long getLongWrapperNamedKey();

        @Accessor(_long = 5l)
        long getLongProvidedDefault();

        @Accessor(_long = 6l)
        Long getLongWrapperProvidedDefault();

        @Accessor(key = "key_long_default", _long = 7l)
        long getLongNamedKeyProvidedDefault();

        @Accessor(key = "key_long_wrapper_default", _long = 8l)
        Long getLongWrapperNamedKeyProvidedDefault();

        //
        // Boolean
        //

        @Accessor
        void setBoolean(boolean v);

        @Accessor
        void setBooleanWrapper(Boolean v);

        @Accessor(key = "key_boolean")
        void setBooleanNamedKey(boolean v);

        @Accessor(key = "key_boolean_wrapper")
        void setBooleanWrapperNamedKey(Boolean v);

        @Accessor
        boolean getBoolean();

        @Accessor
        Boolean getBooleanWrapper();

        @Accessor(key = "key_boolean")
        boolean getBooleanNamedKey();

        @Accessor(key = "key_boolean_wrapper")
        Boolean getBooleanWrapperNamedKey();

        @Accessor(_boolean = true)
        boolean getBooleanProvidedDefault();

        @Accessor(_boolean = true)
        Boolean getBooleanWrapperProvidedDefault();

        @Accessor(key = "key_boolean_default", _boolean = true)
        boolean getBooleanNamedKeyProvidedDefault();

        @Accessor(key = "key_boolean_wrapper_default", _boolean = true)
        Boolean getBooleanWrapperNamedKeyProvidedDefault();

    }

    @Proxy(name = "ProxyWithNameAndMode.file", mode = Context.MODE_MULTI_PROCESS)
    public interface ProxyWithNameAndMode {

    }
}
