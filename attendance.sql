/*
 Navicat Premium Data Transfer

 Source Server         : XLin
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : attendance

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 12/10/2020 17:48:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_attendance
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance`;
CREATE TABLE `t_attendance`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `time` date NULL DEFAULT NULL COMMENT '打卡日期',
  `morning` time(0) NULL DEFAULT NULL COMMENT '上班打卡',
  `afternoon` time(0) NULL DEFAULT NULL COMMENT '下班打卡',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '0 工作日 1周末 2法定节假日 3工作日调休 4当前时间处于工作日但是经过周末调休',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '0 正常上下班打卡 1 迟到/早退 2只有一次打卡记录的异常记录 3下班打卡时间跨天 4下班时间跨天并且上班时间是迟到',
  `userId` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考勤记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attendance
-- ----------------------------
INSERT INTO `t_attendance` VALUES (1, '2018-11-02', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (2, '2018-11-05', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (3, '2018-11-06', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (4, '2018-11-07', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (5, '2018-11-08', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (6, '2018-11-09', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (7, '2018-11-12', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (8, '2018-11-13', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (9, '2018-11-14', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (10, '2018-11-15', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (11, '2018-11-16', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (12, '2018-11-19', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (13, '2018-11-20', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (14, '2018-11-21', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (15, '2018-11-22', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (16, '2018-11-23', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (17, '2018-11-26', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (18, '2018-11-27', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (19, '2018-11-28', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (20, '2018-11-29', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (21, '2018-11-30', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (22, '2018-12-05', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (23, '2018-12-06', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (24, '2018-12-07', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (25, '2018-12-10', '09:15:00', '18:01:01', 0, 1, 4);
INSERT INTO `t_attendance` VALUES (26, '2018-12-11', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (27, '2018-12-12', '08:15:00', '05:01:01', 0, 3, 4);
INSERT INTO `t_attendance` VALUES (28, '2018-12-20', '08:15:00', '05:01:01', 0, 3, 4);
INSERT INTO `t_attendance` VALUES (29, '2018-12-26', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (30, '2018-12-27', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (31, '2018-12-28', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (32, '2018-12-29', '08:15:00', '18:01:01', 3, 0, 4);
INSERT INTO `t_attendance` VALUES (33, '2018-12-08', '08:15:00', '18:01:01', 1, 0, 4);
INSERT INTO `t_attendance` VALUES (34, '2018-12-30', '08:15:00', '18:01:01', 1, 0, 4);
INSERT INTO `t_attendance` VALUES (35, '2018-12-31', '08:15:00', '22:01:01', 4, 0, 4);
INSERT INTO `t_attendance` VALUES (36, '2019-01-02', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (37, '2019-01-03', '08:15:00', '15:01:01', 0, 1, 4);
INSERT INTO `t_attendance` VALUES (38, '2019-01-04', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (39, '2019-01-07', '10:15:00', '18:01:01', 0, 1, 4);
INSERT INTO `t_attendance` VALUES (40, '2019-01-08', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (41, '2019-01-09', '10:15:00', '15:01:01', 0, 1, 4);
INSERT INTO `t_attendance` VALUES (42, '2019-01-10', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (43, '2019-01-11', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (44, '2019-01-14', NULL, '18:01:01', 0, 2, 4);
INSERT INTO `t_attendance` VALUES (45, '2019-01-15', '09:15:00', '03:01:01', 0, 4, 4);
INSERT INTO `t_attendance` VALUES (46, '2019-01-16', '08:15:00', '18:01:01', 0, 0, 4);
INSERT INTO `t_attendance` VALUES (47, '2019-01-17', NULL, '05:01:01', 0, 2, 4);
INSERT INTO `t_attendance` VALUES (48, '2019-01-22', '08:15:00', '04:01:01', 0, 3, 4);

-- ----------------------------
-- Table structure for t_attendance_retroactive
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance_retroactive`;
CREATE TABLE `t_attendance_retroactive`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `time` date NULL DEFAULT NULL COMMENT '打卡日期',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '0 审核未通过 1 审核通过',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考勤补卡表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_business_trip
-- ----------------------------
DROP TABLE IF EXISTS `t_business_trip`;
CREATE TABLE `t_business_trip`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `start_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `working_days` tinyint(0) NULL DEFAULT NULL COMMENT '工作日天数',
  `rest_days` tinyint(0) NULL DEFAULT NULL COMMENT '休息日天数',
  `holiday_days` tinyint(0) NULL DEFAULT NULL COMMENT '节假日天数',
  `apply_reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请原因',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '审核状态 0 未通过 1通过 2驳回',
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_business_trip
-- ----------------------------
INSERT INTO `t_business_trip` VALUES (1, '2018-12-21', '2018-12-25', 3, 2, 0, '商务出差', '2018-12-20 14:25:08', '2018-12-21 14:27:09', 1, '福建省', '福州市', '鼓楼区', 4);
INSERT INTO `t_business_trip` VALUES (2, '2019-01-23', '2019-01-27', 3, 2, 0, '商务出差', '2019-01-22 18:27:16', '2019-01-28 11:00:20', 1, '福建省', '福州市', '鼓楼区', 4);

-- ----------------------------
-- Table structure for t_holiday
-- ----------------------------
DROP TABLE IF EXISTS `t_holiday`;
CREATE TABLE `t_holiday`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `date` date NULL DEFAULT NULL COMMENT '具体日期',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '节日名称或者调休',
  `wage` tinyint(0) NULL DEFAULT NULL COMMENT '工资倍数',
  `year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '节日信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_holiday
-- ----------------------------
INSERT INTO `t_holiday` VALUES (1, '2019-01-01', '元旦', 3, '2019');
INSERT INTO `t_holiday` VALUES (2, '2019-02-02', '春节前调休', 1, '2019');
INSERT INTO `t_holiday` VALUES (3, '2019-02-03', '春节前调休', 1, '2019');
INSERT INTO `t_holiday` VALUES (4, '2019-02-04', '除夕', 3, '2019');
INSERT INTO `t_holiday` VALUES (5, '2019-02-05', '春节', 3, '2019');
INSERT INTO `t_holiday` VALUES (6, '2019-02-06', '春节', 3, '2019');
INSERT INTO `t_holiday` VALUES (7, '2019-02-07', '春节', 3, '2019');
INSERT INTO `t_holiday` VALUES (8, '2019-02-08', '春节', 2, '2019');
INSERT INTO `t_holiday` VALUES (9, '2019-02-09', '春节', 2, '2019');
INSERT INTO `t_holiday` VALUES (10, '2019-02-10', '春节', 2, '2019');
INSERT INTO `t_holiday` VALUES (11, '2019-04-05', '清明节', 3, '2019');
INSERT INTO `t_holiday` VALUES (12, '2019-04-06', '清明节', 2, '2019');
INSERT INTO `t_holiday` VALUES (13, '2019-04-07', '清明节', 2, '2019');
INSERT INTO `t_holiday` VALUES (14, '2019-05-01', '劳动节', 3, '2019');
INSERT INTO `t_holiday` VALUES (15, '2019-06-07', '端午节', 3, '2019');
INSERT INTO `t_holiday` VALUES (16, '2019-06-08', '端午节', 2, '2019');
INSERT INTO `t_holiday` VALUES (17, '2019-06-09', '端午节', 2, '2019');
INSERT INTO `t_holiday` VALUES (18, '2019-09-13', '中秋节', 3, '2019');
INSERT INTO `t_holiday` VALUES (19, '2019-09-14', '中秋节', 2, '2019');
INSERT INTO `t_holiday` VALUES (20, '2019-09-15', '中秋节', 2, '2019');
INSERT INTO `t_holiday` VALUES (21, '2019-09-29', '国庆节前调休', 1, '2019');
INSERT INTO `t_holiday` VALUES (22, '2019-10-01', '国庆节', 3, '2019');
INSERT INTO `t_holiday` VALUES (23, '2019-10-02', '国庆节', 3, '2019');
INSERT INTO `t_holiday` VALUES (24, '2019-10-03', '国庆节', 3, '2019');
INSERT INTO `t_holiday` VALUES (25, '2019-10-04', '国庆节', 2, '2019');
INSERT INTO `t_holiday` VALUES (26, '2019-10-05', '国庆节', 2, '2019');
INSERT INTO `t_holiday` VALUES (27, '2019-10-06', '国庆节', 2, '2019');
INSERT INTO `t_holiday` VALUES (28, '2019-10-07', '国庆节', 2, '2019');
INSERT INTO `t_holiday` VALUES (29, '2019-10-12', '国庆节后调休', 1, '2019');

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `start_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `type_id` tinyint(0) NULL DEFAULT NULL COMMENT '0 审核中 1审核通过 2驳回',
  `apply_reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请原因',
  `number_day` tinyint(0) NULL DEFAULT NULL COMMENT '扣除节假日和双休日后的天数',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '申请用户id',
  `audit_id` bigint(0) NULL DEFAULT NULL COMMENT '审核用户id',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '0审核中 1审核通过 2驳回',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES (1, '2018-12-13', '2018-12-17', 1, '朋友结婚', 3, '2018-12-12 18:45:59', '2019-01-12 18:46:12', 4, 2, 1);
INSERT INTO `t_leave` VALUES (2, '2018-12-03', '2018-12-04', 3, '年假', 2, '2018-11-30 19:00:51', '2018-12-03 19:01:03', 4, 2, 1);
INSERT INTO `t_leave` VALUES (3, '2018-12-18', '2018-12-19', 4, '调休', 2, '2018-12-17 19:02:42', '2018-12-18 19:02:47', 4, 2, 1);
INSERT INTO `t_leave` VALUES (4, '2018-11-01', '2018-11-01', 2, '病假', 1, '2018-10-31 16:51:42', '2018-10-31 16:51:47', 4, 2, 1);
INSERT INTO `t_leave` VALUES (5, '2019-01-18', '2019-01-21', 1, '事假', 2, '2019-01-27 18:10:56', '2019-01-28 10:53:02', 4, 2, 1);

-- ----------------------------
-- Table structure for t_leave_type
-- ----------------------------
DROP TABLE IF EXISTS `t_leave_type`;
CREATE TABLE `t_leave_type`  (
  `id` tinyint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请假类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_leave_type
-- ----------------------------
INSERT INTO `t_leave_type` VALUES (1, '事假');
INSERT INTO `t_leave_type` VALUES (2, '病假');
INSERT INTO `t_leave_type` VALUES (3, '年假');
INSERT INTO `t_leave_type` VALUES (4, '调休');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` tinyint(0) NULL DEFAULT NULL,
  `pid` bigint(0) NULL DEFAULT NULL,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuType` tinyint(0) NULL DEFAULT 0 COMMENT '菜单类型 0 后台菜单 1 前台菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '系统设置', 98, 0, NULL, NULL, 'icon iconfont icon-nav-system-set', 0);
INSERT INTO `t_menu` VALUES (2, '菜单管理', 1, 1, 'menu', '/menu/menuList', NULL, 0);
INSERT INTO `t_menu` VALUES (3, '角色管理', 2, 1, 'role', '/role/roleList', NULL, 0);
INSERT INTO `t_menu` VALUES (4, '权限管理', 3, 1, 'systemPath', '/resourcesPath/resourcesList', NULL, 0);
INSERT INTO `t_menu` VALUES (5, '用户管理', 4, 1, 'user', '/user/userList', NULL, 0);
INSERT INTO `t_menu` VALUES (6, '其他管理', 99, 0, NULL, NULL, NULL, 2);
INSERT INTO `t_menu` VALUES (7, '其他权限', 1, 6, 'other', 'other', NULL, 2);
INSERT INTO `t_menu` VALUES (8, '考勤管理', 1, 0, NULL, NULL, 'icon iconfont icon-kaoqinguanli', 0);
INSERT INTO `t_menu` VALUES (9, '考勤记录', 1, 8, 'signIn', '/attendance/signIn', NULL, 0);
INSERT INTO `t_menu` VALUES (10, '请假记录', 2, 8, 'leave', '/attendance/leave', NULL, 0);
INSERT INTO `t_menu` VALUES (11, '出差记录', 3, 8, 'businessTrip', '/attendance/businessTrip', NULL, 0);
INSERT INTO `t_menu` VALUES (12, '补卡记录', 4, 8, 'retroactive', '/attendance/retroactive', NULL, 0);
INSERT INTO `t_menu` VALUES (13, '我的薪酬', 2, 0, NULL, NULL, 'icon iconfont icon-qian', 0);
INSERT INTO `t_menu` VALUES (14, '我的薪酬', 1, 13, 'wage', '/wage', NULL, 0);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `resourcesId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源Id集',
  `roleId` tinyint(0) NULL DEFAULT NULL COMMENT '角色Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '26,37,27,28,32,35,38,41,40,2,5,6,1,14,8,22,3,10,11,15,16,18,43,21,17,25,7,9,13,12,23,24,4,19,20', 1);
INSERT INTO `t_permission` VALUES (2, '26,37,27,28,32,35,38,41,40,15,16,18,43,21,17,25,23,24,4,19,20', 2);
INSERT INTO `t_permission` VALUES (3, '26,37,27,29,30,31,32,33,34,36,38,39,42,40,43,21,17,25,4,19,20', 3);

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源编码',
  `path` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源值',
  `menuId` bigint(0) NULL DEFAULT NULL COMMENT '所属菜单ID 默认存在一个为0的特别功能菜单',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '所属类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES (1, '角色管理', 'role:list', '/role/roleList', 3, 1);
INSERT INTO `t_resources` VALUES (2, '菜单管理', 'menu:other', '/menu/menuList', 2, 1);
INSERT INTO `t_resources` VALUES (3, '权限管理', 'systemPath:list', '/resourcesPath/resourcesList', 4, 1);
INSERT INTO `t_resources` VALUES (4, '上传图片', 'Upload:other', '/upload/setFileUpload', 7, 11);
INSERT INTO `t_resources` VALUES (5, '新增或者修改菜单列表', 'menu:update', '/menu/editMenu', 2, 2);
INSERT INTO `t_resources` VALUES (6, '菜单保存', 'menu:save', '/menu/save', 2, 3);
INSERT INTO `t_resources` VALUES (7, '菜单编码是否重复', 'menu:other', '/menu/checkCode', 2, 11);
INSERT INTO `t_resources` VALUES (8, '角色权限保存', 'role:save', '/permission/save', 3, 3);
INSERT INTO `t_resources` VALUES (9, '角色权限展示', 'role:other', '/permission/showPermission', 3, 11);
INSERT INTO `t_resources` VALUES (10, '新增或者修改权限', 'systemPath:update', '/resourcesPath/editResources', 4, 2);
INSERT INTO `t_resources` VALUES (11, '权限保存', 'systemPath:save', '/resourcesPath/save', 4, 3);
INSERT INTO `t_resources` VALUES (12, '检测资源编码是否重复', 'systemPath:other', '/resourcesPath/checkRepeat', 4, 11);
INSERT INTO `t_resources` VALUES (13, '角色禁用', 'role:other', '/role/roleDisable', 3, 11);
INSERT INTO `t_resources` VALUES (14, '新增角色', 'role:update', '/role/roleEdit', 3, 2);
INSERT INTO `t_resources` VALUES (15, '用户管理', 'user:list', '/user/userList', 5, 1);
INSERT INTO `t_resources` VALUES (16, '用户新增和修改编辑', 'user:update', '/user/editUserView', 5, 2);
INSERT INTO `t_resources` VALUES (17, '用户信息', 'other:update', '/user/userInfo', 7, 2);
INSERT INTO `t_resources` VALUES (18, '新增用户或者修改用户保存', 'user:save', '/user/editUser', 5, 3);
INSERT INTO `t_resources` VALUES (19, '修改密码', 'editPassword:other', '/user/editPassword', 7, 11);
INSERT INTO `t_resources` VALUES (20, '修改密码保存', 'savePassword:other', '/user/savePassword', 7, 11);
INSERT INTO `t_resources` VALUES (21, '后台首页', 'other:list', '/index', 7, 1);
INSERT INTO `t_resources` VALUES (22, '角色保存', 'role:save', '/role/save', 3, 3);
INSERT INTO `t_resources` VALUES (23, '用户状态修改', 'user:other', '/user/updateStatus', 5, 11);
INSERT INTO `t_resources` VALUES (24, '检验用户名是否重复', 'checkUsername:other', '/user/queryExist', 5, 11);
INSERT INTO `t_resources` VALUES (25, '修改自身资料保存', 'other:save', '/user/saveUserInfo', 7, 3);
INSERT INTO `t_resources` VALUES (26, '考勤记录', 'signIn:list', '/attendance/signIn', 9, 1);
INSERT INTO `t_resources` VALUES (27, '请假记录', 'leave:list', '/attendance/leave', 10, 1);
INSERT INTO `t_resources` VALUES (28, '修改请假状态', 'leave:audit', '/attendance/updateState', 10, 5);
INSERT INTO `t_resources` VALUES (29, '新增或者修改请假记录', 'leave:update', '/attendance/leaveEdit', 10, 2);
INSERT INTO `t_resources` VALUES (30, '请假记录保存', 'leave:save', '/attendance/leaveSave', 10, 3);
INSERT INTO `t_resources` VALUES (31, '请假记录删除', 'leave:delete', '/attendance/delete', 10, 4);
INSERT INTO `t_resources` VALUES (32, '出差记录查看', 'businessTrip:list', '/attendance/businessTrip', 11, 1);
INSERT INTO `t_resources` VALUES (33, '出差记录申请', 'businessTrip:update', '/attendance/businessTripEdit', 11, 2);
INSERT INTO `t_resources` VALUES (34, '出差记录保存', 'businessTrip:save', '/attendance/businessTripSave', 11, 3);
INSERT INTO `t_resources` VALUES (35, '出差审核', 'businessTrip:audit', '/attendance/updateBusinessState', 11, 5);
INSERT INTO `t_resources` VALUES (36, '出差记录删除', 'businessTrip:delete', '/attendance/deleteBusinessTrip', 11, 4);
INSERT INTO `t_resources` VALUES (37, '考勤打卡', 'signIn:save', '/attendance/saveSignIn', 9, 3);
INSERT INTO `t_resources` VALUES (38, '补卡记录', 'retroactive:list', '/attendance/retroactive', 12, 1);
INSERT INTO `t_resources` VALUES (39, '补卡记录提交', 'retroactive:save', '/attendance/retroactiveSave', 12, 3);
INSERT INTO `t_resources` VALUES (40, '补卡记录查看', 'retroactive:view', '/attendance/retroactiveList', 12, 7);
INSERT INTO `t_resources` VALUES (41, '补卡审核', 'retroactive:audit', '/attendance/updateRetroactiveState', 12, 5);
INSERT INTO `t_resources` VALUES (42, '补卡记录删除', 'retroactive:delete', '/attendance/deleteRetroactive', 12, 4);
INSERT INTO `t_resources` VALUES (43, '我的薪酬查看', 'wage:list', '/wage', 14, 1);

-- ----------------------------
-- Table structure for t_resources_type
-- ----------------------------
DROP TABLE IF EXISTS `t_resources_type`;
CREATE TABLE `t_resources_type`  (
  `id` tinyint(0) NOT NULL AUTO_INCREMENT COMMENT '权限类型编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型名称',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型编码',
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resources_type
-- ----------------------------
INSERT INTO `t_resources_type` VALUES (1, '查看', 'list');
INSERT INTO `t_resources_type` VALUES (2, '编辑', 'update');
INSERT INTO `t_resources_type` VALUES (3, '保存', 'save');
INSERT INTO `t_resources_type` VALUES (4, '删除', 'delete');
INSERT INTO `t_resources_type` VALUES (5, '审核', 'audit');
INSERT INTO `t_resources_type` VALUES (6, '消审', 'disaudit');
INSERT INTO `t_resources_type` VALUES (7, '预览', 'view');
INSERT INTO `t_resources_type` VALUES (8, '打印', 'print');
INSERT INTO `t_resources_type` VALUES (9, '导入', 'imp');
INSERT INTO `t_resources_type` VALUES (10, '导出', 'exp');
INSERT INTO `t_resources_type` VALUES (11, '其它功能', 'other');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` tinyint(0) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `roleCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT 0 COMMENT '是否有效  0有效  1无效',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', 'admin', NULL, 0, '2018-10-24 19:36:19', '2018-10-24 19:36:22');
INSERT INTO `t_role` VALUES (2, '管理员', 'administrator', NULL, 0, '2018-12-28 11:58:56', '2018-12-29 17:29:47');
INSERT INTO `t_role` VALUES (3, '员工', 'employees', NULL, 0, '2018-10-24 19:37:16', '2019-01-14 14:40:29');

-- ----------------------------
-- Table structure for t_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_system_parameter`;
CREATE TABLE `t_system_parameter`  (
  `id` tinyint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `money` double(11, 2) NULL DEFAULT 0.00 COMMENT '金额',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '类型',
  `wage` tinyint(0) NULL DEFAULT NULL COMMENT '薪酬倍数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_parameter
-- ----------------------------
INSERT INTO `t_system_parameter` VALUES (1, '工作日加班补贴', 50.00, 1, 1);
INSERT INTO `t_system_parameter` VALUES (2, '礼拜天加班补贴', 100.00, 1, 2);
INSERT INTO `t_system_parameter` VALUES (3, '节假日加班补贴', 150.00, 1, 3);
INSERT INTO `t_system_parameter` VALUES (4, '工作日出差补贴', 100.00, 2, 1);
INSERT INTO `t_system_parameter` VALUES (5, '礼拜天出差补贴', 200.00, 2, 2);
INSERT INTO `t_system_parameter` VALUES (6, '节假日出差补贴', 300.00, 2, 3);
INSERT INTO `t_system_parameter` VALUES (8, '迟到/早退扣款', 200.00, 3, 0);
INSERT INTO `t_system_parameter` VALUES (9, '只有一次打卡扣款', 200.00, 4, 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '是否有效  0有效  1无效',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '该用户的创建方式 0：管理员创建 1：用户注册',
  `photo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定邮箱',
  `wage` double(11, 2) NULL DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'RE7C1DRIE1M7PVUQGUACNV3DF', '15962528888', '中国', '超级管理员', 1, '2017-10-30 16:13:02', '2019-01-27 18:48:49', 0, 0, 'imgUpload/20181229/admin/56aee88cdbe646af8e81c65f038f99d3.jpeg', '1651231412@qq.com', 10000.00);
INSERT INTO `t_user` VALUES (2, 'guoxiaowei', '2P6K23EPNJM73FKQH7HC4LVGJ8', '15962528888', '中国', '郭小微', 2, '2018-10-17 09:34:46', '2019-01-27 20:46:07', 0, 0, 'imgUpload/20181229/xx/fcc49e9de99a4ead9d6b25b40bb5285b.jpeg', '1651231412@qq.com', 9000.00);
INSERT INTO `t_user` VALUES (4, 'test', '2P6K23EPNJM73FKQH7HC4LVGJ8', '15962526666', '中国', '赵依依', 3, '2019-01-09 15:54:19', '2019-01-27 18:48:53', 0, 1, NULL, '1651231412@qq.com', 8000.00);
INSERT INTO `t_user` VALUES (6, 'wangyiyi', '2P6K23EPNJM73FKQH7HC4LVGJ8', '13123175444', '上海上海市松江区', '王一一', 3, '2019-01-27 18:54:11', '2019-01-27 19:09:38', 0, 1, NULL, 'ndlinweiyu@163.com', 10000.00);

-- ----------------------------
-- Table structure for t_wage
-- ----------------------------
DROP TABLE IF EXISTS `t_wage`;
CREATE TABLE `t_wage`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `yearMonth` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工资所属年月',
  `wage` double(11, 2) NULL DEFAULT NULL COMMENT '工资',
  `leave_day` tinyint(0) NULL DEFAULT NULL COMMENT '请假天数',
  `leave_amount` double(11, 2) NULL DEFAULT NULL COMMENT '请假扣款',
  `business_day` tinyint(0) NULL DEFAULT NULL COMMENT '出差天数',
  `business_amount` double(11, 2) NULL DEFAULT NULL COMMENT '出差补贴合计',
  `overtime_day` tinyint(0) NULL DEFAULT NULL COMMENT '加班天数',
  `overtime_amount` double(11, 2) NULL DEFAULT NULL COMMENT '加班补贴',
  `total_amount` double(11, 2) NULL DEFAULT NULL COMMENT '合计工资',
  `pension` double(11, 2) NULL DEFAULT NULL COMMENT '养老保险',
  `unemployment_benefits` double(11, 2) NULL DEFAULT NULL COMMENT '失业保险',
  `medical` double(11, 2) NULL DEFAULT NULL COMMENT '医疗保险',
  `housing_fund` double(11, 2) NULL DEFAULT NULL COMMENT '住房公积金',
  `five_insurances` double(11, 2) NULL DEFAULT NULL COMMENT '五险一金总扣款',
  `gross_salary` double(11, 2) NULL DEFAULT NULL COMMENT '税前工资',
  `taxable_company` double(11, 2) NULL DEFAULT NULL COMMENT '应税工资',
  `rate` float NULL DEFAULT NULL COMMENT '税率',
  `personal_income_tax` double(11, 2) NULL DEFAULT NULL COMMENT '个人所得税',
  `real_wages` double(11, 2) NULL DEFAULT NULL COMMENT '实际工资',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `other_deduct` double(11, 2) NULL DEFAULT NULL COMMENT '其他扣款',
  `attendance` tinyint(0) NULL DEFAULT NULL COMMENT '出勤天数',
  `daily_wage` double(11, 2) NULL DEFAULT NULL COMMENT '日薪',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工资表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wage
-- ----------------------------
INSERT INTO `t_wage` VALUES (1, '2018-11', 8000.00, 1, 363.64, 0, 0.00, 0, 0.00, 7636.36, 640.00, 40.00, 160.00, 960.00, 1800.00, 5836.36, 836.36, 0.03, 25.09, 5811.27, 4, 0.00, 21, 363.64);
INSERT INTO `t_wage` VALUES (2, '2018-12', 8000.00, 3, 1043.48, 5, 700.00, 5, 400.00, 8900.00, 640.00, 40.00, 160.00, 960.00, 1800.00, 7100.00, 2100.00, 0.03, 63.00, 7037.00, 4, 200.00, 23, 347.83);

SET FOREIGN_KEY_CHECKS = 1;
