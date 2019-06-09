/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50010
Source Host           : localhost:3306
Source Database       : attendance

Target Server Type    : MYSQL
Target Server Version : 50010
File Encoding         : 65001

Date: 2019-06-07 20:07:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for application_for_ew
-- ----------------------------
DROP TABLE IF EXISTS `application_for_ew`;
CREATE TABLE `application_for_ew` (
  `applicated_person` int(20) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `state` int(20) NOT NULL,
  `applicated_id` int(20) NOT NULL auto_increment,
  PRIMARY KEY  (`applicated_id`),
  KEY `applicaew` (`applicated_person`),
  CONSTRAINT `applicaew` FOREIGN KEY (`applicated_person`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application_for_ew
-- ----------------------------
INSERT INTO `application_for_ew` VALUES ('4', '2019-06-01 15:36:35', '2019-06-11 15:36:32', '0', '55');
INSERT INTO `application_for_ew` VALUES ('1', '2019-05-01 12:54:30', '2019-05-02 12:54:39', '1', '100');
INSERT INTO `application_for_ew` VALUES ('2', '2019-05-01 08:27:44', '2019-05-09 08:27:48', '0', '101');
INSERT INTO `application_for_ew` VALUES ('1', '2019-06-01 15:25:57', '2019-06-01 20:25:57', '0', '123456');
INSERT INTO `application_for_ew` VALUES ('1', '2019-04-22 04:35:42', '2019-04-17 18:54:25', '0', '123457');

-- ----------------------------
-- Table structure for application_for_leave
-- ----------------------------
DROP TABLE IF EXISTS `application_for_leave`;
CREATE TABLE `application_for_leave` (
  `state` int(20) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `applicated_person` int(20) NOT NULL,
  `certificate_person` int(20) NOT NULL,
  `is_report_back` tinyint(20) NOT NULL,
  `report_back_time` datetime NOT NULL,
  PRIMARY KEY  (`applicated_person`),
  KEY `certificate` (`certificate_person`),
  CONSTRAINT `application` FOREIGN KEY (`applicated_person`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `certificate` FOREIGN KEY (`certificate_person`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application_for_leave
-- ----------------------------

-- ----------------------------
-- Table structure for attendance_record
-- ----------------------------
DROP TABLE IF EXISTS `attendance_record`;
CREATE TABLE `attendance_record` (
  `employeeid` int(20) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime default NULL,
  `is_complete` int(20) NOT NULL,
  KEY `21` (`employeeid`),
  CONSTRAINT `21` FOREIGN KEY (`employeeid`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance_record
-- ----------------------------
INSERT INTO `attendance_record` VALUES ('2', '2019-06-20 14:24:47', '2019-06-04 19:18:09', '0');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-04 09:00:07', '2019-06-04 09:02:49', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-04 09:03:37', '2019-06-04 09:04:02', '1');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(20) NOT NULL,
  `sector_id` int(20) NOT NULL,
  `is_manager` tinyint(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(20) NOT NULL,
  `salary` decimal(20,0) NOT NULL,
  `sex` tinyint(20) NOT NULL,
  `phone_number` int(20) NOT NULL,
  `enroll_time` datetime NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY  (`employee_id`),
  KEY `embese` (`sector_id`),
  KEY `saascas` (`user_id`),
  CONSTRAINT `saas` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`sector_id`),
  CONSTRAINT `saascas` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 10240 kB; (`user_id`) REFER `attendance/user`(`';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '123', '1', '张天', '25', '2000', '0', '1234567891', '2019-05-06 16:04:51', '1000');
INSERT INTO `employee` VALUES ('2', '124', '1', '志昂', '28', '3000', '0', '123456', '2018-08-24 16:24:55', '1001');
INSERT INTO `employee` VALUES ('4', '126', '1', '张和', '26', '3000', '1', '1234567', '1970-01-01 08:00:00', '1002');
INSERT INTO `employee` VALUES ('5', '127', '1', '张和', '26', '3000', '1', '1234567', '1970-01-01 08:00:00', '1003');
INSERT INTO `employee` VALUES ('7', '126', '0', '蔡徐坤', '26', '2550', '1', '456789', '2019-06-06 09:22:45', '1004');
INSERT INTO `employee` VALUES ('8', '125', '1', 'pidan', '17', '1234', '1', '1565', '2019-05-29 16:05:03', '1005');
INSERT INTO `employee` VALUES ('12', '127', '0', 'duff', '25', '4567', '0', '848613', '2019-06-04 14:25:47', '1006');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `name` varchar(20) NOT NULL,
  `age` int(20) NOT NULL,
  `salary` decimal(20,0) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `phone_number` int(20) NOT NULL,
  `enroll_time` datetime NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY  (`name`),
  KEY `asas` (`user_id`),
  CONSTRAINT `asas` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('总经理', '35', '10000', '0', '123', '2019-05-08 16:31:23', '1007');

-- ----------------------------
-- Table structure for sector
-- ----------------------------
DROP TABLE IF EXISTS `sector`;
CREATE TABLE `sector` (
  `sector_id` int(20) NOT NULL,
  `sector_name` varchar(20) NOT NULL,
  `sector_people_number` int(20) NOT NULL,
  `sector_description` varchar(300) default NULL,
  PRIMARY KEY  (`sector_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sector
-- ----------------------------
INSERT INTO `sector` VALUES ('123', '海尔', '10', '这是一个拍过爆红全国的动画片的影视巨头，拥有着市值100亿￥。');
INSERT INTO `sector` VALUES ('124', '阿里', '5', '这是一个让万千少女喊爸爸的部门，每年这个部门都会在11.11日那天举行宗教仪式，祭拜他们的马云爸爸。');
INSERT INTO `sector` VALUES ('125', '腾讯', '7', '这是一个让万千少男喊爸爸的部门，他的产品让少男们如痴如醉。');
INSERT INTO `sector` VALUES ('126', '百度', '3', '这是一个现在不知道凉没凉的公司。');
INSERT INTO `sector` VALUES ('127', '华为', '9', '这是一个站在5G潮头的部门，加入我们，日日996，每隔一段时间举行杀死程序员祭天仪式，在这里你会体会到无限的荣誉（if you can live long）');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL auto_increment,
  `passward` varchar(20) NOT NULL,
  `degree` int(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1000', '0', '0');
INSERT INTO `user` VALUES ('1001', '1', '0');
INSERT INTO `user` VALUES ('1002', '2', '0');
INSERT INTO `user` VALUES ('1003', '3', '0');
INSERT INTO `user` VALUES ('1004', '4', '0');
INSERT INTO `user` VALUES ('1005', '5', '0');
INSERT INTO `user` VALUES ('1006', '6', '0');
INSERT INTO `user` VALUES ('1007', '7', '0');

-- ----------------------------
-- Table structure for work_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `work_arrangement`;
CREATE TABLE `work_arrangement` (
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `employee_id` int(20) NOT NULL,
  `arrange_person` int(20) NOT NULL,
  `work_arrange_id` int(20) NOT NULL,
  PRIMARY KEY  (`work_arrange_id`),
  KEY `arranged` (`employee_id`),
  KEY `arrange` (`arrange_person`),
  CONSTRAINT `arrange` FOREIGN KEY (`arrange_person`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `arranged` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_arrangement
-- ----------------------------
