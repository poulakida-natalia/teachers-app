-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: teachers
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `TEACHER_ID` int NOT NULL,
  `S_NAME` varchar(45) NOT NULL,
  `F_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`TEACHER_ID`),
  KEY `S_NAME_idx` (`S_NAME`) /*!80000 INVISIBLE */
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (5,'Βασιλείου','Αλέξανδρος'),(6,'Σταυροπούλου','Ελένη'),(7,'Κολοκοτρώνης','Θεόδωρος'),(8,'Χατζηγεωργίου','Δημήτρης'),(12,'Λαμπροπούλου','Ναταλία'),(13,'Ρουμελιώτη','Σοφία'),(14,'Φιλιππίδης','Παναγιώτης'),(15,'Σταθοπούλου','Βασιλική'),(16,'Καραγιάννης','Γιάννης'),(17,'Παλαιολόγου','Ιωάννης'),(18,'Σωτηρόπουλος','Καλλιόπη'),(19,'Μπογιατζής','Θεόδωρος'),(20,'Μητροπούλου','Λευκή'),(21,'Γεωργιάδης','Πέτρος'),(22,'Λιβάνιος','Κυριάκος'),(23,'Κουτσογιαννόπουλος','Χρήστος'),(24,'Σιδηρόπουλου','Αγγελική'),(25,'Νικολαΐδης','Τριαντάφυλλος'),(26,'Κατσίκη','Φωτεινή'),(27,'Παπαναστασίου','Αλέξανδρος'),(28,'Πορτοκάλης','Σωτήρης'),(200,'Παλαμάς','Κώστας'),(1000,'Πουλακίδας','Πέτρος'),(1009,'Κωστάκη','Κατερίνα'),(10000,'j','s');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'natalia2','Initial1!');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11 16:31:49
