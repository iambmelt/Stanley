package com.brianjmelton.stanley;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.brianjmelton.stanley.testdata.ExampleProxies.ProxyWithName;

public abstract class AbstractTest extends AndroidTestCase {

    protected static final Class<ProxyWithName> proxyWithNameClass = ProxyWithName.class;

    private static final String NAME = "name";
    private static final String MODE = "mode";

    protected ProxyGenerator proxyGenerator;
    protected ProxyWithName proxy;

    protected SharedPreferences sharedPreferences;

    @Override
    protected void setUp() throws Exception {
        proxyGenerator = new ProxyGenerator();
        proxy = proxyGenerator.create(mContext, proxyWithNameClass);
        sharedPreferences = mContext
                .getSharedPreferences(getSharedPreferencesName(), getSharedPreferencesMode());
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void tearDown() throws Exception {
        sharedPreferences.edit().clear().commit();
    }

    protected String getSharedPreferencesName() {
        return getProxyClassAnnotationValue(NAME, String.class);

    }

    protected int getSharedPreferencesMode() {
        return getProxyClassAnnotationValue(MODE, Integer.class);
    }

    private <T> T getProxyClassAnnotationValue(String name, Class<T> clazz) {
        Exception e = null;
        try {
            for (Annotation annotation : proxyWithNameClass.getAnnotations()) {
                Class<? extends Annotation> type = annotation.annotationType();
                for (Method annotMethod : type.getDeclaredMethods()) {
                    if (name.equals(annotMethod.getName())) {
                        return clazz.cast(annotMethod.invoke(annotation));
                    }
                }
            }
        }
        /* Cannot multi-catch these reflection Exceptions on API < 19 */ catch (IllegalAccessException e1) {
            e = e1;
        } catch (InvocationTargetException e1) {
            e = e1;
        }
        throw new RuntimeException(e);
    }
}
