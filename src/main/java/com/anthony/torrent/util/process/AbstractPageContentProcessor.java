package com.anthony.torrent.util.process;

import com.anthony.browsermocker.processor.HttpResponseProcessor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chend on 2017/6/22.
 */
public abstract class AbstractPageContentProcessor implements HttpResponseProcessor<String> {
    @Override
    public String process(CloseableHttpResponse response) {
        InputStream in = null;
        HttpEntity entity = response.getEntity();
        String res = null;
        try {
            in = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            res=parse(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    protected abstract String parse(BufferedReader bufferedReader) throws IOException;

}
