CREATE DATABASE `crm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crm`;

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_hide` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `source_key` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5ra2gn0xedeida2op8097sr5` (`parent_id`),
  CONSTRAINT `FKf5ra2gn0xedeida2op8097sr5` FOREIGN KEY (`parent_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_resource` */

insert  into `t_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values (1,'2017-01-10 13:56:51','用户管理',NULL,0,2,'用户管理',1,'system:user:index','/admin/user/index',1,'2017-01-10 13:59:01',NULL),(2,'2017-01-10 13:56:51','用户编辑',NULL,0,3,'用户编辑',1,'system:user:edit','/admin/user/edit*',2,'2017-01-10 16:26:42',1),(3,'2017-01-11 16:48:48','用户添加',NULL,0,3,'用户添加',2,'system:user:add','/admin/user/add',2,'2017-01-11 16:49:26',1),(4,'2017-01-11 16:48:48','用户删除',NULL,0,3,'用户删除',3,'system:user:deleteBatch','/admin/user/deleteBatch',2,'2017-01-18 14:11:41',1),(5,'2017-01-11 16:48:48','角色分配',NULL,0,3,'角色分配',4,'system:user:grant','/admin/user/grant/**',2,'2017-01-18 14:11:51',1),(6,'2017-01-12 16:45:10','角色管理',NULL,0,2,'角色管理',2,'system:role:index','/admin/role/index',1,'2017-01-12 16:46:52',NULL),(7,'2017-01-12 16:47:02','角色编辑',NULL,0,3,'角色编辑',1,'system:role:edit','/admin/role/edit*',2,'2017-01-18 10:24:06',1),(8,'2017-01-12 16:47:23','角色添加',NULL,0,3,'角色添加',2,'system:role:add','/admin/role/add',2,'2017-01-12 16:49:16',6),(9,'2017-01-12 16:47:23','角色删除',NULL,0,3,'角色删除',3,'system:role:deleteBatch','/admin/role/deleteBatch',2,'2017-01-18 14:12:03',6),(10,'2017-01-12 16:47:23','资源分配',NULL,0,3,'资源分配',4,'system:role:grant','/admin/role/grant/**',2,'2017-01-18 14:12:11',6),(11,'2017-01-17 11:21:12','资源管理',NULL,0,2,'资源管理',3,'system:resource:index','/admin/resource/index',1,'2017-01-17 11:21:42',NULL),(12,'2017-01-17 11:21:52','资源编辑',NULL,0,3,'资源编辑',1,'system:resource:edit','/admin/resource/edit*',2,'2017-01-17 11:22:36',11),(13,'2017-01-17 11:21:54','资源添加',NULL,0,3,'资源添加',2,'system:resource:add','/admin/resource/add',2,'2017-01-17 11:22:39',11),(14,'2017-01-17 11:21:54','资源删除',NULL,0,3,'资源删除',3,'system:resource:deleteBatch','/admin/resource/deleteBatch',2,'2017-01-18 14:12:31',11);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_key` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`create_time`,`description`,`name`,`role_key`,`status`,`update_time`) values (1,'2017-01-09 17:25:30','超级管理员','administrator','administrator',0,'2017-01-09 17:26:25');

/*Table structure for table `t_role_resource` */

DROP TABLE IF EXISTS `t_role_resource`;

CREATE TABLE `t_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK868kc8iic48ilv5npa80ut6qo` (`resource_id`),
  CONSTRAINT `FK7ffc7h6obqxflhj1aq1mk20jk` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK868kc8iic48ilv5npa80ut6qo` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_resource` */

