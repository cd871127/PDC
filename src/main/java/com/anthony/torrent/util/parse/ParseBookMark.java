package com.anthony.torrent.util.parse;

import com.anthony.torrent.dto.TorrentDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 2017/5/19.
 */
@Component
public class ParseBookMark implements ParseFileUtil {

    private String dirName;

    public ParseBookMark() {
        this.dirName = "";
    }

    public ParseBookMark(String dirName) {
        setDirName(dirName);
    }

    @Override
    public List<TorrentDTO> streamToList(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        if (dirName.equals(""))
            //TODO not handle error
            return null;

        String startStr = "<DT><H3 FOLDED ADD_DATE";
        String endStr = "</H3>";
        String endFlag = "</DL><P>";
        String dataFlag = "<DT><A";

        ArrayList<TorrentDTO> postInfoList = new ArrayList<>();
        try {
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                if (tmp.contains(dirName)
                        && tmp.lastIndexOf(endStr) == tmp.length() - endStr.length()
                        && tmp.indexOf(startStr) == 0) {
                    break;
                }
            }

            while ((tmp = bufferedReader.readLine()) != null) {
                if (tmp.contains(endFlag))
                    break;
                if (tmp.contains(dataFlag)) {
                    String url = ParseUtil.getMiddleStr(tmp, "htm_data", "\" ADD_DATE");
                    String title = ParseUtil.getMiddleStr(tmp, "\">", "</A>");
                    if (null == url || null == title) {
                        //TODO log error

                        continue;
                    }
                    url = "htm_data" + url;
                    TorrentDTO torrentDTO = new TorrentDTO();
                    torrentDTO.setUrl(url);
                    torrentDTO.setTitle(title);
                    postInfoList.add(torrentDTO);
                }
            }
        } catch (IOException e) {
            //TODO log error
            e.printStackTrace();
            postInfoList = null;
        }

        return postInfoList;
    }



    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}
