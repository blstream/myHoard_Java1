ALTER TABLE `Collection` 
	ADD  `created_date_client` DATETIME NOT NULL AFTER `modified_date` ,
	ADD  `modified_date_client` DATETIME NOT NULL AFTER `created_date_client`;
	
UPDATE Collection SET created_date_client = NOW(), modified_date_client = NOW();
