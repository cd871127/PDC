package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthony on 2017/5/23.
 */
public class DownloadPageProcessor implements HttpEntityProcessor<Map<String, String>> {
    @Override
    public Map<String, String> process(HttpEntity entity, TorrentDTO torrentDTO) {
        System.out.println(Thread.currentThread().getName() + " " + this.getClass().getName());
        InputStream in = null;
        HashMap<String, String> map = new HashMap<>();
        try {
            in = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String tmp;
            String prefix = "NAME=\"reff\" value=\"";
            while ((tmp = bufferedReader.readLine()) != null) {
                if (!tmp.contains(prefix))
                    continue;
                int index1 = tmp.indexOf(prefix);
                tmp = tmp.substring(index1 + prefix.length());
                tmp = tmp.substring(0, tmp.indexOf("\">"));
                map.put("ref", torrentDTO.getHashCode());
                map.put("reff", tmp);
                torrentDTO.setStatus(torrentDTO.getStatus() + 1);
                torrentDTO.setParam(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
            torrentDTO.setStatus(-1);
            map = null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
