package com.anthony.torrent.dto;

import org.apache.http.client.methods.HttpGet;

import java.util.HashMap;

/**
 * Created by Anthony on 2017/5/19.
 */
public class TorrentDTO {
    private String url;
    private String title;
    private String hashCode;
    private int status;
    private int isDownload;
    private HashMap<String,String> param;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(int isDownload) {
        this.isDownload = isDownload;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "TorrentDTO{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", hashCode='" + hashCode + '\'' +
                ", status=" + status +
                ", isDownload=" + isDownload +
                '}';
//        return url;
    }
}
