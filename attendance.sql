/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50022
Source Host           : 127.0.0.1:3306
Source Database       : attendance

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2019-06-16 16:45:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alert
-- ----------------------------
DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `employee_id` int(20) NOT NULL,
  `state` int(1) NOT NULL,
  `alert_id` int(20) NOT NULL auto_increment,
  PRIMARY KEY  (`alert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alert
-- ----------------------------

-- ----------------------------
-- Table structure for application_for_ew
-- ----------------------------
DROP TABLE IF EXISTS `application_for_ew`;
CREATE TABLE `application_for_ew` (
  `applicated_id` int(20) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `ew_state` int(11) NOT NULL,
  `ew_id` int(20) NOT NULL auto_increment,
  `ratify_id` int(20) default NULL,
  `ew_reason` varchar(255) NOT NULL,
  PRIMARY KEY  (`ew_id`),
  KEY `asf` (`applicated_id`),
  KEY `asd` (`ratify_id`),
  CONSTRAINT `asf` FOREIGN KEY (`applicated_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application_for_ew
-- ----------------------------
INSERT INTO `application_for_ew` VALUES ('1', '2019-06-13 10:46:22', '2019-06-14 08:46:30', '1', '1', null, 'hghjghd');
INSERT INTO `application_for_ew` VALUES ('1', '2019-06-17 08:48:08', '2019-06-19 08:48:20', '0', '2', null, ' gh');

-- ----------------------------
-- Table structure for application_for_leave
-- ----------------------------
DROP TABLE IF EXISTS `application_for_leave`;
CREATE TABLE `application_for_leave` (
  `leave_id` int(20) NOT NULL auto_increment,
  `state` int(1) default NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `applicated_person` int(11) NOT NULL,
  `ratified_person` int(11) default NULL,
  `is_report_back` tinyint(4) default NULL,
  `report_back_time` datetime default NULL,
  `leave_reason` varchar(255) NOT NULL,
  `reject_reason` varchar(255) default NULL,
  `leave_type` int(11) NOT NULL,
  PRIMARY KEY  (`leave_id`),
  KEY `fdsa` (`applicated_person`),
  KEY `das` (`ratified_person`),
  CONSTRAINT `fdsa` FOREIGN KEY (`applicated_person`) REFERENCES `employee` (`employee_id`)
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
INSERT INTO `attendance_record` VALUES ('12', '2019-06-04 09:00:07', '2019-06-04 09:02:49', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-04 09:03:37', '2019-06-04 09:04:02', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-08 21:39:26', '2019-06-08 21:39:30', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-09 09:28:09', '2019-06-11 19:17:53', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-11 19:26:22', '2019-06-11 19:26:23', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-11 19:26:24', '2019-06-11 19:26:26', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-11 19:26:27', '2019-06-11 19:26:28', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-11 19:26:29', '2019-06-11 19:26:31', '1');
INSERT INTO `attendance_record` VALUES ('12', '2019-06-11 19:26:33', '2019-06-11 19:26:35', '1');
INSERT INTO `attendance_record` VALUES ('15', '2019-06-14 11:37:09', null, '0');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(20) NOT NULL auto_increment,
  `sector_id` int(20) NOT NULL,
  `is_manager` tinyint(2) NOT NULL,
  `name` varchar(20) default NULL,
  `age` int(20) NOT NULL,
  `salary` decimal(20,0) NOT NULL,
  `sex` tinyint(20) NOT NULL,
  `phone_number` int(20) NOT NULL,
  `enroll_time` datetime NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY  (`employee_id`),
  UNIQUE KEY `saascas` USING BTREE (`user_id`),
  KEY `em` USING BTREE (`sector_id`),
  CONSTRAINT `saas` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`sector_id`),
  CONSTRAINT `saascas` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 10240 kB; (`user_id`) REFER `attendance/user`(`';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '123', '0', '??', '35', '5000', '1', '78984', '2019-05-06 16:04:51', '1000');
INSERT INTO `employee` VALUES ('5', '127', '1', '张和', '26', '3000', '1', '1234567', '1970-01-01 08:00:00', '1003');
INSERT INTO `employee` VALUES ('7', '126', '0', '蔡徐坤', '26', '2550', '1', '456789', '2019-06-06 09:22:45', '1004');
INSERT INTO `employee` VALUES ('8', '125', '0', 'pidan', '17', '1234', '1', '1565', '2019-05-29 16:05:03', '1005');
INSERT INTO `employee` VALUES ('12', '127', '0', 'duff', '25', '4567', '0', '848613', '2019-06-04 14:25:47', '1006');
INSERT INTO `employee` VALUES ('15', '124', '0', '健康和', '456', '9845', '1', '89456', '2019-06-04 11:36:46', '1008');
INSERT INTO `employee` VALUES ('16', '123', '0', 'dakjs', '0', '0', '0', '0', '2019-06-12 16:29:23', '1027');

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
  `password` varchar(20) NOT NULL,
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
INSERT INTO `user` VALUES ('1008', '123456', '0');
INSERT INTO `user` VALUES ('1027', '12356', '0');

-- ----------------------------
-- Table structure for work_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `work_arrangement`;
CREATE TABLE `work_arrangement` (
  `employee_id` int(20) NOT NULL,
  `arrange_person` int(20) NOT NULL,
  `work_arrange_id` int(20) NOT NULL auto_increment,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY  (`work_arrange_id`),
  KEY `arranged` (`employee_id`),
  KEY `arrange` (`arrange_person`),
  CONSTRAINT `arrange` FOREIGN KEY (`arrange_person`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `arranged` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_arrangement
-- ----------------------------
INSERT INTO `work_arrangement` VALUES ('1', '1', '3', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
INSERT INTO `work_arrangement` VALUES ('1', '1', '4', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
INSERT INTO `work_arrangement` VALUES ('1', '1', '5', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
INSERT INTO `work_arrangement` VALUES ('1', '1', '6', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
INSERT INTO `work_arrangement` VALUES ('1', '1', '7', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
INSERT INTO `work_arrangement` VALUES ('1', '1', '8', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
INSERT INTO `work_arrangement` VALUES ('1', '1', '9', '2019-06-05 04:44:38', '2019-06-12 07:44:45');
