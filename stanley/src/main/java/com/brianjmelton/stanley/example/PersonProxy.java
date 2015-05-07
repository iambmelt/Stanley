package com.brianjmelton.stanley.example;

import android.content.Context;

import com.brianjmelton.stanley.annot.Accessor;
import com.brianjmelton.stanley.annot.Proxy;

@Proxy(name = "PREFERENCES_FILE_NAME", mode = Context.MODE_PRIVATE)
public interface PersonProxy {

    @Accessor(key = "PREF_NAME")
    public String getFirstName();

    @Accessor(key = "PREF_NAME")
    public void setFirstName(String name);

    @Accessor(_String = Accessor._null)
    public String getMiddleName();

    @Accessor
    public void setMiddleName(String name);

    @Accessor(key = "PREF_HUNGRY", _boolean = true)
    public boolean getIsHungry();

    @Accessor(key = "PREF_HUNGRY")
    public void setIsHungry(boolean isHungry);

    @Accessor(_int = 10)
    public int getAge();

    @Accessor
    public void setAge(int age);

    @Accessor
    public float getHeightInches();

    @Accessor
    public void setHeightInches(float heightInches);

    @Accessor
    public long getAgeMillis();

    @Accessor
    public void setAgeMillis(long ageMillis);

    @Accessor(_boolean = true)
    public boolean getLikesPizza();
}
