-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ukulele_user
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id_` bigint(64) NOT NULL COMMENT '主键ID',
  `username_` varchar(64) NOT NULL COMMENT '用户名',
  `password_` varchar(255) NOT NULL,
  `salt_` varchar(255) DEFAULT NULL COMMENT '随机盐',
  `phone_` varchar(20) NOT NULL COMMENT '简介',
  `avatar_` varchar(255) DEFAULT NULL COMMENT '头像',
  `label_` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `enable_` tinyint(1) DEFAULT '1' COMMENT '0-正常，1-删除',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL,
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
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$vg5QNHhCknAqevx9vM2s5esllJEzF/pa8VZXtFYHhhOhUcCw/GWyS',NULL,'17034642111','assets/tmp/img/1.png','admin,super',10,1,NULL,'2018-04-19 23:15:18',NULL,'2019-04-22 10:45:22'),(2,'admin0','$2a$06$0UIWycE.89T4bAg0ldubq.U9qIwr9mjsbIBL/Un3NyUKTJKahnZYm',NULL,'18267151472','assets/tmp/img/2.png','admin,super',10,1,NULL,'2019-04-08 11:56:00',NULL,'2019-05-18 08:26:25'),(1129664437547368450,'admin1','$2a$06$pfAOF1KZ4NgMVrX.WrYHPeyyVCmMg.M.XG1at1loeEEedQQbSb0Sy',NULL,'15266669999','assets/tmp/img/5.png','admin,super',1,1,NULL,'2019-05-18 08:26:04',NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ukulele_user'
--

--
-- Dumping routines for database 'ukulele_user'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-02 19:59:14
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ukulele_portal
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ant_icon`
--

