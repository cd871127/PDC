package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Anthony on 2017/5/23.
 */
public class PostPageProcessor implements HttpEntityProcessor<String> {
    @Override
    public String process(HttpEntity entity, TorrentDTO torrentDTO) {
        System.out.println(Thread.currentThread().getName() + " " + this.getClass().getName());
        String hashCode = null;
        InputStream in = null;
        try {
            in = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String tmp = "";
            String prefix = "rmdown.com/link.php?hash=";
            while ((tmp = bufferedReader.readLine()) != null) {
                if (!tmp.contains(prefix))
                    continue;
                int index1 = tmp.indexOf(prefix);
                tmp = tmp.substring(index1 + prefix.length());
                hashCode = tmp.substring(0, "17191ae3bf0995c143e25a79ebe3fc0c698278c45cb".length());
                HashMap<String, String> map = new HashMap<>();
                map.put("hash", hashCode);
                torrentDTO.setParam(map);
                torrentDTO.setHashCode(hashCode);
                torrentDTO.setStatus(torrentDTO.getStatus() + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            torrentDTO.setStatus(-1);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                torrentDTO.setStatus(-1);
            }
        }
        System.out.println(hashCode);
        return hashCode;
    }
}
