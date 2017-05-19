package com.anthony.torrent.service;

import com.anthony.torrent.dao.TorrentDAO;
import com.anthony.torrent.dto.TorrentDTO;
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

    private InputStream in;

    @Before
    public void setUp() throws Exception {
        File file = new File("C:\\Users\\Anthony\\Desktop\\Bookmark.html");
        in = new FileInputStream(file);
    }

    @After
    public void tearDown() throws Exception {
        in.close();
    }

    @Test
    public void importTorrentBookMark() throws Exception {
        System.out.println(torrentService.importTorrentBookMark(in));
    }
//    @Test
//    public void importTorrentBookMark2() throws Exception {
//
//        TorrentDTO torrentDTO=new TorrentDTO();
//        torrentDTO.setHashCode("hhhh");
//        torrentDTO.setIsDownload(1);
//        torrentDTO.setStatus(1);
//        torrentDTO.setTitle("22222");
//        torrentDTO.setUrl("test");
//
//        torrentDAO.updateTorrentInfo(torrentDTO);
//    }

}