-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: manageuser_nguyenthiminhhang
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_group`
--

LOCK TABLES `mst_group` WRITE;
/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` VALUES (1,'Group 1'),(2,'Group 2'),(3,'Group 3'),(4,'Group 4');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_japan` (
  `code_level` varchar(15) NOT NULL,
  `name_level` varchar(255) NOT NULL,
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_japan`
--

LOCK TABLES `mst_japan` WRITE;
/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` VALUES ('N1','Level 1'),('N2','Level 2'),('N3','Level 3'),('N4','Level 4'),('N5','Level 5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code_level` varchar(15) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`detail_user_japan_id`),
  KEY `user_id` (`user_id`),
  KEY `code_level` (`code_level`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_2` FOREIGN KEY (`code_level`) REFERENCES `mst_japan` (`code_level`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_detail_user_japan`
--

LOCK TABLES `tbl_detail_user_japan` WRITE;
/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` VALUES (1,1,'N3','2015-01-01','2017-01-01',500),(2,2,'N2','2015-01-10','2017-01-10',600),(3,3,'N3','2015-01-01','2017-01-01',550),(4,4,'N4','2016-02-10','2018-01-10',450),(5,5,'N5','2017-04-04','2019-08-10',400);
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `login_name` varchar(15) NOT NULL,
  `passwords` varchar(50) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `full_name_kana` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `birthday` date NOT NULL,
  `salt` varchar(255) NOT NULL,
  `role` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,1,'admin','fa05f63370a9c8698fbfa5c0f1055474dda84e5','admin',NULL,'aaaa@gmail.com','01234567891','1990-01-01','aifjsodifojk',1),(2,2,'ntmhang','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Thị Minh Hằng',NULL,'minhhang.mta@gmail.com','01234567895','1990-01-01','woriudskfnkaisdo',0),(3,1,'nguyenvana','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Văn A',NULL,'nguyenvana@gmail.com','0967245698','1992-01-04','woriudskfnkaisdo',0),(4,2,'nguyenthib','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Thị B',NULL,'nguyenthib@gmail.com','01235698400','1991-02-10','woriudskfnkaisdo',0),(5,1,'lethixoa','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Thị Xoa',NULL,'xoalt@luvina.net','01235698400','1991-02-10','woriudskfnkaisdo',0),(6,1,'dangthihan','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Đặng Thị Hân',NULL,'handt@luvina.net','01235698400','1990-01-01','woriudskfnkaisdo',0),(7,1,'lenghiemthuy','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Nghiêm Thủy',NULL,'thuyln@luvina.net','0967245698','1991-02-10','woriudskfnkaisdo',0),(8,2,'lephuonganh','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Phương Anh',NULL,'anhlp@luvina.net','01235698400','1992-01-04','woriudskfnkaisdo',0);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26  9:56:49
