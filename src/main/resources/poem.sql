/*
Navicat MySQL Data Transfer

Source Server         : new
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : poem

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2020-03-31 20:40:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_administrator
-- ----------------------------
DROP TABLE IF EXISTS `t_administrator`;
CREATE TABLE `t_administrator` (
  `administratorId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` char(11) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`administratorId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_administrator
-- ----------------------------
INSERT INTO `t_administrator` VALUES ('1', '15979510879', '444444');

-- ----------------------------
-- Table structure for t_attention
-- ----------------------------
DROP TABLE IF EXISTS `t_attention`;
CREATE TABLE `t_attention` (
  `attentionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `beAttentedId` int(11) NOT NULL,
  PRIMARY KEY (`attentionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attention
-- ----------------------------

-- ----------------------------
-- Table structure for t_author
-- ----------------------------
DROP TABLE IF EXISTS `t_author`;
CREATE TABLE `t_author` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `authorName` varchar(100) NOT NULL,
  `aboutAuthor` text NOT NULL,
  `dynastyId` int(11) NOT NULL,
  `authorNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_author
-- ----------------------------

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `collectionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `poemId` bigint(20) NOT NULL,
  PRIMARY KEY (`collectionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_collection
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `commentText` text NOT NULL,
  `inputTime` date NOT NULL,
  `userId` int(11) NOT NULL,
  `workId` int(11) NOT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_draft
-- ----------------------------
DROP TABLE IF EXISTS `t_draft`;
CREATE TABLE `t_draft` (
  `draftId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `text` text,
  `imgPath` varchar(255) DEFAULT NULL,
  `isEssay` tinyint(4) NOT NULL,
  PRIMARY KEY (`draftId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_draft
-- ----------------------------

-- ----------------------------
-- Table structure for t_dynasty
-- ----------------------------
DROP TABLE IF EXISTS `t_dynasty`;
CREATE TABLE `t_dynasty` (
  `dynastyId` int(11) NOT NULL AUTO_INCREMENT,
  `dynastyName` varchar(255) NOT NULL,
  `dynastyNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`dynastyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dynasty
-- ----------------------------

-- ----------------------------
-- Table structure for t_errorinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_errorinfo`;
CREATE TABLE `t_errorinfo` (
  `errorId` int(11) NOT NULL AUTO_INCREMENT,
  `errorText` text NOT NULL,
  `inputTime` date NOT NULL,
  `state` tinyint(4) NOT NULL,
  `verifyAdministratorId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `poemId` bigint(20) NOT NULL,
  PRIMARY KEY (`errorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_errorinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_gamepass
-- ----------------------------
DROP TABLE IF EXISTS `t_gamepass`;
CREATE TABLE `t_gamepass` (
  `gamePassId` int(11) NOT NULL AUTO_INCREMENT,
  `score` bigint(20) NOT NULL,
  PRIMARY KEY (`gamePassId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gamepass
-- ----------------------------

-- ----------------------------
-- Table structure for t_highestscore
-- ----------------------------
DROP TABLE IF EXISTS `t_highestscore`;
CREATE TABLE `t_highestscore` (
  `highestScoreId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `score` bigint(20) NOT NULL,
  PRIMARY KEY (`highestScoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_highestscore
-- ----------------------------

-- ----------------------------
-- Table structure for t_key
-- ----------------------------
DROP TABLE IF EXISTS `t_key`;
CREATE TABLE `t_key` (
  `keyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyName` varchar(10) NOT NULL,
  `keyText` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`keyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_key
-- ----------------------------

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like` (
  `likeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `workId` int(11) NOT NULL,
  `inputTime` date NOT NULL,
  PRIMARY KEY (`likeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_like
-- ----------------------------

-- ----------------------------
-- Table structure for t_normaluser
-- ----------------------------
DROP TABLE IF EXISTS `t_normaluser`;
CREATE TABLE `t_normaluser` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` char(11) NOT NULL,
  `penName` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL,
  `headPicPath` varchar(255) DEFAULT '',
  `personalizedSig` varchar(100) DEFAULT '',
  `sex` bit(1) NOT NULL,
  `birth` date DEFAULT NULL,
  `disableTime` int(11) DEFAULT '0',
  `rewardPoints` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_normaluser
-- ----------------------------
INSERT INTO `t_normaluser` VALUES ('7', '13320151388', '茶儿香', '123456', 'boy.jpg', '这个家伙很懒，没有留下签名', '', '1970-01-01', '0', '0');
INSERT INTO `t_normaluser` VALUES ('8', '15538123221', '墨离', '1008610', 'girl.jpg', '这个家伙很懒，没有留下签名', '\0', '1970-01-01', '0', '0');

-- ----------------------------
-- Table structure for t_poem
-- ----------------------------
DROP TABLE IF EXISTS `t_poem`;
CREATE TABLE `t_poem` (
  `poemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `translatedText` text,
  `wordsAnnotation` text,
  `videoPath` varchar(255) DEFAULT NULL,
  `authorId` int(11) NOT NULL,
  `poemComment` text,
  `typeId` int(11) NOT NULL,
  PRIMARY KEY (`poemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_poem
-- ----------------------------

-- ----------------------------
-- Table structure for t_poem_theme
-- ----------------------------
DROP TABLE IF EXISTS `t_poem_theme`;
CREATE TABLE `t_poem_theme` (
  `ptId` bigint(20) NOT NULL AUTO_INCREMENT,
  `poemId` bigint(20) NOT NULL,
  `themeId` int(11) NOT NULL,
  PRIMARY KEY (`ptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_poem_theme
-- ----------------------------

-- ----------------------------
-- Table structure for t_points_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_points_grade`;
CREATE TABLE `t_points_grade` (
  `gradeId` int(11) NOT NULL AUTO_INCREMENT,
  `miniPoints` bigint(20) NOT NULL,
  `gradeName` varchar(50) NOT NULL,
  PRIMARY KEY (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_points_grade
-- ----------------------------
INSERT INTO `t_points_grade` VALUES ('1', '0', '白身');
INSERT INTO `t_points_grade` VALUES ('2', '5', '童生');
INSERT INTO `t_points_grade` VALUES ('3', '20', '秀才');
INSERT INTO `t_points_grade` VALUES ('4', '40', '举人');
INSERT INTO `t_points_grade` VALUES ('5', '60', '贡生');
INSERT INTO `t_points_grade` VALUES ('6', '80', '进士');

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `questionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `questionText` text NOT NULL,
  `answer` varchar(255) NOT NULL,
  `questionType` int(11) NOT NULL,
  `chosed` tinyint(4) NOT NULL,
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------

-- ----------------------------
-- Table structure for t_reportinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_reportinfo`;
CREATE TABLE `t_reportinfo` (
  `reportId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `beReportedUserId` int(11) NOT NULL,
  `reportReason` text NOT NULL,
  `state` tinyint(4) NOT NULL,
  `inputTime` date NOT NULL,
  PRIMARY KEY (`reportId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reportinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_theme
-- ----------------------------
DROP TABLE IF EXISTS `t_theme`;
CREATE TABLE `t_theme` (
  `themeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `themeName` varchar(255) NOT NULL,
  `themeNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`themeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_theme
-- ----------------------------

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeNumber` varchar(20) NOT NULL,
  `typeName` varchar(255) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_userspeak
-- ----------------------------
DROP TABLE IF EXISTS `t_userspeak`;
CREATE TABLE `t_userspeak` (
  `userSpeakId` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyId` bigint(20) NOT NULL,
  `userId` int(11) NOT NULL,
  `speakText` varchar(255) NOT NULL,
  PRIMARY KEY (`userSpeakId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userspeak
-- ----------------------------

-- ----------------------------
-- Table structure for t_work
-- ----------------------------
DROP TABLE IF EXISTS `t_work`;
CREATE TABLE `t_work` (
  `workId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `text` text NOT NULL,
  `imgPath` varchar(255) DEFAULT NULL,
  `likeNum` int(10) unsigned zerofill NOT NULL,
  `isEssay` tinyint(4) NOT NULL,
  `inputTime` date NOT NULL,
  PRIMARY KEY (`workId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_work
-- ----------------------------
