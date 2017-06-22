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
            "htm_data/15/1612/2187678.html",
            "htm_data/15/1612/2187689.html",
            "htm_data/15/1612/2187691.html",
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
            "htm_data/15/1612/2187892.html",
            "htm_data/15/1612/2187904.html",
            "htm_data/15/1612/2190246.html",
            "htm_data/15/1612/2190250.html",
            "htm_data/15/1612/2190263.html",
            "htm_data/15/1612/2190280.html",
            "htm_data/15/1612/2190286.html",
            "htm_data/15/1612/2190288.html",
            "htm_data/15/1612/2190291.html",
            "htm_data/15/1612/2190293.html",
            "htm_data/15/1612/2190295.html",
            "htm_data/15/1612/2190297.html",
            "htm_data/15/1612/2190299.html",
            "htm_data/15/1612/2190325.html",
            "htm_data/15/1612/2190339.html",
            "htm_data/15/1612/2190342.html",
    };
    @Test
    public void testProcessor() throws MalformedURLException {
        MultiThreadBrowserMocker<String> m = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder().setThreadCount(6)
//                .setProxy("127.0.0.1",1080,"http")
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
        String res=m.post(new URL("http://www.rmdown.com/link.php"),param);
//        String res=m.get(new URL("http://www.rmdown.com/link.php?hash=163a394ed74eb4d6da918342da79aa2925592c0b5d5"));
        System.out.println(res);
    }
}