package com.mappasfish;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

public class Util {
    // schedule the start of the service every 10 - 30 seconds
    private static JobScheduler jobScheduler;

    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(1001, serviceComponent);
        //builder.setPeriodic(5*1000);
        builder.setMinimumLatency(1 * 1000); // wait at least
        //builder.setOverrideDeadline(3 * 1000); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }

    public static void scheduleJob23(Context context) {
        ComponentName serviceComponent = new ComponentName(context, JobService23.class);
        JobInfo.Builder builder = new JobInfo.Builder(1001, serviceComponent);
        builder.setPeriodic(1*1000);
        //builder.setMinimumLatency(1 * 1000); // wait at least
        //builder.setOverrideDeadline(3 * 1000); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }

    public static void cancelJob(){
        jobScheduler.cancelAll();
    }

}
