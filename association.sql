/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : association

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 04/05/2021 20:20:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `synopsis` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动描述',
  `level` int(0) NULL DEFAULT NULL COMMENT '活动级别(系统使用枚举)',
  `is_leave` tinyint(1) NULL DEFAULT NULL COMMENT '可否请假 1可以 0不可以',
  `status` int(0) NULL DEFAULT NULL COMMENT '活动审核状态(系统使用枚举)',
  `sign_up_person` int(0) NULL DEFAULT NULL COMMENT '报名人数限制',
  `credit` double(10, 2) NULL DEFAULT NULL COMMENT '活动学分',
  `score` double(11, 1) NULL DEFAULT NULL COMMENT '活动评分',
  `register_start_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '报名开始时间',
  `register_end_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '报名结束时间',
  `activity_start_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '活动开始时间',
  `activity_end_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '活动结束时间',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `activity_type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动类型id  (1,2,3)',
  `label_type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签类型id',
  `organization_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织id',
  `association_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '考研《数据结构》', '哈哈哈哈哈', 2, 0, 0, 100, 1.00, 5.0, '2021-03-29 21:02:41', '2021-03-29 21:02:41', '2021-03-29 21:02:41', '2021-03-29 21:02:41', '2021-03-29 21:02:41', '2021-03-27 17:34:34', '1,2', '1,2', '1', NULL);
INSERT INTO `activity` VALUES (9, '哈哈哈', '的点点滴滴', 2, 1, 2, 200, 1.00, 4.0, '2021-04-03 14:36:00', '2021-04-03 14:36:00', '2021-04-03 14:36:00', '2021-04-03 14:36:00', '2021-04-03 14:37:07', '2021-04-03 14:37:07', '1,2', '1,2,3', '1', NULL);
INSERT INTO `activity` VALUES (11, '和', '发发发', 1, 1, 1, 200, 1.00, 4.0, '2021-04-06 00:02:00', '2021-04-06 00:02:00', '2021-04-06 00:02:00', '2021-04-06 00:02:00', '2021-04-06 00:02:19', '2021-04-06 00:02:19', '1', '1', '1', NULL);
INSERT INTO `activity` VALUES (12, '方法反反复复付', '顶顶顶顶', 1, 1, 0, 200, 1.00, 5.0, '2021-04-06 01:28:33', '2021-04-06 01:28:33', '2021-04-06 01:28:33', '2021-04-06 01:28:33', '2021-04-06 01:28:33', '2021-04-06 01:21:24', '1', '2,3', '1', '4');
INSERT INTO `activity` VALUES (13, '算法设计', '算法方面研究', 2, 0, 0, 200, 1.00, 4.5, '2021-04-08 00:03:41', '2021-04-08 00:03:41', '2021-04-08 00:03:41', '2021-04-08 00:03:41', '2021-04-08 00:03:41', '2021-04-07 20:49:56', '1,2', '1,2', '1', '1');

-- ----------------------------
-- Table structure for activity_register
-- ----------------------------
DROP TABLE IF EXISTS `activity_register`;
CREATE TABLE `activity_register`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '活动表id',
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '用户表id',
  `register_status` int(0) NULL DEFAULT NULL COMMENT '报名审核状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_register
-- ----------------------------
INSERT INTO `activity_register` VALUES (1, 12, 2, 0);
INSERT INTO `activity_register` VALUES (5, 13, 2, 0);

-- ----------------------------
-- Table structure for activity_type
-- ----------------------------
DROP TABLE IF EXISTS `activity_type`;
CREATE TABLE `activity_type`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `synopsis` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_type
-- ----------------------------
INSERT INTO `activity_type` VALUES (1, '主题教育', '关于主题的教育');
INSERT INTO `activity_type` VALUES (2, '主题讲座', '主题的讲座');
INSERT INTO `activity_type` VALUES (3, '社会实践', '社会的实践');

-- ----------------------------
-- Table structure for assoc_member
-- ----------------------------
DROP TABLE IF EXISTS `assoc_member`;
CREATE TABLE `assoc_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL,
  `association_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assoc_member