insert  into `t_role_resource`(`role_id`,`resource_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `locked` int(11) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`address`,`birthday`,`create_time`,`delete_status`,`description`,`email`,`locked`,`nick_name`,`password`,`sex`,`telephone`,`update_time`,`user_name`) values (1,'上海','2017-01-09 17:26:39','2017-01-09 17:26:41',0,'超级管理员','whoismy8023@163.com',0,'admin','UUKHSDDI5KPA43A8VL06V0TU2',1,'15923930000','2017-01-09 17:27:11','admin');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1);


/*********新功能*********/


/*********菜单 start*********/
-- 新增会员管理菜单
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (10000, '2018-1-11 16:04:39', '会员管理', NULL, 0, 2, '会员管理', 1, 'crm:member:index', '/admin/member/index', 1, '2018-1-11 16:07:47', NULL);

-- 会员管理授权
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 10000);


-- 新增会员添加按钮
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (10010, '2018-1-11 16:04:39', '会员添加', NULL, 0, 3, '会员添加', 1, 'crm:member:add', '/admin/member/addview', 2, '2018-1-11 16:07:47', 10000);

-- 新增会员编辑按钮
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (10020, '2018-1-11 16:04:39', '会员编辑', NULL, 0, 3, '会员编辑', 1, 'crm:member:edit', '/admin/member/edit*', 2, '2018-1-11 16:07:47', 10000);


-- 添加会员按钮授权
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 10010);
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 10020);


-- 新增会员编码管理菜单
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (20000, '2018-1-11 16:04:39', '会员编码管理', NULL, 0, 2, '会员编码管理', 1, 'crm:membercode:index', '/admin/membercode/index', 1, '2018-1-11 16:07:47', NULL);

-- 会员管理授权
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 20000);


-- 新增工作人员管理菜单
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (30000, '2018-1-11 16:04:39', '工作人员管理', NULL, 0, 2, '工作人员管理', 1, 'crm:staff:index', '/admin/staff/index', 1, '2018-1-11 16:07:47', NULL);

-- 工作人员管理授权
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 30000);


-- 新增工作人员添加按钮
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (30010, '2018-1-11 16:04:39', '工作人员添加', NULL, 0, 3, '工作人员添加', 1, 'crm:staff:add', '/admin/staff/addview', 2, '2018-1-11 16:07:47', 30000);

-- 新增工作人员编辑按钮
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (30020, '2018-1-11 16:04:39', '工作人员编辑', NULL, 0, 3, '工作人员编辑', 1, 'crm:staff:edit', '/admin/staff/edit*', 2, '2018-1-11 16:07:47', 30000);

-- 新增分配记录按钮
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (30030, '2018-1-11 16:04:39', '分配记录', NULL, 0, 3, '分配记录', 1, 'crm:staff:allotrecords', '/admin/staff/allotrecords*', 2, '2018-1-11 16:07:47', 30000);

-- 添加工作人员按钮授权
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 30010);
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 30020);
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 30030);


-- 新增投资记录管理菜单
INSERT INTO `t_resource` 
(`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`) 
VALUES (40000, '2018-1-11 16:04:39', '投资记录管理', NULL, 0, 2, '投资记录管理', 1, 'crm:investment:index', '/admin/investment/index', 1, '2018-1-11 16:07:47', NULL);

-- 投资记录管理授权
INSERT INTO `t_role_resource` (`role_id`, `resource_id`) VALUES (1, 40000);

/*********菜单 end*********/


/*********业务表 start*********/

-- 会员表

DROP TABLE IF EXISTS `t_a_member`;

CREATE TABLE `t_a_member` (
	`id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`phone`  varchar(20) NULL DEFAULT NULL COMMENT '手机号' ,
	`real_name`  varchar(100) NULL DEFAULT NULL COMMENT '真实姓名' ,
	`identity_type`  varchar(100) NULL DEFAULT NULL COMMENT '证件类型(01:身份证, 02:因私护照, 03:因公护照, 04:香港永久性居民身份证, 05:澳门永久性居民身份证, 06:港澳居民来往内地通行证, 07:台湾居民来往大陆通行证, 08:外国人永久居住证, 09:其他证件)' ,
	`identity_no`  varchar(100) NULL DEFAULT NULL COMMENT '证件号' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`address` varchar(255) NULL DEFAULT NULL COMMENT '地址' ,
	`user_status` varchar(100) NULL DEFAULT '00' COMMENT '用户状态(00:正常,10:冻结)' ,
	`email`  varchar(100) NULL DEFAULT NULL COMMENT '电子邮件' ,
	`app_platform` varchar(30) NULL DEFAULT 'A' COMMENT 'app平台(A:融侨宝,B:金管家,C:融侨普惠)' ,
	`financial_level`  int(11) NULL DEFAULT 0 COMMENT '理财等级(0-5)' ,
	`register_date`  varchar(30) NULL DEFAULT NULL COMMENT '注册日期(年月:1801)' ,
	`staff_no`  varchar(30) NULL DEFAULT NULL COMMENT '工作人员编号(从G-Z)' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
)COMMENT='融侨宝会员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_b_member`;

