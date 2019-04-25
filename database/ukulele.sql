-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 192.168.1.180    Database: ukulele_user
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ukulele_user`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ukulele_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ukulele_user`;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id_` bigint(64) NOT NULL COMMENT '主键ID',
  `username_` varchar(64)   NOT NULL COMMENT '用户名',
  `password_` varchar(255)   NOT NULL,
  `salt_` varchar(255) DEFAULT NULL COMMENT '随机盐',
  `phone_` varchar(20) NOT NULL COMMENT '简介',
  `avatar_` varchar(255)  DEFAULT NULL COMMENT '头像',
  `label_` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `enable_` tinyint(1) DEFAULT '1' COMMENT '0-正常，1-删除',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_idx1_username` (`username_`) USING BTREE,
  UNIQUE KEY `user_idx2_phone` (`phone_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$vg5QNHhCknAqevx9vM2s5esllJEzF/pa8VZXtFYHhhOhUcCw/GWyS',NULL,'17034642111','assets/tmp/img/1.png','admin,super',10,1,NULL,'2018-04-19 23:15:18',NULL,'2019-04-22 10:45:22'),(2,'test','$2a$06$0UIWycE.89T4bAg0ldubq.U9qIwr9mjsbIBL/Un3NyUKTJKahnZYm',NULL,'18267151472','assets/tmp/img/2.png','admin,super',10,1,NULL,'2019-04-08 11:56:00',NULL,'2019-04-22 10:45:22');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ukulele_user'
--

--
-- Current Database: `ukulele_portal`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ukulele_portal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ukulele_portal`;

--
-- Table structure for table `ant_icon`
--

DROP TABLE IF EXISTS `ant_icon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ant_icon` (
  `id_` bigint(64) NOT NULL,
  `type_` enum('class','icon','img') DEFAULT NULL,
  `value_` varchar(45) DEFAULT NULL COMMENT '值，包含：类名、图标 `type`、图像',
  `theme_` enum('outline','twotone','fill') DEFAULT NULL COMMENT '图标主题风格，默认：`outline`',
  `spin_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有旋转动画，默认：`false`',
  `two_tone_color` varchar(45) DEFAULT NULL COMMENT '仅适用双色图标，设置双色图标的主要颜色，仅对当前 icon 生效',
  `iconfont_` varchar(45) DEFAULT NULL COMMENT '指定来自 IconFont 的图标类型',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ant_icon`
--

LOCK TABLES `ant_icon` WRITE;
/*!40000 ALTER TABLE `ant_icon` DISABLE KEYS */;
INSERT INTO `ant_icon` VALUES (1,'icon','setting','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL),(2,'icon','bulb','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL),(3,'icon','eye','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ant_icon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ant_menu`
--

DROP TABLE IF EXISTS `ant_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ant_menu` (
  `id_` bigint(64) NOT NULL,
  `parent_id` bigint(64) NOT NULL DEFAULT '0',
  `key_` varchar(45) DEFAULT NULL,
  `text_` varchar(45) DEFAULT NULL COMMENT '文本',
  `i18n_` varchar(45) DEFAULT NULL COMMENT 'i18n主键',
  `group_` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示分组名，默认：`true`',
  `link_` varchar(45) DEFAULT NULL,
  `link_exact` tinyint(1) NOT NULL DEFAULT '0' COMMENT '路由是否精准匹配，默认：`false`',
  `external_link` varchar(45) DEFAULT NULL COMMENT '外部链接',
  `target_` enum('_blank','_self','_parent','_top') DEFAULT NULL COMMENT '链接 target',
  `icon_id` bigint(64) DEFAULT NULL COMMENT '图标',
  `disabled_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `hide_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏菜单',
  `hide_in_breadcrumb` tinyint(1) NOT NULL DEFAULT '0' COMMENT '隐藏面包屑，指 `page-header` 组件的自动生成面包屑时有效',
  `acl_` varchar(45) DEFAULT NULL COMMENT 'ACL配置',
  `shortcut_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否快捷菜单项',
  `shortcut_root` tinyint(1) NOT NULL DEFAULT '0' COMMENT '快捷菜单根节',
  `reuse_` tinyint(1) NOT NULL DEFAULT '0',
  `open_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否展开，当设置 `checkStrictly` 时有效',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ant_menu`
--

LOCK TABLES `ant_menu` WRITE;
/*!40000 ALTER TABLE `ant_menu` DISABLE KEYS */;
INSERT INTO `ant_menu` VALUES (1,0,'SYS_CONF','系统配置',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(2,0,'OPS_MONI','运维管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(3,1,'SYS_MANA','系统管理',NULL,1,NULL,0,NULL,NULL,1,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(4,2,'SYS_MONI','系统监控',NULL,1,NULL,0,NULL,NULL,3,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(5,3,'DEP_MANA','部门管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(6,3,'MNU_MANA','菜单管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(7,3,'RLE_MANA','角色管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(8,3,'DIC_MANA','字典管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(9,3,'USR_MANA','用户管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(10,4,'RCD_MANA','日志管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(11,4,'SVR_MONI','服务监控',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(12,4,'REG_CNT','注册中心',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(13,4,'CHA_MONI','链路监控',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(14,1,'BAS_CONF','基础配置',NULL,1,NULL,0,NULL,NULL,2,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(15,14,'ICO_MANA','图标管理',NULL,1,'/config/icon',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ant_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ant_role_menu`
--

DROP TABLE IF EXISTS `ant_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ant_role_menu` (
  `role_id` bigint(64) NOT NULL,
  `menu_id` bigint(64) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ant_role_menu`
--

LOCK TABLES `ant_role_menu` WRITE;
/*!40000 ALTER TABLE `ant_role_menu` DISABLE KEYS */;
INSERT INTO `ant_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15);
/*!40000 ALTER TABLE `ant_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `id_` bigint(64) NOT NULL,
  `name_` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(64) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `enable_` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否删除  -1：已删除  0：正常',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (10,'顶级部门',0,0,1,NULL,'2018-08-20 03:46:57',NULL,'2019-04-20 13:59:55'),(11,'一级部门',10,0,1,NULL,'2018-08-20 03:47:10',NULL,'2019-04-20 13:59:55'),(12,'二级部门',11,1,1,NULL,'2018-08-20 03:47:19',NULL,'2019-04-20 13:59:55');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept_relation`
--

DROP TABLE IF EXISTS `sys_dept_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept_relation` (
  `ancestor_` bigint(64) NOT NULL COMMENT '祖先节点',
  `descendant_` bigint(64) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor_`,`descendant_`),
  KEY `idx1` (`ancestor_`) USING BTREE,
  KEY `idx2` (`descendant_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept_relation`
--

LOCK TABLES `sys_dept_relation` WRITE;
/*!40000 ALTER TABLE `sys_dept_relation` DISABLE KEYS */;
INSERT INTO `sys_dept_relation` VALUES (10,10),(10,11),(10,12),(11,11),(11,12),(12,12);
/*!40000 ALTER TABLE `sys_dept_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `id_` bigint(64) NOT NULL COMMENT '编号',
  `value_` varchar(100) NOT NULL COMMENT '数据值',
  `label_` varchar(100) NOT NULL COMMENT '标签名',
  `type_` varchar(100) NOT NULL COMMENT '类型',
  `description_` varchar(100) NOT NULL COMMENT '描述',
  `sort_` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `remark_` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `enable_` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标记',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id_`),
  KEY `sys_dict_value` (`value_`) USING BTREE,
  KEY `sys_dict_label` (`label_`) USING BTREE,
  KEY `sys_dict_del_flag` (`enable_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id_` bigint(64) NOT NULL COMMENT '菜单ID',
  `name_` varchar(32) NOT NULL COMMENT '菜单名称',
  `permission_` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `path_` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `url_` varchar(128) DEFAULT NULL COMMENT '请求链接',
  `method_` varchar(32) DEFAULT NULL COMMENT '请求方法',
  `parent_id` bigint(64) DEFAULT NULL COMMENT '父菜单ID',
  `icon_` varchar(32) DEFAULT NULL COMMENT '图标',
  `component_` varchar(64) DEFAULT NULL COMMENT 'VUE页面',
  `sort_` int(11) DEFAULT '1' COMMENT '排序值',
  `type_` char(1) DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `enable_` tinyint(1) DEFAULT '1' COMMENT '0--正常 1--删除',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',NULL,'/admin',NULL,NULL,-1,'wordpress','Layout',1,'0',1,NULL,'2017-11-07 12:56:00',NULL,'2019-04-20 14:00:12'),(2,'用户管理',NULL,'user','',NULL,1,'home','views/admin/user/index',2,'0',1,NULL,'2017-11-02 14:24:37',NULL,'2019-04-22 13:40:56'),(3,'菜单管理',NULL,'menu','',NULL,1,'file-o','views/admin/menu/index',3,'0',1,NULL,'2017-11-08 01:57:27',NULL,'2019-04-20 14:00:12'),(4,'角色管理',NULL,'role',NULL,NULL,1,'file-o','views/admin/role/index',4,'0',1,NULL,'2017-11-08 02:13:37',NULL,'2019-04-20 14:00:12'),(5,'日志管理',NULL,'log',NULL,NULL,1,'file-o','views/admin/log/index',5,'0',1,NULL,'2017-11-20 06:06:22',NULL,'2019-04-20 14:00:12'),(6,'字典管理',NULL,'dict',NULL,NULL,1,'file-o','views/admin/dict/index',6,'0',1,NULL,'2017-11-29 03:30:52',NULL,'2019-04-20 14:00:12'),(7,'部门管理',NULL,'dept',NULL,NULL,1,'file-o','views/admin/dept/index',7,'0',1,NULL,'2018-01-20 05:17:19',NULL,'2019-04-20 14:00:12'),(8,'系统监控',NULL,'/taroco-admin',NULL,NULL,-1,'pie-chart','Layout',8,'0',1,NULL,'2018-01-22 04:30:41',NULL,'2019-04-20 14:00:12'),(14,'接口文档',NULL,'taroco-api','',NULL,8,'file-text-o','views/service/swagger/index',2,'0',1,NULL,'2018-01-23 02:56:43',NULL,'2019-04-20 14:00:12'),(21,'用户查看','',NULL,'/admin/user/**','GET',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-07 12:58:05',NULL,'2019-04-20 14:00:12'),(22,'用户新增','sys_user_add',NULL,'/admin/user/*','POST',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:52:09',NULL,'2019-04-20 14:00:12'),(23,'用户修改','sys_user_upd',NULL,'/admin/user/**','PUT',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:52:48',NULL,'2019-04-20 14:00:12'),(24,'用户删除','sys_user_del',NULL,'/admin/user/*','DELETE',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:54:01',NULL,'2019-04-20 14:00:12'),(25,'服务监控',NULL,'service',NULL,NULL,8,'server','views/service/index',1,'0',1,NULL,'2018-08-10 00:09:16',NULL,'2019-04-20 14:00:12'),(28,'调用链监控',NULL,'zipkin',NULL,NULL,8,'bar-chart-o','views/service/zipkin/index',3,'0',1,NULL,'2018-09-18 09:18:53',NULL,'2019-04-20 14:00:12'),(31,'菜单查看',NULL,NULL,'/admin/menu/**','GET',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:57:56',NULL,'2019-04-20 14:00:12'),(32,'菜单新增','sys_menu_add',NULL,'/admin/menu/*','POST',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:15:53',NULL,'2019-04-20 14:00:12'),(33,'菜单修改','sys_menu_edit',NULL,'/admin/menu/*','PUT',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:16:23',NULL,'2019-04-20 14:00:12'),(34,'菜单删除','sys_menu_del',NULL,'/admin/menu/*','DELETE',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:16:43',NULL,'2019-04-20 14:00:12'),(41,'角色查看',NULL,NULL,'/admin/role/**','GET',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:01',NULL,'2019-04-20 14:00:12'),(42,'角色新增','sys_role_add',NULL,'/admin/role/*','POST',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:18',NULL,'2019-04-20 14:00:12'),(43,'角色修改','sys_role_edit',NULL,'/admin/role/*','PUT',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:41',NULL,'2019-04-20 14:00:12'),(44,'角色删除','sys_role_del',NULL,'/admin/role/*','DELETE',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:59',NULL,'2019-04-20 14:00:12'),(45,'分配权限','sys_role_perm',NULL,'/admin/role/*','PUT',4,NULL,NULL,NULL,'1',1,NULL,'2018-04-19 23:22:55',NULL,'2019-04-20 14:00:12'),(51,'日志查看',NULL,NULL,'/admin/log/**','GET',5,NULL,NULL,NULL,'1',1,NULL,'2017-11-20 06:07:25',NULL,'2019-04-20 14:00:12'),(52,'日志删除','sys_log_del',NULL,'/admin/log/*','DELETE',5,NULL,NULL,NULL,'1',1,NULL,'2017-11-20 12:37:37',NULL,'2019-04-20 14:00:12'),(61,'字典查看',NULL,NULL,'/admin/dict/**','GET',6,NULL,NULL,NULL,'1',1,NULL,'2017-11-19 14:04:24',NULL,'2019-04-20 14:00:12'),(62,'字典删除','sys_dict_del',NULL,'/admin/dict/**','DELETE',6,NULL,NULL,NULL,'1',1,NULL,'2017-11-29 03:30:11',NULL,'2019-04-20 14:00:12'),(63,'字典新增','sys_dict_add',NULL,'/admin/dict/**','POST',6,NULL,NULL,NULL,'1',1,NULL,'2018-05-11 14:34:55',NULL,'2019-04-20 14:00:12'),(64,'字典修改','sys_dict_upd',NULL,'/admin/dict/**','PUT',6,NULL,NULL,NULL,'1',1,NULL,'2018-05-11 14:36:03',NULL,'2019-04-20 14:00:12'),(71,'部门查看','',NULL,'/admin/dept/**','GET',7,NULL,'',NULL,'1',1,NULL,'2018-01-20 05:17:19',NULL,'2019-04-20 14:00:12'),(72,'部门新增','sys_dept_add',NULL,'/admin/dept/**','POST',7,NULL,NULL,NULL,'1',1,NULL,'2018-01-20 06:56:16',NULL,'2019-04-20 14:00:12'),(73,'部门修改','sys_dept_edit',NULL,'/admin/dept/**','PUT',7,NULL,NULL,NULL,'1',1,NULL,'2018-01-20 06:56:59',NULL,'2019-04-20 14:00:12'),(74,'部门删除','sys_dept_del',NULL,'/admin/dept/**','DELETE',7,NULL,NULL,NULL,'1',1,NULL,'2018-01-20 06:57:28',NULL,'2019-04-20 14:00:12'),(100,'客户端管理','','client','','',1,'file-o','views/admin/client/index',9,'0',1,NULL,'2018-01-20 05:17:19',NULL,'2019-04-20 14:00:12'),(101,'客户端新增','sys_client_add',NULL,'/admin/client/**','POST',100,'1',NULL,NULL,'1',1,NULL,'2018-05-15 13:35:18',NULL,'2019-04-20 14:00:12'),(102,'客户端修改','sys_client_upd',NULL,'/admin/client/**','PUT',100,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:37:06',NULL,'2019-04-20 14:00:12'),(103,'客户端删除','sys_client_del',NULL,'/admin/client/**','DELETE',100,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:39:16',NULL,'2019-04-20 14:00:12'),(104,'客户端查看',NULL,NULL,'/admin/client/**','GET',100,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:39:57',NULL,'2019-04-20 14:00:12'),(110,'路由管理',NULL,'route',NULL,NULL,1,'file-o','views/admin/route/index',8,'0',1,NULL,'2018-05-15 13:44:51',NULL,'2019-04-20 14:00:12'),(111,'路由查看',NULL,NULL,'/admin/route/**','GET',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:45:59',NULL,'2019-04-20 14:00:12'),(112,'路由新增','sys_route_add',NULL,'/admin/route/**','POST',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:52:22',NULL,'2019-04-20 14:00:12'),(113,'路由修改','sys_route_upd',NULL,'/admin/route/**','PUT',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:55:38',NULL,'2019-04-20 14:00:12'),(114,'路由删除','sys_route_del',NULL,'/admin/route/**','DELETE',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:56:45',NULL,'2019-04-20 14:00:12'),(251,'服务查询',NULL,NULL,'/taroco-admin/api/**','GET',25,NULL,NULL,1,'1',1,NULL,'2018-08-10 00:25:50',NULL,'2019-04-20 14:00:12'),(252,'设置权重和标签','taroco_admin_set_weight',NULL,'/taroco-registry/eureka/apps/**','PUT',25,NULL,NULL,2,'1',1,NULL,'2018-08-10 00:54:10',NULL,'2019-04-20 14:00:12'),(253,'服务日志级别设置',NULL,NULL,'/taroco-admin/api/applications/*/loggers/*','POST',25,NULL,NULL,1,'1',1,NULL,'2018-08-10 01:17:06',NULL,'2019-04-20 14:00:12'),(254,'删除服务',NULL,NULL,'/taroco-admin/api/applications/*','DELETE',25,'',NULL,4,'1',1,NULL,'2018-09-18 09:37:08',NULL,'2019-04-20 14:00:12');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id_` bigint(64) NOT NULL,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT '1' COMMENT '删除标识（0-正常,1-删除）',
  `create_by` bigint(64) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `role_idx1_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'admin','ROLE_ADMIN','超级管理员',1,NULL,'2017-10-29 07:45:51',NULL,'2019-04-20 14:00:19');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_dept` (
  `id_` bigint(64) NOT NULL,
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(64) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (14,14,1),(16,1,10);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,14),(1,21),(1,22),(1,23),(1,24),(1,25),(1,28),(1,31),(1,32),(1,33),(1,34),(1,41),(1,42),(1,43),(1,44),(1,45),(1,51),(1,52),(1,61),(1,62),(1,63),(1,64),(1,71),(1,72),(1,73),(1,74),(1,100),(1,101),(1,102),(1,103),(1,104),(1,110),(1,111),(1,112),(1,113),(1,114),(1,251),(1,252),(1,253),(1,254);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_zuul_route`
--

DROP TABLE IF EXISTS `sys_zuul_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_zuul_route` (
  `id_` bigint(64) NOT NULL COMMENT 'router Id',
  `path_` varchar(255) NOT NULL COMMENT '路由路径',
  `service_id` varchar(255)  NOT NULL COMMENT '服务名称',
  `url_` varchar(255)  DEFAULT NULL COMMENT 'url代理',
  `strip_prefix` char(1)  DEFAULT '1' COMMENT '转发去掉前缀',
  `retryable_` char(1) DEFAULT '1' COMMENT '是否重试',
  `sensitive_header_list` varchar(255) DEFAULT NULL COMMENT '敏感请求头',
  `enable_` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `create_by` bigint(64) DEFAULT NULL COMMENT '删除标识（0-正常,1-删除）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态路由配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_zuul_route`
--

LOCK TABLES `sys_zuul_route` WRITE;
/*!40000 ALTER TABLE `sys_zuul_route` DISABLE KEYS */;
INSERT INTO `sys_zuul_route` VALUES (3,'/taroco-admin/**','taroco-admin','','1','1','',1,0,'2018-05-17 06:09:06',NULL,'2018-08-02 00:31:06'),(4,'/admin/**','taroco-rbac-service','','1','1','',1,0,'2018-05-21 03:40:38',NULL,'2018-08-20 09:36:08'),(5,'/auth/**','taroco-authentication-server','','1','1','',1,0,'2018-05-21 03:41:08',NULL,'2018-08-02 00:31:34'),(6,'/taroco-registry/**','taroco-registry','','1','1','',1,0,'2018-05-21 03:41:08',NULL,'2018-08-02 00:32:09'),(7,'/taroco-monitor/**','taroco-monitor','','1','1','',1,0,'2018-05-21 03:41:08',NULL,'2018-08-02 00:32:09'),(8,'/taroco-config/**','taroco-config',NULL,'1','1',NULL,1,0,'2018-08-05 19:36:21',NULL,'2018-08-06 03:36:16'),(9,'/taroco-demo1/**','taroco-demo1','','1','1','',1,1,'2018-08-09 18:29:13',NULL,'2018-08-10 06:24:44');
/*!40000 ALTER TABLE `sys_zuul_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ukulele_portal'
--

--
-- Current Database: `ukulele_syslog`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ukulele_syslog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ukulele_syslog`;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id_` bigint(64) NOT NULL COMMENT '编号',
  `type_` varchar(50) NOT NULL COMMENT '日志类型',
  `title_` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method_` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params_` text COMMENT '操作提交的数据',
  `time_` mediumtext COMMENT '执行时间',
  `exception_` text COMMENT '异常信息',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id_`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type_`) USING BTREE,
  KEY `sys_log_create_date` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1121241683549933570,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:36:57'),(1121241838424608770,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:37:41'),(1121242926116687874,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:42:00'),(1121244349386641409,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:47:39'),(1121244414071197698,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:47:55'),(1121284272030404609,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:26:18'),(1121284396135665665,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:26:47'),(1121284529556475906,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:27:19'),(1121287310673629186,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:38:22'),(1121287754082865154,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:40:08'),(1121288073420394498,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:41:24'),(1121288283026542594,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:42:14'),(1121288338483630081,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:42:27'),(1121288958351429633,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:44:55'),(1121289035715366914,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:45:13'),(1121294039725559810,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:05:06'),(1121294902741356545,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:08:32'),(1121294945712001026,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:08:42'),(1121295110040637442,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:09:22'),(1121295443240341506,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:10:41'),(1121295761382494210,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:11:57'),(1121295802956435458,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:12:07'),(1121295992178266114,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:12:52'),(1121296028228308994,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:13:00'),(1121296236219650049,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:13:50'),(1121296278997356546,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:14:00'),(1121297021301084161,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:16:57'),(1121299433936048130,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:26:32'),(1121316879384367105,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:35:52'),(1121316914637492226,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:36:00'),(1121320565263224834,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:50:31'),(1121321566045130754,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:54:29'),(1121321628875804673,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:54:44'),(1121321670252613633,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:54:54'),(1121322764789796865,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:59:15'),(1121323193669963777,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:00:57'),(1121323233507463169,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:01:07'),(1121324072758333441,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:04:27'),(1121324602603786241,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:06:33'),(1121324714306490370,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:07:00'),(1121324821361905665,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:07:25'),(1121324899162050562,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:07:44'),(1121325817106452481,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:11:23'),(1121325947020824578,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:11:54'),(1121326187568353282,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:12:51'),(1121326305805783042,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:13:19'),(1121326320049639426,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:13:23'),(1121326487318482945,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:02'),(1121326526614917121,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:12'),(1121326589302984706,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:27'),(1121326667711303681,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:45'),(1121326815258529793,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:15:21'),(1121327119865663490,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:16:33'),(1121327119886635010,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:16:33'),(1121327158985936897,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:16:43'),(1121327231597727745,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:17:00'),(1121328633929715713,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:22:34'),(1121328672706056194,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:22:44'),(1121328696030580737,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:22:49'),(1121328833431785473,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:23:22'),(1121328859532939266,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:23:28'),(1121329090479706113,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:24:23'),(1121329160725909505,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:24:40'),(1121330020797304833,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:28:05'),(1121331155813715969,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:32:36'),(1121331193134632961,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:32:44'),(1121331239691407362,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:32:56'),(1121331384126459905,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:33:30'),(1121335026292281346,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:47:58'),(1121335835398688769,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:51:11'),(1121335844437413889,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:51:13'),(1121336005272195074,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:51:52'),(1121336144128823297,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:52:25'),(1121336615799279618,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:54:17'),(1121336696359276545,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:54:36'),(1121336778060124161,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:54:56'),(1121337101071863809,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:56:13'),(1121337130088058881,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:56:20'),(1121337633471647746,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:58:20'),(1121337762559741953,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:58:51'),(1121337814019657729,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:03'),(1121337863248203777,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:15'),(1121337933821562882,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:32'),(1121337954931494914,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:37'),(1121338352903835650,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:01:11'),(1121338363242795009,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:01:14'),(1121338387406180353,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:01:20'),(1121339000617619457,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:03:46'),(1121339021350064129,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:03:51'),(1121339292230799362,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:04:55'),(1121339425341231105,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:05:27'),(1121339446853816322,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:05:32'),(1121340622278152193,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:13'),(1121340631199436801,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:15'),(1121340664275718145,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:23'),(1121340791207940098,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:53'),(1121340812288512001,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:58'),(1121340838611963905,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:11:04'),(1121342516526501889,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:17:44'),(1121342528274747393,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:17:47'),(1121342575838154754,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:17:58'),(1121342619920289793,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:18:09'),(1121342656167464961,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:18:17'),(1121342892701044737,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:19:14'),(1121342894043222018,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:19:14'),(1121344269271617538,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:24:42'),(1121344269280006146,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:24:42'),(1121344329568931841,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:24:56'),(1121344371084152833,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:25:06'),(1121344533441466369,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:25:45'),(1121345122229473282,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:05'),(1121345156719235073,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:14'),(1121345180270252034,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:19'),(1121345263338442753,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:39'),(1121346039238545409,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:31:44'),(1121346108486504449,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:32:01'),(1121346160667840513,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:32:13'),(1121346326057635842,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:32:52');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ukulele_syslog'
--

--
-- Current Database: `ukulele_auth`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ukulele_auth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ukulele_auth`;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(255) NOT NULL,
  `token_id` varchar(255) DEFAULT NULL,
  `token` longblob,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT 'user',
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('angular',NULL,'$2a$10$VJ0UI4k2Rxr99k6i06.tf.USJZijvTGvpV/P4zq1jcsDGNSSpxsVy','user','authorization_code,refresh_token,password,implicit,client_credentials','http://127.0.0.1:5000',NULL,43200,NULL,NULL,'false','angular应用'),('test',NULL,'$2a$10$VJ0UI4k2Rxr99k6i06.tf.USJZijvTGvpV/P4zq1jcsDGNSSpxsVy','user','authorization_code,refresh_token,password,implicit,client_credentials','http://localhost:10000',NULL,43200,NULL,NULL,'false','基础应用');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` longblob,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ukulele_auth'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-25 17:38:32
