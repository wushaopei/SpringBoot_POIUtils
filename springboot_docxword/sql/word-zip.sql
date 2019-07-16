/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80016
Source Host           : localhost:3333
Source Database       : poi-utils

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-07-16 11:21:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for word-zip
-- ----------------------------
DROP TABLE IF EXISTS `word-zip`;
CREATE TABLE `word-zip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taxNumbe` varchar(50) DEFAULT NULL,
  `companyName` varchar(50) DEFAULT NULL,
  `companyAddress` varchar(50) DEFAULT NULL,
  `companyTelephone` varchar(50) DEFAULT NULL,
  `accountBank` varchar(50) DEFAULT NULL,
  `accountName` varchar(50) DEFAULT NULL,
  `bankNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of word-zip
-- ----------------------------
INSERT INTO `word-zip` VALUES ('1', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('2', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('3', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('4', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('5', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('6', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('7', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('8', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('9', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('10', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('11', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('12', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('13', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('14', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('15', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('16', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('17', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('18', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('19', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('20', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('21', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('22', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('23', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('24', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('25', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('26', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('27', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('28', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('29', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('30', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('31', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('32', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('33', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('34', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('35', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('36', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('37', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('38', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('39', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('40', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('41', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('42', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('43', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `word-zip` VALUES ('44', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
