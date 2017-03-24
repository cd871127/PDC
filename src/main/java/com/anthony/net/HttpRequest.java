package com.anthony.net;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by chendong239 on 2017-03-24.
 */
public class HttpRequest {

    public String get() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://hq.sinajs.cn/list=sh000001,sh600284,sh603227";
        HttpGet httpGet = new HttpGet(url);
        // 依次是代理地址，代理端口号，协议类型
//        HttpHost proxy = new HttpHost("10.17.171.11", 8080, "http");
//
//        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy)
//                .setSocketTimeout(2000).setConnectTimeout(2000).build();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpGet.setConfig(requestConfig);

        String resultStr = null;

        try {
            CloseableHttpResponse result = httpClient.execute(httpGet);
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultStr = EntityUtils.toString(result.getEntity(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return resultStr;
    }


}
