/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50732
Source Host           : localhost:3306
Source Database       : jpa

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2020-11-06 15:25:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acc` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `who` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `acc` (`acc`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin ', 'admin', '123456', '管理员');
