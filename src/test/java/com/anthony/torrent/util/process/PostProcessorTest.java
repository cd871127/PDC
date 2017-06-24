package com.anthony.torrent.util.process;

import com.anthony.browsermocker.mocker.MultiThreadBrowserMocker;
import com.anthony.browsermocker.processor.SimpleResponseProcessor;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by chend on 2017/6/22.
 */
public class PostProcessorTest {
    final public String[] u = {
            "htm_data/15/1612/2187667.html",
            "htm_data/15/1612/2187672.html",
            "htm_data/15/1612/2187673.html",
            "htm_data/15/1612/2187677.html",
            "htm_data/15/1612/2187678.html",
            "htm_data/15/1612/2187691.html",
            "htm_data/15/1612/2187689.html",
            "htm_data/15/1612/2187694.html",
            "htm_data/15/1612/2187695.html",
            "htm_data/15/1612/2187698.html",
            "htm_data/15/1612/2187702.html",
            "htm_data/15/1612/2187727.html",
            "htm_data/15/1612/2187730.html",
            "htm_data/15/1612/2187733.html",
            "htm_data/15/1612/2187742.html",
            "htm_data/15/1612/2187748.html",
            "htm_data/15/1612/2187764.html",
            "htm_data/15/1612/2187774.html",
            "htm_data/15/1612/2187780.html",
            "htm_data/15/1612/2187802.html",
            "htm_data/15/1612/2187814.html",
            "htm_data/15/1612/2187822.html",
            "htm_data/15/1612/2187824.html",
            "htm_data/15/1612/2187825.html",
            "htm_data/15/1612/2187829.html",
            "htm_data/15/1612/2187892.html"

    };

    @Test
    public void testProcessor() throws MalformedURLException {
        MultiThreadBrowserMocker<String> m = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(6)
                .setProxy("127.0.0.1", 1080, "http")
                .setProcessor(new PostProcessor()).build();
        Map<String, URL> urlMap = new HashMap<>();
        String baseStr = "http://t66y.com/";

        for (String a : u) {
            urlMap.put(a, new URL(baseStr + a));
        }

        Map<String, String> mm = m.get(urlMap);


        MultiThreadBrowserMocker<String> m2 = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(6)
                .setProxy("127.0.0.1", 1080, "http")
                .setProcessor(new DownloadProcessor()).build();

        String torrentStr = "http://www.rmdown.com/link.php";

        Map<String, String> param = new HashMap<>();
        param.put("hash", "163a394ed74eb4d6da918342da79aa2925592c0b5d5");

        Map<String, URL> urls = new HashMap<>();
        Map<String, Map<String, String>> param2 = new HashMap<>();


        for (Map.Entry<String, String> e : mm.entrySet()) {
            if (e.getValue() != null) {
                urls.put(e.getKey(), new URL(torrentStr));
                Map<String, String> p = new HashMap<>();
                p.put("hash", e.getValue());
                param2.put(e.getKey(), p);
            }
        }

        Map<String, String> res = m2.get(urls, param2);
        res.forEach((k, v) -> System.out.println(v));
        System.out.println(u.length+": "+res.size());
        Set<String> origin=urlMap.keySet();
        Set<String> done=res.keySet();
        System.out.println(origin.size());
        System.out.println(done.size());
        System.out.println(u.length);
//        System.out.println(origin.removeAll(done));
//        System.out.println(origin.);
    }

    @Test
    public void testProcessor2() throws MalformedURLException {
        MultiThreadBrowserMocker<String> m = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(6)
                .setProxy("127.0.0.1", 1080, "http")
                .setProcessor(new DownloadProcessor()).build();
//        163197f48c11a32100f22fd5b64f02a50f4470693fe
//        1639b29ac48e937b4c19a2d4e4368f5cffabe294f50
//        163a394ed74eb4d6da918342da79aa2925592c0b5d5
//        1636c66a91c555d4225ed132053ba440a00ec7c65a5
        String torrentStr = "http://www.rmdown.com/link.php";

        Map<String, String> param = new HashMap<>();
        param.put("hash", "163a394ed74eb4d6da918342da79aa2925592c0b5d5");

        String res = m.get(new URL(torrentStr), param);
//        String res=m.get(new URL("http://www.rmdown.com/link.php?hash=163a394ed74eb4d6da918342da79aa2925592c0b5d5"));
        System.out.println(res);
    }

    @Test
    public void testProcessor3() throws Exception {
        MultiThreadBrowserMocker<String> m = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(11)
                .setProcessor(new SimpleResponseProcessor()).build();
        URL url = new URL("http://www.baidu.com");
        Map<String, URL> map = new HashMap<>();
        for (int i = 0; i != 100; ++i) {
            map.put(String.valueOf(i), url);
        }
        long start = System.currentTimeMillis();
        m.get(map);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test4()
    {
        List<Integer> list=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        for(int i=0;i!=10;++i)
            list.add(i);
        list.forEach((v)->list2.add(v));
        System.out.println(list2);
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
