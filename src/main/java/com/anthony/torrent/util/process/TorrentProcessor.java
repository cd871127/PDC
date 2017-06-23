package com.anthony.torrent.util.process;

import com.anthony.browsermocker.processor.AbstractResponseProcessor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chend on 2017/6/23.
 */
public class TorrentProcessor extends AbstractResponseProcessor<String> {

    @Override
    public String process(CloseableHttpResponse response, Map param) {
        HttpEntity entity = response.getEntity();
        Map<String, String> paraMap = requestParam(param);
        if (null == paraMap || null == paraMap.get("ref"))
            return null;
        File file = new File("torrent\\"+paraMap.get("ref")+".torrent");
        FileOutputStream fos = null;
        try {
            File dir=new File("torrent");
            if(!dir.exists()) {
                dir.mkdir();
            }

            boolean flag;
            if (file.exists())
                return null;
            else
                flag = file.createNewFile();
            if (!flag)
                return null;
            fos = new FileOutputStream(file);
            entity.writeTo(fos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "OK";
    }


}
