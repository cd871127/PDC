package com.anthony.torrent.dto;

/**
 * Created by Anthony on 2017/5/19.
 */
public class TorrentDTO {
    private String url;
    private String title;
    private String hashCode;
    private int status;
    private int isDownload;


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
}
