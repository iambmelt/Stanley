package com.brianjmelton.stanley.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Accessor {

    String _null = "7026345d-fe2b-494d-930e-c1d0cbb8527c";

    String key() default _null;

    String _String() default _null;

    int _int() default 0;

    float _float() default 0F;

    long _long() default 0L;

    boolean _boolean() default false;

}
