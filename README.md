# Stanley
Convenient stashing of simple data formats in SharedPreferences

Example
=======

Define an interface

    @Proxy(name = "Prefs", mode = Context.MODE_PRIVATE)
    public interface PersonProxy {
        @Accessor
        void setName(String name);
    }

Make an instance

    PersonProxy proxy = new ProxyGenerator().create(Context.this, PersonProxy.class);

And go!

    proxy.setName("Fred");

Download
========
// TODO

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
