package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;

/**
 * Created by Anthony on 2017/5/24.
 */
public class TorrentContentProcessor implements HttpEntityProcessor<Boolean> {
    @Override
    public Boolean process(HttpEntity entity,TorrentDTO torrentDTO) {

        return true;
    }
}
