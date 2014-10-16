SET MODE MYSQL;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT 'username',
  `password` varchar(50) NOT NULL COMMENT 'password',
  `name` varchar(50) NOT NULL COMMENT 'name',
  `email` varchar(50) NOT NULL COMMENT 'email',
  `mobile` varchar(50) NOT NULL COMMENT 'mobile',
  `gender` int NOT NULL COMMENT 'gender',
  `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp not null DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);
