/*
 Navicat Premium Data Transfer

 Source Server         : 114.215.81.253
 Source Server Type    : MySQL
 Source Server Version : 50549
 Source Host           : 114.215.81.253
 Source Database       : sso

 Target Server Type    : MySQL
 Target Server Version : 50549
 File Encoding         : utf-8

 Date: 04/01/2017 14:31:49 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `active` tinyint(1) DEFAULT '0',
  `role` varchar(128) DEFAULT 'AGENT',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('13162894291', '1', '1', 'AGENT'), ('13521678152', '1', '1', 'AGENT'), ('13701323695', '1', '1', 'AGENT'), ('13801755217', '1', '1', 'AGENT'), ('13917079247', '1', '1', 'AGENT'), ('15000900223', '1', '1', 'AGENT'), ('15216899686', '1', '1', 'AGENT'), ('15810785643', '1', '1', 'AGENT'), ('15929924725', '1', '1', 'AGENT'), ('18521311399', '1', '1', 'AGENT'), ('18611572691', '1', '1', 'AGENT'), ('18720022002', '1', '1', 'AGENT'), ('18720032003', '1', '1', 'AGENT'), ('18720042004', '1', '1', 'AGENT'), ('18720052005', '1', '1', 'AGENT'), ('18720062006', '1', '1', 'AGENT'), ('18817801843', '1', '1', 'AGENT'), ('18911153356', '1', '1', 'AGENT'), ('agent', 'secret', '1', 'ROLE_AGENT');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
