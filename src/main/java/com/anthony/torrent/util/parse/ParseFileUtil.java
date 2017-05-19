package com.anthony.torrent.util.parse;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Anthony on 2017/5/19.
 */
public interface ParseFileUtil {
    List<?> streamToList(InputStream in);
}
