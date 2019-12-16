package com.car.ttmusic.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.car.lib_commin_ui.base.BaseActivity;
import com.car.lib_network.listener.DisposeDataListener;
import com.car.ttmusic.R;
import com.car.ttmusic.api.RequestCenter;
import com.car.ttmusic.login.LoginEvent;
import com.car.ttmusic.model.user.User;
import com.car.ttmusic.utils.UserManager;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.ttmusic.view.login
 * @time 2019/12/11 21:38
 * @description
 */
public class LoginActivity extends BaseActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        findViewById(R.id.login_view).setOnClickListener(v -> {
            RequestCenter.login(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    User user = (User) responseObj;
                    UserManager.getInstance().saveUser(user);
                    EventBus.getDefault().post(new LoginEvent());
                    finish();
                }

                @Override
                public void onFailure(Object reasonObj) {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
