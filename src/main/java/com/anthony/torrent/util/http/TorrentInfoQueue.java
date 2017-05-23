package com.anthony.torrent.util.http;

import com.anthony.torrent.dto.TorrentDTO;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Anthony on 2017/5/23.
 * 保存序列，多个线程下载其中的内容
 */
@Component
public class TorrentInfoQueue {
    private ArrayBlockingQueue<TorrentDTO> abQueue;

    public TorrentDTO take() throws InterruptedException {
        return abQueue.take();
    }

}