CREATE TABLE `t_b_member` (
	`id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`phone`  varchar(20) NULL DEFAULT NULL COMMENT '手机号' ,
	`real_name`  varchar(100) NULL DEFAULT NULL COMMENT '真实姓名' ,
	`identity_type`  varchar(100) NULL DEFAULT NULL COMMENT '证件类型(01:身份证, 02:因私护照, 03:因公护照, 04:香港永久性居民身份证, 05:澳门永久性居民身份证, 06:港澳居民来往内地通行证, 07:台湾居民来往大陆通行证, 08:外国人永久居住证, 09:其他证件)' ,
	`identity_no`  varchar(100) NULL DEFAULT NULL COMMENT '证件号' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`address`  varchar(255) NULL DEFAULT NULL COMMENT '地址' ,
	`user_status`  varchar(100) NULL DEFAULT '00' COMMENT '用户状态(00:正常,10:冻结)' ,
	`email`  varchar(100) NULL DEFAULT NULL COMMENT '电子邮件' ,
	`app_platform`  varchar(30) NULL DEFAULT 'B' COMMENT 'app平台(A:融侨宝,B:金管家,C:融侨普惠)' ,
	`financial_level`  int(11) NULL DEFAULT 0 COMMENT '理财等级(0-5)' ,
	`register_date`  varchar(30) NULL DEFAULT NULL COMMENT '注册日期(年月:1801)' ,
	`staff_no`  varchar(30) NULL DEFAULT NULL COMMENT '工作人员编号(从G-Z)' ,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
)COMMENT='金管家会员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_c_member`;

CREATE TABLE `t_c_member` (
	`id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`phone`  varchar(20) NULL DEFAULT NULL COMMENT '手机号' ,
	`real_name`  varchar(100) NULL DEFAULT NULL COMMENT '真实姓名' ,
	`identity_type`  varchar(100) NULL DEFAULT NULL COMMENT '证件类型(01:身份证, 02:因私护照, 03:因公护照, 04:香港永久性居民身份证, 05:澳门永久性居民身份证, 06:港澳居民来往内地通行证, 07:台湾居民来往大陆通行证, 08:外国人永久居住证, 09:其他证件)' ,
	`identity_no`  varchar(100) NULL DEFAULT NULL COMMENT '证件号' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`address`  varchar(255) NULL DEFAULT NULL COMMENT '地址' ,
	`user_status`  varchar(100) NULL DEFAULT '00' COMMENT '用户状态(00:正常,10:冻结)' ,
	`email`  varchar(100) NULL DEFAULT NULL COMMENT '电子邮件' ,
	`app_platform`  varchar(30) NULL DEFAULT 'C' COMMENT 'app平台(A:融侨宝,B:金管家,C:融侨普惠)' ,
	`financial_level`  int(11) NULL DEFAULT 0 COMMENT '理财等级(0-5)' ,
	`register_date`  varchar(30) NULL DEFAULT NULL COMMENT '注册日期(年月:1801)' ,
	`staff_no`  varchar(30) NULL DEFAULT NULL COMMENT '工作人员编号(从G-Z)' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
)COMMENT='融侨普惠会员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 会员编码表

DROP TABLE IF EXISTS `t_a_member_code`;

CREATE TABLE `t_a_member_code` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`member_code`  varchar(32) NULL DEFAULT NULL COMMENT '会员编码' ,
	`member_id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`province`  varchar(32) NULL DEFAULT NULL COMMENT '省份(身份证对应数字)' ,
	`app_platform`  varchar(30) NULL DEFAULT 'A' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`is_investment`  int(11) NULL DEFAULT 0 COMMENT '是否投资理财(0:否，1:是)' ,
	`financial_level`  int(11) NULL DEFAULT 0 COMMENT '理财等级(0-5)' ,
	`investment_amount` varchar(4) NULL DEFAULT '0000' COMMENT '投资金额(1-9999元起算1w=0001,逢1w进1)' ,
	`register_date`  varchar(30) NULL DEFAULT NULL COMMENT '注册日期(年月:1801)' ,
	`birthdate`  varchar(30) NULL DEFAULT NULL COMMENT '出生日期(年月日:920623)' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`staff_no`  varchar(30) NULL DEFAULT NULL COMMENT '工作人员编号(从G-Z)' ,
	`code_no`  varchar(30) NULL DEFAULT '00000000' COMMENT '编号' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`member_id`)
)COMMENT='融侨宝会员编码表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_b_member_code`;

