/*
Navicat MySQL Data Transfer

Source Server         : ma
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : ma

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-11-03 16:09:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_coursewares
-- ----------------------------
DROP TABLE IF EXISTS `t_coursewares`;
CREATE TABLE `t_coursewares` (
  `id` varchar(32) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `duration` int(10) DEFAULT NULL,
  `device_type` varchar(20) DEFAULT NULL,
  `file_type` varchar(20) DEFAULT NULL,
  `open` varchar(1) DEFAULT NULL,
  `transform_status` varchar(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coursewares
-- ----------------------------
INSERT INTO `t_coursewares` VALUES ('0d2d1206c58c47b5a7b037edd9674249', '9', '9', '9', 'PC', 'SCORM包', '1', '0', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('1665f48839d7453db1b76b911dc09000', '2', '2', '2', 'PC,MOBILE', '视频', '1', '1', null, null);
INSERT INTO `t_coursewares` VALUES ('1afd690916ae471fa6968d1ea3a10703', '11', '11', '11', 'PC', 'SCORM包', '1', '0', null, null);
INSERT INTO `t_coursewares` VALUES ('26da3625d0cc4991afd5828b7dd71466', 'test6', 'test6', '16', 'PC,MOBILE', '视频', '1', '0', '2016-05-17 22:44:12', '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('32b5b5bbea7244ecb40fa4dd215c8b15', 'test3', 'test3', '13', 'PC', 'SCORM包', '1', '0', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('573e78a21f2641d0ba60518c98b0ba44', '7', '7', '7', 'PC', 'SCORM包', '1', '0', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('57fbaeb776224c48ab25cf15944aa789', '5', '5', '5', 'PC', 'SCORM包', '1', '0', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('95187e00f7ac44d3a4c1237cbe9dabf2', '112', '12', '12', 'PC', 'SCORM包', '1', '1', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('d309002c024a4353a91ba98043787599', 'test5', 'test5', '15', 'PC,MOBILE', 'SCORM包', '1', '0', '2016-05-17 22:43:41', '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('d897aaf7c96844e98b2558df12f351c2', '3', '3', '3', 'PC,MOBILE', '录播课程', '1', '1', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('e740d626327141de8c5796bb23a8b60a', '6', '6', '6', 'PC', 'SCORM包', '1', '0', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('fbf6799527b844dabcc9828d890d7581', 'test', 'test', '11', 'PC,MOBILE', '视频', '1', '0', null, '2016-05-17 22:54:32');
INSERT INTO `t_coursewares` VALUES ('ff6d662e633c42329ae6273566df7e46', '10', '10', '10', 'PC', '文档', '1', '0', null, null);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `permission` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '删除用户', null, null, 'user:delete');
INSERT INTO `t_permission` VALUES ('2', '修改用户', null, null, 'user:update');

-- ----------------------------
-- Table structure for t_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_role`;
CREATE TABLE `t_permission_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) DEFAULT '0',
  `role_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission_role
-- ----------------------------
INSERT INTO `t_permission_role` VALUES ('1', '1', '1');
INSERT INTO `t_permission_role` VALUES ('2', '2', '1');
INSERT INTO `t_permission_role` VALUES ('3', '3', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', null, '管理员');
INSERT INTO `t_role` VALUES ('2', null, '经理');
INSERT INTO `t_role` VALUES ('3', null, '小职员');

-- ----------------------------
-- Table structure for t_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_role_user`;
CREATE TABLE `t_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_user
-- ----------------------------
INSERT INTO `t_role_user` VALUES ('1', '3', '1');
INSERT INTO `t_role_user` VALUES ('2', '4', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `name` varchar(10) DEFAULT NULL,
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
  `age` int(3) DEFAULT NULL COMMENT '用户名字',
  `birthday` datetime(6) DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', '3', 'E10ADC3949BA59ABBE56E057F20F883E', '123', null);
INSERT INTO `t_user` VALUES ('1234', '4', '81DC9BDB52D04DC20036DBD8313ED055', '1', null);
INSERT INTO `t_user` VALUES ('22', '5', '81DC9BDB52D04DC20036DBD8313ED055', '22', null);
INSERT INTO `t_user` VALUES ('66', '6', '81DC9BDB52D04DC20036DBD8313ED055', '66', null);
INSERT INTO `t_user` VALUES ('123', '7', '123', '12', null);
