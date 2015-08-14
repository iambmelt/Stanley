package com.brianjmelton.stanley;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProxyGenerator {

    private final Map<Class<?>, Map<Method, MethodInfo>> proxyMethodInfoCache = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T create(Context context, Class<T> proxy) {
        checkIsInterface(proxy);
        checkIsNotExtendingAnotherInterface(proxy);
        return (T) Proxy.newProxyInstance(
                proxy.getClassLoader(),
                new Class<?>[]{proxy},
                new SharedPreferencesHandler(context, proxy,
                        getMethodInfoCache(proxy)));
    }

    private <T> void checkIsNotExtendingAnotherInterface(Class<T> proxy) {
        if (0 < proxy.getInterfaces().length) {
            throw new IllegalArgumentException(
                    "Interfaces extending other interfaces are not supported at this time");
        }
    }

    private static <T> void checkIsInterface(Class<T> clazz) {
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("Proxy must be defined by annotating an interface");
        }
    }

    private Map<Method, MethodInfo> getMethodInfoCache(Class<?> proxy) {
        synchronized (proxyMethodInfoCache) {
            Map<Method, MethodInfo> methodInfoCache = proxyMethodInfoCache.get(proxy);
            if (methodInfoCache == null) {
                methodInfoCache = new LinkedHashMap<>();
                proxyMethodInfoCache.put(proxy, methodInfoCache);
            }
            return methodInfoCache;
        }
    }

    private static MethodInfo getMethodInfo(Map<Method, MethodInfo> cache, Method method) {
        synchronized (cache) {
            MethodInfo methodInfo = cache.get(method);
            if (methodInfo == null) {
                methodInfo = new MethodInfo(method);
                cache.put(method, methodInfo);
            }
            return methodInfo;
        }
    }

    private class SharedPreferencesHandler implements InvocationHandler {

        private static final String NAME = "name";
        private static final String MODE = "mode";
        private final Context context;
        private final Class<?> clazz;
        private final Map<Method, MethodInfo> methodDetailsCache;

        public SharedPreferencesHandler(Context context,
                                        Class<?> proxy,
                                        Map<Method, MethodInfo> methodDetailsCache) {
            this.context = context;
            this.clazz = proxy;
            this.methodDetailsCache = methodDetailsCache;
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // If the method is a method from Object then defer to normal invocation.
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }

            String name = null;
            int mode = 0;

            for (Annotation annotation : clazz.getAnnotations()) {
                Class<? extends Annotation> type = annotation.annotationType();
                for (Method annotMethod : type.getDeclaredMethods()) {
                    if (NAME.equals(annotMethod.getName())) {
                        name = (String) annotMethod.invoke(annotation);
                    } else if (MODE.equals(annotMethod.getName())) {
                        mode = (int) annotMethod.invoke(annotation);
                    }
                }
            }

            SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);

            MethodInfo methodInfo = getMethodInfo(methodDetailsCache, method);
            if (methodInfo.hasReturnType) {
                // getter
                return methodInfo.type.get(sharedPreferences, methodInfo);
            } else {
                // setter
                Class<?> paramType = method.getParameterTypes()[0];
                persistenceDelegates.get(paramType)
                        .persist(sharedPreferences.edit(), methodInfo.key, args[0]);
            }
            return null;
        }

    }

    private interface Persister {
        void persist(SharedPreferences.Editor editor, String key, Object what);
    }

    private static final Map<Class<?>, Persister> persistenceDelegates
            = new HashMap<Class<?>, Persister>() {{
        put(String.class, new Persister() {
            @Override
            public void persist(SharedPreferences.Editor editor, String key, Object what) {
                editor.putString(key, (String) what).commit();
            }
        });
        put(Integer.class, new Persister() {
            @Override
            public void persist(SharedPreferences.Editor editor, String key, Object what) {
                editor.putInt(key, (Integer) what).commit();
            }
        });
        put(Float.class, new Persister() {
            @Override
            public void persist(SharedPreferences.Editor editor, String key, Object what) {
                editor.putFloat(key, (Float) what).commit();
            }
        });
        put(Long.class, new Persister() {
            @Override
            public void persist(SharedPreferences.Editor editor, String key, Object what) {
                editor.putLong(key, (Long) what).commit();
            }
        });
        put(Boolean.class, new Persister() {
            @Override
            public void persist(SharedPreferences.Editor editor, String key, Object what) {
                editor.putBoolean(key, (Boolean) what).commit();
            }
        });
    }};
}
