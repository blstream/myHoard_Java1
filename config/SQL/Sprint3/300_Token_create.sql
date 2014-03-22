CREATE TABLE IF NOT EXISTS `Token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(250) COLLATE utf8_bin NOT NULL,
  `refresh_token` varchar(250) COLLATE utf8_bin NOT NULL,
  `created_date` datetime NOT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1;