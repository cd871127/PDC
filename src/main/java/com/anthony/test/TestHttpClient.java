package com.anthony.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created by chend on 2017/5/20.
 */
public class TestHttpClient {

    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpGet;

    public static void main(String[] args) throws IOException {
        TestHttpClient t = new TestHttpClient();
        t.get();
    }

    public TestHttpClient() {
        httpClient = HttpClients.createDefault();
        context = HttpClientContext.create();
        httpGet = new HttpGet("http://www.baidu.com/");
    }

    public void get() throws IOException {
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
//            InputStream instream = entity.getContent();
            System.out.println(response.getStatusLine().getStatusCode());
        }
        response.close();
    }
}
