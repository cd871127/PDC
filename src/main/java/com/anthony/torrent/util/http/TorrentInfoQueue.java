package com.anthony.torrent.util.http;

import com.anthony.config.SystemConfigParameter;
import com.anthony.torrent.dto.TorrentDTO;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Anthony on 2017/5/23.
 * 保存序列，多个线程下载其中的内容
 */
@Component
public class TorrentInfoQueue {

    private ArrayBlockingQueue<TorrentDTO> queue = new ArrayBlockingQueue<>(SystemConfigParameter.getInstance().getDownloadListSize());

    public TorrentDTO take() throws InterruptedException {
        return queue.take();
    }

    public void put(TorrentDTO torrentDTO) throws InterruptedException {
        queue.put(torrentDTO);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

}
