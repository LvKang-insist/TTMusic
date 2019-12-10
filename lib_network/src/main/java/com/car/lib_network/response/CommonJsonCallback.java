package com.car.lib_network.response;

import android.os.Handler;
import android.os.Looper;

import com.car.lib_network.R;
import com.car.lib_network.listener.DisposeDataHandle;
import com.car.lib_network.listener.DisposeDataListener;
import com.google.gson.Gson;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_network.response
 * @time 2019/12/9 22:30
 * @description 处理 json 类型的响应
 */
public class CommonJsonCallback implements Callback {
    protected final String EMPTY_MSG = "";

    /**
     * 网络层异常
     */
    protected final int NETWORK_ERROR = -1;
    /**
     * JSON 异常
     */
    protected final int JSON_ERROR = -2;

    /**
     * 未知的异常
     */
    protected final int OTHER_ERROR = -3;


    private DisposeDataListener mListener;
    private Class<?> mClass;
    private Handler mDeliveryHandler;

    public CommonJsonCallback(DisposeDataHandle handle) {
        mListener = handle.mListener;
        mClass = handle.mClass;
        mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new Exception("网络错误"));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(String result) {
        if (result == null || result.isEmpty()) {
            mListener.onFailure(new Exception("数据为 null"));
            return;
        }
        if (mClass == null) {
            mListener.onSuccess(result);
        } else {
            Object obj = new Gson().fromJson(result, mClass);
            if (obj != null) {
                mListener.onSuccess(obj);
            } else {
                mListener.onFailure(new Exception("解析失败"));
            }
        }
    }


}
