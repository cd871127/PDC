package com.anthony.torrent.util.process;

import com.anthony.browsermocker.mocker.MultiThreadBrowserMocker;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chend on 2017/6/22.
 */
public class PostProcessorTest {
    final public String[] u = {
            "htm_data/15/1612/2187667.html",
            "htm_data/15/1612/2187672.html",
            "htm_data/15/1612/2187673.html",
            "htm_data/15/1612/2187677.html",
    };
    @Test
    public void testProcessor() throws MalformedURLException {
        MultiThreadBrowserMocker<String> m = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(6)
                .setProxy("127.0.0.1",1080,"http")
                .setProcessor(new PostProcessor()).build();
        Map<String, URL> urlMap = new HashMap<>();
        String baseStr = "http://t66y.com/";

        for(String a:u) {
            urlMap.put(a, new URL(baseStr+a));
        }

        Map<String,String> mm=m.get(urlMap);
        mm.forEach((k,v)-> System.out.println(v));

    }

    @Test
    public void testProcessor2() throws MalformedURLException {
        MultiThreadBrowserMocker<String> m = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(6)
                .setProxy("127.0.0.1",1080,"http")
                .setProcessor(new DownloadProcessor()).build();
//        163197f48c11a32100f22fd5b64f02a50f4470693fe
//        1639b29ac48e937b4c19a2d4e4368f5cffabe294f50
//        163a394ed74eb4d6da918342da79aa2925592c0b5d5
//        1636c66a91c555d4225ed132053ba440a00ec7c65a5
        String torrentStr="http://www.rmdown.com/link.php";

        Map<String,String> param=new HashMap<>();
        param.put("hash","163a394ed74eb4d6da918342da79aa2925592c0b5d5");

        String res=m.get(new URL("http://www.rmdown.com/link.php"),param);
//        String res=m.get(new URL("http://www.rmdown.com/link.php?hash=163a394ed74eb4d6da918342da79aa2925592c0b5d5"));
        System.out.println(res);
    }
}
//
//       if (2!=torrentDTO.getStatus()) {
//               httpGet.setHeader("Proxy-Connection", "keep-alive");
//               httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//               httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
//               httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//               }
//               httpGet.setHeader("Upgrade-Insecure-Requests", "1");
//               httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
