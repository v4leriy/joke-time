package com.udacity.joketime.jokes;

public class Jokes {

    private static int count = 0;

    public static String getJoke() {
        return "Joke #" + count++;
    }
}
