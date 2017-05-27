CREATE SCHEMA `ManagerProiecte` ;

CREATE TABLE `ManagerProiecte`.`profesor` (
  `id` INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `ManagerProiecte`.`profesor` (`id`, `username`, `password`) VALUES ('1', 'admin', 'admin');