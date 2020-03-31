/*
Navicat MySQL Data Transfer

Source Server         : testSpringMVC
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : registeruser

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-07-25 16:37:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `myusertable`
-- ----------------------------
DROP TABLE IF EXISTS `myusertable`;
CREATE TABLE `myusertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL,
  `upwd` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myusertable
-- ----------------------------
INSERT INTO myusertable VALUES ('1', '陈恒', '123456');
INSERT INTO myusertable VALUES ('2', 'éæ', '123456');
INSERT INTO myusertable VALUES ('3', '11', '11');
INSERT INTO myusertable VALUES ('4', '11', '11');
INSERT INTO myusertable VALUES ('5', '', '');
INSERT INTO myusertable VALUES ('6', '大多数多所多所', '123456');
