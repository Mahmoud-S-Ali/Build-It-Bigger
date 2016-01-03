/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Toty.myapplication.jokebackend;

import com.example.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokebackend.myapplication.Toty.example.com",
    ownerName = "jokebackend.myapplication.Toty.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    /*@ApiMethod(name = "sayHi2")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }*/

    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        Joke jokeObj = new Joke();
        String joke = jokeObj.getJoke();

        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }

}
