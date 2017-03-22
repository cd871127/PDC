CREATE TABLE user_info
(
  user_name    VARCHAR(32) PRIMARY KEY NOT NULL,
  pass_word    VARCHAR(255)            NOT NULL,
  created_date INTEGER DEFAULT current_date
);
