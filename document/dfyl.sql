/*
 Navicat MySQL Data Transfer

 Source Server         : yxy
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : works

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 04/07/2022 16:15:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `doctor` int DEFAULT NULL,
  `time` date DEFAULT NULL,
  `num` int DEFAULT NULL,
  `clinic` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fees` int DEFAULT NULL,
  `generate` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` enum('预约','取消') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '预约',
  PRIMARY KEY (`id`),
  KEY `fk_doctor_idx` (`doctor`),
  KEY `fk_patient` (`patient`),
  CONSTRAINT `fk_doctor` FOREIGN KEY (`doctor`) REFERENCES `doctor` (`id`),
  CONSTRAINT `fk_patient` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=99840 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of appointment
-- ----------------------------
BEGIN;
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (5773, '430103200104020043', 3, '2022-07-05', 1, '呼吸内科', 25, '2022-07-04 14:55:29', '取消');
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (6840, '430103200104020043', 1, '2022-07-04', 4, '神经内科', 35, '2022-07-04 15:45:52', '取消');
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (38699, '430103200104020043', 1, '2022-07-04', 2, '神经内科', 35, '2022-07-04 15:22:46', '取消');
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (47770, '430103200104020043', 1, '2022-07-04', NULL, '神经内科', 35, '2022-07-04 15:26:46', '预约');
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (88153, '430103200104020043', 3, '2022-07-05', 2, '呼吸内科', 25, '2022-07-04 15:23:13', '预约');
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (94414, '430103200104020043', 5, '2022-07-04', 1, '心血管内科', 10, '2022-07-04 14:55:54', '预约');
INSERT INTO `appointment` (`id`, `patient`, `doctor`, `time`, `num`, `clinic`, `fees`, `generate`, `state`) VALUES (98574, '430103200104020043', 1, '2022-07-04', 1, '神经内科', 35, '2022-07-04 14:52:25', '预约');
COMMIT;

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(8) NOT NULL,
  `fees` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of clinic
-- ----------------------------
BEGIN;
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (1, '普通门诊', 10);
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (2, '副主任门诊', 25);
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (3, '主任门诊', 35);
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (4, '知名专家', 80);
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (5, '特需门诊', 300);
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (6, '国际门诊', 600);
INSERT INTO `clinic` (`id`, `title`, `fees`) VALUES (7, '急症门诊', 10);
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(8) NOT NULL,
  `parent` int DEFAULT NULL,
  `about` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_fk_idx` (`parent`),
  CONSTRAINT `parent_fk` FOREIGN KEY (`parent`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (1, '内科', NULL, '');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (2, '外科', NULL, '');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (3, '妇产科', NULL, '');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (4, '儿科', NULL, '');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (5, '神经内科', 1, '神经内科是关于神经方面的二级学科。主要医治脑血管疾病（脑梗塞、脑出血）、偏头痛、脑部炎症性疾病（脑炎、脑膜炎）、脊髓炎、癫痫、痴呆、代谢病和遗传倾向疾病、三叉神经痛、坐骨神经病、周围神经病及重症肌无力等。主要检查手段包括头颈部MRI，CT，ECT，PETCT，脑电图，TCD（经颅多普勒超声），肌电图，诱发电位及血流变学检查、基因诊断等。同时与心理科交叉进行神经衰弱、失眠等功能性疾患的诊治');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (6, '呼吸内科', 1, '呼吸内科疾病主要分为感染性和非感染性，感染性一般就是从感冒到咽喉炎、气管炎、支气管炎，然后肺炎有大叶性肺炎和小叶性肺炎，当然了还要根据病原体分，还有病毒性、细菌性，有非典型的支原体、衣原体这一类，还有就是霉菌引发的，这些都是跟感染相关的，还有一些慢性感染是支气管扩张或者肺脓肿这一类的。非感染性的呼吸内科疾病，最多的当然是哮喘和慢阻肺，慢阻肺也就是以前说的老慢支、肺气肿这一类的。还有间质性肺病是引起肺间质纤维化的一种疾病，还有一些免疫性的肺病，比如肺的血管炎、肉芽肿，还有结节病这一类，都是呼吸内科的疾病。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (7, '消化内科', 1, '消化内科是研究食管、胃、小肠、大肠、肝、胆及胰腺等疾病为主要内容的临床三级学科。消化内科疾病种类繁多，医学知识面广，操作复杂而精细。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (8, '心血管内科', 1, '心内科，即心血管内科，是各级医院大内科为了诊疗心血管疾病而设置的一个临床科室，治疗的疾病包括心绞痛、高血压、猝死、心律失常、心力衰竭、早搏、心律不齐、心肌梗死、心肌病、心肌炎、急性心肌梗死等心血管疾病。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (9, '普通外科', 2, '是以手术为主要方法治疗肝脏、胆道、胰腺、胃肠、肛肠、血管疾病、甲状腺和乳房的肿瘤及外伤等其它疾病的临床学科，是外科系统最大的专科。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (10, '骨科', 2, '骨科是最常见的科室之一，主要研究骨骼肌肉系统的解剖、生理与病理，运用药物、手术及物理方法保持和发展这一系统的正常形态与功能。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (11, '神经外科', 2, '神经外科（Neurosurgery）是外科学中的一个分支，是在外科学以手术为主要治疗手段的基础上，应用独特的神经外科学研究方法，研究人体神经系统，如脑、脊髓和周围神经系统，以及与之相关的附属机构，如颅骨、头皮、脑血管脑膜等结构的损伤、炎症、肿瘤、畸形和某些遗传代谢障碍或功能紊乱疾病，如：癫痫、帕金森病、神经痛等疾病的病因及发病机制，并探索新的诊断、治疗、预防技术的一门高、精、尖学科。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (12, '妇科', 3, '妇科是医疗机构的一个诊疗科目，妇科是妇产科的一个分支专业，是以诊疗女性妇科病为主的专业科室，分为西医妇科与中医妇科。女性生殖系统的疾病即为妇科疾病，包括外阴疾病、阴道疾病、子宫疾病、输卵管疾病、卵巢疾病等。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (13, '产科', 3, '产科是临床医学四大主要学科之一，主要研究女性生殖器官疾病的病因、病理、诊断及防治，妊娠、分娩的生理和病理变化，高危妊娠及难产的预防和诊治，女性生殖内分分泌，计划生育及妇女保健等。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (14, '儿科', 4, '普通儿科致力于儿童内科疾病的诊治，对儿童各系统疾病形成了规范、合理的诊疗模式，包括小儿呼吸、消化、神经、内分泌、肾脏、心血管、感染、儿科急诊等疾病的治疗。');
INSERT INTO `department` (`id`, `title`, `parent`, `about`) VALUES (15, '新生儿科', 4, '新生儿科：主要是指胎儿娩出之后从脐带结扎到生后28天内的新生儿，这都属于新生儿科，但是所治疗的疾病包括了新生儿的内科、外科疾病，其中内科疾病包括了消化、内分泌，还有感染和呼吸等各个系统的疾病，28天以内的新生儿期，所有患病的新生儿都在新生儿科进行治疗。');
COMMIT;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(8) NOT NULL,
  `department` int DEFAULT NULL,
  `level` enum('住院医师','主治医师','副主任医师','主任医师') NOT NULL DEFAULT '住院医师',
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dokcer_fk_department_idx` (`department`),
  CONSTRAINT `dokcer_fk_department` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of doctor
-- ----------------------------
BEGIN;
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (1, '扁鹊', 5, '主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (2, '华佗', 10, '主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (3, '张仲景', 6, '副主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (4, '孙思邈', 7, '主治医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (5, '李时珍', 8, '主治医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (6, '东方宇辉', 5, '主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (7, '东方土锤', 10, '副主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (8, '东方呦呦', 11, '副主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (9, '东方顿顿', 12, '副主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (10, '东方七七', 9, '副主任医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (11, '东方蓓蓓', 14, '主治医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (12, '东方东东', 9, '主治医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (13, '东方杰西', 11, '主治医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (14, '东方大琪', 13, '主治医师', NULL);
INSERT INTO `doctor` (`id`, `name`, `department`, `level`, `password`) VALUES (15, '东方老鱼', 7, '住院医师', NULL);
COMMIT;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` varchar(18) NOT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` char(11) NOT NULL,
  `state` enum('正常','注销') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of patient
-- ----------------------------
BEGIN;
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020043', '赛罗', '15011111112', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020044', '雷欧', '13122222222', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020045', '迪伽', '18633333333', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020046', '赛伽', '15044444444', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020047', '捷徳', '13755555555', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020048', '贝利亚', '13966666666', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430103200104020089', '赛文', '13100000000', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('430626200105234827', 'hello', '18564736538', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('45654656767867', 'yygy', '1232343', '正常');
INSERT INTO `patient` (`id`, `name`, `phone`, `state`) VALUES ('530103200104020043', 'hello', '15789763628', '正常');
COMMIT;

-- ----------------------------
-- Table structure for scheduling
-- ----------------------------
DROP TABLE IF EXISTS `scheduling`;
CREATE TABLE `scheduling` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor` int NOT NULL,
  `time` date NOT NULL,
  `clinic` int NOT NULL,
  `count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `scheduling_fk_doctor_idx` (`doctor`),
  KEY `scheduling_fk_clinic_idx` (`clinic`),
  CONSTRAINT `scheduling_fk_clinic` FOREIGN KEY (`clinic`) REFERENCES `clinic` (`id`),
  CONSTRAINT `scheduling_fk_doctor` FOREIGN KEY (`doctor`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of scheduling
-- ----------------------------
BEGIN;
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (1, 1, '2022-07-04', 3, 21);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (2, 1, '2022-07-10', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (3, 1, '2022-07-06', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (4, 1, '2022-07-07', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (5, 1, '2022-07-08', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (6, 1, '2022-07-09', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (7, 2, '2022-07-04', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (8, 2, '2022-07-05', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (9, 2, '2022-07-06', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (10, 2, '2022-07-07', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (11, 2, '2022-07-08', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (12, 2, '2022-07-09', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (13, 3, '2022-07-04', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (14, 3, '2022-07-05', 2, 28);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (15, 3, '2022-07-06', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (16, 3, '2022-07-07', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (17, 3, '2022-07-08', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (18, 3, '2022-07-09', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (19, 4, '2022-07-04', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (20, 4, '2022-07-05', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (21, 4, '2022-07-06', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (22, 4, '2022-07-07', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (23, 4, '2022-07-08', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (24, 4, '2022-07-09', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (25, 5, '2022-07-04', 7, 29);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (26, 5, '2022-07-05', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (27, 5, '2022-07-06', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (28, 5, '2022-07-07', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (29, 5, '2022-07-08', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (30, 5, '2022-07-09', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (31, 6, '2022-07-04', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (32, 6, '2022-07-05', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (33, 6, '2022-07-06', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (34, 6, '2022-07-07', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (35, 6, '2022-07-08', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (36, 6, '2022-07-09', 3, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (37, 7, '2022-07-04', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (38, 7, '2022-07-05', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (39, 7, '2022-07-06', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (40, 7, '2022-07-07', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (41, 7, '2022-07-08', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (42, 7, '2022-07-09', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (43, 8, '2022-07-04', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (44, 8, '2022-07-05', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (45, 8, '2022-07-06', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (46, 8, '2022-07-07', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (47, 8, '2022-07-08', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (48, 8, '2022-07-09', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (49, 9, '2022-07-04', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (50, 9, '2022-07-05', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (51, 9, '2022-07-06', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (52, 9, '2022-07-07', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (53, 9, '2022-07-08', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (54, 9, '2022-07-09', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (55, 10, '2022-07-04', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (56, 10, '2022-07-05', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (57, 10, '2022-07-06', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (58, 10, '2022-07-07', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (59, 10, '2022-07-08', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (60, 10, '2022-07-09', 2, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (61, 11, '2022-07-04', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (62, 11, '2022-07-05', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (63, 11, '2022-07-06', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (64, 11, '2022-07-07', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (65, 11, '2022-07-08', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (66, 11, '2022-07-09', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (67, 12, '2022-07-04', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (68, 12, '2022-07-05', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (69, 12, '2022-07-06', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (70, 12, '2022-07-07', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (71, 12, '2022-07-08', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (72, 12, '2022-07-09', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (73, 13, '2022-07-04', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (74, 13, '2022-07-05', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (75, 13, '2022-07-06', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (76, 13, '2022-07-07', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (77, 13, '2022-07-08', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (78, 13, '2022-07-09', 1, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (79, 14, '2022-07-04', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (80, 14, '2022-07-05', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (81, 14, '2022-07-06', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (82, 14, '2022-07-07', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (83, 14, '2022-07-08', 7, 30);
INSERT INTO `scheduling` (`id`, `doctor`, `time`, `clinic`, `count`) VALUES (84, 14, '2022-07-09', 7, 30);
COMMIT;

-- ----------------------------
-- View structure for v_appointmentrecord
-- ----------------------------
DROP VIEW IF EXISTS `v_appointmentrecord`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_appointmentrecord` AS select `appointment`.`id` AS `id`,`patient`.`name` AS `pname`,`patient`.`id` AS `id_card`,`appointment`.`num` AS `num`,`appointment`.`time` AS `time`,`appointment`.`clinic` AS `clinic`,`doctor`.`name` AS `dname`,`appointment`.`generate` AS `generate`,`appointment`.`fees` AS `fees`,`appointment`.`state` AS `state` from ((`appointment` join `patient` on((`appointment`.`patient` = `patient`.`id`))) join `doctor` on((`appointment`.`doctor` = `doctor`.`id`)));

-- ----------------------------
-- View structure for v_scheduling
-- ----------------------------
DROP VIEW IF EXISTS `v_scheduling`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_scheduling` AS select `s`.`id` AS `id`,`d`.`id` AS `did`,`d`.`name` AS `doctor`,`s`.`time` AS `time`,`c`.`title` AS `clinic`,`c`.`fees` AS `fees`,`p`.`id` AS `pid`,`p`.`title` AS `department`,`s`.`count` AS `count` from (((`doctor` `d` join `scheduling` `s` on((`s`.`doctor` = `d`.`id`))) join `clinic` `c` on((`s`.`clinic` = `c`.`id`))) join `department` `p` on((`d`.`department` = `p`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
