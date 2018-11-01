/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : shiro_mvc

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-10-02 14:55:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL,
  `rolename` varchar(32) DEFAULT NULL,
  `model` varchar(32) DEFAULT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `resource_type` varchar(32) DEFAULT NULL,
  `url` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'admin', '查询权限', 'userInfo:view', 'menu', '/findbyId');
INSERT INTO `sys_permission` VALUES ('2', 'admin', '新增权限', 'userInfo:add', 'button', '/addUser');
INSERT INTO `sys_permission` VALUES ('3', 'test', '修改权限', 'userInfo:del', 'button', '/updateUser');
INSERT INTO `sys_permission` VALUES ('4', 'admin', '退出当前账号', 'selectRole', 'role', '/logout');
INSERT INTO `sys_permission` VALUES ('5', 'vip', '删除账号权限', 'userInfo:del', 'vip', 'deleteUserById');
INSERT INTO `sys_permission` VALUES ('6', 'test', 'role', 'role', 'role', '/updateRoleById');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `role` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', '4', 'VIP会员', 'vip');
INSERT INTO `sys_role` VALUES ('3', '2', 'test', 'test');
INSERT INTO `sys_role` VALUES ('4', '3', 'test', 'test');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '管理员', 'df655ad8d3229f3269fad2a8bab59b6c', '8d78869f470951332959580424d4bf4f', '0');
INSERT INTO `user_info` VALUES ('2', 'test', '测试员', '20a93eebcd55783c1e977dd08d9cf933', 'aaaaaaaaaaa', '0');
INSERT INTO `user_info` VALUES ('3', 'test', '测试员', '695467c5618a661ca7ab1b22b0d903c2', 'zzzzz', '0');
INSERT INTO `user_info` VALUES ('4', 'vip', 'vip客户', 'af3f8a7442df6eda151b5b4dc0cf0d5e', 'zzzzz', '0');
