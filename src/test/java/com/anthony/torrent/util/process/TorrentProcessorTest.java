package com.anthony.torrent.util.process;

import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chend on 2017/6/23.
 */
public class TorrentProcessorTest {
    @Test
    public void process() throws Exception {
        HttpGet httpGet = new HttpGet("http://www.baidu.com/link.php?haha=11112&bbb=4444");
//        Map<String, Object> map = new HashMap<>();
//        map.put("httpRequestBase", httpGet);
//        map.put("method", "get");
//        TorrentProcessor torrentProcessor = new TorrentProcessor();
//        torrentProcessor.requestParam(map).forEach((k, v) -> System.out.println(k + ": " + v));
    }

}