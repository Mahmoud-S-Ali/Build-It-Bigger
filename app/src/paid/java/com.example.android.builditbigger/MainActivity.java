package com.example.android.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.jokedisplay.JokeActivity;

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.Callback {
    public static String LOG_TAG = "MAIN_ACTIVITY";
    public static String JOKE_KEY = "JOKE KEY";

    private ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar)findViewById(R.id.joke_progress_bar);
        hideProgressBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        if (Utilities.isNetworkAvailable(this) == false) {
            Toast.makeText(this, "Please check Network Connection", Toast.LENGTH_LONG).show();
        } else {
            showProgressBar();
            createJoke();
        }
    }

    @Override
    public void onTaskFinished(String joke) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
        hideProgressBar();
    }

    private void createJoke() {
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
        jokeTask.setCallbackListener(this);
        jokeTask.execute(this);
    }

    private void showProgressBar() {
        if (mSpinner != null) {
            mSpinner.setVisibility(View.VISIBLE);
        }
        findViewById(R.id.fragment_main).setVisibility(View.GONE);
    }
    private void hideProgressBar() {
        if (mSpinner != null) {
            mSpinner.setVisibility(View.GONE);
        }
        findViewById(R.id.fragment_main).setVisibility(View.VISIBLE);
    }
}
