-- 25.03.2022 Creation of user table
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `height` int DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `activity_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

-- Creation of authority table
CREATE TABLE `prepit_test`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

-- Bridge between tables
  CREATE TABLE `prepit_test`.`user_authority` (
  `user_id` INT NOT NULL,
  `authority_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `authority_id`));


-- populate tables
INSERT INTO `prepit_test`.`user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `age`, `weight`, `height`, `gender`) VALUES ('1', 'ewald', '$2a$10$jOVHZFg9GmggtQRK6GpAwutl.ay5q2pHi4Y0aRo4vxmHsl2APGx4q', 'berla.ewald30@gmail.com', 'Ewald', 'Berla', '22', '87', '180', 'male');
INSERT INTO `prepit_test`.`authority` (`id`, `code`, `description`) VALUES ('1', 'ADMIN', 'Application administrator');
INSERT INTO `prepit_test`.`authority` (`id`, `code`, `description`) VALUES ('2', 'USER', 'Simple user');
INSERT INTO `prepit_test`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');

-- 4.11.2022 Add field enabled to user
ALTER TABLE `prepit_test`.`user` 
ADD COLUMN `enabled` TINYINT NULL DEFAULT 0 AFTER `activity_type`,
ADD COLUMN `usercol` VARCHAR(45) NULL AFTER `enabled`;

-- create token for mail verification
CREATE TABLE `verification_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)