package com.anthony.torrent.util.http.process;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.http.HttpEntity;

import java.io.*;

/**
 * Created by Anthony on 2017/5/24.
 */
public class TorrentContentProcessor implements HttpEntityProcessor<Boolean> {
    @Override
    public Boolean process(HttpEntity entity, TorrentDTO torrentDTO) {
        System.out.println(Thread.currentThread().getName() + " " + this.getClass().getName());
        File file = new File(torrentDTO.getHashCode() + ".torrent");
        InputStream in = null;
        FileWriter fw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            in = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                fw.write(tmp);
            }
            torrentDTO.setStatus(torrentDTO.getStatus() + 1);

        } catch (IOException e) {
            e.printStackTrace();
            torrentDTO.setStatus(-1);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
