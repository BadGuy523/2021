/*
 Navicat Premium Data Transfer

 Source Server         : zjq
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : mytest

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 10/04/2022 22:43:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称职别',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `date_of_birth` date NULL DEFAULT NULL COMMENT '出生时间',
  `sys_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sys_create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sys_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `sys_update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES (3496, '梁**', '13800000000', NULL, NULL, '1', '1947-11-01', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3497, '王**', '13800000000', 'test', 'test1@qq.com', '1', '1958-07-01', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3498, '廖**', '13800000000', NULL, NULL, '2', '1958-08-01', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3499, '宋**', '13800000000', NULL, NULL, '1', '1948-08-31', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3500, '林**', '13800000000', NULL, NULL, '1', NULL, '2019-03-28 14:53:44', 'admin', '2019-05-31 11:21:47', 'admin');
INSERT INTO `test_user` VALUES (3501, '黄**', '13800000000', 'test', NULL, '1', '1975-05-18', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3504, '邢**', '13800000000', NULL, NULL, '1', '1971-04-01', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3505, '曾**', '13800000000', 'test', NULL, '1', '1974-08-29', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3506, '屈**', '13800000000', NULL, NULL, '1', '1975-05-18', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3507, '叶**', '13800000000', 'test', NULL, '1', '1969-10-23', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3508, '高**', '13800000000', 'test', NULL, '2', '1978-05-20', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3509, '赖**', '13800000000', 'test', NULL, '2', '1985-03-15', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3511, '连**', '13800000000', 'test', NULL, '1', '1978-03-06', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3513, '欧**', '13800000000', 'test', NULL, '1', '1984-11-10', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3514, '卢**', '13800000000', NULL, NULL, '1', '1980-06-01', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3515, '麦**', '13800000000', NULL, NULL, '2', '1986-12-07', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3516, '吴**', '13800000000', 'test', NULL, '2', '1965-05-17', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3517, '李**', '13800000000', 'test', NULL, '1', '1965-06-18', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3519, '马*', '13800000000', 'test', NULL, '1', '1979-08-29', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (3520, '刘**', '13800000000', NULL, NULL, '1', '1984-09-25', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');
INSERT INTO `test_user` VALUES (6999, '张*', '13800000000', 'test', 'test@qq.com', '1', '1990-06-10', '2019-03-28 14:53:44', 'admin', '2019-05-31 11:17:55', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
