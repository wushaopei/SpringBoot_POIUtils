/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80016
Source Host           : localhost:3333
Source Database       : poi-utils

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-07-17 15:24:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for invoiceorder
-- ----------------------------
DROP TABLE IF EXISTS `invoiceorder`;
CREATE TABLE `invoiceorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceOrder` varchar(20) DEFAULT NULL,
  `taxNumber` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `companyName` varchar(50) DEFAULT NULL,
  `companyAddress` varchar(50) DEFAULT NULL,
  `companyTelephone` varchar(50) DEFAULT NULL,
  `accountBank` varchar(50) DEFAULT NULL,
  `accountName` varchar(50) DEFAULT NULL,
  `bankNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of invoiceorder
-- ----------------------------
INSERT INTO `invoiceorder` VALUES ('1', '0001', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('2', '0002', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('3', '0003', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('4', '0004', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('5', '0005', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('6', '0006', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('7', '0007', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('8', '0008', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('9', '0009', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('10', '00010', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('11', '00011', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('12', '00012', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('13', '00013', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('14', '00014', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('15', '00015', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('16', '00016', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('17', '00017', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('18', '00018', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('19', '00019', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('20', '00020', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('21', '00021', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('22', '00022', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('23', '00023', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('24', '00024', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('25', '00025', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('26', '00026', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('27', '00027', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('28', '00028', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('29', '00029', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('30', '00030', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('31', '00031', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('32', '00032', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('33', '00033', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('34', '00034', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('35', '00035', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('36', '00036', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('37', '00037', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('38', '00038', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('39', '00039', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('40', '00040', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('41', '00041', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('42', '00042', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('43', '00043', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
INSERT INTO `invoiceorder` VALUES ('44', '00044', '111', '南宁证券', '南宁西路', '1234567', '123456785', '234', '1234');
