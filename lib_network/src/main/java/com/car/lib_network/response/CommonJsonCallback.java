package com.car.lib_network.response;

import javax.security.auth.callback.Callback;

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

//    public CommonJsonCallback(Dishea)
}
