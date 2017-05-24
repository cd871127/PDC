package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;

import java.util.Map;

/**
 * Created by Anthony on 2017/5/23.
 */
public class DownloadPageProcessor implements HttpEntityProcessor<Map<String, String>> {
    @Override
    public Map<String, String> process(HttpEntity entity, TorrentDTO torrentDTO) {
        return null;
    }
}
