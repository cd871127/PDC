package com.anthony.config;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
public class SystemConfigParameter {
    private static SystemConfigParameter instance = new SystemConfigParameter();
    private Integer tokenExpireTime = 30;//用户登录验证的token过期时间,单位:分
    private String caoLiuBaseUrl = "http://t66y.com/";  //草榴的基础地址
    private String torrentBaseUrl = "http://www.rmdown.com/"; //种子的基础地址
    private Integer downloadListSize = 3; //下载队列大小
    private Integer downloadThreadCount = 3;

    private SystemConfigParameter() {
    }

    public static SystemConfigParameter getInstance() {
        return instance;
    }

    public Integer getDownloadListSize() {
        return downloadListSize;
    }

    public Integer getDownloadThreadCount() {
        return downloadThreadCount;
    }

    public Integer getTokenExpireTime() {
        return tokenExpireTime;
    }

    public String getCaoLiuBaseUrl() {
        return caoLiuBaseUrl;
    }

    public String getTorrentBaseUrl() {
        return torrentBaseUrl;
    }

    public SystemConfigParameter setTokenExpireTime(Integer tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
        return instance;
    }

    public SystemConfigParameter setCaoLiuBaseUrl(String caoLiuBaseUrl) {
        this.caoLiuBaseUrl = caoLiuBaseUrl;
        return instance;
    }

    public SystemConfigParameter setTorrentBaseUrl(String torrentBaseUrl) {
        this.torrentBaseUrl = torrentBaseUrl;
        return instance;
    }

    public SystemConfigParameter setDownloadListSize(Integer downloadListSize) {
        this.downloadListSize = downloadListSize;
        return instance;
    }

    public SystemConfigParameter setDownloadThreadCount(Integer downloadThreadCount) {
        this.downloadThreadCount = downloadThreadCount;
        return instance;
    }

}
