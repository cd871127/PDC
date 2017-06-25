package com.anthony.torrent.dao;

import com.anthony.torrent.dto.TorrentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Anthony on 2017/5/19.
 */
@Mapper
@Repository
public interface TorrentDAO {
    int addTorrentInfo(List torrentDTOS);

    int updateTorrentInfo(TorrentDTO torrentDTO);

    List<TorrentDTO> queryTorrentDTODownloadList(Map<String, String> param);

    int updateTorrentInfoStatus(Map param);

    String queryTitleByHashCode(String hashCode);


}
