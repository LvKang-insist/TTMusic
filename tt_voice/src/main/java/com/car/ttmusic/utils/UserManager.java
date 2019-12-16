package com.car.ttmusic.utils;

import com.car.ttmusic.model.user.User;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.ttmusic.utils
 * @time 2019/12/11 21:50
 * @description 单例管理用户信息
 */
public class UserManager {

    private static UserManager mInstance;
    private User mUser;

    public static UserManager getInstance() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new UserManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 保存用户信息到内存
     */
    public void saveUser(User user) {
        mUser = user;
        saveLocal(user);
    }

    /**
     * 保存到数据库
     *
     * @param user
     */
    private void saveLocal(User user) {

    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public User getUser() {
        return mUser;
    }


    /**
     * 从数据库中获取用户信息
     *
     * @return
     */
    private User getLocal() {
        return null;
    }


    public boolean hasLogin() {
        return getUser() == null;
    }

    /**
     * 删除用户信息
     */
    public void removeUser() {
        mUser = null;
        removeLocal();
    }

    /**
     * 删除数据库登录信息
     */
    private void removeLocal() {

    }
}
