package com.car.lib_audio.mediaplayer.core;

import com.car.lib_audio.mediaplayer.model.AudioBean;

import java.util.ArrayList;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_audio.mediaplayer.core
 * @time 2019/12/17 22:59
 * @description 控制播放逻辑
 */
public class AudioController {
    /**
     * 播放模式
     */
    public enum PlayMode {
        /**
         * 列表循环
         */
        LOOP,
        /**
         * 随机
         */
        RANDOM,
        /**
         * 单曲循环
         */
        REPEAT,
    }

    private static class SingletonHolder {
        public static final AudioController M_INSTANCE = new AudioController();
    }

    public static AudioController getInstance() {
        return SingletonHolder.M_INSTANCE;
    }

    /**
     * 核心播放器
     */
    private AudioPlayer mAudioPlayer;
    /**
     * 歌曲队列
     */
    private ArrayList<AudioBean> mQueue;
    /**
     * 当前播放索引
     */
    private int mQueueIndex;
    /**
     * 循环模式
     */
    private PlayMode mPlayMode;

    private AudioController() {
        mAudioPlayer = new AudioPlayer();
        mQueue = new ArrayList<>();
        mQueueIndex = 0;
        mPlayMode = PlayMode.LOOP;
    }
}
