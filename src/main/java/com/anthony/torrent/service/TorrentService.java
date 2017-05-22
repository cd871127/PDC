package com.anthony.torrent.service;

import com.anthony.torrent.dao.TorrentDAO;
import com.anthony.torrent.dto.TorrentDTO;
import com.anthony.torrent.util.parse.ParseBookMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Anthony on 2017/5/19.
 */
@Service
public class TorrentService {

    @Resource
    private TorrentDAO torrentDAO;

    @Autowired
    ParseBookMark parseBookMark;

    //导入书签
    //添加url到数据库
    public int importTorrentBookMark(InputStream in, String dirName) {
//        ParseBookMark parseBookMark = new ParseBookMark(dirName);
        parseBookMark.setDirName(dirName);
        List<TorrentDTO> torrentDTOS = parseBookMark.streamToList(in);
        if(null==torrentDTOS||torrentDTOS.isEmpty())
            //TODO file error
            return 0;
        int successCount = 0;
        int startIndex = 0;
        int delta = 500;
        //sqlite max insert 500 data
        long a=System.currentTimeMillis();
        while (startIndex < torrentDTOS.size()) {
            int endIndex = torrentDTOS.size() > startIndex + delta ? startIndex + delta : torrentDTOS.size();
            List tmp = torrentDTOS.subList(startIndex, endIndex);
            successCount += endIndex - startIndex;
            torrentDAO.addTorrentInfo(tmp);
            startIndex += delta;
        }
        return successCount;
    }

    //over load   implement default parameter
    public int importTorrentBookMark(InputStream in) {
        return importTorrentBookMark(in, "wuhaha");
    }

}
