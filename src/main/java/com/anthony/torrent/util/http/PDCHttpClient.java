package com.anthony.torrent.util.http;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by Anthony on 2017/5/23.1
 */

public class PDCHttpClient {
    public static final CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        HttpHost proxy = new HttpHost("127.0.0.1", 1080, "http");
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setSocketTimeout(10000).setConnectTimeout(5000).build();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler((exception, executionCount, context) -> {
                    if (executionCount > 3) {
                        return false;
                    }
                    return true;
                })
                .build();
    }

}
