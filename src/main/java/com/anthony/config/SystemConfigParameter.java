package com.anthony.config;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
public class SystemConfigParameter {
    private static SystemConfigParameter instance = new SystemConfigParameter();
    private SystemConfigParameter (){}
    public static SystemConfigParameter getInstance() {
        return instance;
    }

    private Integer tokenExpireTime=30;//用户登录验证的token过期时间,单位:分



    public Integer getTokenExpireTime() {
        return tokenExpireTime;
    }

    public SystemConfigParameter setTokenExpireTime(Integer tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
        return instance;
    }
}
