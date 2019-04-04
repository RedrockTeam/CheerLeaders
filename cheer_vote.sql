/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : cheer_vote

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-04-04 23:08:42
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
INSERT INTO `cheerleader` VALUES ('1', 'telecommunication', '5');
INSERT INTO `cheerleader` VALUES ('2', 'computing', '0');
INSERT INTO `cheerleader` VALUES ('3', 'automation', '0');
INSERT INTO `cheerleader` VALUES ('4', 'advanced_manufacturing', '0');
INSERT INTO `cheerleader` VALUES ('5', 'photoelectricity', '0');
INSERT INTO `cheerleader` VALUES ('6', 'software', '0');
INSERT INTO `cheerleader` VALUES ('7', 'bioinformatics', '0');
INSERT INTO `cheerleader` VALUES ('8', 'science', '0');
INSERT INTO `cheerleader` VALUES ('9', 'economic_management', '0');
INSERT INTO `cheerleader` VALUES ('10', 'media_arts', '0');
INSERT INTO `cheerleader` VALUES ('11', 'foreign_languages', '0');
INSERT INTO `cheerleader` VALUES ('12', 'international', '0');
INSERT INTO `cheerleader` VALUES ('13', 'cyberspace_security', '0');
INSERT INTO `cheerleader` VALUES ('101', 'telecommunication', '0');
INSERT INTO `cheerleader` VALUES ('102', 'computing', '0');
INSERT INTO `cheerleader` VALUES ('103', 'automation', '0');
INSERT INTO `cheerleader` VALUES ('104', 'advanced_manufacturing', '0');
INSERT INTO `cheerleader` VALUES ('105', 'photoelectricity', '0');
INSERT INTO `cheerleader` VALUES ('106', 'software', '0');
INSERT INTO `cheerleader` VALUES ('107', 'bioinformatics', '0');
INSERT INTO `cheerleader` VALUES ('108', 'science', '0');
INSERT INTO `cheerleader` VALUES ('109', 'economic_management', '0');
INSERT INTO `cheerleader` VALUES ('110', 'media_arts', '0');
INSERT INTO `cheerleader` VALUES ('111', 'foreign_languages', '0');
INSERT INTO `cheerleader` VALUES ('112', 'international', '0');
INSERT INTO `cheerleader` VALUES ('113', 'cyberspace_security', '0');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `openid` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `polls` int(10) DEFAULT NULL,
  `pollstatus` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`openid`),
  UNIQUE KEY `user` (`openid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('ouRCyjsC0L1vtNSC2fYCDJGRePTA', 'zzz', '0', '1:1:1:1:1');
INSERT INTO `users` VALUES ('?', 'zzz', '5', '');
