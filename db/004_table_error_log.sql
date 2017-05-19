CREATE TABLE error_log_tbl
(
  error_code   VARCHAR(32) PRIMARY KEY NOT NULL,
  error_desc   VARCHAR(32)             NOT NULL,
  error_info   VARCHAR(32)             NOT NULL,
  created_date INTEGER DEFAULT current_date
);