/*
 Navicat Premium Data Transfer

 Source Server         : linion
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : yygh_hosp

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 01/02/2023 19:27:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hospital_set
-- ----------------------------
DROP TABLE IF EXISTS `hospital_set`;
CREATE TABLE `hospital_set`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `hosname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `hoscode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `api_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api基础路径',
  `sign_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名秘钥',
  `contacts_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contacts_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机',
  `status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_hoscode`(`hoscode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医院设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hospital_set
-- ----------------------------
INSERT INTO `hospital_set` VALUES (1, '湘雅医院', '1000_1', 'https://100861', '345345353', 'zhangsan', '13232332345', 1, '2022-11-18 15:15:10', '2023-01-08 18:09:37', 0);
INSERT INTO `hospital_set` VALUES (2, '北京国际医院', '1000_2', 'https://1008611', '25255445656', 'lisi', '12313242123', 1, '2022-11-18 15:15:26', '2022-11-21 19:36:09', 0);
INSERT INTO `hospital_set` VALUES (3, '北京妇产医院', '1000_3', 'https://1008612', '2344243234', 'wangwu', '43454544554', 1, '2022-11-18 15:16:22', '2022-11-21 19:35:43', 0);
INSERT INTO `hospital_set` VALUES (4, '中南医院', '1000_4', 'https://1008613', '4565465565', 'zhaoliu', '56757575243', 1, '2022-11-18 15:16:39', '2023-01-08 18:09:39', 0);
INSERT INTO `hospital_set` VALUES (5, '北京协和医院', '1000_5', 'https://1008614', 'ad75ca710c125bd946d23308c7a22cf4', 'hello', '18628739282', 1, '2022-11-21 18:11:04', '2022-12-02 09:59:34', 0);
INSERT INTO `hospital_set` VALUES (6, '北京人大医院', '1000_6', 'https://1008616', '1991b6b2fd715bb4dac7923d6c066a5d', 'hi', '16738475937', 1, '2022-11-21 19:09:23', '2022-12-02 09:59:34', 0);
INSERT INTO `hospital_set` VALUES (7, '北京光合医院', '1000_7', 'http://100851', '3c871c45ebbc42de288aef8c7867e3fa', 'hellod', '12746482984', 1, '2022-11-25 11:00:06', '2022-12-02 10:59:25', 0);

SET FOREIGN_KEY_CHECKS = 1;
