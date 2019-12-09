package com.car.ttmusic.view.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.car.ttmusic.R;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.ttmusic.view.mine
 * @time 2019/12/9 21:18
 * @description
 */
public class MineFragment extends Fragment {


    public static Fragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_layout, null, false);
        return view;
    }
}
