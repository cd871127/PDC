DROP TABLE user_info;
CREATE TABLE user_info
(
  user_id      INT PRIMARY KEY NOT NULL,
  user_name    VARCHAR(32)     NOT NULL,
  pass_word    VARCHAR(255)    NOT NULL,
  user_type    VARCHAR(1)      NOT NULL,--'0' 管理员用户 '1' 普通用户 '2' 外部应用用户
  created_date INTEGER DEFAULT current_date
);

INSERT INTO user_info (user_id,user_name, pass_word,user_type) VALUES (1,'cd', '123','1');

SELECT *
FROM user_info;

SELECT user_name as  name,pass_word as age from user_info


