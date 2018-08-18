package com.udacity.joketime;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    @Test
    public void getJoke() throws Exception {
        CountDownLatch signal = new CountDownLatch(1);

        JokeAsyncTask.JokeListener listener = joke -> {
            System.out.println("Got joke: " + joke);
            assertFalse("Failed to get joke", TextUtils.isEmpty(joke));
            signal.countDown();
        };

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.runOnMainSync(() -> new JokeAsyncTask(listener).execute());

        assertTrue("Timeout waiting for joke", signal.await(30, TimeUnit.SECONDS));
    }
}
