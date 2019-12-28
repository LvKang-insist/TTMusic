package com.car.lib_pullalive;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_pullalive
 * @time 2019/12/28 20:35
 * @description 一个轻量级的后台job service，利用空闲的时间执行一些小事情，跳过进程不被回收的概率
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AliveJobService extends JobService {

    private static final String TAG = "AliveJobService";

    private static final int START_ALIVE = 0x01;
    private static final int STOP_ALIVE = 0x02;

    private JobScheduler mJobScheduler;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START_ALIVE:
                    Log.e(TAG, "start alive ");
                    jobFinished((JobParameters) msg.obj, true);
                    break;
                case STOP_ALIVE:
                    Log.e(TAG, "stop alive ");
                    break;
                default:
                    break;
            }
        }
    };

    public static void start(Context context) {
        Intent intent = new Intent(context, AliveJobService.class);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JobInfo job = initJobInfo(startId);
        //提交自己的 job 到 System process 中
        if (mJobScheduler.schedule(job) <= 0) {
            Log.e(TAG, "AlieJobService failed");
        } else {
            Log.d(TAG, "AliveJobService Success");
        }
        // 粘性的方式启动 Service
        return START_STICKY;
    }

    /**
     * 初始化 JobInfo
     *
     * @param startId
     * @return
     */
    private JobInfo initJobInfo(int startId) {
        JobInfo.Builder builder = new JobInfo.Builder(startId, new ComponentName(getPackageName(), AliveJobService.class.getName()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS)
                    .setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS)
                    .setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR);
        } else {
            builder.setPeriodic(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
        }
        //是否持久化
        builder.setPersisted(false);
        //网络
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
        //充电
        builder.setRequiresCharging(false);
        //电池充足
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setRequiresBatteryNotLow(true);
        }
        return builder.build();
    }


    /**
     * 开始任务
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        mHandler.sendMessage(Message.obtain(mHandler, START_ALIVE, params));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.sendMessage(Message.obtain(mHandler, 2));
        return false;
    }
}
