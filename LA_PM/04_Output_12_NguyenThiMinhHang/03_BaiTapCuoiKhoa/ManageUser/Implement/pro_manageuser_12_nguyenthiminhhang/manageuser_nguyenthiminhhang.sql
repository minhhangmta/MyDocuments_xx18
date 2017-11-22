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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_detail_user_japan`
--

LOCK TABLES `tbl_detail_user_japan` WRITE;
/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` VALUES (1,1,'N3','2015-01-01','2017-01-01',500),(2,2,'N2','2015-01-10','2017-01-10',600),(3,3,'N3','2015-01-01','2017-01-01',550),(4,4,'N5','2016-02-10','2018-01-10',450),(5,5,'N5','2017-04-04','2019-08-10',400),(6,30,'N1','2017-11-08','2018-11-08',300),(8,33,'N2','2017-11-09','2018-11-09',600),(12,45,'N2','2017-11-09','2018-11-09',800),(13,47,'N1','2004-11-09','2006-11-09',900),(16,66,'N1','2013-01-01','2018-01-01',900),(19,71,'N5','2017-11-14','2018-11-14',400),(21,72,'N2','2010-11-14','2016-11-14',800),(24,52,'N1','2017-11-16','2018-11-16',900),(25,28,'N2','2017-11-20','2018-11-20',800);
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
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,1,'admin','fa05f63370a9c8698fbfa5c0f1055474dda84e5','admin',NULL,'aaaa@gmail.com','01234567891','1990-01-01','aifjsodifojk',1),(2,2,'ntmhang','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Thị Minh Hằng',NULL,'minhhang.mta@gmail.com','01234567895','1990-01-01','woriudskfnkaisdo',0),(3,1,'nguyenvana','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Văn A',NULL,'nguyenvana@gmail.com','0967245698','1992-01-04','woriudskfnkaisdo',0),(4,2,'nguyenthib','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Thị Xoa',NULL,'nguyenthib@gmail.com','01235698400','1991-02-10','woriudskfnkaisdo',0),(5,1,'lethixoa','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Thị Xoa',NULL,'xoalt@luvina.net','01235698400','1991-02-10','woriudskfnkaisdo',0),(6,1,'dangthihan','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Vũ Thị Hân',NULL,'handt@luvina.net','01235698400','1990-01-01','woriudskfnkaisdo',0),(7,1,'lenghiemthuy','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Nghiêm Thủy',NULL,'thuyln@luvina.net','0967245698','1991-02-10','woriudskfnkaisdo',0),(8,2,'lephuonganh','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Lê Phương Anh',NULL,'anhlp@luvina.net','01235698400','1992-01-04','woriudskfnkaisdo',0),(9,3,'tranthanhthuy','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Trần Thanh Thủy',NULL,'tranthuy.nute@gmail.com','0982527982','1991-02-10','woriudskfnkaisdo',0),(10,3,'ngomanhha','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Ngô Mạnh Hà',NULL,'manhhachkt08@gmail.com','0973776072','1980-04-01','woriudskfnkaisdo',0),(17,4,'vutienlap','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Vũ Tiến Lập',NULL,'tienlapspktnd@gmail.com','0917749254','1982-01-05','woriudskfnkaisdo',0),(18,4,'nguyenvantham','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Văn Thẩm',NULL,'nvt.isst.nute@gmail.com','0904770053','1980-04-01','woriudskfnkaisdo',0),(19,3,'nguyendanghoan','fa05f63370a9c8698fbfa5c0f1055474dda84e5','Nguyễn Đăng Hoàn',NULL,'danghoang87hl@gmail.com','0974880788','1980-04-01','woriudskfnkaisdo',0),(21,4,'trantrungkien','fa05f63370a9c8698fbfa5c0f1055474dda84e5','John\'s Anh',NULL,'trungkienspktnd@gamail.com','0983888611','1982-01-05','woriudskfnkaisdo',0),(28,3,'trantrungkien','5b93a5aa80f945b1753b1d66225f7b7930b213a0','Charlies Puthss',NULL,'charlie@gamail.com','8888-9999-8887','1982-01-05','tpqjlrmvvmezjoiynucf',0),(30,1,'oaidgdoi','4b3f49df21c490de885f984cd2a4437ec3cc8d4','Nguyễn Thị',NULL,'adfjio@gmail.com','8888-9999-8888','1980-11-08','mwfpcqnnavvnmkeiayvf',0),(32,3,'taylor_swift','18aef9b9ec31aaae1b6c7d0abb6dd0a6b2ce8013','Taylor Swift',NULL,'taylor@gmail.com','8888-9999-8888','1984-11-09','hjkyaykskycqniijqdir',0),(33,2,'nguyen123','e133b67e5ae369e135ad01ddc3a1d877f03de94b','Ninh Nguyen',NULL,'ninhnguyen@gmail.com','8888-9999-8889','1980-11-10','ckxgirsemowkuosfcryc',0),(45,2,'vuthuy','3761895275dee8936e6f577dafefd5f5701aeb73','vu vu',NULL,'vuvu@gmail.com','8888-9999-8888','1980-11-09','sbfikdamtwvdyhwomqjp',0),(46,2,'facebook','9473a2981c6d54076aa78627badc9d448b790d66','facebook',NULL,'facebook@facebook.com','8888-9999-8888','2000-11-09','pdbexxpvbzhrmxlsthwp',0),(47,2,'aoifjsod','a7d7858088fa57b915b40d6eaf0fb365b63b0ace','dinh thi',NULL,'dinhthi@gmail.com','8888-9999-8888','1980-11-09','ustziedoejtwvnxklkxi',0),(52,3,'haanhtuan','a697506182cb9e222564afe570562d0ae839ff0b','ha anh tuan',NULL,'anhtuan@gmail.com','7894-7894-7892','1980-11-09','hejehetxavefzlyczjqx',0),(53,3,'haanhtuannn','3ecbae81bb024eb47049c997bb1b8b108ae902c1','tuan anh',NULL,'anhtuanmm@gmail.com','7894-7894-7892','1980-11-09','wgrdniguvswqthexpmne',0),(63,2,'hoangpm','5c7ecb7ad0b6d687f50a37570cce9297d8565fc1','Phạm Minh Hoàng',NULL,'hoangpm@gmail.com','8888-9999-8888','1980-11-12','qybgnpfmwcmdxhkyecan',0),(66,2,'doifgdfo','9bdadfc918d71dc0e091c568d068b2b1ec93445b','Justin',NULL,'justin@gmail.com','8888-9999-8888','2017-11-13','oefrhpcskdwdacoobiok',0),(68,2,'igjofd','82212def5bb697b12b432fd1629a7b2f28cd471','sdfof',NULL,'ddddg@gmail.com','8888-9999-8888','2017-11-13','ybyzgrjebsunvuvrrisd',0),(71,1,'pmhoang','bb66f16e677a467def12cc917b6b8f3dfc7b546a','PM Hoang',NULL,'yyyhoang@gmai.com','8888-9999-8887','2008-11-14','obuwvopowjkpdkhmekzo',0),(72,2,'thaopt','18ff21e86374aecc24cf495aa779a3e1e8502b0f','Nguyễn Thị Phương Thảo','カタ','thaopt98@gmail.com','4568-7897-7889','1998-09-16','gnqdzrxrkschpzsqhlrh',0),(95,1,'test','e756b3c1ee95bb00f645e892cfec51de6856a625','test',NULL,'test@gmail.com','1111-1111-1111','2017-11-16','petutrykdciskkhbybmn',0),(97,1,'apsfosdofj','9e9112bf6ee9facb281aeaffb4c0a78beeec8be6','sofdpof',NULL,'adfopahng@gmail.com','1-1-1','2017-11-17','pccicwoqeixgyisxvxqt',0);
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

-- Dump completed on 2017-11-22 10:16:34
