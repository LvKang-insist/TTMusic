package com.car.lib_audio.mediaplayer.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.car.lib_audio.R;
import com.car.lib_audio.mediaplayer.model.AudioBean;
import com.car.lib_image_loader.app.ImageLoaderManager;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_audio.mediaplayer.core
 * @time 2019/12/25 23:18
 * @description
 */
public class MusicPlayerActivity extends AppCompatActivity {

    private AudioBean mAudioBean;
    private PlayMode mPlayMode;
    private RelativeLayout mBgView;
    private TextView mInfoView;
    private TextView mAuthorView;
    private TextView mStartTimeView;
    private TextView mTotalTimeView;
    private SeekBar mProgressView;
    private ImageView mPreViousView;
    private ImageView mPlayModeView;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MusicPlayerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_music_service_layout);
        initData();
        initView();
    }

    private void initData() {
        mAudioBean = AudioController.getInstance().getNowPlaying();
        mPlayMode = AudioController.getInstance().getPlayMode();
    }

    private void initView() {
        mBgView = findViewById(R.id.root_layout);
        //给背景添加模糊效果
        ImageLoaderManager.getInstance().displayImageForViewGroup(mBgView, mAudioBean.album);
        findViewById(R.id.back_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.title_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.share_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.show_list_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mInfoView = findViewById(R.id.album_view);
        mInfoView.setText(mAudioBean.albumInfo);
        mInfoView.requestFocus();//跑马灯效果，获取焦点

        mAuthorView = findViewById(R.id.author_view);
        mAuthorView.setText(mAudioBean.author);

        changeFavouriteStatus(false);

        mStartTimeView = findViewById(R.id.start_time_view);
        mTotalTimeView = findViewById(R.id.total_time_view);
        mProgressView = findViewById(R.id.progress_view);
        mProgressView.setProgress(0);
        mProgressView.setEnabled(false);


        mPlayModeView = findViewById(R.id.play_mode_view);
        mPlayModeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        upDataPlayModeView();

        mPreViousView = findViewById(R.id.previous_view);
        mPreViousView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上一首
            }
        });

        ImageView mPlayView = findViewById(R.id.play_view);
        mPlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂停
            }
        });

        ImageView mNextView = findViewById(R.id.next_view);
        mNextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂停
            }
        });
    }

    private void changeFavouriteStatus(boolean b) {
    }

    private void upDataPlayModeView() {
        switch (mPlayMode) {
            case LOOP:
                mPlayModeView.setImageResource(R.mipmap.player_loop);
                break;
            case REPEAT:
                mPlayModeView.setImageResource(R.mipmap.player_random);
                break;
            case RANDOM:
                mPlayModeView.setImageResource(R.mipmap.player_once);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
