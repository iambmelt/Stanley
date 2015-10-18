# Stanley
[![Build Status](https://travis-ci.org/iambmelt/Stanley.svg?branch=master)](https://travis-ci.org/iambmelt/Stanley)
[ ![Download](https://api.bintray.com/packages/iambmelton/maven/stanley/images/download.svg) ](https://bintray.com/iambmelton/maven/stanley/_latestVersion)[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Stanley-green.svg?style=flat)](https://android-arsenal.com/details/1/2647)

Convenient stashing of simple data formats on Android with SharedPreferences

Example
=======

Define an interface

```java
@Proxy(name = "Prefs", mode = Context.MODE_PRIVATE)
public interface Person {
    @Accessor
    void setLastName(String name);
}
```

Make an instance

```java
Person elvis = new ProxyGenerator().create(context, Person.class);
```

And go!

```java
elvis.setLastName("presley");
```

Download
========
```compile 'com.brianjmelton:stanley:1.0.1'```

License
=======

    Copyright 2015 Brian Melton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
