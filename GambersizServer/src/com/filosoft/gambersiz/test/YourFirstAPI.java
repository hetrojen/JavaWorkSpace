package com.filosoft.gambersiz.test;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(name = "myApi",
     version = "v1",
     namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
                                ownerName = "helloworld.example.com",
                                packagePath=""))
public class YourFirstAPI {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(path = "sayHi/{name}", httpMethod = "get")
    public MyBean sayHi(@Named("name") String name, @Named("accessToken") String accessToken) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

}