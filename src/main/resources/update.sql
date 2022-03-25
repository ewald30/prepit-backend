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
  `auth_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `auth_id`));

-- populate tables
INSERT INTO `prepit_test`.`authority` (`id`, `code`, `description`) VALUES ('1', 'ADMIN', 'Application administrator');
INSERT INTO `prepit_test`.`authority` (`id`, `code`, `description`) VALUES ('2', 'USER', 'Simple user');
INSERT INTO `prepit_test`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');
