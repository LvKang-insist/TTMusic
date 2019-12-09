package com.car.ttmusic.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.car.ttmusic.model.CHANNEL;
import com.car.ttmusic.view.discory.DiscordFragment;
import com.car.ttmusic.view.friend.FriendFragment;
import com.car.ttmusic.view.mine.MineFragment;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.ttmusic.view.home.adapter
 * @time 2019/12/9 20:56
 * @description 首页 Viewpager adapter
 */
public class HomePageAdapter extends FragmentPagerAdapter {
    private CHANNEL[] mList;

    public HomePageAdapter(FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        this.mList = datas;
    }

    @Override
    public Fragment getItem(int i) {
        int type = mList[i].getValue();
        switch (type) {
            case CHANNEL.MINE_ID:
                return MineFragment.newInstance();
            case CHANNEL.DISCORY_ID:
                return DiscordFragment.newInstance();
            case CHANNEL.FRIEND_ID:
                return FriendFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mList.length;
    }
}
