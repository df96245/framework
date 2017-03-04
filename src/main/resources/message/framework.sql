/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-03-02 22:25:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `email` varchar(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('60398b59ff5011e68a5380fa5b1432fc', 'admin', 'admin@163.com', '2017-03-02 21:58:55', '5ec922a6ff5011e68a5380fa5b1432fc');
INSERT INTO `message` VALUES ('9234855aff2911e68a5380fa5b1432fc', 'zzf', 'zzf@163.com', '2017-03-02 17:21:09', '92284e7fff2911e68a5380fa5b1432fc');
INSERT INTO `message` VALUES ('c56c72e6fd0711e6a13c80fa5b1432fc', 'superadmin', '123', '2017-03-02 13:08:21', '2c1a13b1ff0611e68a5380fa5b1432fc');

-- ----------------------------
-- Table structure for message_role
-- ----------------------------
DROP TABLE IF EXISTS `message_role`;
CREATE TABLE `message_role` (
  `id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `role_id` varchar(40) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_role
-- ----------------------------
INSERT INTO `message_role` VALUES ('60754c69ff5011e68a5380fa5b1432fc', 'admin', '6623c146ff0811e68a5380fa5b1432fc', 'ADMIN');
INSERT INTO `message_role` VALUES ('9257e74eff2911e68a5380fa5b1432fc', 'zzf', '99d6b422ff2511e68a5380fa5b1432fc', 'USER');
INSERT INTO `message_role` VALUES ('bbe56dd3ff0b11e68a5380fa5b1432fc', 'superadmin', 'f401cb4cff0a11e68a5380fa5b1432fc', 'SUPER_ADMIN');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(40) NOT NULL,
  `name` varchar(15) NOT NULL,
  `content` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('6e23c7acff4d11e68a5380fa5b1432fc', 'user:admin:*', '管理用户');
INSERT INTO `permission` VALUES ('b21db9a9ff0b11e68a5380fa5b1432fc', 'system:*', '系统管理');
INSERT INTO `permission` VALUES ('bbe56dd3ff0b11e68a5380fa5b1432fc', 'user:self:*', '用户自身管理');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` varchar(40) NOT NULL,
  `name` varchar(15) NOT NULL,
  `content` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('6623c146ff0811e68a5380fa5b1432fc', 'ADMIN', '普通管理员');
INSERT INTO `roles` VALUES ('99d6b422ff2511e68a5380fa5b1432fc', 'USER', '用户');
INSERT INTO `roles` VALUES ('f401cb4cff0a11e68a5380fa5b1432fc', 'SUPER_ADMIN', '超级管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(40) NOT NULL,
  `role_id` varchar(40) NOT NULL,
  `permission_id` varchar(40) NOT NULL,
  `perm_name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('3b4d2faeff5111e68a5380fa5b1432fc', '6623c146ff0811e68a5380fa5b1432fc', '6e23c7acff4d11e68a5380fa5b1432fc', 'user:admin:*');
INSERT INTO `role_permission` VALUES ('92284e7fff2911e68a5380fa5b1432fc', '99d6b422ff2511e68a5380fa5b1432fc', 'bbe56dd3ff0b11e68a5380fa5b1432fc', 'user:self:*');
INSERT INTO `role_permission` VALUES ('9234855aff2911e68a5380fa5b1432fc', 'f401cb4cff0a11e68a5380fa5b1432fc', 'b21db9a9ff0b11e68a5380fa5b1432fc', 'system:*');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(50) NOT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('2c1a13b1ff0611e68a5380fa5b1432fc', 'superadmin', '317b724c50cb40ba540905c6f4d9161a', '5ef89ebf14960ef28d4116f03c841ddd');
INSERT INTO `users` VALUES ('5ec922a6ff5011e68a5380fa5b1432fc', 'admin', '41d457fb5863918dc01f61f5e1a48dec', '10997cbe3e2a4d8fe68a03aaaff3b98d');
INSERT INTO `users` VALUES ('92284e7fff2911e68a5380fa5b1432fc', 'zzf', 'ca1396e4c07ded1e63bdbfa4774d85e9', '219a7211ce578f5ae3773691eda7a47d');
