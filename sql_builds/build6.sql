ALTER TABLE `ManagerProiecte`.`studenti` 
DROP INDEX `id_echipa_UNIQUE` ;

ALTER TABLE `ManagerProiecte`.`studenti` 
ADD INDEX `student_id_echipa` (`id_echipa` ASC),
DROP INDEX `id_echipa_UNIQUE` ;

