DROP DATABASE IF EXISTS board;

CREATE DATABASE board DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE board;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS permission;
DROP TABLE IF EXISTS role_permission;
 
 

 
/*==============================================================*/
/* Table: user     用户表                                     */
/*==============================================================*/
create table user
(
   user_id		int		not null	AUTO_INCREMENT	COMMENT '用户编号',
   user_name		varchar(45)     not null			COMMENT '用户名',
   password		varchar(45)     not null			COMMENT '密码',
   role_id		int             not null			COMMENT '角色编号',
   name			varchar(45)	default NULL			COMMENT '姓名',
   sex			VARCHAR(2)	default '男'			COMMENT '性别',
   phone		varchar(11)	default NULL			COMMENT '手机号',
   email		varchar(45)	default NULL			COMMENT '邮箱地址',
   last_login_date      datetime					COMMENT '最后登录时间',
   primary key (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '用户表';


/*==============================================================*/
/* Table: role    角色表                                */
/*==============================================================*/
create table role
(
   role_id		int		not null			COMMENT '角色编号',
   role_name		varchar(45)	default NULL			COMMENT '角色名',
   description		varchar(255)					COMMENT '角色描述',
   primary key (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '角色表';


/*==============================================================*/
/* Table: permission   权限表                           */
/*==============================================================*/
create table permission
(
   permission_id	int		not null			COMMENT '权限编号',
   permission_name	varchar(45)	default NULL			COMMENT '权限名',
   description		varchar(255)					COMMENT '描述' ,
   primary key (permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '权限表';


/*==============================================================*/
/* Table: role_permission       角色-权限中间表                           */
/*==============================================================*/
create table role_permission
(
   role_id		int		not null	default 0	COMMENT '角色编号',
   permission_id	int		not null	default 0	COMMENT '权限编号',
   primary key (role_id, permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '角色-权限中间表';

/*==============================================================*/
/* Table: message       消息内容表                           */
/*==============================================================*/
create table message
(
   message_id		int		not null	AUTO_INCREMENT	COMMENT '消息编号',
   super_message_id	int		default NULL	        	COMMENT '父消息编号',
   message_content	text						COMMENT '消息内容',
   message_date		datetime					COMMENT '消息发布时间',
   image_url		varchar(255)					COMMENT '图片地址',
   video_url		varchar(255)					COMMENT '视频地址',
   user_id		int		not null	default 0	COMMENT '用户编号',
   primary key (message_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '角色-权限中间表';
  

/*插入正式数据==============================================================*/

INSERT INTO user (user_id,user_name,password,role_id,name,sex,phone,email,last_login_date)  
VALUES (1,'admin','admin','1','超级管理员','男','15111111111','admin@qq.com','2006-08-10 17:32:50');

INSERT INTO role (role_id,role_name,description)  VALUES (1,'超级管理员','所有权限');
INSERT INTO role (role_id,role_name,description)  VALUES (2,'管理员','回复用户信息');
INSERT INTO role (role_id,role_name,description)  VALUES (3,'普通用户','发布信息');

INSERT INTO permission (permission_id,permission_name,description)  VALUES (1,'发布信息','发布信息');
INSERT INTO permission (permission_id,permission_name,description)  VALUES (2,'回复信息','回复信息');
INSERT INTO permission (permission_id,permission_name,description)  VALUES (3,'高级权限','待定');

INSERT INTO role_permission (role_id,permission_id) 
VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(3,1);


/*插入测试数据==============================================================*/
 


 




