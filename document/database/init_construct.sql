drop table if exists T_BOX_GROUP;

drop table if exists T_BOX_INFO;

drop table if exists T_BOX_LOG;

drop table if exists T_CUSTOMER_INFO;

drop table if exists T_EXPRESSMAN_INFO;

drop table if exists T_EXPRESS_TAKE_INFO;

drop table if exists T_REPAIRER_INFO;

drop table if exists T_REPAIR_RECORD;

drop table if exists user_wx_info;

drop table if exists user_wx_relt;

/*==============================================================*/
/* Table: T_BOX_GROUP                                           */
/*==============================================================*/
create table T_BOX_GROUP
(
  BOX_GROUP_ID         bigint(20) not null auto_increment comment '箱子组ID',
  BOX_NAME             varchar(128) not null comment '名称',
  ADDRESS              varchar(256) comment '详细地址',
  REPAIRER_INFO_ID     bigint(20) not null comment '维修人员ID',
  STATUS               enum('NOACTIVE','NORMAL','FROZEN','DEMISE') not null comment '状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户',
  CREATETIME           datetime not null comment '创建时间',
  primary key (BOX_GROUP_ID)
);

alter table T_BOX_GROUP comment '箱子组';

/*==============================================================*/
/* Table: T_BOX_INFO                                            */
/*==============================================================*/
create table T_BOX_INFO
(
  BOX_INFO_ID          bigint(20) not null auto_increment comment '箱子ID',
  BOX_NAME             varchar(256) not null comment '箱子名称,箱子组名+编号',
  BOX_CODE             varchar(32) not null comment '箱子编号',
  BOX_UNIQUE_CODE      varchar(64) not null comment '内部唯一代码',
  BOX_GROUP_ID         bigint(20) comment '箱子组ID',
  REPAIRER_INFO_ID     bigint(20) comment '维修人员ID',
  SEC_KEY              varchar(128) not null comment '加密密钥',
  STATUS               enum('NOACTIVE','NORMAL','REPAIR','DEMISE') not null comment '基本状态,NOACTIVE未激活,NORMAL正常,REPAIR维修,DEMISE注销',
  EXPRESS_STATUS       enum('EMPTY','FULL') not null comment '快件状态,EMPTY空,FULL有货物',
  MOBILE_PHONE         varchar(16) comment '快件隶属手机号',
  PROXY_CUSTOMER_INFO_ID varchar(16) comment '快件代领人ID号',
  OPENTIME             datetime comment '上次开启时间',
  CHECKNUM             int(10) not null comment '校验数值,用于客户端传递的值必须大于服务器保存的值,默认值为0',
  CREATETIME           datetime not null comment '创建时间',
  primary key (BOX_INFO_ID)
);

alter table T_BOX_INFO comment '箱子表';

/*==============================================================*/
/* Table: T_BOX_LOG                                             */
/*==============================================================*/
create table T_BOX_LOG
(
  BOX_LOG_ID           bigint(20) not null comment '开启记录ID',
  RECORD_ID            bigint(20) not null comment '箱子侧记录的ID号',
  BOX_INFO_ID          bigint(20) not null comment '箱子ID',
  ERROR_COUNT          int(5) not null comment '开启前密码输入错误次数,默认0',
  ENTERTIME            char(10) comment '记录登记时间',
  primary key (BOX_LOG_ID)
);

alter table T_BOX_LOG comment '箱子开启记录,用于保存箱子的每次开启的信息';

/*==============================================================*/
/* Table: T_CUSTOMER_INFO                                       */
/*==============================================================*/
create table T_CUSTOMER_INFO
(
  CUSTOMER_INFO_ID     bigint(20) not null auto_increment comment '客户ID号',
  BIND_ACCOUNT         varchar(32) comment '绑定的外部帐号',
  LOGIN_NAME           varchar(32) comment '登录名,唯一,自动生成,用户只能修改一次',
  PASSWORD             varchar(64) comment '登录密码',
  COUNTRY              varchar(32) comment '国家',
  PROVINCE             varchar(32) comment '省份',
  CITY                 varchar(32) comment '城市',
  REALNAME             varchar(32) comment '真实姓名',
  SEX                  enum('UNKNOWN','MALE','FEMALE') comment '性别',
  MOBILE_PHONE         varchar(16) comment '客户手机号',
  STATUS               enum('NORMAL','FROZEN','DEMISE') comment '状态,NORMAL正常,FROZEN冻结,DEMISE销户',
  REGISTER_IP_ADDRESS  varchar(32) comment '注册IP地址',
  REGISTER_TIME        datetime comment '注册时间',
  LAST_LOGIN_IP        varchar(32) comment '上次登录IP',
  LAST_LOGIN_TIME      datetime comment '上次登录时间',
  SOURCE               varchar(32) comment '注册来源',
  HEAD_IMG_PATH        varchar(128) comment '客户头像存储路径',
  primary key (CUSTOMER_INFO_ID)
);

alter table T_CUSTOMER_INFO comment '客户信息';

