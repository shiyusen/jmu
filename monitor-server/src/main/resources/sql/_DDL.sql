
drop DATABASE if exists aido;
CREATE DATABASE aido;
use aido;


drop table if exists aido_model_file;
CREATE TABLE aido_model_file(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  type VARCHAR(20)  DEFAULT 'PMML' COMMENT '模型类型：TENSORFLOW， PMML， XGBOOST' ,
  file_name varchar(128) NOT NULL DEFAULT '' COMMENT '文件名',
  file_size bigint(11) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小byte',
  origin_file_name varchar(128) NOT NULL DEFAULT '' COMMENT '如果模型文件已经存在，则此处填写磁盘里的文件名。不存在，则和file_name相同',
  md5 VARCHAR(64) NOT  NULL COMMENT 'pmml file md5值',
  description varchar(256) DEFAULT '' COMMENT '模型文件模型描述',
  user_name VARCHAR(32) NOT NULL  COMMENT '作者' ,
  process TINYINT(2) unsigned  NOT NULL default 0 COMMENT '进度，0~100.100说明文件完整上传' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (ID),
   KEY idx_file_name(file_name(32)),
   KEY idx_md5(md5)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模型文件表';



drop table if exists aido_info;
CREATE TABLE aido_info(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  service_key VARCHAR(32) NOT NULL COMMENT '模型标识' ,
  service_name VARCHAR(32) NOT NULL COMMENT '模型名字，默认模型文件名' ,
  fid bigint(11) unsigned NOT NULL COMMENT '模型文件id',
  description varchar(256) DEFAULT '' COMMENT '模型描述',
  input TEXT  COMMENT '模型入参数据结构',
  output TEXT  COMMENT '模型出参数据结构',
  version char(12) NOT NULL  COMMENT '版本号。默认：yyyyMMdd0001' ,
  user_name VARCHAR(32) NOT NULL  COMMENT '作者' ,
  state TINYINT(2) unsigned  NOT NULL default 0 COMMENT '0可用，1标记删除' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (ID),
  UNIQUE KEY idx_service_key(service_key,version),
  KEY idx_fid(fid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模型信息表';


drop table if exists aido_resources;
CREATE TABLE aido_resources(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  node_num TINYINT(2) unsigned  NOT NULL default 3 COMMENT '节点数量' ,
  cpu_num TINYINT(2) unsigned  NOT NULL default 1 COMMENT 'cpu数量' ,
  cpu_use TINYINT(2) unsigned NOT NULL default 20 COMMENT '单个cpu使用率，20%' ,
  mem MEDIUMINT(2) unsigned NOT NULL default 1024 COMMENT '内存单位M' ,
  qps MEDIUMINT(2) unsigned NOT NULL default 1024 COMMENT '每秒能处理查询数目' ,
  user_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '作者' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模型资源配置表';


drop table if exists aido_auth;
CREATE TABLE aido_auth(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  mid bigint unsigned COMMENT '模型id或服务id' ,
  user_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '拥有模型权限的用户名',
  auth VARCHAR(32) NOT NULL DEFAULT 'watcher' COMMENT '所有者：owner,使用者：user,观察者：watcher',
  type TINYINT(2) unsigned  NOT NULL default 1 COMMENT '1 模型，2服务' ,
  PRIMARY KEY (ID),
  KEY idx_mid(mid),
  KEY idx_user_name(user_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模型权限表';




drop table if exists aido_container;
CREATE TABLE aido_container(
  ID bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  name VARCHAR(32) NOT NULL UNIQUE COMMENT '实例名字。默认service_key_version_rid' ,
  mid bigint unsigned COMMENT '模型id' ,
  rid bigint unsigned COMMENT '资源id' ,
  state smallint(6) NOT NULL DEFAULT '0' COMMENT '模型状态，0初始态，1测试中，2测试通过，3发布中，4发布成功，5下线，99测试未通过，999发布失败',
  user_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '作者' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模型实例表(模型容器)';




drop table if exists aido_brother;
CREATE TABLE aido_brother(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  cid bigint unsigned NOT NULL COMMENT '模型实例id' ,
  bid bigint unsigned NOT NULL COMMENT 'ab或陪跑实例的id' ,
  relation TINYINT(2) unsigned  NOT NULL default 1 COMMENT '类型：1陪跑(默认),2AB测试' ,
  ratio TINYINT(2) unsigned default 50 COMMENT 'relation=2时有效，流量比：cid/bid=ratio' ,
  user_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '作者' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (ID),
  KEY idx_cid(cid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='AB测试或冠军挑战';




drop table if exists aido_log;
CREATE TABLE aido_log(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  action VARCHAR(54) NOT NULL DEFAULT '' COMMENT '日志名称(含模型id_日期戳)' ,
  level char(5) NOT NULL DEFAULT 'info' COMMENT 'info,warn,error,notify(发告警消息)' ,
  log TEXT COMMENT '操作(含出参，入参)' ,
  user_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '作者' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  PRIMARY KEY (ID),
  KEY idx_created(created)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模型在线日志(操作日志和模型入参出参)';



