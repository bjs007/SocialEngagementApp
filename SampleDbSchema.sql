-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: proj-514-02.cs.iastate.edu    Database: socialDb
-- ------------------------------------------------------
-- Server version	5.5.50-MariaDB

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
-- Table structure for table `Discussion`
--

DROP TABLE IF EXISTS `Discussion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Discussion` (
  `discussionID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `adminID` int(11) DEFAULT NULL,
  `adminMessage` varchar(500) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `admintime` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `broadcasts`
--

DROP TABLE IF EXISTS `broadcasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `broadcasts` (
  `broadcastid` int(11) NOT NULL AUTO_INCREMENT,
  `postdesc` varchar(1000) DEFAULT NULL,
  `posttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userid` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`broadcastid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`coms514user`@`%`*/ /*!50003 trigger update_dashboard_by_broadcast
after insert on broadcasts for each row
begin
insert into dashboard(entry_desc,entry_type,post_id) values('broadcast',2,new.broadcastid);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`coms514user`@`%`*/ /*!50003 trigger delete_dashboard_by_broadcast
before delete on broadcasts for each row
begin
declare entryid Integer;
select a.entry_id into @entryid from dashboard a, broadcasts b where a.post_id = old.broadcastid and a.entry_type = 2 limit 1;
delete from dashboard where entry_id = @entryid;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `date_time` varchar(45) NOT NULL,
  `post_id` int(11) NOT NULL,
  `comment_string` varchar(200) NOT NULL,
  `module_type` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dashboard`
--

DROP TABLE IF EXISTS `dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dashboard` (
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `entry_desc` varchar(50) DEFAULT NULL,
  `entry_type` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_desc` varchar(45) DEFAULT NULL,
  `created_date_time` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `resources_needed` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `event_date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_archived` varchar(45) DEFAULT 'false',
  `is_resources_satisfied` varchar(45) DEFAULT 'false',
  `notify_sms_sent` varchar(45) DEFAULT NULL,
  `time_to_display` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`coms514user`@`%`*/ /*!50003 trigger update_dashboard_by_event
after insert on events for each row
begin
insert into dashboard(entry_desc,entry_type,post_id) values('event',1,new.event_id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`coms514user`@`%`*/ /*!50003 trigger delete_dashboard_by_event
before delete on events for each row
begin
declare entryid Integer;
select a.entry_id into @entryid from dashboard a where a.post_id = old.event_id and a.entry_type = 1 limit 1;
delete from dashboard where entry_id = @entryid;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `moduleenum`
--

DROP TABLE IF EXISTS `moduleenum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moduleenum` (
  `module_enum_value` int(10) unsigned NOT NULL,
  `Module_enum_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(5) NOT NULL AUTO_INCREMENT,
  `usertype` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `emergencycontact_name` varchar(100) DEFAULT NULL,
  `emergencycontact_phone` int(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'socialDb'
--

--
-- Dumping routines for database 'socialDb'
--
/*!50003 DROP PROCEDURE IF EXISTS `getHomeInfo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`coms514user`@`%` PROCEDURE `getHomeInfo`()
BEGIN

SET SQL_SAFE_UPDATES = 0;
drop table if exists homeinfo;
drop table if exists homeinfotemp;

create table homeinfotemp(
entry_id int,
entry_desc varchar(1000),
entry_type int,
post_id int,
activity_desc varchar(1000),
create_date_time varchar(100),
user_id Int
);
create table homeinfo(
entry_id int,
entry_desc varchar(1000),
entry_type int,
post_id int,
activity_desc varchar(1000),
create_date_time varchar(100),
user_id int
);

insert into homeinfotemp select a.entry_id, a.entry_desc, a.entry_type, a.post_id, b.event_desc, b.created_date_time,b.user_id from dashboard a, events b where a.post_id = b.event_id and a.entry_type = 1;
insert into homeinfotemp select a.entry_id, a.entry_desc, a.entry_type, a.post_id, b.postdesc, b.posttime,b.userid from dashboard a, broadcasts b where a.post_id = b.broadcastid and a.entry_type = 2;

insert into homeinfo select * from homeinfotemp order by create_date_time desc;
select * from homeinfo;

drop table homeinfo;
drop table homeinfotemp;
SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getInfoByDateBroadcast` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`coms514user`@`%` PROCEDURE `getInfoByDateBroadcast`()
BEGIN

SET SQL_SAFE_UPDATES = 0;
drop table if exists homeinfo;
drop table if exists homeinfotemp;

create table homeinfotemp(
entry_id int,
entry_desc varchar(1000),
entry_type int,
post_id int,
activity_desc varchar(1000),
create_date_time varchar(100),
user_id Int
);
create table homeinfo(
entry_id int,
entry_desc varchar(1000),
entry_type int,
post_id int,
activity_desc varchar(1000),
create_date_time varchar(100),
user_id int
);

insert into homeinfotemp select a.entry_id, a.entry_desc, a.entry_type, a.post_id, b.postdesc, b.posttime,b.userid from dashboard a, broadcasts b where a.post_id = b.broadcastid and a.entry_type = 2;

insert into homeinfo select * from homeinfotemp order by create_date_time desc;
select * from homeinfo order by create_date_time desc;

drop table homeinfo;
drop table homeinfotemp;
SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getInfoByDateEvent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`coms514user`@`%` PROCEDURE `getInfoByDateEvent`()
BEGIN

SET SQL_SAFE_UPDATES = 0;
drop table if exists homeinfo;
drop table if exists homeinfotemp;

create table homeinfotemp(
entry_id int,
entry_desc varchar(1000),
entry_type int,
post_id int,
activity_desc varchar(1000),
create_date_time varchar(100),
user_id Int
);
create table homeinfo(
entry_id int,
entry_desc varchar(1000),
entry_type int,
post_id int,
activity_desc varchar(1000),
create_date_time varchar(100),
user_id int
);

insert into homeinfotemp select a.entry_id, a.entry_desc, a.entry_type, a.post_id, b.event_desc, b.created_date_time,b.user_id from dashboard a, events b where a.post_id = b.event_id and a.entry_type = 1;
insert into homeinfo select * from homeinfotemp order by create_date_time desc;
select * from homeinfo order by create_date_time desc;

drop table homeinfo;
drop table homeinfotemp;
SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-10 15:29:41
