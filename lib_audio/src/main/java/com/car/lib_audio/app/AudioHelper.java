package com.car.lib_audio.app;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_audio.app
 * @time 2019/12/17 21:40
 * @description 唯一与外界通信的帮助类
 */
public class AudioHelper {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
