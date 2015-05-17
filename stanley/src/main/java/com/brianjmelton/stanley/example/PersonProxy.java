package com.brianjmelton.stanley.example;

import android.content.Context;

import com.brianjmelton.stanley.annot.Accessor;
import com.brianjmelton.stanley.annot.Proxy;

@Proxy(name = "PREFERENCES_FILE_NAME", mode = Context.MODE_PRIVATE)
public interface PersonProxy {

    @Accessor(key = "PREF_NAME")
    String getFirstName();

    @Accessor(key = "PREF_NAME")
    void setFirstName(String name);

    @Accessor(_String = Accessor._null)
    String getMiddleName();

    @Accessor
    void setMiddleName(String name);

    @Accessor(key = "PREF_HUNGRY", _boolean = true)
    boolean getIsHungry();

    @Accessor(key = "PREF_HUNGRY")
    void setIsHungry(boolean isHungry);

    @Accessor(_int = 10)
    int getAge();

    @Accessor
    void setAge(int age);

    @Accessor
    float getHeightInches();

    @Accessor
    void setHeightInches(float heightInches);

    @Accessor
    long getAgeMillis();

    @Accessor
    void setAgeMillis(long ageMillis);

    @Accessor(_boolean = true)
    boolean getLikesPizza();
}
