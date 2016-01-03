package com.example.android.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.toty.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Toty on 12/27/2015.
 */
class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private MyApi myApiService = null;
    private Context mContext;
    Callback mCallback;

    public interface Callback {
        public void onTaskFinished(String jokeStr);
    }

    public void setCallbackListener(Callback listener){
        mCallback = listener;
    }

    public void Hello(){}

    @Override
    protected String doInBackground(Context... context) {
        if(myApiService == null) {  // Only do this once
            // Used for local testing
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketeller-1172.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = context[0];

        try {

            return myApiService.tellJoke().execute().getData();
            //return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onTaskFinished(result);
    }
}
