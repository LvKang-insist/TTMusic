package com.car.lib_audio.mediaplayer.event;

import com.car.lib_audio.mediaplayer.core.AudioController;
import com.car.lib_audio.mediaplayer.core.PlayMode;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_audio.mediaplayer.event
 * @time 2019/12/18 20:27
 * @description 设置播放模式的事件
 */
public class AudioPlayModeEvent {

    PlayMode mPlayMode;

    public AudioPlayModeEvent(PlayMode playMode) {
        mPlayMode = playMode;
    }
}