CREATE TABLE `t_b_member_code` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`member_code`  varchar(32) NULL DEFAULT NULL COMMENT '会员编码' ,
	`member_id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`province`  varchar(32) NULL DEFAULT NULL COMMENT '省份(身份证对应数字)' ,
	`app_platform`  varchar(30) NULL DEFAULT 'B' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`is_investment`  int(11) NULL DEFAULT 0 COMMENT '是否投资理财(0:否，1:是)' ,
	`financial_level`  int(11) NULL DEFAULT 0 COMMENT '理财等级(0-5)' ,
	`investment_amount` varchar(4) NULL DEFAULT '0000' COMMENT '投资金额(1-9999元起算1w=0001,逢1w进1)' ,
	`register_date`  varchar(30) NULL DEFAULT NULL COMMENT '注册日期(年月:1801)' ,
	`birthdate`  varchar(30) NULL DEFAULT NULL COMMENT '出生日期(年月日:920623)' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`staff_no`  varchar(30) NULL DEFAULT NULL COMMENT '工作人员编号(从G-Z)' ,
	`code_no`  varchar(30) NULL DEFAULT '00000000' COMMENT '编号' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`member_id`)
)COMMENT='金管家会员编码表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_c_member_code`;

CREATE TABLE `t_c_member_code` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`member_code`  varchar(32) NULL DEFAULT NULL COMMENT '会员编码' ,
	`member_id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`province`  varchar(32) NULL DEFAULT NULL COMMENT '省份(身份证对应数字)' ,
	`app_platform`  varchar(30) NULL DEFAULT 'C' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`is_investment`  int(11) NULL DEFAULT 0 COMMENT '是否投资理财(0:否，1:是)' ,
	`financial_level`  int(11) NULL DEFAULT 0 COMMENT '理财等级(0-5)' ,
	`investment_amount` varchar(4) NULL DEFAULT '0000' COMMENT '投资金额(1-9999元起算1w=0001,逢1w进1)' ,
	`register_date`  varchar(30) NULL DEFAULT NULL COMMENT '注册日期(年月:1801)' ,
	`birthdate`  varchar(30) NULL DEFAULT NULL COMMENT '出生日期(年月日:920623)' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`staff_no`  varchar(30) NULL DEFAULT NULL COMMENT '工作人员编号(从G-Z)' ,
	`code_no`  varchar(30) NULL DEFAULT '00000000' COMMENT '编号' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`member_id`)
)COMMENT='融侨普惠会员编码表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 工作人员表

DROP TABLE IF EXISTS `t_a_staff`;

CREATE TABLE `t_a_staff` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`app_platform`  varchar(30) NULL DEFAULT 'A' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`staff_no`  varchar(32) NOT NULL COMMENT '工作人员编号(G-Z)' ,
	`name`  varchar(32) NULL DEFAULT NULL COMMENT '工作人员名称' ,
	`phone`  varchar(32) NULL DEFAULT NULL COMMENT '手机号' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`status` varchar(100) NULL DEFAULT '00' COMMENT '状态(00:正常,10:冻结)' ,
	`email`  varchar(100) NULL DEFAULT NULL COMMENT '电子邮件' ,
	`member_num` int(11) NULL DEFAULT 0 COMMENT '会员人数' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`staff_no`)
)COMMENT='工作人员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_b_staff`;

CREATE TABLE `t_b_staff` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`app_platform`  varchar(30) NULL DEFAULT 'B' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`staff_no`  varchar(32) NOT NULL COMMENT '工作人员编号(G-Z)' ,
	`name`  varchar(32) NULL DEFAULT NULL COMMENT '工作人员名称' ,
	`phone`  varchar(32) NULL DEFAULT NULL COMMENT '手机号' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`status` varchar(100) NULL DEFAULT '00' COMMENT '状态(00:正常,10:冻结)' ,
	`email`  varchar(100) NULL DEFAULT NULL COMMENT '电子邮件' ,
	`member_num` int(11) NULL DEFAULT 0 COMMENT '会员人数' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`staff_no`)
)COMMENT='工作人员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_c_staff`;

