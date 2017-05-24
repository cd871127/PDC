package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;

/**
 * Created by Anthony on 2017/5/23.
 */
public class PostPageProcessor implements HttpEntityProcessor<String> {
    @Override
    public String process(HttpEntity entity, TorrentDTO torrentDTO) {
        return null;
    }
}
