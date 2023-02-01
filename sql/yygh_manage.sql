/*
 Navicat Premium Data Transfer

 Source Server         : linion
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : yygh_manage

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 01/02/2023 19:27:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for booking_rule
-- ----------------------------
DROP TABLE IF EXISTS `booking_rule`;
CREATE TABLE `booking_rule`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hoscode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医院编号',
  `hosname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医院名称',
  `cycle` int(0) NULL DEFAULT NULL COMMENT '预约周期',
  `releaseTime` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '放号时间',
  `stopTime` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停挂时间',
  `quitDay` int(0) NULL DEFAULT NULL COMMENT '退号截止天数（如：就诊前一天为-1，当天为0）',
  `quitTime` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '退号时间',
  `rule` json NULL COMMENT '预约规则',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booking_rule
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hoscode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医院编号',
  `hosname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医院名称',
  `depcode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科室编号',
  `depname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科室名称',
  `bigcode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '大科室编号',
  `bigname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '大科室名称',
  `intro` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科室描述',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '1000_1', '湘雅医院', '0', '全部类别', '666666', '科室管理', '科室总类别', '2022-11-30 21:12:38', '2022-12-04 15:10:02', 0);
INSERT INTO `department` VALUES (2, '1000_1', '湘雅医院', '2432432', '内科', '0', '全部类别', '', '2022-11-30 21:27:42', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (3, '1000_1', '湘雅医院', '2973472', '外科', '0', '全部类别', '', '2022-11-30 21:27:42', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (4, '1000_1', '湘雅医院', '2344243234', '妇产科', '0', '全部类别', '', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (5, '1000_1', '湘雅医院', '4565465565', '儿科', '0', '全部类别', '', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (6, '1000_1', '湘雅医院', '2de288aef', '神经内科', '2432432', '内科', '神经内科是关于神经方面的二级学科。主要医治脑血管疾病（脑梗塞、脑出血）、偏头痛、脑部炎症性疾病（脑炎、脑膜炎）、脊髓炎、癫痫、痴呆、代谢病和遗传倾向疾病、三叉神经痛、坐骨神经病、周围神经病及重症肌无力等。主要检查手段包括头颈部MRI，CT，ECT，PETCT，脑电图，TCD（经颅多普勒超声），肌电图，诱发电位及血流变学检查、基因诊断等。同时与心理科交叉进行神经衰弱、失眠等功能性疾患的诊治', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (7, '1000_1', '湘雅医院', '8c7867e3fa', '呼吸内科', '2432432', '内科', '呼吸内科疾病主要分为感染性和非感染性，感染性一般就是从感冒到咽喉炎、气管炎、支气管炎，然后肺炎有大叶性肺炎和小叶性肺炎，当然了还要根据病原体分，还有病毒性、细菌性，有非典型的支原体、衣原体这一类，还有就是霉菌引发的，这些都是跟感染相关的，还有一些慢性感染是支气管扩张或者肺脓肿这一类的。非感染性的呼吸内科疾病，最多的当然是哮喘和慢阻肺，慢阻肺也就是以前说的老慢支、肺气肿这一类的。还有间质性肺病是引起肺间质纤维化的一种疾病，还有一些免疫性的肺病，比如肺的血管炎、肉芽肿，还有结节病这一类，都是呼吸内科的疾病。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (8, '1000_1', '湘雅医院', '232fesfd2', '消化内科', '2432432', '内科', '消化内科是研究食管、胃、小肠、大肠、肝、胆及胰腺等疾病为主要内容的临床三级学科。消化内科疾病种类繁多，医学知识面广，操作复杂而精细。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (9, '1000_1', '湘雅医院', 'sfsdf545', '心血管内科', '2432432', '内科', '心内科，即心血管内科，是各级医院大内科为了诊疗心血管疾病而设置的一个临床科室，治疗的疾病包括心绞痛、高血压、猝死、心律失常、心力衰竭、早搏、心律不齐、心肌梗死、心肌病、心肌炎、急性心肌梗死等心血管疾病。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (10, '1000_1', '湘雅医院', 'sgfdg65u67', '普通外科', '2973472', '外科', '是以手术为主要方法治疗肝脏、胆道、胰腺、胃肠、肛肠、血管疾病、甲状腺和乳房的肿瘤及外伤等其它疾病的临床学科，是外科系统最大的专科。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (11, '1000_1', '湘雅医院', 'khhgbrfrf3s', '骨科', '2973472', '外科', '骨科是最常见的科室之一，主要研究骨骼肌肉系统的解剖、生理与病理，运用药物、手术及物理方法保持和发展这一系统的正常形态与功能。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (12, '1000_1', '湘雅医院', 'e3425fdfgfg', '神经外科', '2973472', '外科', '神经外科（Neurosurgery）是外科学中的一个分支，是在外科学以手术为主要治疗手段的基础上，应用独特的神经外科学研究方法，研究人体神经系统，如脑、脊髓和周围神经系统，以及与之相关的附属机构，如颅骨、头皮、脑血管脑膜等结构的损伤、炎症、肿瘤、畸形和某些遗传代谢障碍或功能紊乱疾病，如：癫痫、帕金森病、神经痛等疾病的病因及发病机制，并探索新的诊断、治疗、预防技术的一门高、精、尖学科。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (13, '1000_1', '湘雅医院', '4546dfsdfdhgn', '妇科', '2344243234', '妇产科', '妇科是医疗机构的一个诊疗科目，妇科是妇产科的一个分支专业，是以诊疗女性妇科病为主的专业科室，分为西医妇科与中医妇科。女性生殖系统的疾病即为妇科疾病，包括外阴疾病、阴道疾病、子宫疾病、输卵管疾病、卵巢疾病等。', '2022-11-30 21:12:38', '2023-01-07 21:11:08', 0);
INSERT INTO `department` VALUES (14, '1000_1', '湘雅医院', '64uyx38b4xt', '产科', '2344243234', '妇产科', '产科是临床医学四大主要学科之一，主要研究女性生殖器官疾病的病因、病理、诊断及防治，妊娠、分娩的生理和病理变化，高危妊娠及难产的预防和诊治，女性生殖内分分泌，计划生育及妇女保健等。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (15, '1000_1', '湘雅医院', 'xy2r8xn7s', '儿科', '4565465565', '儿科', '普通儿科致力于儿童内科疾病的诊治，对儿童各系统疾病形成了规范、合理的诊疗模式，包括小儿呼吸、消化、神经、内分泌、肾脏、心血管、感染、儿科急诊等疾病的治疗。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);
INSERT INTO `department` VALUES (16, '1000_1', '湘雅医院', 'r9q83ytzcz', '新生儿科', '4565465565', '儿科', '新生儿科：主要是指胎儿娩出之后从脐带结扎到生后28天内的新生儿，这都属于新生儿科，但是所治疗的疾病包括了新生儿的内科、外科疾病，其中内科疾病包括了消化、内分泌，还有感染和呼吸等各个系统的疾病，28天以内的新生儿期，所有患病的新生儿都在新生儿科进行治疗。', '2022-11-30 21:12:38', '2022-12-15 21:52:11', 0);

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `id` bigint(0) NOT NULL COMMENT '医生id',
  `hoscode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `depcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属科室',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医生姓名',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `skill` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '擅长技能',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '挂号费',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `intro` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医生介绍',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (2099335, '1000_1', '2de288aef', 'world', '普通医师', '内科诊治', 120, '14678934563', NULL, '2023-01-08 18:19:00', '2023-01-08 21:00:10', 0);
INSERT INTO `doctor` VALUES (4539218, '1000_1', '2de288aef', 'whole', '普通医师', '内科手术', 120, '16231819237', 'helloelele', '2022-12-28 20:58:21', '2023-01-08 18:19:24', 0);
INSERT INTO `doctor` VALUES (6126837, '1000_1', '2de288aef', 'hello', '主任医师', '内科手术', 100, '17347369732', 'fsafafdsdfsvfvs', '2022-12-15 22:54:07', '2023-01-08 19:02:46', 0);
INSERT INTO `doctor` VALUES (6787443, '1000_1', '2de288aef', 'hi', '主治医师', '内科手术', 200, '15734894678', NULL, '2023-01-08 18:19:58', '2023-01-08 21:11:08', 0);
INSERT INTO `doctor` VALUES (8851362, '1000_1', '2de288aef', 'tom', '住院医师', '住院手续', 150, '24789457932', NULL, '2023-01-08 18:20:49', '2023-01-08 18:20:49', 0);

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hoscode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医院编号',
  `hosname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医院名称',
  `hostype` bigint(0) NULL DEFAULT NULL COMMENT '医院类型',
  `province_code` bigint(0) NULL DEFAULT NULL COMMENT '省code',
  `city_code` bigint(0) NULL DEFAULT NULL COMMENT '市code',
  `district_code` bigint(0) NULL DEFAULT NULL COMMENT '区code',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '具体地址',
  `intro` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医院简介',
  `route` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '坐车路线',
  `logo_data` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医院标志图',
  `booking_rule` json NULL COMMENT '预约规则',
  `status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hospital
-- ----------------------------
INSERT INTO `hospital` VALUES (1, '1000_1', '湘雅医院', 3, 130000, 130400, 130403, '大望路', '湘雅医院是集医疗、教学、科研于一体的大型三级甲等综合医院，是国家卫生计生委指定的全国疑难重症诊治指导中心，也是最早承担高干保健和外宾医疗任务的医院之一，以学科齐全、技术力量雄厚、特色专科突出、多学科综合优势强大享誉海内外。在2010、2011、2012、2013、2014年复旦大学医院管理研究所公布的“中国最佳医院排行榜”中连续五年名列榜首。\\n\\n医院建成于1921年，由洛克菲勒基金会创办。建院之初，就志在“建成亚洲最好的医学中心”。90余年来，医院形成了“严谨、求精、勤奋、奉献”的协和精神和兼容并蓄的特色文化风格，创立了“三基”、“三严”的现代医学教育理念，形成了以“教授、病案、图书馆”著称的协和“三宝”，培养造就了张孝骞、林巧稚等一代医学大师和多位中国现代医学的领军人物，并向全国输送了大批的医学管理人才，创建了当今知名的10余家大型综合及专科医院。2011年在总结90年发展经验的基础上，创新性提出了“待病人如亲人，提高病人满意度；待同事如家人，提高员工幸福感”新办院理念。\\n\\n目前，医院共有2个院区、总建筑面积53万平方米，在职职工4000余名、两院院士5人、临床和医技科室53个、国家级重点学科20个、国家临床重点专科29个、博士点16个、硕士点29个、国家级继续医学教育基地6个、二级学科住院医师培养基地18个、三级学科专科医师培养基地15个。开放住院床位2000余张，单日最高门诊量约1.5万人次、年出院病人约8万余人次。被评为“全国文明单位”、“全国创先争优先进基层党组织”、“全国卫生系统先进集体”、“首都卫生系统文明单位”、“最受欢迎三甲医院”，荣获全国五一劳动奖章。同时，医院还承担着支援老少边穷地区、国家重要活动和突发事件主力医疗队的重任，在2008年北京奥运工作中荣获“特别贡献奖”。\\n\\n90多年来，协和人以执着的医志、高尚的医德、精湛的医术和严谨的学风书写了辉煌的历史，今天的协和人正为打造“国际知名、国内一流”医院的目标而继续努力。', '东院区乘车路线：106、108、110、111、116、684、685路到东单路口北；41、104快、814路到东单路口南；1、52、802路到东单路口西；20、25、37、39路到东单路口东；103、104、420、803路到新东安市场；地铁1、5号线到东单。\\n西院区乘车路线：68路到辟才胡同东口；更多乘车路线详见须知。', 'D:\\Java\\project\\hospital\\front-end\\vue-admin-template-master\\build\\hospLogo\\xiangyayi_hospital.jpg', '{\"rule\": [\"西院区预约号取号地点：西院区门诊楼一层大厅挂号窗口取号\", \"东院区预约号取号地点：东院区老门诊楼一层大厅挂号窗口或新门诊楼各楼层挂号/收费窗口取号\"], \"cycle\": 10, \"quitDay\": -1, \"quitTime\": \"15:30\", \"stopTime\": \"23:20\", \"releaseTime\": \"18:24\"}', 0, '2022-11-28 20:09:44', '2023-01-08 18:09:55', 0);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `schedule_id` bigint(0) NULL DEFAULT NULL COMMENT '排班id',
  `patient_id` bigint(0) NULL DEFAULT NULL COMMENT '就诊人id',
  `number` int(0) NULL DEFAULT NULL COMMENT '预约号序',
  `fetch_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建议取号时间',
  `fetch_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取号地点',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '医事服务费',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `quit_time` datetime(0) NULL DEFAULT NULL COMMENT '退号时间',
  `order_status` tinyint(0) NULL DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hoscode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `depcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室编号',
  `docid` bigint(0) NULL DEFAULT NULL COMMENT '医生id',
  `work_date` date NULL DEFAULT NULL COMMENT '安排日期',
  `work_time` tinyint(0) NULL DEFAULT 0 COMMENT '安排时间（0：上午 1：下午）',
  `reserved_number` int(0) NULL DEFAULT 0 COMMENT '可预约数',
  `available_number` int(0) NULL DEFAULT 0 COMMENT '剩余预约数',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '排班状态（-1：停诊 0：停约 1：可约）',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医生日程安排表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (1, '1000_1', '2de288aef', 6126837, '2023-01-01', 0, 40, 10, 1, '2022-12-15 22:55:07', '2023-01-08 21:09:40', 0);
INSERT INTO `schedule` VALUES (13, '1000_1', '2de288aef', 4539218, '2023-01-07', 0, 50, 50, 1, '2023-01-08 18:21:38', '2023-01-08 21:09:40', 0);
INSERT INTO `schedule` VALUES (14, '1000_1', '2de288aef', 8851362, '2023-01-02', 0, 50, 50, 1, '2023-01-08 18:25:00', '2023-01-08 21:09:40', 0);
INSERT INTO `schedule` VALUES (15, '1000_1', '2de288aef', 2099335, '2023-01-03', 1, 50, 50, 1, '2023-01-08 18:25:41', '2023-01-08 21:09:40', 0);
INSERT INTO `schedule` VALUES (16, '1000_1', '2de288aef', 6787443, '2023-01-06', 0, 50, 50, 1, '2023-01-08 18:28:16', '2023-01-08 21:11:08', 0);
INSERT INTO `schedule` VALUES (17, '1000_1', '2de288aef', 6787443, '2023-01-04', 0, 50, 50, 1, '2023-01-08 18:30:13', '2023-01-08 21:11:08', 0);
INSERT INTO `schedule` VALUES (18, '1000_1', '2de288aef', 6787443, '2023-01-05', 1, 50, 50, 1, '2023-01-08 18:33:25', '2023-01-08 21:11:08', 0);
INSERT INTO `schedule` VALUES (19, '1000_1', '2de288aef', 8851362, '2023-01-01', 0, 45, 45, 1, '2023-01-08 18:45:51', '2023-01-08 21:09:40', 0);

SET FOREIGN_KEY_CHECKS = 1;
