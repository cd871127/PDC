package com.anthony.torrent.util.http;

import com.anthony.config.SystemConfigParameter;
import com.anthony.torrent.dto.TorrentDTO;
import com.anthony.torrent.util.http.process.HttpEntityProcessor;
import com.anthony.torrent.util.http.process.ProcessorFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Anthony on 2017/5/24.
 * 1
 */
@Component
public class DownloadTask implements Callable<TorrentDTO> {

    @Resource
    private TorrentInfoQueue downloadQueue;

    @Override
    public TorrentDTO call() throws Exception {
        TorrentDTO torrentDTO = downloadQueue.take();
        while (torrentDTO.getStatus() < 4) {
            torrentDTO = sendRequest(torrentDTO);
        }
        return torrentDTO;
    }

    private TorrentDTO sendRequest(TorrentDTO torrentDTO) {
        HttpContext context = HttpClientContext.create();
        CloseableHttpResponse response;
        Map map = createParam(torrentDTO);
        if (null == map) {
            torrentDTO.setStatus(-1);
            return torrentDTO;
        }
        HttpGet httpGet = (HttpGet) map.get("httpget");
        HttpEntityProcessor processor = (HttpEntityProcessor) map.get("processor");
        try {
            response = PDCHttpClient.httpClient.execute(httpGet, context);

            if (200 != response.getStatusLine().getStatusCode()) {
                torrentDTO.setStatus(-1);
                return torrentDTO;
            }
            processor.process(response.getEntity(), torrentDTO);
            response.close();
        } catch (IOException e) {
            //TODO log torrentDTO
            e.printStackTrace();
            torrentDTO.setStatus(-1);
        }
        return torrentDTO;
    }

    private Map<String, Object> createParam(TorrentDTO torrentDTO) {
        Map<String, Object> resMap = new HashMap<>();
        HttpEntityProcessor httpEntityProcessor;
        String url = null;
        switch (torrentDTO.getStatus()) {
            case 1:
                httpEntityProcessor = ProcessorFactory.createPostPageProcessor();
                url = SystemConfigParameter.getInstance().getCaoLiuBaseUrl() + torrentDTO.getUrl();
                break;
            case 2:
                httpEntityProcessor = ProcessorFactory.createDownloadPageProcessor();
                url = SystemConfigParameter.getInstance().getTorrentBaseUrl() + "link.php";
                break;
            case 3:
                httpEntityProcessor = ProcessorFactory.createTorrentContentProcessor();
                url = SystemConfigParameter.getInstance().getTorrentBaseUrl() + "download.php";
                break;
            default:
                httpEntityProcessor = null;
        }
        if (null == httpEntityProcessor)
            return null;

        url += assembleParameter(torrentDTO.getParam());
        HttpGet httpGet = new HttpGet(url);
        resMap.put("processor", httpEntityProcessor);
        resMap.put("httpget", httpGet);

        return resMap;
    }

    private String assembleParameter(Map<String, String> param) {
        StringBuilder paramStr = null;
        if (null != param) {
            paramStr = new StringBuilder("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (null != v)
                    paramStr.append(k).append("=").append(v).append("&");
            }
            paramStr = new StringBuilder(paramStr.substring(0, paramStr.length() - 1));
        }
        return null == paramStr ? null : paramStr.toString();
    }
}
