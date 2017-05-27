CREATE TABLE `ManagerProiecte`.`proiecte` (
  `id_proiect` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titlu` VARCHAR(45) NOT NULL,
  `descriere` VARCHAR(2048) NULL,
  `nr_maxim_studenti` INT NOT NULL,
  `nr_studenti` INT NULL,
  PRIMARY KEY (`id_proiect`));

CREATE TABLE `ManagerProiecte`.`studenti` (
  `id_student` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nume` VARCHAR(45) NOT NULL,
  `prenume` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nr_prezente` INT NULL,
  `id_proiect` INT UNSIGNED NULL,
  PRIMARY KEY (`id_student`),
  INDEX `id_proiect_idx` (`id_proiect` ASC),
  CONSTRAINT `fk_student_id_proiect`
    FOREIGN KEY (`id_proiect`)
    REFERENCES `ManagerProiecte`.`proiecte` (`id_proiect`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