CREATE TABLE `t_c_staff` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`app_platform`  varchar(30) NULL DEFAULT 'C' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`staff_no`  varchar(32) NOT NULL COMMENT '工作人员编号(G-Z)' ,
	`name`  varchar(32) NULL DEFAULT NULL COMMENT '工作人员名称' ,
	`phone`  varchar(32) NULL DEFAULT NULL COMMENT '手机号' ,
	`sex` int(11) NULL DEFAULT NULL COMMENT '性别(1:男, 2:女)' ,
	`status` varchar(100) NULL DEFAULT '00' COMMENT '状态(00:正常,10:冻结)' ,
	`email`  varchar(100) NULL DEFAULT NULL COMMENT '电子邮件' ,
	`member_num` int(11) NULL DEFAULT 0 COMMENT '会员人数' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`staff_no`)
)COMMENT='工作人员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_a_staff_allot_records`;

CREATE TABLE `t_a_staff_allot_records` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID' ,
	`staff_no`  varchar(32) NOT NULL COMMENT '工作人员编号' ,
	`app_platform`  varchar(30) NULL DEFAULT 'A' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`member_id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`allot_time` timestamp NULL DEFAULT NULL COMMENT '分配时间' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
)COMMENT='工作人员分配记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_b_staff_allot_records`;

CREATE TABLE `t_b_staff_allot_records` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID' ,
	`staff_no`  varchar(32) NOT NULL COMMENT '工作人员编号' ,
	`app_platform`  varchar(30) NULL DEFAULT 'B' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`member_id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`allot_time` timestamp NULL DEFAULT NULL COMMENT '分配时间' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
)COMMENT='工作人员分配记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_c_staff_allot_records`;

CREATE TABLE `t_c_staff_allot_records` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID' ,
	`staff_no`  varchar(32) NOT NULL COMMENT '工作人员编号' ,
	`app_platform`  varchar(30) NULL DEFAULT 'C' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`member_id`  varchar(32) NOT NULL COMMENT '会员ID' ,
	`allot_time` timestamp NULL DEFAULT NULL COMMENT '分配时间' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
)COMMENT='工作人员分配记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 投资记录

DROP TABLE IF EXISTS `t_a_investment_records`;

CREATE TABLE `t_a_investment_records` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`app_platform`  varchar(30) NULL DEFAULT 'A' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`member_id`  varchar(32) NULL DEFAULT NULL COMMENT '会员ID' ,
	`order_no`  varchar(100) NULL DEFAULT NULL COMMENT '订单编号' ,
	`title`  varchar(128) NULL DEFAULT NULL COMMENT '订单标题-存储标题类或者短内容' ,
	`total_price`  decimal(15,4) NULL DEFAULT 0.0000 COMMENT '总金额' ,
	`order_status`  varchar(100) NULL DEFAULT NULL COMMENT '订单状态(apply_ma_to_rf_confirm:成功;apply_ma_to_rf_no_confirm:失败)' ,
	`product_id`  varchar(32)  NULL DEFAULT NULL COMMENT '产品ID' ,
	`order_type`  varchar(32) NULL DEFAULT NULL COMMENT '订单类型(batch_apply-批量申购;apply-单笔申购;withdraw_realtime-实时提现)' ,
	`order_token`  varchar(36) NULL DEFAULT NULL COMMENT '订单唯一标示token' ,
	`channel_trade_id`  varchar(2050) NULL DEFAULT NULL COMMENT '第三方唯一交易流水号' ,
	`investment_time` timestamp NULL DEFAULT NULL COMMENT '投资时间' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`order_no`),
INDEX `t_investment_ix_order_no` (`order_no`),
INDEX `t_investment_ix_app_platform` (`app_platform`),
INDEX `t_investment_ix_create_time` (`create_time`),
INDEX `t_investment_ix_user_id` (`member_id`) 
)COMMENT='投资记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_b_investment_records`;

