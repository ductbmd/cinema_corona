-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 172.104.185.85    Database: teko_test
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

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
-- Table structure for table `tbl_cinemas`
--

DROP TABLE IF EXISTS `tbl_cinemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cinemas` (
  `id` int(11) NOT NULL,
  `rows` int(11) DEFAULT NULL,
  `columns` int(11) DEFAULT NULL,
  `min_distance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cinemas`
--

LOCK TABLES `tbl_cinemas` WRITE;
/*!40000 ALTER TABLE `tbl_cinemas` DISABLE KEYS */;
INSERT INTO `tbl_cinemas` VALUES (1,10,15,7);
/*!40000 ALTER TABLE `tbl_cinemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_seats`
--

DROP TABLE IF EXISTS `tbl_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_seats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `row` int(11) DEFAULT NULL,
  `col` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_seats`
--

LOCK TABLES `tbl_seats` WRITE;
/*!40000 ALTER TABLE `tbl_seats` DISABLE KEYS */;
INSERT INTO `tbl_seats` VALUES (46,0,0,0,1),(47,0,1,0,1),(48,0,2,0,1),(49,0,3,0,1),(50,0,4,0,1),(51,0,5,0,1),(52,0,6,0,1),(53,0,7,0,1),(54,0,8,0,1),(55,0,9,0,1),(56,0,10,0,1),(57,0,11,0,1),(58,0,12,0,1),(59,0,13,0,1),(60,0,14,0,1),(61,1,0,0,1),(62,1,1,0,1),(63,1,2,0,1),(64,1,3,0,1),(65,1,4,0,1),(66,1,5,0,1),(67,1,6,0,1),(68,1,7,0,1),(69,1,8,0,1),(70,1,9,0,1),(71,1,10,0,1),(72,1,11,0,1),(73,1,12,0,1),(74,1,13,0,1),(75,1,14,0,1),(76,2,0,0,1),(77,2,1,0,1),(78,2,2,0,1),(79,2,3,0,1),(80,2,4,0,1),(81,2,5,0,1),(82,2,6,0,1),(83,2,7,0,1),(84,2,8,0,1),(85,2,9,0,1),(86,2,10,0,1),(87,2,11,0,1),(88,2,12,0,1),(89,2,13,0,1),(90,2,14,0,1),(91,3,0,0,1),(92,3,1,0,1),(93,3,2,0,1),(94,3,3,0,1),(95,3,4,0,1),(96,3,5,0,1),(97,3,6,0,1),(98,3,7,0,1),(99,3,8,0,1),(100,3,9,0,1),(101,3,10,0,1),(102,3,11,0,1),(103,3,12,0,1),(104,3,13,0,1),(105,3,14,0,1),(106,4,0,0,1),(107,4,1,0,1),(108,4,2,0,1),(109,4,3,0,1),(110,4,4,0,1),(111,4,5,0,1),(112,4,6,0,1),(113,4,7,0,1),(114,4,8,0,1),(115,4,9,0,1),(116,4,10,0,1),(117,4,11,0,1),(118,4,12,0,1),(119,4,13,0,1),(120,4,14,0,1),(121,5,0,0,1),(122,5,1,0,1),(123,5,2,0,1),(124,5,3,0,1),(125,5,4,0,1),(126,5,5,0,1),(127,5,6,0,1),(128,5,7,0,1),(129,5,8,0,1),(130,5,9,0,1),(131,5,10,0,1),(132,5,11,0,1),(133,5,12,0,1),(134,5,13,0,1),(135,5,14,0,1),(136,6,0,0,1),(137,6,1,0,1),(138,6,2,0,1),(139,6,3,0,1),(140,6,4,0,1),(141,6,5,0,1),(142,6,6,0,1),(143,6,7,0,1),(144,6,8,0,1),(145,6,9,0,1),(146,6,10,0,1),(147,6,11,0,1),(148,6,12,0,1),(149,6,13,0,1),(150,6,14,0,1),(151,7,0,0,1),(152,7,1,0,1),(153,7,2,0,1),(154,7,3,0,1),(155,7,4,0,1),(156,7,5,0,1),(157,7,6,0,1),(158,7,7,0,1),(159,7,8,0,1),(160,7,9,0,1),(161,7,10,0,1),(162,7,11,0,1),(163,7,12,0,1),(164,7,13,0,1),(165,7,14,0,1),(166,8,0,0,1),(167,8,1,0,1),(168,8,2,0,1),(169,8,3,0,1),(170,8,4,0,1),(171,8,5,0,1),(172,8,6,0,1),(173,8,7,0,1),(174,8,8,0,1),(175,8,9,0,1),(176,8,10,0,1),(177,8,11,0,1),(178,8,12,0,1),(179,8,13,0,1),(180,8,14,0,1),(181,9,0,0,1),(182,9,1,0,1),(183,9,2,0,1),(184,9,3,0,1),(185,9,4,0,1),(186,9,5,0,1),(187,9,6,0,1),(188,9,7,0,1),(189,9,8,0,1),(190,9,9,0,1),(191,9,10,0,1),(192,9,11,0,1),(193,9,12,0,1),(194,9,13,0,1),(195,9,14,0,1);
/*!40000 ALTER TABLE `tbl_seats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-28 22:53:26
