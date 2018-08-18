package com.udacity.joketime.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.joketime.jokes.Jokes;

/** An endpoint class we are exposing */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.joketime.udacity.com",
                ownerName = "backend.joketime.udacity.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {
        JokeBean response = new JokeBean();
        response.setJoke(Jokes.getJoke());
        return response;
    }

}
