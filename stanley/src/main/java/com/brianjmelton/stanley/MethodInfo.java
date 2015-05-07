package com.brianjmelton.stanley;

import android.content.SharedPreferences;

import com.brianjmelton.stanley.annot.Accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class MethodInfo {

    private static final String SET = "set";
    private static final String GET = "get";

    private interface Getter {
        Object get(SharedPreferences sharedPreferences, MethodInfo methodInfo);
    }

    enum Type implements Getter {
        _String(String.class, String.class) {
            @Override
            public Object get(SharedPreferences sharedPreferences, MethodInfo methodInfo) {
                return sharedPreferences.getString(methodInfo.key, methodInfo._String);
            }
        },
        _int(int.class, Integer.class) {
            @Override
            public Object get(SharedPreferences sharedPreferences, MethodInfo methodInfo) {
                return sharedPreferences.getInt(methodInfo.key, methodInfo._int);
            }
        },
        _float(float.class, Float.class) {
            @Override
            public Object get(SharedPreferences sharedPreferences, MethodInfo methodInfo) {
                return sharedPreferences.getFloat(methodInfo.key, methodInfo._float);
            }
        },
        _long(long.class, Long.class) {
            @Override
            public Object get(SharedPreferences sharedPreferences, MethodInfo methodInfo) {
                return sharedPreferences.getLong(methodInfo.key, methodInfo._long);
            }
        },
        _boolean(boolean.class, Boolean.class) {
            @Override
            public Object get(SharedPreferences sharedPreferences, MethodInfo methodInfo) {
                return sharedPreferences.getBoolean(methodInfo.key, methodInfo._boolean);
            }
        };

        public final Class<?> clazz;
        public final Class<?> wrapperClazz;


        Type(Class<?> simpleClass, Class<?> wrapperClass) {
            this.clazz = simpleClass;
            this.wrapperClazz = wrapperClass;
        }
    }

    final static Map<String, Type> typeByNameLookup = new HashMap<>();
    final static Map<Class<?>, Type> typeByClassLookup = new HashMap<>();

    static {
        for (Type t : Type.values()) {
            typeByNameLookup.put(t.name(), t);
            typeByClassLookup.put(t.clazz, t);
            typeByClassLookup.put(t.wrapperClazz, t);
        }
    }

    final Method method;

    final boolean hasReturnType;
    final Class<?> returnType;
    final Type type;

    String key;
    String _String;
    int _int;
    float _float;
    long _long;
    boolean _boolean;

    MethodInfo(Method method) {
        this.method = method;

        for (Annotation methodAnnotation : method.getAnnotations()) {
            try {
                Class<? extends Annotation> annotationType = methodAnnotation.annotationType();
                if (Accessor.class != annotationType) {
                    continue;
                }

                for (Method annotMethod : annotationType.getDeclaredMethods()) {
                    final String methodName = annotMethod.getName();

                    if ("key".equals(methodName)) {
                        key = (String) annotMethod.invoke(methodAnnotation);
                        continue;
                    }

                    Type t = typeByNameLookup.get(methodName);
                    Class<?> klazz = t.wrapperClazz;
                    Object v = annotMethod.invoke(methodAnnotation);
                    getClass().getDeclaredField(t.name()).set(this, klazz.cast(v));
                }

            } catch (IllegalArgumentException
                    | InvocationTargetException
                    | IllegalAccessException
                    | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        // init the key - if the accessor annotation has a 'key' parameter use that, otherwise
        // chop off 'get' or 'set' and use the rest of the method name
        if (key.equals(Accessor._null)) {
            final String name = method.getName();
            if (name.startsWith(SET)) {
                key = name.replaceFirst(SET, "");
            } else if (name.startsWith(GET)) {
                key = name.replaceFirst(GET, "");
            } else {
                throw new RuntimeException("@Accessor annotation on non setter/getter");
            }
        }

        if (_String.equals(Accessor._null)) {
            _String = null;
        }

        // test if this is a getter or a setter
        returnType = method.getReturnType();
        hasReturnType = returnType != void.class;
        type = typeByClassLookup.get(returnType);
    }

}
