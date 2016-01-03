package com.example.android.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Toty on 12/26/2015.
 */
public class JokeActivity extends ActionBarActivity {
    public static String JOKE_KEY = "JOKE KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.joke_container, new JokeFragment(), null)
                    .commit();
        }*/
    }
}

