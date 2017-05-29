DROP TABLE `ManagerProiecte`.`echipa`;

CREATE TABLE `ManagerProiecte`.`echipa` (
  `id_echipa` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email_student_lider_echipa` VARCHAR(45) NOT NULL,
  `id_proiect` INT UNSIGNED NULL,
  PRIMARY KEY (`id_echipa`),
  UNIQUE INDEX `email_student_lider_echipa_UNIQUE` (`email_student_lider_echipa` ASC),
  INDEX `fk_echipa_id_proiect_idx` (`id_proiect` ASC),
  CONSTRAINT `fk_email_student_lider_echipa`
    FOREIGN KEY (`email_student_lider_echipa`)
    REFERENCES `ManagerProiecte`.`studenti` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_echipa_id_proiect`
    FOREIGN KEY (`id_proiect`)
    REFERENCES `ManagerProiecte`.`proiecte` (`id_proiect`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `ManagerProiecte`.`echipa` 
DROP FOREIGN KEY `fk_email_student_lider_echipa`;
ALTER TABLE `ManagerProiecte`.`echipa` 
DROP INDEX `email_student_lider_echipa_UNIQUE` ;

ALTER TABLE `ManagerProiecte`.`echipa` 
DROP COLUMN `email_student_lider_echipa`;

ALTER TABLE `ManagerProiecte`.`studenti` 
ADD COLUMN `id_echipa` INT NULL AFTER `nr_prezente`,
ADD UNIQUE INDEX `id_echipa_UNIQUE` (`id_echipa` ASC);

ALTER TABLE `ManagerProiecte`.`studenti` 
CHANGE COLUMN `id_echipa` `id_echipa` INT(11) UNSIGNED NULL DEFAULT NULL ;
ALTER TABLE `ManagerProiecte`.`studenti` 
ADD CONSTRAINT `fk_student_id_echipa`
  FOREIGN KEY (`id_echipa`)
  REFERENCES `ManagerProiecte`.`echipa` (`id_echipa`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
