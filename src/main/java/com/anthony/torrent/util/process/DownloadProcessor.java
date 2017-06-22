package com.anthony.torrent.util.process;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by chend on 2017/6/22.
 */
public class DownloadProcessor extends AbstractPageContentProcessor {


    @Override
    protected String parse(BufferedReader bufferedReader) throws IOException {
        String tmp;
        String res = null;
        String prefix = "NAME=\"reff\" value=\"";
        while ((tmp = bufferedReader.readLine()) != null) {
            if (!tmp.contains(prefix))
                continue;
            int index1 = tmp.indexOf(prefix);
            tmp = tmp.substring(index1 + prefix.length());
            res = tmp.substring(0, tmp.indexOf("\">"));
        }
        return res;
    }


}
