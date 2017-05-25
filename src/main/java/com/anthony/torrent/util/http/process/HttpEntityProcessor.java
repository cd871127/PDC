package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;


/**
 * Created by Anthony on 2017/5/23.
 */
public interface HttpEntityProcessor<T> {
    T process(HttpEntity entity, TorrentDTO torrentDTO);
}
