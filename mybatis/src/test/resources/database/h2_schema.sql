SET MODE MYSQL;

-- --------------------------------------------------------


CREATE TABLE IF NOT EXISTS shop (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  shop_name varchar(50) NOT NULL DEFAULT '',
  city varchar(50) NOT NULL DEFAULT '',
  create_time timestamp NOT NULL DEFAULT 0,
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);