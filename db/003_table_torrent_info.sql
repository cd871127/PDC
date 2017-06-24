DROP TABLE torrent_info_tbl;
CREATE TABLE torrent_info_tbl
(
  post_url     VARCHAR(1024) PRIMARY KEY NOT NULL,
  user_id      INTEGER REFERENCES user_info_tbl (user_id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
  hash_code    VARCHAR(1024),
  title        VARCHAR(1024)             NOT NULL,
  status       INTEGER DEFAULT 0 NOT NULL, --'0' 未下载 '1' 已下载post并且获得hashCode '2' 下载downloadpage 3 完成 -1 异常
  is_download  INTEGER DEFAULT 0 NOT NULL, --'0' 用户未下载 '1' 用户已下载
  updated_date INTEGER DEFAULT current_date,
  created_date INTEGER DEFAULT current_date
);


CREATE TRIGGER torrent_info_ai_trg
  AFTER
  UPDATE
  ON torrent_info_tbl
BEGIN
  UPDATE torrent_info_tbl
  SET updated_date = current_date
  WHERE post_url = new.post_url;
END;

UPDATE torrent_info_tbl
SET updated_date = 0, hash_code = '1'
WHERE post_url = 'test';
SELECT *
FROM torrent_info_tbl
WHERE post_url = 'test';
COMMIT;

DELETE FROM torrent_info_tbl;

INSERT INTO torrent_info_tbl (post_url, user_id, hash_code, title) SELECT
                                                                     'test',
                                                                     55,
                                                                     '2',
                                                                     '3';

INSERT INTO torrent_info_tbl (post_url, hash_code, title) SELECT
                                                            'test',
                                                            '2',
                                                            '3'
                                                          UNION ALL SELECT
                                                                      '4',
                                                                      '4',
                                                                      '4';
