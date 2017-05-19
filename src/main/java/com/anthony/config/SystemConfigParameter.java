package com.anthony.config;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
public class SystemConfigParameter {
    private static SystemConfigParameter instance = new SystemConfigParameter();
    private Integer tokenExpireTime = 30;//用户登录验证的token过期时间,单位:分
    private String calLiuBaseUrl="www.caoliu.com";  //草榴的基础地址
    private String torrentBaseUrl="www.yahaha.com"; //种子的基础地址

    private SystemConfigParameter() {
    }

    public static SystemConfigParameter getInstance() {
        return instance;
    }

    public Integer getTokenExpireTime() {
        return tokenExpireTime;
    }

    public String getCalLiuBaseUrl() {
        return calLiuBaseUrl;
    }

    public String getTorrentBaseUrl() {
        return torrentBaseUrl;
    }

    public SystemConfigParameter setTokenExpireTime(Integer tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
        return instance;
    }

    public SystemConfigParameter setCalLiuBaseUrl(String calLiuBaseUrl) {
        this.calLiuBaseUrl = calLiuBaseUrl;
        return instance;
    }

    public SystemConfigParameter setTorrentBaseUrl(String torrentBaseUrl) {
        this.torrentBaseUrl = torrentBaseUrl;
        return instance;
    }
}
