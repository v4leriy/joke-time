package com.udacity.joketime.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(EXTRA_JOKE);
        if (joke == null) {
            finish();
        } else {
            TextView jokeView = findViewById(R.id.joke_view);
            jokeView.setText(joke);
        }
    }

}
