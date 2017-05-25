package com.anthony.torrent.util.parse;

/**
 * Created by Anthony on 2017/5/25.
 */
public class ParseUtil {
    public static String getMiddleStr(String str, String startStr, String endStr) {
        int index1 = str.indexOf(startStr);
        int index2 = str.indexOf(endStr);
        if (-1 == index1 || -1 == index2)
            return null;
        return str.substring(index1 + startStr.length(), index2);
    }
}
