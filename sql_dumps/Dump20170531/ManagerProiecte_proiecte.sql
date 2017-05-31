-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: ManagerProiecte
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `proiecte`
--

DROP TABLE IF EXISTS `proiecte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proiecte` (
  `id_proiect` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titlu` varchar(45) NOT NULL,
  `descriere` varchar(2048) DEFAULT NULL,
  `nr_max_studenti` int(11) NOT NULL,
  PRIMARY KEY (`id_proiect`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proiecte`
--

LOCK TABLES `proiecte` WRITE;
/*!40000 ALTER TABLE `proiecte` DISABLE KEYS */;
INSERT INTO `proiecte` VALUES (1,'Taxi Manager','Lala aceasta este o descriere foarte buna..\r\n\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet rhoncus mi, id posuere tellus. Nam nisl leo, commodo sit amet risus at, bibendum tempor mi.',2),(2,'Online banking','Rain man...\r\n\r\nRaining money',1),(3,'Aplicatie chat','Practic facebook.. cel putin',3),(4,'Manager proiecte facultate','Implementati aceasta aplicatie pentru alegerea proiectelor de catre studenti. Este permis si varianta non-web, desi nu ar ajuta prea mult studentii si cred ca ar fi mai dificil. Varianta non-web trebuie sa fie client server!',2),(5,'Catalog virtual','High five!',1),(6,'Manager concedii','La cat mai multe!',3),(7,'Pariuri online','Super-mega-ultra-giga-tera-bet!',2),(8,'Walktie-talkie','it doesn\'t get any more 80\'s than this!',3),(9,'Forum','Let\'s.. forum?',3),(10,'Manage parcare','Dacias only',1),(11,'Manageri sali de sedinte','Sefi only!',1);
/*!40000 ALTER TABLE `proiecte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-31 17:12:26
