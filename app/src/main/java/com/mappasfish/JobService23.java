package com.mappasfish;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

public class JobService23 extends JobService {
    private static final String TAG = JobService23.class.getSimpleName();
    final static String MY_ACTION = "MY_ACTION";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job Scheduled");

        Boolean data = NetUtil.isOnLine(getApplicationContext());
        Intent intent = new Intent();
        intent.setAction(MY_ACTION);
        intent.putExtra("DATAPASSED", data);
        sendBroadcast(intent);

        Util.scheduleJob23(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
