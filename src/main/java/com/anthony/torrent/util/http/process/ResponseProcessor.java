package com.anthony.torrent.util.http.process;

import org.apache.http.HttpEntity;

import java.io.InputStream;


/**
 * Created by Anthony on 2017/5/23.
 */
public interface ResponseProcessor {
    Object process(HttpEntity entity);
}
