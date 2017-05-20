/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.134
Source Server Version : 50550
Source Host           : 192.168.10.134:3306
Source Database       : postbox

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2017-05-20 15:01:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_BOX_INFO
-- ----------------------------
DROP TABLE IF EXISTS `T_BOX_INFO`;
CREATE TABLE `T_BOX_INFO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `SKEY` varchar(128) NOT NULL COMMENT '密钥',
  `MSTATUS` enum('E','F') NOT NULL COMMENT '装货状态,E空,F满',
  `MOBILENO` varchar(16) DEFAULT NULL COMMENT '属主手机号',
  `OPENTIME` datetime DEFAULT NULL COMMENT '上次开启时间',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COMMENT='终端设备表';

-- ----------------------------
-- Records of T_BOX_INFO
-- ----------------------------
INSERT INTO `T_BOX_INFO` VALUES ('10001', 'zhangzhongqiang', 'E', NULL , '2017-05-20 14:44:45', '2017-05-18 19:23:45');

-- ----------------------------
-- Table structure for USER_INFO
-- ----------------------------
DROP TABLE IF EXISTS `USER_INFO`;
CREATE TABLE `USER_INFO` (
  `USER_INFO_ID` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `BIND_ACCOUNT` varchar(30) DEFAULT NULL COMMENT '绑定的外部账户，现对应通行证账号',
  `LOGIN_NAME` varchar(16) DEFAULT NULL COMMENT '唯一，自动生成，用户只能修改一次',
  `PASSWORD` varchar(256) DEFAULT NULL COMMENT '密码',
  `COUNTRY` varchar(30) DEFAULT NULL COMMENT '国家',
  `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(30) DEFAULT NULL COMMENT '城市',
  `NICKNAME` varchar(30) DEFAULT NULL COMMENT '昵称',
  `SEX` varchar(16) DEFAULT NULL COMMENT '性别',
  `MOBILE_PHONE` varchar(30) DEFAULT NULL COMMENT '手机号',
  `IS_SPECIAL` varchar(16) DEFAULT NULL COMMENT '是否为特别用户',
  `REGISTER_IP_ADDRESS` varchar(48) DEFAULT NULL COMMENT 'IP地址',
  `REGISTER_TIME` datetime DEFAULT NULL COMMENT '注册时间',
  `LAST_LOGIN_TIME` varchar(30) DEFAULT NULL COMMENT '最后登录时间',
  `SOURCE` varchar(30) DEFAULT NULL COMMENT '账号来源',
  `RECOMMEND_ID` varchar(30) DEFAULT NULL COMMENT '推荐人',
  `RANK` varchar(30) DEFAULT NULL COMMENT '等级',
  `HEAD_IMG_PATH` varchar(512) DEFAULT NULL COMMENT '头像文件存储路径',
  PRIMARY KEY (`USER_INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='用户（包含特别用户）基本信息';

-- ----------------------------
-- Table structure for USER_WX_INFO
-- ----------------------------
DROP TABLE IF EXISTS `USER_WX_INFO`;
CREATE TABLE `USER_WX_INFO` (
  `OPENID` varchar(64) NOT NULL COMMENT '微信用户的唯一标识',
  `APPID` varchar(64) NOT NULL COMMENT '微信公众号或应用id',
  `NICKNAME` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `SEX` varchar(10) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `PROVINCE` varchar(30) DEFAULT NULL COMMENT '用户个人资料填写的省份',
  `CITY` varchar(30) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `COUNTRY` varchar(30) DEFAULT NULL COMMENT '国家，如中国为CN',
  `HEADIMGURL` varchar(512) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `PRIVILEGE` varchar(256) DEFAULT NULL COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  `UNIONID` varchar(64) NOT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  PRIMARY KEY (`OPENID`,`UNIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户微信信息';

-- ----------------------------
-- Table structure for USER_WX_RELT
-- ----------------------------
DROP TABLE IF EXISTS `USER_WX_RELT`;
CREATE TABLE `USER_WX_RELT` (
  `USER_INFO_ID` bigint(18) NOT NULL COMMENT '用户id',
  `UNIONID` varchar(64) NOT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(512) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`USER_INFO_ID`,`UNIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户与基本用户关联信息';


COMMIT;