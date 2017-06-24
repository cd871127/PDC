package com.anthony.torrent.controller;

import com.anthony.torrent.service.TorrentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chend on 2017/6/24.
 */
@RestController("torrentController")
@RequestMapping("/torrent")
public class TorrentController {
    @Resource
    private TorrentService torrentService;

    @RequestMapping(value = "bookmark", method = RequestMethod.POST)
    public int importBookMark(@RequestParam MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();

        return torrentService.importTorrentBookMark(in);
//        return 0;
    }

    @RequestMapping(value = "downloadTorrent", method = RequestMethod.GET)
    public int downloadTorrent(@RequestParam int count) {
        return torrentService.downloadTorrent(count);
    }
}
