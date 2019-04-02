/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : cheer_vote

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-04-02 15:27:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cheerleader`
-- ----------------------------
DROP TABLE IF EXISTS `cheerleader`;
CREATE TABLE `cheerleader` (
  `id` int(10) NOT NULL,
  `collage` varchar(255) DEFAULT NULL,
  `polls` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cheerleader` (`collage`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cheerleader
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `openid` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `polls` int(10) DEFAULT NULL,
  `pollstatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`openid`),
  UNIQUE KEY `user` (`openid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