LOCK TABLES `ant_icon` WRITE;
/*!40000 ALTER TABLE `ant_icon` DISABLE KEYS */;
INSERT INTO `ant_icon` VALUES (1,'icon','setting','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL),(2,'icon','bulb','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL),(3,'icon','eye','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL),(1122534400369008642,'icon','fast-forward','outline',0,NULL,NULL,1,NULL,NULL,NULL,NULL);
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
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ant_menu`
--

LOCK TABLES `ant_menu` WRITE;
/*!40000 ALTER TABLE `ant_menu` DISABLE KEYS */;
INSERT INTO `ant_menu` VALUES (1,0,'SYS_CONF','系统配置',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(2,0,'OPS_MONI','运维管理',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(3,1,'SYS_MANA','系统管理',NULL,1,NULL,0,NULL,NULL,1,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(4,2,'SYS_MONI','系统监控',NULL,1,NULL,0,NULL,NULL,3,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(5,3,'DEP_MANA','部门管理',NULL,1,'/system/dept',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(6,3,'MNU_MANA','菜单管理',NULL,1,'/system/menu',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(7,3,'RLE_MANA','角色管理',NULL,1,'/system/role',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(8,3,'DIC_MANA','字典管理',NULL,1,'/system/dict',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(9,3,'USR_MANA','用户管理',NULL,1,'/system/user',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(10,4,'RCD_MANA','日志管理',NULL,1,'/monitor/log',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(11,4,'SVR_MONI','服务监控',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(12,4,'REG_CNT','注册中心',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(13,4,'CHA_MONI','链路监控',NULL,1,NULL,0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(14,1,'BAS_CONF','基础配置',NULL,1,NULL,0,NULL,NULL,2,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL),(15,14,'ICO_MANA','图标管理',NULL,1,'/config/icon',0,NULL,NULL,NULL,0,0,0,NULL,0,0,0,0,1,NULL,NULL,NULL,NULL);
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
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL,
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
  `index_id` bigint(64) NOT NULL COMMENT '类型',
  `label_` varchar(100) NOT NULL COMMENT '标签名',
  `value_` varchar(100) NOT NULL COMMENT '数据值',
  `sort_` smallint(6) NOT NULL COMMENT '排序（升序）',
  `remark_` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `enable_` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标记',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL,
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
INSERT INTO `sys_dict` VALUES (1135089310990012417,1135007704606511106,'男','1',2,NULL,1,NULL,'2019-06-02 07:42:35',NULL,'2019-06-02 07:42:35'),(1135117340483452930,1135007704606511106,'女','0',1,NULL,1,NULL,'2019-06-02 09:33:58',NULL,'2019-06-02 09:33:58');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_index`
--

DROP TABLE IF EXISTS `sys_dict_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_index` (
  `id_` bigint(32) NOT NULL,
  `key_` varchar(50) DEFAULT NULL,
  `name_` varchar(200) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE KEY `code` (`key_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='数据字典索引表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_index`
--

LOCK TABLES `sys_dict_index` WRITE;
/*!40000 ALTER TABLE `sys_dict_index` DISABLE KEYS */;
INSERT INTO `sys_dict_index` VALUES (1135007704606511106,'GENDER','性别',1,NULL,'2019-06-02 02:18:18',NULL,'2019-06-02 02:18:18');
/*!40000 ALTER TABLE `sys_dict_index` ENABLE KEYS */;
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
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL,
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
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(64) DEFAULT NULL,
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
INSERT INTO `sys_role` VALUES (1,'admin','ROLE_ADMIN','超级管理员',1,NULL,'2017-10-29 07:45:51',NULL,'2019-04-20 14:00:19'),(1129917086955921410,'user','ROLE_USER','普通用户',1,NULL,'2019-05-19 01:10:01',NULL,NULL);
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
  `service_id` varchar(255) NOT NULL COMMENT '服务名称',
  `url_` varchar(255) DEFAULT NULL COMMENT 'url代理',
  `strip_prefix` char(1) DEFAULT '1' COMMENT '转发去掉前缀',
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
-- Dumping events for database 'ukulele_portal'
--

--
-- Dumping routines for database 'ukulele_portal'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-02 19:59:14
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ukulele_syslog
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
INSERT INTO `sys_log` VALUES (1121241683549933570,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:36:57'),(1121241838424608770,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:37:41'),(1121242926116687874,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:42:00'),(1121244349386641409,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:47:39'),(1121244414071197698,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 02:47:55'),(1121284272030404609,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:26:18'),(1121284396135665665,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:26:47'),(1121284529556475906,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:27:19'),(1121287310673629186,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:38:22'),(1121287754082865154,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:40:08'),(1121288073420394498,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:41:24'),(1121288283026542594,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:42:14'),(1121288338483630081,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:42:27'),(1121288958351429633,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:44:55'),(1121289035715366914,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 05:45:13'),(1121294039725559810,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:05:06'),(1121294902741356545,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:08:32'),(1121294945712001026,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:08:42'),(1121295110040637442,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:09:22'),(1121295443240341506,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:10:41'),(1121295761382494210,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:11:57'),(1121295802956435458,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:12:07'),(1121295992178266114,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:12:52'),(1121296028228308994,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:13:00'),(1121296236219650049,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:13:50'),(1121296278997356546,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:14:00'),(1121297021301084161,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:16:57'),(1121299433936048130,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 06:26:32'),(1121316879384367105,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:35:52'),(1121316914637492226,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:36:00'),(1121320565263224834,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:50:31'),(1121321566045130754,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:54:29'),(1121321628875804673,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:54:44'),(1121321670252613633,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:54:54'),(1121322764789796865,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 07:59:15'),(1121323193669963777,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:00:57'),(1121323233507463169,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:01:07'),(1121324072758333441,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:04:27'),(1121324602603786241,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:06:33'),(1121324714306490370,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:07:00'),(1121324821361905665,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:07:25'),(1121324899162050562,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:07:44'),(1121325817106452481,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:11:23'),(1121325947020824578,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:11:54'),(1121326187568353282,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:12:51'),(1121326305805783042,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:13:19'),(1121326320049639426,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:13:23'),(1121326487318482945,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:02'),(1121326526614917121,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:12'),(1121326589302984706,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:27'),(1121326667711303681,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:14:45'),(1121326815258529793,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:15:21'),(1121327119865663490,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:16:33'),(1121327119886635010,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:16:33'),(1121327158985936897,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:16:43'),(1121327231597727745,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:17:00'),(1121328633929715713,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:22:34'),(1121328672706056194,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:22:44'),(1121328696030580737,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:22:49'),(1121328833431785473,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:23:22'),(1121328859532939266,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:23:28'),(1121329090479706113,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:24:23'),(1121329160725909505,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:24:40'),(1121330020797304833,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:28:05'),(1121331155813715969,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:32:36'),(1121331193134632961,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:32:44'),(1121331239691407362,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:32:56'),(1121331384126459905,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:33:30'),(1121335026292281346,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:47:58'),(1121335835398688769,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:51:11'),(1121335844437413889,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:51:13'),(1121336005272195074,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:51:52'),(1121336144128823297,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:52:25'),(1121336615799279618,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:54:17'),(1121336696359276545,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:54:36'),(1121336778060124161,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:54:56'),(1121337101071863809,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:56:13'),(1121337130088058881,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:56:20'),(1121337633471647746,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:58:20'),(1121337762559741953,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:58:51'),(1121337814019657729,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:03'),(1121337863248203777,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:15'),(1121337933821562882,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:32'),(1121337954931494914,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 08:59:37'),(1121338352903835650,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:01:11'),(1121338363242795009,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:01:14'),(1121338387406180353,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:01:20'),(1121339000617619457,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:03:46'),(1121339021350064129,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:03:51'),(1121339292230799362,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:04:55'),(1121339425341231105,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:05:27'),(1121339446853816322,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:05:32'),(1121340622278152193,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:13'),(1121340631199436801,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:15'),(1121340664275718145,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:23'),(1121340791207940098,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:53'),(1121340812288512001,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:10:58'),(1121340838611963905,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:11:04'),(1121342516526501889,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:17:44'),(1121342528274747393,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:17:47'),(1121342575838154754,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:17:58'),(1121342619920289793,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:18:09'),(1121342656167464961,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:18:17'),(1121342892701044737,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:19:14'),(1121342894043222018,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:19:14'),(1121344269271617538,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:24:42'),(1121344269280006146,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:24:42'),(1121344329568931841,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:24:56'),(1121344371084152833,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:25:06'),(1121344533441466369,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:25:45'),(1121345122229473282,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:05'),(1121345156719235073,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:14'),(1121345180270252034,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:19'),(1121345263338442753,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:28:39'),(1121346039238545409,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:31:44'),(1121346108486504449,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:32:01'),(1121346160667840513,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-icon/getByParam','GET',NULL,NULL,NULL,'admin','2019-04-25 09:32:13'),(1121346326057635842,'Operation','Operation',NULL,'192.168.6.79',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 09:32:52'),(1121400783018303490,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:09:15'),(1121400838588637186,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:09:29'),(1121400909141024769,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:09:46'),(1121407919362318338,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:37:37'),(1121408062333558785,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:38:11'),(1121409556692770817,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:44:08'),(1121411188847452162,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:50:37'),(1121411227418271746,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 13:50:46'),(1121414026608680962,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 14:01:53'),(1121416448710189058,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 14:11:31'),(1121420502035243009,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-25 14:27:37'),(1121722884325531649,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 10:29:10'),(1121722911852748802,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 10:29:17'),(1121757550327783425,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:46:56'),(1121757640413044738,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon','POST',NULL,NULL,NULL,'admin','2019-04-26 12:47:17'),(1121757645165191169,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:47:19'),(1121757690295902210,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon','PUT',NULL,NULL,NULL,'admin','2019-04-26 12:47:29'),(1121757692095258625,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:47:30'),(1121757715231039489,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/1121757642938040322','DELETE',NULL,NULL,NULL,'admin','2019-04-26 12:47:35'),(1121757716292198402,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:47:35'),(1121758480611827714,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:50:38'),(1121758510118756353,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:50:45'),(1121759093965873154,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 12:53:04'),(1121761300530487298,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:01:50'),(1121761342356086785,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:02:00'),(1121762889920045058,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:08:09'),(1121762889957793793,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:08:09'),(1121763049156796418,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:08:47'),(1121763049165185025,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:08:47'),(1121764837293449218,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:15:53'),(1121764837331197953,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:15:53'),(1121764942343987201,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:16:18'),(1121764942343987202,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:16:18'),(1121766991731253250,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:24:27'),(1121766991731253251,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:24:27'),(1121767445739495426,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:26:15'),(1121770532227145729,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:38:31'),(1121773449730482178,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:50:07'),(1121773939457417217,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page','GET',NULL,NULL,NULL,'admin','2019-04-26 13:52:03'),(1121774196937351169,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:53:05'),(1121774857133383682,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:55:42'),(1121775596152971266,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 13:58:38'),(1121777855741976577,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 14:07:37'),(1121778167747862529,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 14:08:51'),(1121780822125731842,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 14:19:24'),(1121780822125731843,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:19:24'),(1121780862936309761,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:19:34'),(1121784298310373378,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:33:12'),(1121784421983621121,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:33:43'),(1121785107483967489,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:36:25'),(1121785198743633922,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 14:36:48'),(1121785459235078146,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:37:50'),(1121785857228390402,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 14:39:25'),(1121785887976833025,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:39:32'),(1121786083792109569,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:40:19'),(1121787031365009409,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:44:04'),(1121787205109858305,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:44:46'),(1121787325272473602,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:45:15'),(1121787504574775297,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:45:58'),(1121787629539868673,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:46:27'),(1121788920911552514,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:51:35'),(1121789126184984577,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:52:24'),(1121789204853350402,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:52:43'),(1121789333538791426,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.101',NULL,NULL,'admin','2019-04-26 14:53:14'),(1121789421862445058,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:35'),(1121789454372495361,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:42'),(1121789456075382786,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:43'),(1121789456155074562,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:43'),(1121789459254665217,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:44'),(1121789511893180417,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:56'),(1121789524505452545,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:53:59'),(1121789532336218113,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=3&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:54:01'),(1121789544478728193,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=1&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:54:04'),(1121789563487313921,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=5&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:54:08'),(1121789632026435585,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=5&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:54:25'),(1121789759071903746,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','current=5&size=10&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 14:54:55'),(1121790008058372098,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 14:55:54'),(1121790048109780994,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET',NULL,NULL,NULL,'admin','2019-04-26 14:56:04'),(1121790140640321537,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','requestUrl=undefined&title_=undefined&userAgent=undefined&method_=undefined&serviceId=undefined&type_=undefined&remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:56:26'),(1121790313781190658,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=undefined&method_=undefined&current=1&size=10&requestUrl=undefined&title_=undefined&serviceId=undefined&type_=undefined&remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:57:07'),(1121790325323915265,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=undefined&method_=undefined&current=1&size=10&requestUrl=undefined&title_=undefined&serviceId=undefined&type_=undefined&remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:57:10'),(1121790330805870593,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=undefined&method_=undefined&current=1&size=10&requestUrl=undefined&title_=undefined&serviceId=undefined&type_=undefined&remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 14:57:11'),(1121791421467516930,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:01:31'),(1121791421467516931,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=null&method_=null&params_=null&createBy=null&requestUrl=null&title_=null&exception_=null&serviceId=null&type_=null&remoteAddr=null',NULL,NULL,'admin','2019-04-26 15:01:31'),(1121791494913974273,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=undefined&method_=undefined&params_=undefined&createBy=undefined&requestUrl=undefined&title_=undefined&exception_=undefined&serviceId=undefined&type_=undefined&remoteAddr=undefined',NULL,NULL,'admin','2019-04-26 15:01:49'),(1121791559418175489,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=undefined&method_=undefined&params_=undefined&createBy=undefined&requestUrl=undefined&title_=undefined&exception_=undefined&serviceId=undefined&type_=undefined&remoteAddr=undefined',NULL,NULL,'admin','2019-04-26 15:02:04'),(1121791797851774978,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page','GET','userAgent=undefined&method_=undefined&params_=undefined&createBy=undefined&requestUrl=undefined&title_=undefined&exception_=undefined&serviceId=undefined&type_=undefined&remoteAddr=undefined',NULL,NULL,'admin','2019-04-26 15:03:01'),(1121793768558075906,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:10:50'),(1121793768558075907,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/undefined/undefined','GET',NULL,NULL,NULL,'admin','2019-04-26 15:10:51'),(1121793975987380225,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/undefined/undefined','GET',NULL,NULL,NULL,'admin','2019-04-26 15:11:40'),(1121794210646106113,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:12:36'),(1121794251939028993,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/undefined/undefined','GET',NULL,NULL,NULL,'admin','2019-04-26 15:12:46'),(1121794373078917121,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:13:15'),(1121794422332628993,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:13:27'),(1121794490729144321,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','userAgent=undefined&method_=undefined&params_=undefined&createBy=undefined&requestUrl=undefined&title_=undefined&exception_=undefined&serviceId=undefined&type_=undefined&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:13:43'),(1121794745881239554,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','userAgent=undefined&method_=undefined&params_=undefined&createBy=undefined&requestUrl=undefined&title_=undefined&exception_=undefined&serviceId=undefined&type_=undefined&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:14:44'),(1121794885421539330,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:15:17'),(1121794903557709826,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:15:22'),(1121795013872099330,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','userAgent=undefined&method_=undefined&params_=undefined&createBy=undefined&requestUrl=undefined&title_=undefined&exception_=undefined&serviceId=undefined&type_=null&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:15:48'),(1121795480937209858,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:17:39'),(1121795522137858050,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:17:49'),(1121795611837243394,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','userAgent=null&method_=null&params_=null&createBy=null&requestUrl=null&title_=null&exception_=null&serviceId=null&type_=null&remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:18:10'),(1121795829265768449,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:19:02'),(1121795841332781058,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:19:05'),(1121795857074003970,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:19:09'),(1121799845731278850,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/2/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:35:00'),(1121799922315075586,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/2/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:35:18'),(1121799937842388993,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/2/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:35:22'),(1121799956892917761,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:35:26'),(1121800834534252546,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:38:56'),(1121800849851850754,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:38:59'),(1121800960086548482,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:39:26'),(1121801016118255617,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:39:39'),(1121801021549879298,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:39:40'),(1121801214601109506,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:40:26'),(1121801270213386241,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:40:40'),(1121801273241673729,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:40:40'),(1121801283224117250,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:40:43'),(1121801854530265090,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:42:59'),(1121801854538653698,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:42:59'),(1121801917180583937,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:43:14'),(1121801952869916674,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:43:22'),(1121801994427080705,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/3/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:43:32'),(1121802037162844162,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/3/30','GET',NULL,NULL,NULL,'admin','2019-04-26 15:43:42'),(1121802083107250177,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/30','GET',NULL,NULL,NULL,'admin','2019-04-26 15:43:53'),(1121802190372380674,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:44:19'),(1121802228238557185,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:44:28'),(1121802286803623938,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:44:42'),(1121802308861468674,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/3/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:44:47'),(1121802345226084354,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:44:56'),(1121802724089176066,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:46:26'),(1121802725242609665,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:46:26'),(1121802757530361858,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.109',NULL,NULL,'admin','2019-04-26 15:46:34'),(1121802781370785793,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','remoteAddr=192.168.0.104',NULL,NULL,'admin','2019-04-26 15:46:40'),(1121802799553093633,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/5/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:46:44'),(1121802813696286722,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/3/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:46:48'),(1121802830217650178,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/3/40','GET',NULL,NULL,NULL,'admin','2019-04-26 15:46:51'),(1121802865286225922,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/40','GET',NULL,NULL,NULL,'admin','2019-04-26 15:47:00'),(1121802913587830785,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/40','GET',NULL,NULL,NULL,'admin','2019-04-26 15:47:11'),(1121802949809840129,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/50','GET',NULL,NULL,NULL,'admin','2019-04-26 15:47:20'),(1121803066751229954,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:47:48'),(1121803066784784386,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:47:48'),(1121803111420567553,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','title_=Login',NULL,NULL,'admin','2019-04-26 15:47:59'),(1121803162825957377,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:48:11'),(1121803210091569154,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','title_=Login',NULL,NULL,'admin','2019-04-26 15:48:22'),(1121803443571695618,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET','title_=Login',NULL,NULL,'admin','2019-04-26 15:49:18'),(1121803650279579650,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 15:50:07'),(1121805843820871682,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:58:49'),(1121805873919197185,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:58:57'),(1121805904344678402,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:59:04'),(1121805972674084866,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 15:59:21'),(1121806152500674561,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-26 16:00:04'),(1121806153796714498,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:00:04'),(1121806176496287746,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:00:09'),(1121806212869292033,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:00:18'),(1121806333640081409,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:00:47'),(1121811583520362498,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-25 16:00:00'),(1121811709890547714,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-25 16:00:00'),(1121815202147741698,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:36:00'),(1121816922336706561,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:42:51'),(1121817265678237698,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:44:13'),(1121817284414193666,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:44:18'),(1121817795649519618,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:45:52'),(1121817967502737409,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:46:35'),(1121817967540486145,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:46:30'),(1121817996489572354,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:47:07'),(1121818165146730497,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:47:48'),(1121820157092978689,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 16:55:42'),(1121821720603668482,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:01:55'),(1121821750701993986,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:02:02'),(1121823412716040194,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:08:38'),(1121830525492183042,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:36:54'),(1121830604802277378,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:37:13'),(1121830682560479233,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:37:32'),(1121830728051900418,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:37:43'),(1121831014006964226,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:38:51'),(1121832039082229762,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:42:55'),(1121832102227476482,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:43:10'),(1121832160347947009,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:43:24'),(1121834723843715073,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:53:35'),(1121834768521441282,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:53:46'),(1121834784208138242,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:53:50'),(1121834931331739650,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-26 17:54:25'),(1122534022659354626,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-28 16:12:15'),(1122534098169409537,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-28 16:12:39'),(1122534134760517633,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-28 16:12:48'),(1122534235616751617,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-28 16:13:12'),(1122534275877875714,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-28 16:13:21'),(1122534397932122114,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon','POST',NULL,NULL,NULL,'admin','2019-04-28 16:13:50'),(1122534401874767874,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-28 16:13:51'),(1122534537485004801,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon','PUT',NULL,NULL,NULL,'admin','2019-04-28 16:14:24'),(1122534626072899586,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-28 16:14:45'),(1122534626072899587,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-04-28 16:14:45'),(1122534678006771713,'Operation','Operation',NULL,'192.168.0.104',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-04-28 16:14:57'),(1127409657202483202,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 03:06:22'),(1127409691390255106,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 03:06:31'),(1127409741466050562,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 03:06:43'),(1127411008573677569,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 03:11:45'),(1127411056371965953,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/32/10','GET',NULL,NULL,NULL,'admin','2019-05-12 03:11:56'),(1127412370506129410,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 03:17:10'),(1127412798853619713,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 03:18:52'),(1127473817588211714,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 07:21:20'),(1127473830036905986,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 07:21:23'),(1127473900681568257,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 07:21:40'),(1127481978646831106,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 07:53:45'),(1127496375192657921,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 08:50:58'),(1127496375192657922,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 08:50:58'),(1127496548614545410,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 08:51:39'),(1127496548614545411,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 08:51:39'),(1127497653742346242,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2018-12-12 08:56:03'),(1127497694175436802,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-12 08:56:12'),(1127500533010407425,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 09:07:29'),(1127500579546210306,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-12 09:07:40'),(1129572791744954370,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 02:21:53'),(1129579259999215618,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 02:47:36'),(1129579675654742018,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 02:49:15'),(1129581925852700673,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 02:58:12'),(1129581925852700674,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/user/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 02:58:12'),(1129581962586415105,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/user/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 02:58:21'),(1129582141356040193,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 02:59:03'),(1129582171085266945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 02:59:10'),(1129583223595524098,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:03:21'),(1129583223603912705,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 03:03:21'),(1129585137691320321,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:10:58'),(1129585404679741442,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 03:12:01'),(1129585695173042178,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:13:11'),(1129585800580096001,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:13:36'),(1129585865667305473,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:13:51'),(1129586511787253762,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 03:16:25'),(1129586511820808194,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:16:25'),(1129586557480001538,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:16:36'),(1129586615076184065,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:16:50'),(1129586975215902721,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 03:18:16'),(1129586992920059906,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:18:20'),(1129597136571654145,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2001-05-18 03:58:38'),(1129597136571654146,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2001-05-18 03:58:38'),(1129597321624346626,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 03:59:23'),(1129597321628540930,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:59:23'),(1129597416512086017,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 03:59:45'),(1129597416554029057,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 03:59:45'),(1129597942133874690,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 04:01:50'),(1129597942133874691,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 04:01:50'),(1129598661897412609,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 04:04:42'),(1129598661905801218,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 04:04:42'),(1129646940039340033,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 07:16:32'),(1129648869956050945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 07:24:13'),(1129648892085198850,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 07:24:18'),(1129648916479270914,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 07:24:24'),(1129648950499270658,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 07:24:32'),(1129664033459724289,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/','POST',NULL,NULL,NULL,'admin','2019-05-18 08:24:28'),(1129664171561377793,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 08:25:01'),(1129664194894290945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 08:25:06'),(1129664203287093249,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 08:25:08'),(1129664333461512194,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2011-05-18 08:25:39'),(1129664355548717058,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 08:25:45'),(1129664437090181122,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/','POST',NULL,NULL,NULL,'admin','2019-05-18 08:26:04'),(1129664438860177409,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 08:26:05'),(1129664525573218305,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/','PUT',NULL,NULL,NULL,'admin','2019-05-18 08:26:25'),(1129664583580442625,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 08:26:39'),(1129668525123100674,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET','username=admin0',NULL,NULL,'admin','2019-05-18 08:42:19'),(1129668617934659586,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET','phone=1526666699999',NULL,NULL,'admin','2019-05-18 08:42:41'),(1129668657025572866,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET','phone=15266669999',NULL,NULL,'admin','2019-05-18 08:42:50'),(1129679992631255041,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 09:27:53'),(1129680101041430529,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 09:28:19'),(1129680302225416194,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 09:29:07'),(1129680350657044481,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 09:29:18'),(1129680364074622977,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 09:29:21'),(1129701316791558146,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 10:52:37'),(1129701361708359682,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 10:52:48'),(1129701382281420801,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-iconpage/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 10:52:53'),(1129702082285592578,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-18 10:55:39'),(1129702099394158593,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 10:55:43'),(1129702120806080513,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 10:55:49'),(1129702138241802242,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 10:55:53'),(1129702146550718465,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-18 10:55:55'),(1129910652075638786,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 00:44:26'),(1129910669377142786,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:44:30'),(1129910687404261377,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:44:35'),(1129911268663492609,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:46:53'),(1129911276662030337,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:46:55'),(1129911295737724930,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:47:00'),(1129911331129262081,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:47:08'),(1129911343317909505,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:47:11'),(1129911517071147009,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 00:47:53'),(1129911542828367874,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:47:59'),(1129911581369827329,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 00:48:08'),(1129911581369827330,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:48:08'),(1129911617549893633,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:48:17'),(1129911738022887427,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:48:52'),(1129911820478709762,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 00:49:05'),(1129911876388782082,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:49:18'),(1129911898475986945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:49:23'),(1129912198876233730,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:50:35'),(1129912463373238274,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:51:38'),(1129912821042511874,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:53:03'),(1129912852109721602,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:53:11'),(1129913057005666305,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET','phone=170',NULL,NULL,'admin','2019-05-19 00:54:00'),(1129913262161657858,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 00:54:49'),(1129913262161657859,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:54:49'),(1129913316104601602,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:55:01'),(1129913481146269698,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:55:41'),(1129913655495098370,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 00:56:22'),(1129913676797968386,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:56:27'),(1129913824710098945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET','roleName=hjjbjj',NULL,NULL,'admin','2019-05-19 00:57:03'),(1129913869543014401,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:57:13'),(1129914042197344258,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/','POST',NULL,NULL,NULL,'admin','2019-05-19 00:57:55'),(1129914059960221697,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:57:59'),(1129914135206035457,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/','PUT',NULL,NULL,NULL,'admin','2019-05-19 00:58:17'),(1129914166994665474,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 00:58:24'),(1129916948409614337,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/1129914044097421314','DELETE',NULL,NULL,NULL,'admin','2019-05-19 01:09:27'),(1129916949349138433,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 01:09:28'),(1129917086649679874,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/','POST',NULL,NULL,NULL,'admin','2019-05-19 01:10:00'),(1129917088390316034,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 01:10:01'),(1130053973846654977,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 10:13:57'),(1130053984336609282,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 10:13:59'),(1130060003439673345,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 10:37:54'),(1130060366343438337,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 10:39:21'),(1130060675786604546,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-19 10:40:35'),(1130063039163662338,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2008-12-19 10:49:58'),(1130063039163662339,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2008-12-19 10:49:58'),(1130063079277985793,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-19 10:50:08'),(1134434779616722945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 12:21:42'),(1134434805311029249,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-icon/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:21:48'),(1134434840442519554,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:21:57'),(1134434904414044161,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:22:12'),(1134434914375516161,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:22:14'),(1134435074400796674,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:22:53'),(1134442187919409154,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:09'),(1134442205367713794,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:13'),(1134442252071288833,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:24'),(1134442260522811394,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:26'),(1134442342731169793,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:46'),(1134442347177132034,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:47'),(1134442376872804353,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:54'),(1134442382996488194,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:51:55'),(1134442711410491393,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:53:13'),(1134442739763986434,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:53:20'),(1134442748949512194,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 12:53:22'),(1134444491376640002,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:00:18'),(1134444599417716738,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:00:44'),(1134444863050694658,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:01:46'),(1134444885322448897,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:01:52'),(1134445801211641858,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:05:30'),(1134445822120247298,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:05:35'),(1134446242473394178,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:07:15'),(1134446293748760577,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:07:28'),(1134447158656188418,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:10:54'),(1134447217229643777,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:11:08'),(1134447454333648897,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:12:04'),(1134448012314492930,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:14:17'),(1134448045688569858,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:14:25'),(1134448095592398850,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:14:37'),(1134448610149613569,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:16:40'),(1134448680253210625,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:16:57'),(1134448809945284610,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET','name=www&key=ww',NULL,NULL,'admin','2019-05-31 13:17:27'),(1134450064834904066,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:22:27'),(1134450128101785602,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:22:42'),(1134451387550613506,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:27:42'),(1134451456265895938,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:27:58'),(1134451521223081985,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:28:14'),(1134451643365408769,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET','name=ssss&key=ssss',NULL,NULL,'admin','2019-05-31 13:28:43'),(1134454003017924610,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:38:06'),(1134454003017924611,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:38:06'),(1134454195112853505,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:38:51'),(1134454240285507586,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:39:02'),(1134454569181855746,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2021-07-18 18:40:21'),(1134454569181855747,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-08-18 18:40:21'),(1134455596199133186,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-05-31 13:44:25'),(1134455645670948865,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:44:37'),(1134455728525230081,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','POST',NULL,NULL,NULL,'admin','2019-05-31 13:44:57'),(1134457585490411521,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:52:20'),(1134457612627558401,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:52:26'),(1134457618667356161,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-05-31 13:52:28'),(1135001298549514242,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 01:52:50'),(1135001356816785409,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 01:53:05'),(1135002122105298945,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','POST',NULL,NULL,NULL,'admin','2019-06-02 01:56:07'),(1135002125183918082,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 01:56:08'),(1135003943687331841,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 02:03:22'),(1135003998326530049,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:03:35'),(1135004075564638210,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','PUT',NULL,NULL,NULL,'admin','2019-06-02 02:03:53'),(1135004077233971201,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:03:54'),(1135004194146000898,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','PUT',NULL,NULL,NULL,'admin','2019-06-02 02:04:21'),(1135004195597230082,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:04:22'),(1135004442121641985,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','PUT',NULL,NULL,NULL,'admin','2019-06-02 02:05:21'),(1135004443669340161,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:05:21'),(1135004525235970049,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','PUT',NULL,NULL,NULL,'admin','2019-06-02 02:05:40'),(1135004526859165698,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:05:41'),(1135004583788453890,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/1135002123808165890','DELETE',NULL,NULL,NULL,'admin','2019-06-02 02:05:54'),(1135005444690321410,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 02:09:20'),(1135005444690321411,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:09:20'),(1135006034560458754,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:11:40'),(1135007199515811841,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:16:18'),(1135007222420905986,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index1135002123808165890','DELETE',NULL,NULL,NULL,'admin','2019-06-02 02:16:23'),(1135007496963268610,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:17:29'),(1135007496963268611,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 02:17:29'),(1135007524305936386,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index/1135002123808165890','DELETE',NULL,NULL,NULL,'admin','2019-06-02 02:17:35'),(1135007581675626497,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:17:49'),(1135007702878429185,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/index','POST',NULL,NULL,NULL,'admin','2019-06-02 02:18:18'),(1135007706242260993,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:18:19'),(1135010846668660738,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 02:30:47'),(1135012367699451905,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 02:36:50'),(1135028380306415617,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 03:40:28'),(1135028382059634690,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 03:40:28'),(1135028563337453570,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 03:41:11'),(1135028609374134274,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 03:41:22'),(1135028741507293186,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','PUT',NULL,NULL,NULL,'admin','2019-06-02 03:41:54'),(1135029601343807490,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 03:45:19'),(1135029712752910338,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','PUT',NULL,NULL,NULL,'admin','2019-06-02 03:45:45'),(1135030327453327361,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 03:48:12'),(1135030595016368130,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 03:49:16'),(1135030649823338498,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 03:49:29'),(1135071613501743105,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','POST',NULL,NULL,NULL,'admin','2019-06-02 06:32:15'),(1135072162842320897,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 06:34:26'),(1135072184082276354,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 06:34:31'),(1135074008327692290,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 06:41:46'),(1135074034315599874,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 06:41:53'),(1135074810651910146,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','POST',NULL,NULL,NULL,'admin','2019-06-02 06:44:58'),(1135075337292914690,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 06:47:03'),(1135075355420696577,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 06:47:08'),(1135077531085873154,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 06:55:46'),(1135077531085873155,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 06:55:46'),(1135077696345645058,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','POST',NULL,NULL,NULL,'admin','2019-06-02 06:56:26'),(1135077697813651458,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 06:56:26'),(1135089310629273601,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','POST',NULL,NULL,NULL,'admin','2019-06-02 07:42:35'),(1135089312223109121,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 07:42:35'),(1135098137424601089,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 08:17:39'),(1135098149441282049,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 08:17:42'),(1135112617193807874,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:15:11'),(1135112625062322178,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:15:13'),(1135112837747089410,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:16:04'),(1135112843375845377,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:16:05'),(1135112864712269826,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:16:10'),(1135112921905799169,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:16:24'),(1135113697491329026,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:19:29'),(1135113734560587777,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:19:38'),(1135113789879263234,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:19:51'),(1135113873815674882,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:20:11'),(1135113922339577858,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:20:23'),(1135114017202151426,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:20:45'),(1135114151654760449,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:21:17'),(1135114350406049793,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 09:22:05'),(1135114350406049794,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:22:05'),(1135114543234981890,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:22:51'),(1135114543234981891,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 09:22:51'),(1135114914514771970,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','PUT',NULL,NULL,NULL,'admin','2019-06-02 09:24:19'),(1135114917635334145,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:24:20'),(1135115096195244033,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','PUT',NULL,NULL,NULL,'admin','2019-06-02 09:25:02'),(1135115099080925185,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:25:03'),(1135115684094058498,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:27:23'),(1135115685310406658,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 09:27:23'),(1135115748212383745,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/1135077696547000322','DELETE',NULL,NULL,NULL,'admin','2019-06-02 09:27:38'),(1135117188003700738,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/1135077696547000322','DELETE',NULL,NULL,NULL,'admin','2019-06-02 09:33:21'),(1135117269960400897,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/1135077696547000322','DELETE',NULL,NULL,NULL,'admin','2019-06-02 09:33:41'),(1135117271713619970,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:33:41'),(1135117338788929537,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/','POST',NULL,NULL,NULL,'admin','2019-06-02 09:33:57'),(1135117341800439809,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:33:58'),(1135117521920630785,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/ant-menu/user','GET',NULL,NULL,NULL,'admin','2019-06-02 09:34:41'),(1135117521920630786,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:34:41'),(1135118521192587266,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:38:39'),(1135118549327978497,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:38:46'),(1135118564427472897,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:38:49'),(1135118632371003394,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:39:06'),(1135118884763246593,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:40:06'),(1135118893026025473,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:40:08'),(1135118900999397378,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:40:10'),(1135118967302955009,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:40:25'),(1135122370569609218,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET','method=POST',NULL,NULL,'admin','2019-06-02 09:53:57'),(1135122420381163521,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/syslog-service/log/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:54:09'),(1135123243198750722,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/role/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:57:25'),(1135123251914514433,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/user-service/user/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:57:27'),(1135123259917246465,'Operation','Operation',NULL,'192.168.0.108',NULL,'/api/portal-service/dict/page/1/10','GET',NULL,NULL,NULL,'admin','2019-06-02 09:57:29');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ukulele_syslog'
--

--
-- Dumping routines for database 'ukulele_syslog'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-02 19:59:14
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ukulele_auth
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Dumping events for database 'ukulele_auth'
--

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

-- Dump completed on 2019-06-02 19:59:14
