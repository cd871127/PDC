package com.anthony.torrent.service;

import com.anthony.browsermocker.mocker.MultiThreadBrowserMocker;
import com.anthony.config.SystemConfigParameter;
import com.anthony.torrent.dao.TorrentDAO;
import com.anthony.torrent.dto.TorrentDTO;
import com.anthony.torrent.util.http.DownloadTask;
import com.anthony.torrent.util.http.TorrentInfoQueue;
import com.anthony.torrent.util.parse.ParseBookMark;
import com.anthony.torrent.util.process.DownloadProcessor;
import com.anthony.torrent.util.process.PostProcessor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
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

    @Resource
    private PostProcessor postProcessor;

    @Resource
    private DownloadProcessor downloadProcessor;

    public int downloadTorrent(int count) {
        Map<String, String> param = new HashMap<>();
        param.put("status", "0");
        param.put("count", String.valueOf(count));
        List<TorrentDTO> torrentList = torrentDAO.queryTorrentDTODownloadList(param);
        System.out.println(torrentList.get(0));
        //get hashcode
        MultiThreadBrowserMocker<String> postMocker = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder()
                .setThreadCount(SystemConfigParameter.getInstance().getDownloadThreadCount())
                .setProxy("127.0.0.1", 1080, "http")
                .setRetryCount(3)
                .setProcessor(postProcessor).build();
        Map<String, URL> urlMap = new HashMap<>();
        String url;
        final String baseUrl = SystemConfigParameter.getInstance().getCaoLiuBaseUrl();
        for (TorrentDTO dto : torrentList) {
            url = dto.getUrl();
            try {
                urlMap.put(url, new URL(baseUrl + url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        //get post content
        Map<String, String> hashMap = postMocker.get(urlMap);
        Set<String> errorSet = new HashSet<>();

        //cache error url
        hashMap.forEach((k, v) -> {
            System.out.println(k+": "+v);
                    if (v == null)
                        errorSet.add(k);
                }
        );
        //remove error post from result
        errorSet.forEach(hashMap::remove);

        TorrentDTO torrentDTO = new TorrentDTO();
        //update hashCode
        hashMap.forEach((k, v) ->
        {
            torrentDTO.setUrl(k);
            torrentDTO.setHashCode(v);
            torrentDAO.updateTorrentInfo(torrentDTO);
        });


        MultiThreadBrowserMocker<String> m2 = (MultiThreadBrowserMocker<String>) MultiThreadBrowserMocker.<String>builder()
                .setThreadCount(SystemConfigParameter.getInstance().getDownloadThreadCount())
                .setProxy("127.0.0.1", 1080, "http")
                .setRetryCount(3)
                .setProcessor(downloadProcessor).build();


        String torrentUrl = SystemConfigParameter.getInstance().getTorrentBaseUrl() + "link.php";
        Map<String, URL> torrentUrls = new HashMap<>();
        Map<String, Map<String, String>> torrentParam = new HashMap<>();

        for (Map.Entry<String, String> e : hashMap.entrySet()) {
            if (e.getValue() != null) {
                try {
                    torrentUrls.put(e.getKey(), new URL(torrentUrl));
                    Map<String, String> tmp = new HashMap<>();
                    tmp.put("hash", e.getValue());
                    torrentParam.put(e.getKey(), tmp);
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        }

        Map<String, String> resMap = m2.get(torrentUrls, torrentParam);

        //cache error
        resMap.forEach((k, v) -> {
                    if (v == null)
                        errorSet.add(k);
                }
        );
        errorSet.forEach(resMap::remove);
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", "2");
        errorMap.put("urls", errorSet);
        if(!errorSet.isEmpty())
        //log error url
            torrentDAO.updateTorrentInfoStatus(errorMap);

        //log success url
        Map<String, Object> successMap = new HashMap<>();
        successMap.put("status", "1");
        successMap.put("urls", resMap.keySet());
        if(!resMap.isEmpty())
            torrentDAO.updateTorrentInfoStatus(successMap);

        return resMap.size();
    }

    public void downloadTorrent() {
        final int threadCount = SystemConfigParameter.getInstance().getDownloadThreadCount();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        LinkedList<Future<TorrentDTO>> futures = new LinkedList<>();
//        while (!downloadQueue.isEmpty()) {
        System.out.println(Thread.currentThread().getName() + ": submit");
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

        //distinct
        Set<String> urlSet = new HashSet<>();
        Set<TorrentDTO> repetitionSet = new HashSet<>();
        for (TorrentDTO dto : torrentDTOS) {
            if (urlSet.contains(dto.getUrl()))
                repetitionSet.add(dto);
            else
                urlSet.add(dto.getUrl());
        }
        torrentDTOS.removeAll(repetitionSet);

        int successCount = 0;
        int startIndex = 0;
        int delta = 500;
        //sqlite max insert 500 data
        while (startIndex < torrentDTOS.size()) {
            int endIndex = torrentDTOS.size() > startIndex + delta ? startIndex + delta : torrentDTOS.size();
            List tmp = torrentDTOS.subList(startIndex, endIndex);
            successCount += endIndex - startIndex;
            try {
                torrentDAO.addTorrentInfo(tmp);
            } catch (UncategorizedSQLException e) {
                //TODO log error
                successCount = 0;
                e.printStackTrace();
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
