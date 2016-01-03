package com.example.android.builditbigger;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Toty on 1/2/2016.
 */
public class EndpointsAsyncTaskTest extends InstrumentationTestCase implements EndpointsAsyncTask.Callback {
    private CountDownLatch mSignal;
    private Context mContext;

    @Override
    protected void setUp() throws Exception {
        mContext = getInstrumentation().getContext();
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mSignal.countDown();
    }

    @MediumTest
    public void testAsyncTask() throws Throwable {
        final EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
        jokeTask.setCallbackListener(this);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                jokeTask.execute(mContext);
            }
        });

        mSignal.await();
    }

    @Override
    public void onTaskFinished(String joke) {
        assertNotNull(joke);
        mSignal.countDown();
    }
}
