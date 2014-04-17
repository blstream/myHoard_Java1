ALTER TABLE `Item` 
	ADD  `created_date_client` DATETIME NOT NULL AFTER `modified_date` ,
	ADD  `modified_date_client` DATETIME NOT NULL AFTER `created_date_client`;
	
UPDATE Item SET created_date_client = NOW(), modified_date_client = NOW();
