CREATE DATABASE  IF NOT EXISTS `ukulele_portal` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ukulele_portal`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ukulele_portal
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
INSERT INTO `sys_menu` VALUES (1,'系统管理',NULL,'/admin',NULL,NULL,-1,'wordpress','Layout',1,'0',1,NULL,'2017-11-07 12:56:00',NULL,'2019-04-20 14:00:12'),(2,'用户管理',NULL,'user','',NULL,1,'file-o','views/admin/user/index',2,'0',1,NULL,'2017-11-02 14:24:37',NULL,'2019-04-20 14:00:12'),(3,'菜单管理',NULL,'menu','',NULL,1,'file-o','views/admin/menu/index',3,'0',1,NULL,'2017-11-08 01:57:27',NULL,'2019-04-20 14:00:12'),(4,'角色管理',NULL,'role',NULL,NULL,1,'file-o','views/admin/role/index',4,'0',1,NULL,'2017-11-08 02:13:37',NULL,'2019-04-20 14:00:12'),(5,'日志管理',NULL,'log',NULL,NULL,1,'file-o','views/admin/log/index',5,'0',1,NULL,'2017-11-20 06:06:22',NULL,'2019-04-20 14:00:12'),(6,'字典管理',NULL,'dict',NULL,NULL,1,'file-o','views/admin/dict/index',6,'0',1,NULL,'2017-11-29 03:30:52',NULL,'2019-04-20 14:00:12'),(7,'部门管理',NULL,'dept',NULL,NULL,1,'file-o','views/admin/dept/index',7,'0',1,NULL,'2018-01-20 05:17:19',NULL,'2019-04-20 14:00:12'),(8,'系统监控',NULL,'/taroco-admin',NULL,NULL,-1,'pie-chart','Layout',8,'0',1,NULL,'2018-01-22 04:30:41',NULL,'2019-04-20 14:00:12'),(14,'接口文档',NULL,'taroco-api','',NULL,8,'file-text-o','views/service/swagger/index',2,'0',1,NULL,'2018-01-23 02:56:43',NULL,'2019-04-20 14:00:12'),(21,'用户查看','',NULL,'/admin/user/**','GET',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-07 12:58:05',NULL,'2019-04-20 14:00:12'),(22,'用户新增','sys_user_add',NULL,'/admin/user/*','POST',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:52:09',NULL,'2019-04-20 14:00:12'),(23,'用户修改','sys_user_upd',NULL,'/admin/user/**','PUT',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:52:48',NULL,'2019-04-20 14:00:12'),(24,'用户删除','sys_user_del',NULL,'/admin/user/*','DELETE',2,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:54:01',NULL,'2019-04-20 14:00:12'),(25,'服务监控',NULL,'service',NULL,NULL,8,'server','views/service/index',1,'0',1,NULL,'2018-08-10 00:09:16',NULL,'2019-04-20 14:00:12'),(28,'调用链监控',NULL,'zipkin',NULL,NULL,8,'bar-chart-o','views/service/zipkin/index',3,'0',1,NULL,'2018-09-18 09:18:53',NULL,'2019-04-20 14:00:12'),(31,'菜单查看',NULL,NULL,'/admin/menu/**','GET',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 01:57:56',NULL,'2019-04-20 14:00:12'),(32,'菜单新增','sys_menu_add',NULL,'/admin/menu/*','POST',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:15:53',NULL,'2019-04-20 14:00:12'),(33,'菜单修改','sys_menu_edit',NULL,'/admin/menu/*','PUT',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:16:23',NULL,'2019-04-20 14:00:12'),(34,'菜单删除','sys_menu_del',NULL,'/admin/menu/*','DELETE',3,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:16:43',NULL,'2019-04-20 14:00:12'),(41,'角色查看',NULL,NULL,'/admin/role/**','GET',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:01',NULL,'2019-04-20 14:00:12'),(42,'角色新增','sys_role_add',NULL,'/admin/role/*','POST',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:18',NULL,'2019-04-20 14:00:12'),(43,'角色修改','sys_role_edit',NULL,'/admin/role/*','PUT',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:41',NULL,'2019-04-20 14:00:12'),(44,'角色删除','sys_role_del',NULL,'/admin/role/*','DELETE',4,NULL,NULL,NULL,'1',1,NULL,'2017-11-08 02:14:59',NULL,'2019-04-20 14:00:12'),(45,'分配权限','sys_role_perm',NULL,'/admin/role/*','PUT',4,NULL,NULL,NULL,'1',1,NULL,'2018-04-19 23:22:55',NULL,'2019-04-20 14:00:12'),(51,'日志查看',NULL,NULL,'/admin/log/**','GET',5,NULL,NULL,NULL,'1',1,NULL,'2017-11-20 06:07:25',NULL,'2019-04-20 14:00:12'),(52,'日志删除','sys_log_del',NULL,'/admin/log/*','DELETE',5,NULL,NULL,NULL,'1',1,NULL,'2017-11-20 12:37:37',NULL,'2019-04-20 14:00:12'),(61,'字典查看',NULL,NULL,'/admin/dict/**','GET',6,NULL,NULL,NULL,'1',1,NULL,'2017-11-19 14:04:24',NULL,'2019-04-20 14:00:12'),(62,'字典删除','sys_dict_del',NULL,'/admin/dict/**','DELETE',6,NULL,NULL,NULL,'1',1,NULL,'2017-11-29 03:30:11',NULL,'2019-04-20 14:00:12'),(63,'字典新增','sys_dict_add',NULL,'/admin/dict/**','POST',6,NULL,NULL,NULL,'1',1,NULL,'2018-05-11 14:34:55',NULL,'2019-04-20 14:00:12'),(64,'字典修改','sys_dict_upd',NULL,'/admin/dict/**','PUT',6,NULL,NULL,NULL,'1',1,NULL,'2018-05-11 14:36:03',NULL,'2019-04-20 14:00:12'),(71,'部门查看','',NULL,'/admin/dept/**','GET',7,NULL,'',NULL,'1',1,NULL,'2018-01-20 05:17:19',NULL,'2019-04-20 14:00:12'),(72,'部门新增','sys_dept_add',NULL,'/admin/dept/**','POST',7,NULL,NULL,NULL,'1',1,NULL,'2018-01-20 06:56:16',NULL,'2019-04-20 14:00:12'),(73,'部门修改','sys_dept_edit',NULL,'/admin/dept/**','PUT',7,NULL,NULL,NULL,'1',1,NULL,'2018-01-20 06:56:59',NULL,'2019-04-20 14:00:12'),(74,'部门删除','sys_dept_del',NULL,'/admin/dept/**','DELETE',7,NULL,NULL,NULL,'1',1,NULL,'2018-01-20 06:57:28',NULL,'2019-04-20 14:00:12'),(100,'客户端管理','','client','','',1,'file-o','views/admin/client/index',9,'0',1,NULL,'2018-01-20 05:17:19',NULL,'2019-04-20 14:00:12'),(101,'客户端新增','sys_client_add',NULL,'/admin/client/**','POST',100,'1',NULL,NULL,'1',1,NULL,'2018-05-15 13:35:18',NULL,'2019-04-20 14:00:12'),(102,'客户端修改','sys_client_upd',NULL,'/admin/client/**','PUT',100,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:37:06',NULL,'2019-04-20 14:00:12'),(103,'客户端删除','sys_client_del',NULL,'/admin/client/**','DELETE',100,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:39:16',NULL,'2019-04-20 14:00:12'),(104,'客户端查看',NULL,NULL,'/admin/client/**','GET',100,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:39:57',NULL,'2019-04-20 14:00:12'),(110,'路由管理',NULL,'route',NULL,NULL,1,'file-o','views/admin/route/index',8,'0',1,NULL,'2018-05-15 13:44:51',NULL,'2019-04-20 14:00:12'),(111,'路由查看',NULL,NULL,'/admin/route/**','GET',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:45:59',NULL,'2019-04-20 14:00:12'),(112,'路由新增','sys_route_add',NULL,'/admin/route/**','POST',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:52:22',NULL,'2019-04-20 14:00:12'),(113,'路由修改','sys_route_upd',NULL,'/admin/route/**','PUT',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:55:38',NULL,'2019-04-20 14:00:12'),(114,'路由删除','sys_route_del',NULL,'/admin/route/**','DELETE',110,NULL,NULL,NULL,'1',1,NULL,'2018-05-15 13:56:45',NULL,'2019-04-20 14:00:12'),(251,'服务查询',NULL,NULL,'/taroco-admin/api/**','GET',25,NULL,NULL,1,'1',1,NULL,'2018-08-10 00:25:50',NULL,'2019-04-20 14:00:12'),(252,'设置权重和标签','taroco_admin_set_weight',NULL,'/taroco-registry/eureka/apps/**','PUT',25,NULL,NULL,2,'1',1,NULL,'2018-08-10 00:54:10',NULL,'2019-04-20 14:00:12'),(253,'服务日志级别设置',NULL,NULL,'/taroco-admin/api/applications/*/loggers/*','POST',25,NULL,NULL,1,'1',1,NULL,'2018-08-10 01:17:06',NULL,'2019-04-20 14:00:12'),(254,'删除服务',NULL,NULL,'/taroco-admin/api/applications/*','DELETE',25,'',NULL,4,'1',1,NULL,'2018-09-18 09:37:08',NULL,'2019-04-20 14:00:12');
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
INSERT INTO `sys_role` VALUES (1,'admin','ROLE_ADMIN','超级管理员',1,NULL,'2017-10-29 07:45:51',NULL,'2019-04-20 14:00:19'),(14,'demo','demo','demo用户',1,NULL,'2018-04-19 23:14:32',NULL,'2018-04-21 15:35:22');
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
INSERT INTO `sys_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,14),(1,21),(1,22),(1,23),(1,24),(1,25),(1,28),(1,31),(1,32),(1,33),(1,34),(1,41),(1,42),(1,43),(1,44),(1,45),(1,51),(1,52),(1,61),(1,62),(1,63),(1,64),(1,71),(1,72),(1,73),(1,74),(1,100),(1,101),(1,102),(1,103),(1,104),(1,110),(1,111),(1,112),(1,113),(1,114),(1,251),(1,252),(1,253),(1,254),(14,1),(14,2),(14,3),(14,4),(14,5),(14,6),(14,7),(14,8),(14,14),(14,21),(14,31),(14,41),(14,51),(14,61),(14,71);
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
  `path_` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '路由路径',
  `service_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '服务名称',
  `url_` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'url代理',
  `strip_prefix` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COMMENT '转发去掉前缀',
  `retryable_` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COMMENT '是否重试',
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

-- Dump completed on 2019-04-21 15:45:55
