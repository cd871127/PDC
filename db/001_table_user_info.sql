DROP TABLE user_info_tbl;
CREATE TABLE user_info_tbl
(
  user_id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  user_name    VARCHAR(32)     NOT NULL,
  pass_word    VARCHAR(255)    NOT NULL,
  user_type    VARCHAR(1)      NOT NULL, --'0' 管理员用户 '1' 普通用户 '2' 外部应用用户
  created_date INTEGER DEFAULT current_date
);

INSERT INTO user_info_tbl (user_id, user_name, pass_word, user_type) VALUES (1, 'cd', '123', '1');
INSERT INTO user_info_tbl (user_id, user_name, pass_word, user_type) VALUES (2, 'cd', '123', '1');

update user_info_tbl set created_date=0;

SELECT *
FROM user_info_tbl;

SELECT
  user_name AS name,
  pass_word AS age
FROM user_info_tbl;


SELECT count(1)
FROM user_info_tbl
WHERE user_name = 'cd' AND pass_word = '123';


