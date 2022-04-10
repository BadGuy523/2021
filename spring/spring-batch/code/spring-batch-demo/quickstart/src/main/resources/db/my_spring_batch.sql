/*
 Navicat Premium Data Transfer

 Source Server         : zjq
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : my_spring_batch

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 10/04/2022 22:42:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for batch_job_execution
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution`;
CREATE TABLE `batch_job_execution`  (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NULL DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime(0) NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `EXIT_CODE` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LAST_UPDATED` datetime(0) NULL DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`) USING BTREE,
  INDEX `JOB_INST_EXEC_FK`(`JOB_INSTANCE_ID`) USING BTREE,
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_job_execution
-- ----------------------------
INSERT INTO `batch_job_execution` VALUES (1, 2, 1, '2022-04-10 16:32:59', '2022-04-10 16:32:59', '2022-04-10 16:32:59', 'COMPLETED', 'COMPLETED', '', '2022-04-10 16:32:59', NULL);
INSERT INTO `batch_job_execution` VALUES (2, 2, 2, '2022-04-10 16:34:11', '2022-04-10 16:34:11', '2022-04-10 16:34:11', 'COMPLETED', 'COMPLETED', '', '2022-04-10 16:34:11', NULL);
INSERT INTO `batch_job_execution` VALUES (3, 2, 3, '2022-04-10 16:34:19', '2022-04-10 16:34:19', '2022-04-10 16:34:19', 'COMPLETED', 'COMPLETED', '', '2022-04-10 16:34:19', NULL);
INSERT INTO `batch_job_execution` VALUES (4, 2, 4, '2022-04-10 16:35:45', '2022-04-10 16:35:45', '2022-04-10 16:35:46', 'COMPLETED', 'COMPLETED', '', '2022-04-10 16:35:46', NULL);
INSERT INTO `batch_job_execution` VALUES (5, 2, 5, '2022-04-10 18:32:12', '2022-04-10 18:32:12', '2022-04-10 18:32:12', 'COMPLETED', 'COMPLETED', '', '2022-04-10 18:32:12', NULL);
INSERT INTO `batch_job_execution` VALUES (6, 2, 6, '2022-04-10 18:33:59', '2022-04-10 18:33:59', '2022-04-10 18:33:59', 'COMPLETED', 'COMPLETED', '', '2022-04-10 18:33:59', NULL);
INSERT INTO `batch_job_execution` VALUES (7, 2, 7, '2022-04-10 18:34:10', '2022-04-10 18:34:10', '2022-04-10 18:34:10', 'COMPLETED', 'COMPLETED', '', '2022-04-10 18:34:10', NULL);
INSERT INTO `batch_job_execution` VALUES (8, 2, 8, '2022-04-10 22:13:30', '2022-04-10 22:13:31', '2022-04-10 22:13:31', 'COMPLETED', 'COMPLETED', '', '2022-04-10 22:13:31', NULL);
INSERT INTO `batch_job_execution` VALUES (9, 2, 9, '2022-04-10 22:15:05', '2022-04-10 22:15:05', '2022-04-10 22:15:05', 'FAILED', 'FAILED', '', '2022-04-10 22:15:05', NULL);
INSERT INTO `batch_job_execution` VALUES (10, 2, 10, '2022-04-10 22:17:37', '2022-04-10 22:17:37', '2022-04-10 22:17:37', 'COMPLETED', 'COMPLETED', '', '2022-04-10 22:17:37', NULL);
INSERT INTO `batch_job_execution` VALUES (11, 2, 11, '2022-04-10 22:32:22', '2022-04-10 22:32:22', '2022-04-10 22:32:22', 'FAILED', 'FAILED', '', '2022-04-10 22:32:22', NULL);
INSERT INTO `batch_job_execution` VALUES (12, 2, 12, '2022-04-10 22:33:37', '2022-04-10 22:33:37', '2022-04-10 22:33:37', 'FAILED', 'FAILED', '', '2022-04-10 22:33:37', NULL);
INSERT INTO `batch_job_execution` VALUES (13, 2, 13, '2022-04-10 22:33:59', '2022-04-10 22:33:59', '2022-04-10 22:33:59', 'COMPLETED', 'COMPLETED', '', '2022-04-10 22:33:59', NULL);
INSERT INTO `batch_job_execution` VALUES (14, 2, 14, '2022-04-10 22:40:19', '2022-04-10 22:40:19', '2022-04-10 22:40:19', 'FAILED', 'FAILED', '', '2022-04-10 22:40:19', NULL);
INSERT INTO `batch_job_execution` VALUES (15, 2, 15, '2022-04-10 22:41:19', '2022-04-10 22:41:19', '2022-04-10 22:41:19', 'COMPLETED', 'COMPLETED', '', '2022-04-10 22:41:19', NULL);

-- ----------------------------
-- Table structure for batch_job_execution_context
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution_context`;
CREATE TABLE `batch_job_execution_context`  (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SERIALIZED_CONTEXT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`) USING BTREE,
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_job_execution_context
-- ----------------------------
INSERT INTO `batch_job_execution_context` VALUES (1, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (2, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (3, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (4, '{}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (5, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (6, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (7, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (8, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (9, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (10, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (11, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (12, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (13, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (14, '{\"@class\":\"java.util.HashMap\"}', NULL);
INSERT INTO `batch_job_execution_context` VALUES (15, '{\"@class\":\"java.util.HashMap\"}', NULL);

-- ----------------------------
-- Table structure for batch_job_execution_params
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution_params`;
CREATE TABLE `batch_job_execution_params`  (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `KEY_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STRING_VAL` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DATE_VAL` datetime(0) NULL DEFAULT NULL,
  `LONG_VAL` bigint(20) NULL DEFAULT NULL,
  `DOUBLE_VAL` double NULL DEFAULT NULL,
  `IDENTIFYING` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  INDEX `JOB_EXEC_PARAMS_FK`(`JOB_EXECUTION_ID`) USING BTREE,
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_job_execution_params
-- ----------------------------
INSERT INTO `batch_job_execution_params` VALUES (2, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649579650756, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (3, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649579659138, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (4, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649579745279, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (5, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649586731631, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (6, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649586838584, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (7, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649586849644, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (8, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649600010458, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (9, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649600104996, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (10, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649600257102, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (11, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649601141951, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (12, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649601217144, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (13, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649601239059, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (14, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649601618734, 0, 'Y');
INSERT INTO `batch_job_execution_params` VALUES (15, 'LONG', 'time', '', '1970-01-01 08:00:00', 1649601679030, 0, 'Y');

-- ----------------------------
-- Table structure for batch_job_execution_seq
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution_seq`;
CREATE TABLE `batch_job_execution_seq`  (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `UNIQUE_KEY_UN`(`UNIQUE_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_job_execution_seq
-- ----------------------------
INSERT INTO `batch_job_execution_seq` VALUES (15, '0');

-- ----------------------------
-- Table structure for batch_job_instance
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_instance`;
CREATE TABLE `batch_job_instance`  (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NULL DEFAULT NULL,
  `JOB_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_KEY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`) USING BTREE,
  UNIQUE INDEX `JOB_INST_UN`(`JOB_NAME`, `JOB_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_job_instance
-- ----------------------------
INSERT INTO `batch_job_instance` VALUES (1, 0, 'stringJob', 'd41d8cd98f00b204e9800998ecf8427e');
INSERT INTO `batch_job_instance` VALUES (2, 0, 'stringJob', '7259c97e8fddb5ac62f07c44b4ebfda8');
INSERT INTO `batch_job_instance` VALUES (3, 0, 'stringJob', 'b59f01b49443621c8e882cc839c29797');
INSERT INTO `batch_job_instance` VALUES (4, 0, 'file2DbJob', 'bf572ee3aab8b4cf7edbaebb15bf7104');
INSERT INTO `batch_job_instance` VALUES (5, 0, 'stringJob', 'c5870fb58ddee56eb9323d23dce3f3bb');
INSERT INTO `batch_job_instance` VALUES (6, 0, 'stringJob', 'c86b47879312a4249529b5624e96f565');
INSERT INTO `batch_job_instance` VALUES (7, 0, 'stringJob', '2f2d159c39091ea57fc1b6e263d5f0a5');
INSERT INTO `batch_job_instance` VALUES (8, 0, 'dbJob', 'f1c8341af9254535ef06be8a2d8a8d78');
INSERT INTO `batch_job_instance` VALUES (9, 0, 'dbJob', '1ca4a8250f742d6516e0a806c05865d3');
INSERT INTO `batch_job_instance` VALUES (10, 0, 'dbJob', '1aee621f5ff4b157aa8241472307e2f0');
INSERT INTO `batch_job_instance` VALUES (11, 0, 'dbJob', '1d1d0b5d77f474953c7c754e91b18ec1');
INSERT INTO `batch_job_instance` VALUES (12, 0, 'dbJob', 'd71b9259ba41957f6258cec3541b0eb8');
INSERT INTO `batch_job_instance` VALUES (13, 0, 'dbJob', '73c239b28a4cdcbbcfb756e178ac272b');
INSERT INTO `batch_job_instance` VALUES (14, 0, 'dbJob', '2183b8efea1aae21b98977438f7bfd4c');
INSERT INTO `batch_job_instance` VALUES (15, 0, 'dbJob', 'b61f933f5504f30895d495c3dd486700');

-- ----------------------------
-- Table structure for batch_job_seq
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_seq`;
CREATE TABLE `batch_job_seq`  (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `UNIQUE_KEY_UN`(`UNIQUE_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_job_seq
-- ----------------------------
INSERT INTO `batch_job_seq` VALUES (15, '0');

-- ----------------------------
-- Table structure for batch_step_execution
-- ----------------------------
DROP TABLE IF EXISTS `batch_step_execution`;
CREATE TABLE `batch_step_execution`  (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime(0) NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) NULL DEFAULT NULL,
  `READ_COUNT` bigint(20) NULL DEFAULT NULL,
  `FILTER_COUNT` bigint(20) NULL DEFAULT NULL,
  `WRITE_COUNT` bigint(20) NULL DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) NULL DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) NULL DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) NULL DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) NULL DEFAULT NULL,
  `EXIT_CODE` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LAST_UPDATED` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`) USING BTREE,
  INDEX `JOB_EXEC_STEP_FK`(`JOB_EXECUTION_ID`) USING BTREE,
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_step_execution
-- ----------------------------
INSERT INTO `batch_step_execution` VALUES (1, 4, 'stringStep', 1, '2022-04-10 16:32:59', '2022-04-10 16:32:59', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:32:59');
INSERT INTO `batch_step_execution` VALUES (2, 4, 'string2Step', 1, '2022-04-10 16:32:59', '2022-04-10 16:32:59', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:32:59');
INSERT INTO `batch_step_execution` VALUES (3, 4, 'stringStep', 2, '2022-04-10 16:34:11', '2022-04-10 16:34:11', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:34:11');
INSERT INTO `batch_step_execution` VALUES (4, 4, 'string2Step', 2, '2022-04-10 16:34:11', '2022-04-10 16:34:11', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:34:11');
INSERT INTO `batch_step_execution` VALUES (5, 4, 'stringStep', 3, '2022-04-10 16:34:19', '2022-04-10 16:34:19', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:34:19');
INSERT INTO `batch_step_execution` VALUES (6, 4, 'string2Step', 3, '2022-04-10 16:34:19', '2022-04-10 16:34:19', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:34:19');
INSERT INTO `batch_step_execution` VALUES (7, 5, 'file2DbStep', 4, '2022-04-10 16:35:46', '2022-04-10 16:35:46', 'COMPLETED', 3, 21, 0, 21, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 16:35:46');
INSERT INTO `batch_step_execution` VALUES (8, 4, 'stringStep', 5, '2022-04-10 18:32:12', '2022-04-10 18:32:12', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 18:32:12');
INSERT INTO `batch_step_execution` VALUES (9, 4, 'string2Step', 5, '2022-04-10 18:32:12', '2022-04-10 18:32:12', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 18:32:12');
INSERT INTO `batch_step_execution` VALUES (10, 4, 'stringStep', 6, '2022-04-10 18:33:59', '2022-04-10 18:33:59', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 18:33:59');
INSERT INTO `batch_step_execution` VALUES (11, 4, 'string2Step', 6, '2022-04-10 18:33:59', '2022-04-10 18:33:59', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 18:33:59');
INSERT INTO `batch_step_execution` VALUES (12, 4, 'stringStep', 7, '2022-04-10 18:34:10', '2022-04-10 18:34:10', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 18:34:10');
INSERT INTO `batch_step_execution` VALUES (13, 4, 'string2Step', 7, '2022-04-10 18:34:10', '2022-04-10 18:34:10', 'COMPLETED', 2, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 18:34:10');
INSERT INTO `batch_step_execution` VALUES (14, 10, 'dbStep', 8, '2022-04-10 22:13:31', '2022-04-10 22:13:31', 'COMPLETED', 8, 21, 0, 21, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 22:13:31');
INSERT INTO `batch_step_execution` VALUES (15, 2, 'dbStep', 9, '2022-04-10 22:15:05', '2022-04-10 22:15:05', 'FAILED', 0, 3, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.dao.DuplicateKeyException: com.zjq.batchdemo.dao.targets.TargetsDao.insertUser (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry \'3498\' for key \'PRIMARY\'\n; Duplicate entry \'3498\' for key \'PRIMARY\'; nested exception is java.sql.BatchUpdateException: Duplicate entry \'3498\' for key \'PRIMARY\'\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:247)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:70)\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:91)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy61.flushStatements(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.flushStatements(SqlSessionTemplate.java:388)\r\n	at org.mybatis.spring.batch.MyBatisBatchItemWriter.write(MyBatisBatchItemWriter.java:147)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core', '2022-04-10 22:15:05');
INSERT INTO `batch_step_execution` VALUES (16, 5, 'dbStep', 10, '2022-04-10 22:17:37', '2022-04-10 22:17:37', 'COMPLETED', 3, 21, 0, 21, 0, 0, 0, 0, 'COMPLETED', '', '2022-04-10 22:17:37');
INSERT INTO `batch_step_execution` VALUES (17, 2, 'dbStep', 11, '2022-04-10 22:32:22', '2022-04-10 22:32:22', 'FAILED', 0, 10, 0, 0, 0, 0, 0, 2, 'FAILED', 'org.springframework.batch.core.step.skip.SkipLimitExceededException: Skip limit of \'0\' exceeded\r\n	at org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy.shouldSkip(LimitCheckingItemSkipPolicy.java:133)\r\n	at org.springframework.batch.core.step.skip.ExceptionClassifierSkipPolicy.shouldSkip(ExceptionClassifierSkipPolicy.java:70)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.shouldSkip(FaultTolerantChunkProcessor.java:519)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.checkSkipPolicy(FaultTolerantChunkProcessor.java:550)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.scan(FaultTolerantChunkProcessor.java:632)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.access$900(FaultTolerantChunkProcessor.java:56)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor$5.recover(FaultTolerantChunkProcessor.java:434)\r\n	at org.springframework.retry.support.RetryTemplate.handleRetryExhausted(RetryTemplate.java:539)\r\n	at org.springframework.retry.support.RetryTemplate.doExecute(RetryTemplate.java:387)\r\n	at org.springframework.retry.support.RetryTemplate.execute(RetryTemplate.java:255)\r\n	at org.springframework.batch.core.step.item.BatchRetryTemplate.execute(BatchRetryTemplate.java:217)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.write(FaultTolerantChunkProcessor.java:444)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.i', '2022-04-10 22:32:22');
INSERT INTO `batch_step_execution` VALUES (18, 2, 'dbStep', 12, '2022-04-10 22:33:37', '2022-04-10 22:33:37', 'FAILED', 0, 10, 0, 0, 0, 0, 0, 2, 'FAILED', 'org.springframework.batch.core.step.skip.SkipLimitExceededException: Skip limit of \'-1\' exceeded\r\n	at org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy.shouldSkip(LimitCheckingItemSkipPolicy.java:133)\r\n	at org.springframework.batch.core.step.skip.ExceptionClassifierSkipPolicy.shouldSkip(ExceptionClassifierSkipPolicy.java:70)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.shouldSkip(FaultTolerantChunkProcessor.java:519)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.access$500(FaultTolerantChunkProcessor.java:56)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor$5.recover(FaultTolerantChunkProcessor.java:426)\r\n	at org.springframework.retry.support.RetryTemplate.handleRetryExhausted(RetryTemplate.java:539)\r\n	at org.springframework.retry.support.RetryTemplate.doExecute(RetryTemplate.java:387)\r\n	at org.springframework.retry.support.RetryTemplate.execute(RetryTemplate.java:255)\r\n	at org.springframework.batch.core.step.item.BatchRetryTemplate.execute(BatchRetryTemplate.java:217)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.write(FaultTolerantChunkProcessor.java:444)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:208)\r\n	at org.springframework.batch.cor', '2022-04-10 22:33:37');
INSERT INTO `batch_step_execution` VALUES (19, 5, 'dbStep', 13, '2022-04-10 22:33:59', '2022-04-10 22:33:59', 'COMPLETED', 3, 21, 0, 0, 0, 21, 0, 24, 'COMPLETED', '', '2022-04-10 22:33:59');
INSERT INTO `batch_step_execution` VALUES (20, 2, 'dbStep', 14, '2022-04-10 22:40:19', '2022-04-10 22:40:19', 'FAILED', 0, 10, 0, 0, 0, 0, 0, 4, 'FAILED', 'org.springframework.retry.ExhaustedRetryException: Retry exhausted after last attempt in recovery path, but exception is not skippable.; nested exception is org.springframework.dao.DuplicateKeyException: com.zjq.batchdemo.dao.targets.TargetsDao.insertUser (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry \'3507\' for key \'PRIMARY\'\n; Duplicate entry \'3507\' for key \'PRIMARY\'; nested exception is java.sql.BatchUpdateException: Duplicate entry \'3507\' for key \'PRIMARY\'\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor$5.recover(FaultTolerantChunkProcessor.java:429)\r\n	at org.springframework.retry.support.RetryTemplate.handleRetryExhausted(RetryTemplate.java:539)\r\n	at org.springframework.retry.support.RetryTemplate.doExecute(RetryTemplate.java:387)\r\n	at org.springframework.retry.support.RetryTemplate.execute(RetryTemplate.java:255)\r\n	at org.springframework.batch.core.step.item.BatchRetryTemplate.execute(BatchRetryTemplate.java:217)\r\n	at org.springframework.batch.core.step.item.FaultTolerantChunkProcessor.write(FaultTolerantChunkProcessor.java:444)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:208)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:152)\r\n	at org.springframework.batch.c', '2022-04-10 22:40:19');
INSERT INTO `batch_step_execution` VALUES (21, 5, 'dbStep', 15, '2022-04-10 22:41:19', '2022-04-10 22:41:19', 'COMPLETED', 3, 21, 0, 0, 0, 21, 0, 30, 'COMPLETED', '', '2022-04-10 22:41:19');

-- ----------------------------
-- Table structure for batch_step_execution_context
-- ----------------------------
DROP TABLE IF EXISTS `batch_step_execution_context`;
CREATE TABLE `batch_step_execution_context`  (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SERIALIZED_CONTEXT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`) USING BTREE,
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_step_execution_context
-- ----------------------------
INSERT INTO `batch_step_execution_context` VALUES (1, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (2, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (3, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (4, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (5, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (6, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (7, '{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"file2DbItemReader.read.count\":22,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (8, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (9, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (10, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (11, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (12, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (13, '{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (14, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":22,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (15, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":0,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (16, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":22,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (17, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":0,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (18, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":0,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (19, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":22,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (20, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":0,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` VALUES (21, '{\"@class\":\"java.util.HashMap\",\"MyBatisCursorItemReader.read.count\":22,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);

-- ----------------------------
-- Table structure for batch_step_execution_seq
-- ----------------------------
DROP TABLE IF EXISTS `batch_step_execution_seq`;
CREATE TABLE `batch_step_execution_seq`  (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `UNIQUE_KEY_UN`(`UNIQUE_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of batch_step_execution_seq
-- ----------------------------
INSERT INTO `batch_step_execution_seq` VALUES (21, '0');

SET FOREIGN_KEY_CHECKS = 1;
