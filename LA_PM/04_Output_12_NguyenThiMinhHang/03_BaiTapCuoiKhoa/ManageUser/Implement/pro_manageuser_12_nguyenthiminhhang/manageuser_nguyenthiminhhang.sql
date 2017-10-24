-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.38-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema manageuser_nguyenthiminhhang
--

CREATE DATABASE IF NOT EXISTS manageuser_nguyenthiminhhang;
USE manageuser_nguyenthiminhhang;

--
-- Definition of table `mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
CREATE TABLE `mst_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mst_group`
--

/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` (`group_id`,`group_name`) VALUES 
 (1,'Phòng QAT'),
 (2,'Phòng DEV1');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;


--
-- Definition of table `mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
CREATE TABLE `mst_japan` (
  `code_level` varchar(15) NOT NULL,
  `name_level` varchar(255) NOT NULL,
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mst_japan`
--

/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` (`code_level`,`name_level`) VALUES 
 ('1','N1'),
 ('2','N2'),
 ('3','N3'),
 ('4','N4'),
 ('5','N5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;


--
-- Definition of table `tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_detail_user_japan`
--

/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` (`detail_user_japan_id`,`user_id`,`code_level`,`start_date`,`end_date`,`total`) VALUES 
 (1,1,'3','2000-01-01','2002-01-01',500),
 (2,2,'2','2000-01-01','2002-01-01',600);
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;


--
-- Definition of table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
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
  `rule` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_user`
--

/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`user_id`,`group_id`,`login_name`,`passwords`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`,`salt`,`rule`) VALUES 
 (1,1,'admin','fa05f63370a9c8698fbfa5c0f1055474dda84e5','admin',NULL,'aaaa@gmail.com','0123456789','1990-01-01','aifjsodifojk',1),
 (2,2,'hang','fa05f63370a9c8698fbfa5c0f1055474dda84e5','hang',NULL,'bbbb@gmail.com','0123456789','1990-01-01','woriudskfnkaisdo',0);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
