CREATE SCHEMA `ManagerProiecte` ;

CREATE TABLE `ManagerProiecte`.`profesori` (
  `id` INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `parola` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `ManagerProiecte`.`profesori` (`id`, `username`, `password`) VALUES ('1', 'admin', 'admin');