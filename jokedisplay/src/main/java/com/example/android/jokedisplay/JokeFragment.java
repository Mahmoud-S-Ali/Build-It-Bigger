package com.example.android.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Toty on 12/26/2015.
 */
public class JokeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);

        String jokeStr = "No Info";
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(JokeActivity.JOKE_KEY)) {
            jokeStr = intent.getStringExtra(JokeActivity.JOKE_KEY);
        }

        TextView jokeTV = (TextView) rootView.findViewById(R.id.joke_textview);
        jokeTV.setText(jokeStr);

        return rootView;
    }
}
