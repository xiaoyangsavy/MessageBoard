/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : board

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2019-04-28 17:12:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息编号',
  `super_message_id` int(11) DEFAULT NULL COMMENT '父消息编号，对应message_id',
  `message_content` text COMMENT '消息内容',
  `message_date` datetime DEFAULT NULL COMMENT '消息发布时间',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `voice_url` varchar(255) DEFAULT NULL COMMENT '音频地址',
  `video_url` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户编号',
  `message_grade` decimal(10,2) DEFAULT NULL COMMENT '用户评分，默认为null',
  `is_replay` tinyint(1) DEFAULT NULL COMMENT '管理员是否回复，仅对父消息生效',
  `type_id` int(11) DEFAULT NULL,
  `message_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='消息内容表';

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', null, '辽宁大学', '2019-04-16 00:00:00', 'E:\\sex.jpg', '', '', '0', null, null, '1', '辽宁大学');
INSERT INTO `message` VALUES ('2', null, '信息学院计算机', '2018-02-03 00:00:00', '', '', '', '0', null, null, '2', null);
INSERT INTO `message` VALUES ('3', null, '机器学习', '2019-04-05 00:00:00', '', '', '', '0', null, null, '3', null);
INSERT INTO `message` VALUES ('5', null, '高等数学', '2019-05-04 00:00:00', '', '', '', '0', null, null, '1', null);
INSERT INTO `message` VALUES ('6', '1', '辽宁大学信息学院', null, null, null, null, '0', '7.80', null, null, null);
INSERT INTO `message` VALUES ('7', '1', '辽宁大学软件工程', null, null, null, null, '0', null, null, null, null);
INSERT INTO `message` VALUES ('8', null, '计算机研究', '2019-04-28 00:00:00', '', '', '', '0', null, null, '2', '计算机');

-- ----------------------------
-- Table structure for `message_type`
-- ----------------------------
DROP TABLE IF EXISTS `message_type`;
CREATE TABLE `message_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息类型编号，数字递增',
  `type_name` varchar(255) NOT NULL COMMENT '消息类型名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='消息类型表';

-- ----------------------------
-- Records of message_type
-- ----------------------------
INSERT INTO `message_type` VALUES ('1', '教务教学');
INSERT INTO `message_type` VALUES ('2', '后勤服务');
INSERT INTO `message_type` VALUES ('3', '学生管理');
INSERT INTO `message_type` VALUES ('4', '书记信箱');
INSERT INTO `message_type` VALUES ('5', '校长信箱');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL COMMENT '权限编号',
  `permission_name` varchar(45) DEFAULT NULL COMMENT '权限名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述：0可以发表留言、对回复评分；-1可以回复全部板块的留言、管理数据；1+可以回复所属板块的留言',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('-1', '超级管理员权限', '可以回复全部板块的留言、管理数据');
INSERT INTO `permission` VALUES ('0', '普通用户权限', '可以发表留言、对回复评分');
INSERT INTO `permission` VALUES ('1', '普通管理员权限', '可以回复所属板块的留言');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `permission_id` int(11) NOT NULL COMMENT '权限编号：0为普通用户，-1为超级管理员，1+为普通管理员',
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT '男' COMMENT '性别',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱地址',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '-1', '超级管理员', '男', '15111111101', 'admin@qq.com', '2006-08-10 17:32:50');
INSERT INTO `user` VALUES ('2', 'user', 'user', '0', '普通用户', '男', '15111111110', 'user@qq.com', '2006-08-10 17:32:50');
INSERT INTO `user` VALUES ('3', 'admin1', 'admin1', '1', '普通管理员', '男', '15111111111', 'admin1@qq.com', '2006-08-10 17:32:50');
INSERT INTO `user` VALUES ('4', 'admin', 'admin', '0', '普通用户', '男', '123456', '123456@qq.com', null);
INSERT INTO `user` VALUES ('5', 'asd', '123345', '0', '普通用户', '男', '123213423', '12314@QQ.COM', null);
INSERT INTO `user` VALUES ('6', 'qwe', '123456', '0', '普通用户', '男', '12345678', '', null);
INSERT INTO `user` VALUES ('7', 'zg', '123456', '0', '普通用户', '男', '123213213', '', null);
INSERT INTO `user` VALUES ('8', '张三', '2222244444', '0', '普通用户', '男', '897866456', '123213@qwe.com', null);
INSERT INTO `user` VALUES ('9', '李四', '2222244444', '0', '普通用户', '男', '897866456', '123213@qwe.com', null);
INSERT INTO `user` VALUES ('10', '李文', '2222244444', '0', '普通用户', '男', '897866456', '123213@qwe.com', null);
INSERT INTO `user` VALUES ('11', '李文', '2222244444', '0', '普通用户', '男', '897866456', '', null);
INSERT INTO `user` VALUES ('12', '李文', '2222244444', '0', '普通用户', '男', '897866456', '', null);
INSERT INTO `user` VALUES ('13', '', '', '0', '普通用户', '', '', '', null);
INSERT INTO `user` VALUES ('14', '', '', '0', '普通用户', '', '', '', null);
INSERT INTO `user` VALUES ('15', '', '', '0', '普通用户', '', '', '', null);
INSERT INTO `user` VALUES ('16', 'qwe', '', '0', '普通用户', '', '', '', null);
INSERT INTO `user` VALUES ('17', 'qw', '123', '1', '普通用户', '', '', '', null);
INSERT INTO `user` VALUES ('18', '张文', '12321313', '2', null, '男', null, null, null);
