

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_data_column
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_column`;
CREATE TABLE `bus_data_column`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_source_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据资源id',
  `table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `custom_column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '列别名信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_resource
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_resource`;
CREATE TABLE `bus_data_resource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父资源id',
  `resource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '是否删除 1-已删除 0-未删除',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源目录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_data_resource_copy1
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_resource_copy1`;
CREATE TABLE `bus_data_resource_copy1`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父资源id',
  `resource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '是否删除 1-已删除 0-未删除',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源目录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_data_resource_source_rel
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_resource_source_rel`;
CREATE TABLE `bus_data_resource_source_rel`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_resource_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源目录id',
  `data_source_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_source_table`(`data_source_id`, `table_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表格资源目录关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_set
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_set`;
CREATE TABLE `bus_data_set`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_set_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集名称',
  `data_set_type` int(2) NULL DEFAULT NULL COMMENT '数据集类型 1.SQL 2.HTTP接口',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建id',
  `del_flag` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除 1-已删除 0-未删除\'',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_set_http
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_set_http`;
CREATE TABLE `bus_data_set_http`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_set_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集id',
  `request_url` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `content_type` int(1) NULL DEFAULT NULL COMMENT '请求体方式 1.json 2.form-data',
  `request_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求体json',
  `request_header` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求头json',
  `response_path` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '响应JSON数据路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'http数据集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_set_param
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_set_param`;
CREATE TABLE `bus_data_set_param`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `required` tinyint(1) NULL DEFAULT NULL COMMENT '是否必填 true(1)必填 false(0)不必填',
  `param_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数描述',
  `data_set_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集id',
  `param_type` int(2) UNSIGNED NULL DEFAULT NULL COMMENT '参数类型1.字符串 2.数值 3.整数 4.小数 5.布尔值 6.日期 7.时间 8.日期时间 9.对象 10.数组 ',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_set_param`(`param_name`, `data_set_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据集参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_set_res_field
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_set_res_field`;
CREATE TABLE `bus_data_set_res_field`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `field_type` int(2) UNSIGNED NULL DEFAULT NULL COMMENT '字段类型0.未知 1.字符串 2.数值 3.整数 4.小数 5.布尔值 6.日期 7.时间 8.日期时间 9.对象 10.数组 ',
  `data_set_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集id',
  `field_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段描述',
  `field_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段格式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据集结果字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_set_sql
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_set_sql`;
CREATE TABLE `bus_data_set_sql`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_set_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据集id',
  `data_source_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源id',
  `sql_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'sql语句',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sql数据集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_source
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_source`;
CREATE TABLE `bus_data_source`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_base_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  `data_base_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库地址',
  `data_base_type` int(11) NULL DEFAULT NULL COMMENT '数据库类型 1.MySQL 2.ORACLE 3.PostgreSQL 4.SQLServer 5.DM',
  `account_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库账号',
  `account_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库密码',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '是否删除 1-已删除 0-未删除',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_data_table
-- ----------------------------
DROP TABLE IF EXISTS `bus_data_table`;
CREATE TABLE `bus_data_table`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `data_source_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属数据源的ID',
  `table_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格名称',
  `custom_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表别名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_key(data_source_id_table_name)`(`data_source_id`, `table_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表别名信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_assess_dept_rel
-- ----------------------------
DROP TABLE IF EXISTS `data_assess_dept_rel`;
CREATE TABLE `data_assess_dept_rel`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_source_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `dept_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '科室id',
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_source_table_dept`(`data_source_id`, `table_name`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据考核表科室关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_assess_extend_msg
-- ----------------------------
DROP TABLE IF EXISTS `data_assess_extend_msg`;
CREATE TABLE `data_assess_extend_msg`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_source_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '表上次更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_source_table`(`data_source_id`, `table_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库表扩展信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_assess_frequency
-- ----------------------------
DROP TABLE IF EXISTS `data_assess_frequency`;
CREATE TABLE `data_assess_frequency`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_source_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `frequency_unit` int(2) NULL DEFAULT NULL COMMENT '频次单位 1.天 2.月 3.季度 4.年',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_source_table`(`data_source_id`, `table_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据考核频次' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_assess_result
-- ----------------------------
DROP TABLE IF EXISTS `data_assess_result`;
CREATE TABLE `data_assess_result`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `data_source_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `score` int(3) UNSIGNED NULL DEFAULT NULL COMMENT '得分',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_source_table`(`data_source_id`, `table_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据考核结果表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
