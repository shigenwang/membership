/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : membership

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2017-11-20 21:54:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `membership_id` int(11) DEFAULT NULL COMMENT '会员id',
  `membership_name` varchar(255) DEFAULT NULL COMMENT '会员名称',
  `code` varchar(255) DEFAULT NULL COMMENT '会员卡编号',
  `type` int(2) DEFAULT NULL COMMENT '会员卡类型 1月卡 2季卡 3半年卡 4 年卡',
  `createTime` datetime DEFAULT NULL COMMENT '购卡日期',
  `remaining_days` int(11) DEFAULT NULL COMMENT '剩余天数',
  `valid` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `type` varchar(255) DEFAULT NULL COMMENT '课程类型',
  `startTime` datetime DEFAULT NULL COMMENT '课程开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '课程结束时间',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教练id',
  `count` int(11) DEFAULT NULL COMMENT '参加人数',
  `valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '器材名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `code` varchar(255) DEFAULT NULL COMMENT '器材编号',
  `gym_id` int(11) DEFAULT NULL COMMENT '器材关联的健身房',
  `type_id` int(11) DEFAULT NULL COMMENT '器材类型id',
  `damage` int(11) DEFAULT NULL COMMENT '损坏等级',
  `manufacturer` varchar(255) DEFAULT NULL COMMENT '制造商',
  `manufacturer_tel` varchar(255) DEFAULT NULL COMMENT '制造商电话',
  `createTime` datetime DEFAULT NULL COMMENT '生产日期',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用 1 可用 0 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for equipment_type
-- ----------------------------
DROP TABLE IF EXISTS `equipment_type`;
CREATE TABLE `equipment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '类型名字',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用 1 可用 0 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gym
-- ----------------------------
DROP TABLE IF EXISTS `gym`;
CREATE TABLE `gym` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '健身房名字',
  `description` varchar(255) DEFAULT NULL,
  `roomNumber` int(11) DEFAULT NULL COMMENT '健身房号',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '会员id',
  `equipment_id` int(11) DEFAULT NULL COMMENT '器材id',
  `eqt_id` int(11) DEFAULT NULL COMMENT '器材类型id',
  `exerciseTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '锻炼时间',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for maintain
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equipment_id` int(11) DEFAULT NULL COMMENT '器材id',
  `qu_create` datetime DEFAULT NULL COMMENT '器材生产年份',
  `qu_type` varchar(255) DEFAULT NULL COMMENT '器材类型',
  `mauntauners` varchar(255) DEFAULT NULL COMMENT '维护人姓名',
  `maintenance_time` datetime DEFAULT NULL COMMENT '维护时间',
  `valid` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for membership
-- ----------------------------
DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `tel` varchar(255) DEFAULT NULL COMMENT '电话',
  `company` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `level_id` int(11) DEFAULT NULL COMMENT '会员级别id',
  `address` varchar(255) DEFAULT NULL COMMENT '会员地址',
  `audit` int(2) DEFAULT NULL COMMENT '是否审核 1审核通过 0审核中',
  `email` varchar(255) DEFAULT NULL,
  `fitnessNumber` int(11) DEFAULT NULL COMMENT '已经健身次数',
  `leftTimes` int(11) DEFAULT NULL COMMENT '所剩次数',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for membershiplevel
-- ----------------------------
DROP TABLE IF EXISTS `membershiplevel`;
CREATE TABLE `membershiplevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `levelName` varchar(255) DEFAULT NULL COMMENT '级别名称',
  `years` int(11) DEFAULT NULL COMMENT '年限',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用 1 可用 0 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for participate
-- ----------------------------
DROP TABLE IF EXISTS `participate`;
CREATE TABLE `participate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `membership_id` int(11) DEFAULT NULL COMMENT '会员id',
  `membership_name` varchar(255) DEFAULT NULL COMMENT '会员名称',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `course_type` varchar(255) DEFAULT NULL COMMENT '课程类型',
  `valid` int(1) DEFAULT NULL COMMENT '是否正常 1正常 0删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pt
-- ----------------------------
DROP TABLE IF EXISTS `pt`;
CREATE TABLE `pt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `membership_id` int(11) DEFAULT NULL COMMENT '会员id',
  `membership_name` varchar(255) DEFAULT NULL COMMENT '会员名称',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `teacher_name` varchar(255) DEFAULT NULL COMMENT '教师名称',
  `valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(255) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `parent_id` int(255) DEFAULT NULL COMMENT '父编号id',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串',
  `valid` int(11) DEFAULT NULL COMMENT '是否可用 1可用 0 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '授权的资源',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用 1可用 2 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `rank` varchar(255) DEFAULT NULL COMMENT '教师等级',
  `valid` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL COMMENT '性别 1 男 2 女',
  `rolesids` varchar(100) DEFAULT NULL COMMENT '角色id列表',
  `locked` int(1) DEFAULT NULL COMMENT '账号是否锁定 1 正常 0锁定',
  `valid` int(1) DEFAULT NULL COMMENT '是否可用 1可用 0 不可用',
  `type` int(2) DEFAULT NULL COMMENT '身份 1 员工  2 会员  3 教练',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastloginTime` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
