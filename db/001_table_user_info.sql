DROP TABLE user_info;
CREATE TABLE user_info
(
  user_id      INT PRIMARY KEY NOT NULL ,
  user_name    VARCHAR(32)     NOT NULL,
  pass_word    VARCHAR(255)    NOT NULL,
  created_date INTEGER DEFAULT current_date
);

INSERT into user_info (user_name,pass_word) VALUES ('cd','123');

SELECT * from user_info;


