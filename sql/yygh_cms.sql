/*
 Navicat Premium Data Transfer

 Source Server         : linion
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : yygh_cms

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 01/02/2023 19:27:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `image_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `link_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 1 COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '首页实体表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
