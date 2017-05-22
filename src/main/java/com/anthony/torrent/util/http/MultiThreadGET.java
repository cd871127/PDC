package com.anthony.torrent.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;

import java.io.*;

/**
 * Created by chend on 2017/5/20.
 */
public class MultiThreadGET extends MultiThreadHttpRequest {

    private HttpGet httpGet;

    @Override
    public Object call() throws Exception {
        HttpContext context= HttpClientContext.create();
        CloseableHttpResponse response = getHttpClient().execute(httpGet, context);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();

            File file = new File(Thread.currentThread().getName());
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instream))) {
                String tmp;
                while ((tmp = bufferedReader.readLine()) != null) {
                    fw.write(tmp);
                }
                fw.close();
                instream.close();
            }
        }
        response.close();
        return null;
    }
}
