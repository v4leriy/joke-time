package com.udacity.joketime;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.joketime.backend.jokeApi.JokeApi;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    public static final String TAG = "JokeAsyncTask";

    public interface JokeListener {

        void onJoke(String joke);
    }

    private static JokeApi jokeApiService = null;

    private JokeListener listener;

    public JokeAsyncTask(JokeListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (jokeApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
                // end options for devappserver

            jokeApiService = builder.build();
        }

        try {
            return jokeApiService.getJoke().execute().getJoke();
        } catch (Exception e) {
            Log.e(TAG, "Failed to get joke", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onJoke(joke);
    }
}