CREATE TABLE `t_b_investment_records` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`app_platform`  varchar(30) NULL DEFAULT 'B' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`member_id`  varchar(32) NULL DEFAULT NULL COMMENT '会员ID' ,
	`order_no`  varchar(100) NULL DEFAULT NULL COMMENT '订单编号' ,
	`title`  varchar(128) NULL DEFAULT NULL COMMENT '订单标题-存储标题类或者短内容' ,
	`total_price`  decimal(15,4) NULL DEFAULT 0.0000 COMMENT '总金额' ,
	`order_status`  varchar(100) NULL DEFAULT NULL COMMENT '订单状态(apply_ma_to_rf_confirm:成功;apply_ma_to_rf_no_confirm:失败)' ,
	`product_id`  varchar(32)  NULL DEFAULT NULL COMMENT '产品ID' ,
	`order_type`  varchar(32) NULL DEFAULT NULL COMMENT '订单类型(batch_apply-批量申购;apply-单笔申购;withdraw_realtime-实时提现)' ,
	`order_token`  varchar(36) NULL DEFAULT NULL COMMENT '订单唯一标示token' ,
	`channel_trade_id`  varchar(2050) NULL DEFAULT NULL COMMENT '第三方唯一交易流水号' ,
	`investment_time` timestamp NULL DEFAULT NULL COMMENT '投资时间' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`order_no`),
INDEX `t_investment_ix_order_no` (`order_no`),
INDEX `t_investment_ix_app_platform` (`app_platform`),
INDEX `t_investment_ix_create_time` (`create_time`),
INDEX `t_investment_ix_user_id` (`member_id`) 
)COMMENT='投资记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_c_investment_records`;

CREATE TABLE `t_c_investment_records` (
	`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	`app_platform`  varchar(30) NULL DEFAULT 'C' COMMENT 'app平台(A:融侨宝, B:金管家, C:融侨普惠)' ,
	`member_id`  varchar(32) NULL DEFAULT NULL COMMENT '会员ID' ,
	`order_no`  varchar(100) NULL DEFAULT NULL COMMENT '订单编号' ,
	`title`  varchar(128) NULL DEFAULT NULL COMMENT '订单标题-存储标题类或者短内容' ,
	`total_price`  decimal(15,4) NULL DEFAULT 0.0000 COMMENT '总金额' ,
	`order_status`  varchar(100) NULL DEFAULT NULL COMMENT '订单状态(apply_ma_to_rf_confirm:成功;apply_ma_to_rf_no_confirm:失败)' ,
	`product_id`  varchar(32)  NULL DEFAULT NULL COMMENT '产品ID' ,
	`order_type`  varchar(32) NULL DEFAULT NULL COMMENT '订单类型(batch_apply-批量申购;apply-单笔申购;withdraw_realtime-实时提现)' ,
	`order_token`  varchar(36) NULL DEFAULT NULL COMMENT '订单唯一标示token' ,
	`channel_trade_id`  varchar(2050) NULL DEFAULT NULL COMMENT '第三方唯一交易流水号' ,
	`investment_time` timestamp NULL DEFAULT NULL COMMENT '投资时间' ,
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`),
UNIQUE KEY (`order_no`),
INDEX `t_investment_ix_order_no` (`order_no`),
INDEX `t_investment_ix_app_platform` (`app_platform`),
INDEX `t_investment_ix_create_time` (`create_time`),
INDEX `t_investment_ix_user_id` (`member_id`) 
)COMMENT='投资记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*********业务表 end*********/


/*********邮件模板 start*********/

DROP TABLE IF EXISTS `t_mail_template`;

CREATE TABLE `t_mail_template` (
	`id`  int(11) NOT NULL AUTO_INCREMENT ,
	`template_id`  varchar(255) NULL DEFAULT NULL COMMENT '模板ID 唯一' ,
	`description`  varchar(2000) NULL DEFAULT NULL COMMENT '模板说明' ,
	`template_subject`  varchar(255) NULL DEFAULT NULL COMMENT '模板标题' ,
	`template_content`  varchar(12000)NULL DEFAULT NULL COMMENT '模板内容' ,
	`status`  varchar(1) NULL DEFAULT '1' COMMENT '模板状态(0:失效, 1:启用)' ,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
PRIMARY KEY (`id`)
) COMMENT='邮件模板表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*********邮件模板 end*********/
