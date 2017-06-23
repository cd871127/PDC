package com.anthony.torrent.util.process;

import com.anthony.browsermocker.mocker.MultiThreadBrowserMocker;
import com.anthony.browsermocker.mocker.SimpleBrowserMocker;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chend on 2017/6/22.
 */
public class DownloadProcessor extends AbstractPageContentProcessor {

    @Override
    public String process(CloseableHttpResponse response, Map param)
    {
        Map<String,String> paraMap=requestParam(param);
        String hashCode=paraMap.get("hash");
        String reff=super.process(response,param);
//        reff=reff.replace("=","");
        paraMap.remove("hash");
        paraMap.put("ref",hashCode);
        paraMap.put("reff",reff);

//        Map<String,String> headers=new HashMap<>();
//        headers.put("Upgrade-Insecure-Requests", "1");
//        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        SimpleBrowserMocker<String> simpleBrowserMocker = (SimpleBrowserMocker<String>) SimpleBrowserMocker.<String>builder()
                .setProxy("127.0.0.1",1080,"http")
//                .setHeaders(headers)
                .setProcessor(new TorrentProcessor()).build();
        URL url=null;
        try {
            url=new URL("http://www.rmdown.com/download.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return simpleBrowserMocker.get(url,paraMap);
    }

    @Override
    protected String parse(BufferedReader bufferedReader) throws IOException {
        String tmp;
        String res = null;
        String prefix = "NAME=\"reff\" value=\"";
        while ((tmp = bufferedReader.readLine()) != null) {
            if (!tmp.contains(prefix))
                continue;
            int index1 = tmp.indexOf(prefix);
            tmp = tmp.substring(index1 + prefix.length());
            res = tmp.substring(0, tmp.indexOf("\">"));
        }
        return res;
    }


}
