ALTER TABLE `ManagerProiecte`.`studenti` 
DROP FOREIGN KEY `fk_student_id_proiect`;
ALTER TABLE `ManagerProiecte`.`studenti` 
DROP INDEX `id_proiect_idx` ;

ALTER TABLE `ManagerProiecte`.`studenti` 
DROP COLUMN `id_proiect`;

ALTER TABLE `ManagerProiecte`.`studenti` 
CHANGE COLUMN `nr_prezente` `nr_prezente` INT(11) NULL DEFAULT 0 ;

ALTER TABLE `ManagerProiecte`.`studenti` 
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC);

CREATE TABLE `ManagerProiecte`.`echipa` (
  `id_echipa` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email_student_lider_echipa` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_echipa`),
  UNIQUE INDEX `email_student_lider_echipa_UNIQUE` (`email_student_lider_echipa` ASC));
