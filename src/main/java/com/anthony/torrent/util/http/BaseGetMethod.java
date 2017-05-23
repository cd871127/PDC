package com.anthony.torrent.util.http;

import com.anthony.config.SystemConfigParameter;
import com.anthony.torrent.dto.TorrentDTO;
import com.anthony.torrent.util.http.process.PostPageProcessor;
import com.anthony.torrent.util.http.process.ResponseProcessor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Anthony on 2017/5/23.
 */
@Component
public class BaseGetMethod implements Runnable {
    @Resource
    private TorrentInfoQueue torrentInfoQueue;

    public Object sendRequest(HttpGet httpGet, ResponseProcessor processor) {
        HttpContext context = HttpClientContext.create();
        CloseableHttpResponse response = null;
        Object res;

        try {
            response = PDCHttpClient.httpClient.execute(httpGet, context);
            res = processor.process(response.getEntity());
        } catch (IOException e) {
            res = null;
        } finally {
            if (null != response)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
        return res;
    }

    @Override
    public void run() {
        try {
            TorrentDTO torrentDTO = torrentInfoQueue.take();
            String originUrl = SystemConfigParameter.getInstance().getCaoLiuBaseUrl() + torrentDTO.getUrl();
            URL url = new URL(originUrl);
            HttpGet httpGet=new HttpGet(url.toString());
            Object res = sendRequest(httpGet, new PostPageProcessor());
            String hashCode=null;
            if(res instanceof java.lang.String)
                hashCode=(String) res;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }





    }
}
