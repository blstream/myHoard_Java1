ALTER TABLE `Item` MODIFY COLUMN `owner` int(11) DEFAULT NULL;
ALTER TABLE `Collection` MODIFY COLUMN `owner` int(11) DEFAULT NULL;

SET SQL_SAFE_UPDATES = 0;
UPDATE `Item` SET `owner` = NULL;
UPDATE `Collection` SET `owner` = NULL;
SET SQL_SAFE_UPDATES = 1;

DELETE FROM `Item` where `owner` IS NULL OR `owner` = 0;
DELETE FROM `Collection` where `owner` IS NULL OR `owner` = 0;