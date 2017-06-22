package com.anthony.torrent.util.process;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by chend on 2017/6/22.
 */
public class PostProcessor extends AbstractPageContentProcessor {


    @Override
    protected String parse(BufferedReader bufferedReader) throws IOException {
        String tmp;
        String hashCode = null;
        String prefix = "rmdown.com/link.php?hash=";
        while ((tmp = bufferedReader.readLine()) != null) {
            if (tmp.contains(prefix)) {
                int index1 = tmp.indexOf(prefix);
                tmp = tmp.substring(index1 + prefix.length());
                hashCode = tmp.substring(0, "17191ae3bf0995c143e25a79ebe3fc0c698278c45cb".length());
                break;
            }
        }
        return hashCode;
    }
}
