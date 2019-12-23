package com.car.ttmusic.application;

import android.app.Application;

import com.car.lib_audio.app.AudioHelper;

/**
 * @author 345
 */
public class VoiceApplication extends Application {

    private static VoiceApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //音频SDK初始化
        AudioHelper.init(this);
    }

    public static VoiceApplication getInstance() {
        return mApplication;
    }
}