/*==============================================================*/
/* Table: T_EXPRESSMAN_INFO                                     */
/*==============================================================*/
create table T_EXPRESSMAN_INFO
(
  EXPRESSMAN_INFO_ID   bigint(20) not null auto_increment comment '快递员ID号',
  CUSTOMER_INFO_ID     bigint(20) not null comment '映射客户ID',
  EXPRESS_COMPANY      varchar(64) comment '所属快递公司',
  IDCARD_IMG_PATH      varchar(128) comment '身份证图片存储路径',
  WORKCARD_IMG_PATH    varchar(128) comment '工作证件照存储路径',
  STATUS               enum('NOACTIVE','NORMAL','FROZEN','DEMISE') not null comment '状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户',
  APPLYTIME            datetime not null comment '申请时间',
  ACTIVETIME           datetime comment '激活时间',
  primary key (EXPRESSMAN_INFO_ID)
);

alter table T_EXPRESSMAN_INFO comment '快递员信息表';

/*==============================================================*/
/* Table: T_EXPRESS_TAKE_INFO                                   */
/*==============================================================*/
create table T_EXPRESS_TAKE_INFO
(
  EXPRESS_TAKE_INFO    bigint(20) not null comment '领取历史ID',
  BOX_INFO_ID          bigint(20) not null comment '箱子ID',
  BOX_NAME             varchar(256) comment '箱子名称（冗余，防止箱子信息产生变动影响历史记录）',
  BOX_CODE             varchar(32) comment '箱子编号（冗余，防止箱子信息产生变动影响历史记录）',
  CUSTOMER_INFO_ID     bigint(20) not null comment '客户ID',
  MOBILE_PHONE         varchar(16) not null comment '手机号',
  PROXY_CUSTOMER       varchar(16) comment '代领人信息,JSON数据,包含name,mobile,openid',
  SENDTIME             datetime comment '投递时间,该值为箱子上次开启的时间',
  TAKETIME             datetime not null comment '领取时间',
  REMARK               varchar(512) comment '自动备注信息,包含箱子组/箱子的位置记录、编号,代领人手机号（如果有）等',
  primary key (EXPRESS_TAKE_INFO)
);

alter table T_EXPRESS_TAKE_INFO comment '快递领取历史记录';

/*==============================================================*/
/* Table: T_REPAIRER_INFO                                       */
/*==============================================================*/
create table T_REPAIRER_INFO
(
  REPAIRER_INFO_ID     bigint(20) not null auto_increment comment '维修人员ID',
  REALNAME             varchar(32) not null comment '维修人员姓名',
  MOBILE_PHONE         varchar(16) not null comment '维修人员手机号',
  REPAIRER_IMG_PATH    varchar(128) comment '维修人员图片',
  STATUS               enum('NORMAL','FROZEN','DEMISE') not null comment '状态,NORMAL正常,FROZEN冻结,DEMISE销户',
  CREATETIME           datetime not null comment '创建时间',
  primary key (REPAIRER_INFO_ID)
);

alter table T_REPAIRER_INFO comment '维修人员';

/*==============================================================*/
/* Table: T_REPAIR_RECORD                                       */
/*==============================================================*/
create table T_REPAIR_RECORD
(
  REPAIR_RECORD_ID     bigint(20) not null auto_increment comment '报修申请ID',
  CUSTOMER_INFO_ID     bigint(20) not null comment '报修客户ID',
  REPAIRER_INFO_ID     bigint(20) comment '维修人员ID',
  BOX_INFO_ID          bigint(20) comment '箱子ID',
  BOX_UNIQUE_CODE      varchar(64) comment '箱子唯一编码',
  REPAIR_IMG_PATH      varchar(128) comment '拍照截图路径',
  REMARK               varchar(256) comment '备注',
  STATUS               enum('CHECKING','PENDING','REPAIRING','COMPLETE','CACEL') not null comment '报销状态,CHECKING待审核,PENDING待处理,REPAIRING修理中,COMPLETE修理完成,CANCEL取消',
  APPLYTIME            datetime not null comment '报修时间',
  AUTO_REMARK          varchar(256) comment '系统自动备注,可用于自动录入GPS信息、报错信息来源（如待领包裹列表）、审批信息等',
  AUDITTIME            datetime comment '审核时间',
  REPAIRTIME           datetime comment '修理开始时间',
  COMPLETETIME         datetime comment '修理完成时间',
  primary key (REPAIR_RECORD_ID)
);

alter table T_REPAIR_RECORD comment '报修申请';

/*==============================================================*/
/* Table: user_wx_info                                          */
/*==============================================================*/
create table user_wx_info
(
  openid               varchar(64) not null comment '微信用户的唯一标识',
  appid                varchar(64) not null comment '微信公众号或应用id',
  nickname             varchar(256) comment '用户昵称',
  sex                  varchar(10) comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  province             varchar(30) comment '用户个人资料填写的省份',
  city                 varchar(30) comment '普通用户个人资料填写的城市',
  country              varchar(30) comment '国家，如中国为CN',
  headimgurl           varchar(512) comment '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  privilege            varchar(256) comment '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  unionid              varchar(64) not null comment '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  primary key (openid, unionid)
);

alter table user_wx_info comment '用户微信信息';

/*==============================================================*/
/* Table: user_wx_relt                                          */
/*==============================================================*/
create table user_wx_relt
(
  unionid              varchar(64) not null comment '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  CUSTOMER_INFO_ID     bigint(20) not null comment '客户ID号',
  create_time          datetime comment '创建时间',
  remark               varchar(512) comment '注释',
  primary key (unionid),
  unique key AK_Key_2 (CUSTOMER_INFO_ID)
);

alter table user_wx_relt comment '微信用户与基本用户关联信息';