-- ----------------------------
INSERT INTO `assoc_member` VALUES (7, '技术部', 0, 1, 2, 10);
INSERT INTO `assoc_member` VALUES (9, '技术部', 0, 1, 11, 11);
INSERT INTO `assoc_member` VALUES (10, '社团总部', 0, 4, 11, 11);

-- ----------------------------
-- Table structure for association
-- ----------------------------
DROP TABLE IF EXISTS `association`;
CREATE TABLE `association`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团名称\r\n社团名称',
  `synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团介绍',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of association
-- ----------------------------
INSERT INTO `association` VALUES (1, '创客协会', '创新创业的社团', '2021-04-05 11:34:05', '2021-04-05 11:34:06');
INSERT INTO `association` VALUES (4, '网页设计者协会', '设计网页', '2021-04-05 20:35:35', '2021-04-05 20:35:35');

-- ----------------------------
-- Table structure for label_type
-- ----------------------------
DROP TABLE IF EXISTS `label_type`;
CREATE TABLE `label_type`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签类型',
  `synopsis` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label_type
-- ----------------------------
INSERT INTO `label_type` VALUES (1, '社会责任教育', '社会的责任教育');
INSERT INTO `label_type` VALUES (2, '主题活动教育', '主题活动教育');
INSERT INTO `label_type` VALUES (3, '社会实践活动', '社会实践活动社会实');
INSERT INTO `label_type` VALUES (4, '志愿服务活动', '志愿服务活动');
INSERT INTO `label_type` VALUES (5, '学科竞赛', '学科竞赛');
INSERT INTO `label_type` VALUES (6, '校园文体活动', '校园文体活动');
INSERT INTO `label_type` VALUES (7, '社团活动', '社团活动');
INSERT INTO `label_type` VALUES (8, '公共艺术教育', '公共艺术教育');
INSERT INTO `label_type` VALUES (9, '网络知识竞赛', '网络知识竞赛');
INSERT INTO `label_type` VALUES (10, '创业模拟实训', '创业模拟实训');

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `synopsis` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织描述',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '组织创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '组织更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (1, '计算机与信息工程学院组织部', '计算机与信息工程学院组织部简介', '2021-03-23 20:43:54', '2021-03-23 20:43:58');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `authority` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `role` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '校级管理员', '校组织', '2021-03-24 13:42:15', '2021-03-24 14:43:04');
INSERT INTO `role` VALUES (2, '院级管理员', '院组织', '2021-03-24 13:43:39', '2021-03-24 14:42:53');
INSERT INTO `role` VALUES (7, '用户', '学生', '2021-03-24 15:08:29', '2021-03-24 15:08:29');
INSERT INTO `role` VALUES (8, '用户', '社员', '2021-03-25 14:01:44', '2021-03-25 14:01:46');
INSERT INTO `role` VALUES (10, '院级管理员', '部长', '2021-03-26 14:53:55', '2021-04-05 16:09:38');
INSERT INTO `role` VALUES (11, '院级管理员', '会长', '2021-04-05 16:09:50', '2021-04-05 16:09:50');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `activity_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '活动id',
  `grade` double(10, 1) NULL DEFAULT NULL COMMENT '评论分数',
  `comment` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (15, 2, 13, 4.5, '厉害');
INSERT INTO `score` VALUES (16, 2, 12, 5.0, '厉害');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `credit` double(10, 2) NULL DEFAULT NULL COMMENT '学分',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '1启用 0禁用',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `role_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '角色表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '123456', 100.00, 1, '2021-03-29 21:12:36', '2021-03-29 21:12:36', 1);
INSERT INTO `user` VALUES (2, '张三', 'aa', '12341234123', 70.00, 1, '2021-04-09 13:27:30', '2021-04-09 13:27:31', 7);
INSERT INTO `user` VALUES (11, '沐兮', 'aaa', '17856072536', 0.00, 1, '2021-04-12 17:46:36', '2021-04-12 17:46:36', 2);

SET FOREIGN_KEY_CHECKS = 1;
