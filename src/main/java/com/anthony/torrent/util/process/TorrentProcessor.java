package com.anthony.torrent.util.process;

import com.anthony.browsermocker.processor.AbstractResponseProcessor;
import com.anthony.torrent.dao.TorrentDAO;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chend on 2017/6/23.
 */
@Component
public class TorrentProcessor extends AbstractResponseProcessor<String> {

    @Resource
    TorrentDAO torrentDAO;

    @Override
    public String process(CloseableHttpResponse response, Map param) {
        if (200 != response.getStatusLine().getStatusCode())
            return null;
        HttpEntity entity = response.getEntity();
        Map<String, String> paraMap = requestParam(param);
        String downloadDir = "torrent";
        if (null == paraMap || null == paraMap.get("ref"))
            return null;
        String fileName = torrentDAO.queryTitleByHashCode(paraMap.get("ref"));
        String regEx = "[/\\*:?\"<>|]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(fileName);
        fileName = m.replaceAll(" ").trim();
        File file = new File(downloadDir + "\\" + fileName + ".torrent");
        FileOutputStream fos = null;
        try {
            boolean flag = true;
            File dir = new File(downloadDir);

            if (!dir.exists()) {
                flag = dir.mkdir();
            }
            if (!flag)
                return null;

            if (file.exists())
                return null;
            else
                flag = file.createNewFile();
            if (!flag)
                return null;
            fos = new FileOutputStream(file);
            entity.writeTo(fos);

        } catch (IOException e) {
            System.out.println(fileName);
            file.delete();
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
