package com.car.lib_audio.mediaplayer.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.car.lib_audio.R;
import com.car.lib_audio.mediaplayer.core.AudioController;
import com.car.lib_audio.mediaplayer.event.AudioLoadEvent;
import com.car.lib_audio.mediaplayer.event.AudioPauseEvent;
import com.car.lib_audio.mediaplayer.event.AudioStartEvent;
import com.car.lib_audio.mediaplayer.model.AudioBean;
import com.car.lib_image_loader.app.ImageLoaderManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_audio.mediaplayer.view
 * @time 2019/12/23 22:31
 * @description
 */
public class BottomMusicView extends LinearLayout {


    private Context mContext;

    /*
     * View
     */
    private AppCompatImageView mLeftView;
    private AppCompatTextView mTitleView;
    private AppCompatTextView mAlbumView;
    private AppCompatImageView mPlayView;
    private AppCompatImageView mRightView;
    /*
     * data
     */
    private AudioBean mAudioBean;

    public BottomMusicView(Context context) {
        this(context, null);
    }

    public BottomMusicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.bottom_view, this);
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLeftView = rootView.findViewById(R.id.album_view);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLeftView, View.ROTATION.getName(), 0f, 360);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.start();

        mTitleView = rootView.findViewById(R.id.audio_name_view);
        mAlbumView = rootView.findViewById(R.id.audio_album_view);
        mPlayView = rootView.findViewById(R.id.play_view);
        mPlayView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioController.getInstance().playOrPause();
            }
        });
        mRightView = rootView.findViewById(R.id.show_list_view);
        mRightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioLoadEvent(AudioLoadEvent event) {
        //监听加载事件
        mAudioBean = event.mAudioBean;
        showLoadingView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioStartEvent(AudioStartEvent event) {
        showPlayView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioPauseEvent(AudioPauseEvent event) {
        showPauseView();
    }

    private void showLoadingView() {
        //目前loading状态的UI处理与pause逻辑一样，分开为了以后好扩展
        if (mAudioBean != null) {
            ImageLoaderManager.getInstance().displayImageForCircle(mLeftView, mAudioBean.albumPic);
            mTitleView.setText(mAudioBean.name);
            mAlbumView.setText(mAudioBean.album);
            mPlayView.setImageResource(R.mipmap.note_btn_pause_white);
        }
    }

    private void showPlayView() {
        mPlayView.setImageResource(R.mipmap.note_btn_pause_white);

    }

    private void showPauseView() {
        mPlayView.setImageResource(R.mipmap.note_btn_play_white);

    }
}
