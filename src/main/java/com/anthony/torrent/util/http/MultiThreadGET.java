package com.anthony.torrent.util.http;

import com.anthony.torrent.service.TorrentService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by chend on 2017/5/20.
 * 具体的下载逻辑
 */
@Component
public abstract class MultiThreadGET {

    @Resource
    TorrentInfoQueue torrentInfoQueue;



    @Resource
    TorrentService torrentService;

    public void run() throws Exception{
        HttpContext context= HttpClientContext.create();
        HttpGet httpGet=new HttpGet();
        CloseableHttpResponse response = PDCHttpClient.httpClient.execute(httpGet, context);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream in = entity.getContent();

                in.close();
        }
        response.close();
    }


}
