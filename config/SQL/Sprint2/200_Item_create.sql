CREATE TABLE IF NOT EXISTS `Item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `description` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `lat` decimal(11,7)  NOT NULL DEFAULT '0.0',
  `lng` decimal(11,7)  NOT NULL DEFAULT '0.0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `owner` varchar(50) COLLATE utf8_bin NOT NULL,
  `collection` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;