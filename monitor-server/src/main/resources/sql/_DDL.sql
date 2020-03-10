
drop DATABASE if exists jmu;
CREATE DATABASE jmu;
use jmu;



drop table if exists jum_index_val;
CREATE TABLE jum_index_val(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  process VARCHAR(20)  not null COMMENT '主机名(进程名)' ,
  pid VARCHAR(32)  not null COMMENT '主机ip(进程id)' ,
  code VARCHAR(20)  not null COMMENT '指标code' ,
  name VARCHAR(20)  not null COMMENT '指标code' ,
  val int(20)  not null COMMENT '指标值' ,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='监控指标值表';


drop table if exists jum_operation_log;
CREATE TABLE jum_operation_log(
  id bigint unsigned NOT NULL AUTO_INCREMENT  COMMENT '自增序号' ,
  name CHAR(32)  not null COMMENT '主机名' ,
  ip CHAR(32)  not null COMMENT '主机ip' ,
  username CHAR(32) default 'unknow' COMMENT '操作者' ,
  level char(6)  not null COMMENT '操作级别。info,warn,error' ,
  input TEXT  COMMENT '入参',
  output TEXT  COMMENT '出参(含error)',
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='操作监控日志表';
