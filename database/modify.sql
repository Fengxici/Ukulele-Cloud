##2019-04-22 新增 sys_menu_ant和sys_ant_icon两张表
CREATE TABLE `sys_menu_ant` (
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
  `icon_` bigint(64) DEFAULT NULL COMMENT '图标',
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

CREATE TABLE `sys_ant_icon` (
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
