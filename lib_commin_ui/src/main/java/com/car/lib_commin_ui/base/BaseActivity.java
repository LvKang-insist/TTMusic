package com.car.lib_commin_ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.car.lib_commin_ui.utils.StatusBarUtil;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_commin_ui.base
 * @time 2019/12/9 21:10
 * @description
 */
public class BaseActivity extends FragmentActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.statusBarLightMode(this);
    }
}
