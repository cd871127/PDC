package com.anthony.torrent.util.http.process;

/**
 * Created by Anthony on 2017/5/24.
 */
public class ProcessorFactory {
    public static PostPageProcessor createPostPageProcessor() {
        return new PostPageProcessor();
    }

    public static DownloadPageProcessor createDownloadPageProcessor() {
        return new DownloadPageProcessor();
    }

    public static TorrentContentProcessor createTorrentContentProcessor() {
        return new TorrentContentProcessor();
    }

}
