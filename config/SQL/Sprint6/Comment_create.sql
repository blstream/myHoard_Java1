CREATE TABLE `Comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(160) COLLATE utf8_bin NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_date_client` datetime DEFAULT NULL,
  `modified_date_client` datetime DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  `collection` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1;