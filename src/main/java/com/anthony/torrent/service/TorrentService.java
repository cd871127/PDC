package com.anthony.torrent.service;

import com.anthony.config.SystemConfigParameter;
import com.anthony.torrent.dao.TorrentDAO;
import com.anthony.torrent.dto.TorrentDTO;
import com.anthony.torrent.util.http.DownloadTask;
import com.anthony.torrent.util.http.TorrentInfoQueue;
import com.anthony.torrent.util.parse.ParseBookMark;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Anthony on 2017/5/19.
 */
@Service
public class TorrentService {

    @Resource
    private TorrentDAO torrentDAO;

    @Resource
    private ParseBookMark parseBookMark;

    @Resource
    private DownloadTask downloadTask;

    @Resource
    private TorrentInfoQueue downloadQueue;

    public void downloadTorrent() {
        final int threadCount = SystemConfigParameter.getInstance().getDownloadThreadCount();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        LinkedList<Future<TorrentDTO>> futures = new LinkedList<>();


//        while (!downloadQueue.isEmpty()) {
            System.out.println(Thread.currentThread().getName()+": submit");
            futures.add(executor.submit(downloadTask));
//        }

        while (!futures.isEmpty()) {
            for (int i = 0; i != futures.size(); ++i) {
                Future<TorrentDTO> future = futures.get(i);
                if (future.isDone()) {
                    try {
                        TorrentDTO torrentDTO = future.get();
                        System.out.println(torrentDTO);
                        updateTorrentInfo(torrentDTO);
                        futures.remove(i);
                        break;
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        executor.shutdown();

    }

    public int updateTorrentInfo(TorrentDTO torrentDTO) {
        return torrentDAO.updateTorrentInfo(torrentDTO);
    }

    public void queryTorrentDTODownloadList(Map<String, String> param) {
        param.put("count", SystemConfigParameter.getInstance().getDownloadListSize().toString());
        param.putIfAbsent("status", "0");
        List<TorrentDTO> list = torrentDAO.queryTorrentDTODownloadList(param);

        for (TorrentDTO torrentDTO : list) {
            try {
                downloadQueue.put(torrentDTO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //导入书签
    //添加url到数据库
    public int importTorrentBookMark(InputStream in, String dirName) {
//        ParseBookMark parseBookMark = new ParseBookMark(dirName);
        parseBookMark.setDirName(dirName);
        List<TorrentDTO> torrentDTOS = parseBookMark.streamToList(in);
        if (null == torrentDTOS || torrentDTOS.isEmpty())
            //TODO file error
            return 0;
        int successCount = 0;
        int startIndex = 0;
        int delta = 500;
        //sqlite max insert 500 data
        long a = System.currentTimeMillis();
        while (startIndex < torrentDTOS.size()) {
            int endIndex = torrentDTOS.size() > startIndex + delta ? startIndex + delta : torrentDTOS.size();
            List tmp = torrentDTOS.subList(startIndex, endIndex);
            successCount += endIndex - startIndex;
            try {
                torrentDAO.addTorrentInfo(tmp);
            } catch (UncategorizedSQLException e) {
                //TODO log error
                successCount = 0;
                break;
            }
            startIndex += delta;

        }
        return successCount;
    }

    //over load   implement default parameter
    public int importTorrentBookMark(InputStream in) {
        return importTorrentBookMark(in, "wuhaha");
    }

}
