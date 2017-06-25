package com.anthony.torrent.service;

import com.anthony.torrent.dao.TorrentDAO;
import com.anthony.torrent.dto.TorrentDTO;
import com.anthony.torrent.util.http.TorrentInfoQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Anthony on 2017/5/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TorrentServiceTest {

    @Resource
    TorrentService torrentService;
    @Resource
    private TorrentDAO torrentDAO;

    @Resource
    private TorrentInfoQueue queue;

    private InputStream in;

    @Before
    public void setUp() throws Exception {
//        File file = new File("C:\\Users\\Anthony\\Desktop\\Bookmark.html");
//        in = new FileInputStream(file);
    }

    @After
    public void tearDown() throws Exception {
//        in.close();
    }

    @Test
    public void importTorrentBookMark() throws Exception {

        List<TorrentDTO> list = torrentDAO.queryTorrentDTODownloadList(new HashMap<String, String>());

//        torrentService.downloadTorrent();
//        System.out.println(queue.size());
//        System.out.println(torrentService.importTorrentBookMark(in));
//        Map m=new HashMap();
//        m.put("status","0");
//        List l=torrentService.queryTorrentDTODownloadList(m);
//        System.out.println(l.size());
//        l.forEach(System.out::println);
    }

    @Test
    public void test3()
    {
        Set<String> s=new HashSet<>();
        s.add("htm_data/15/1706/2472214.html");
        s.add("htm_data/15/1706/2470763.html");
        s.add("htm_data/2/1706/2466395.html");
        s.add("htm_data/15/1706/2466739.html");
        s.add("htm_data/15/1706/2466031.html");
        Map<String,Object> errorMap=new HashMap<>();
        errorMap.put("status","2");
        errorMap.put("urls",s);
        //log error url
        torrentDAO.updateTorrentInfoStatus(errorMap);
    }

}