

USE `im`;
DROP TABLE IF EXISTS `tb_topic_chat`;

DROP TABLE IF EXISTS `tb_notice_field`;
DROP TABLE IF EXISTS `tb_meeting_topic`;
DROP TABLE IF EXISTS `tb_meeting_time`;
DROP TABLE IF EXISTS `tb_meeting_sign_label_data`;
DROP TABLE IF EXISTS `tb_meeting_sign_label`;

DROP TABLE IF EXISTS `tb_meeting_pic`;
DROP TABLE IF EXISTS `tb_meeting_people`;
DROP TABLE IF EXISTS `tb_meeting_notice`;
DROP TABLE IF EXISTS `tb_meeting_note_detail`;
DROP TABLE IF EXISTS `tb_meeting_note`;
DROP TABLE IF EXISTS `tb_meeting_member`;
DROP TABLE IF EXISTS `tb_meeting_label`;
DROP TABLE IF EXISTS `tb_meeting_data`;
DROP TABLE IF EXISTS `tb_meeting`;

/*Table structure for table `tb_meeting` */



CREATE TABLE `tb_meeting` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '会议序号',
  `meeting_name` VARCHAR(255) DEFAULT NULL COMMENT '会议名称',
  `meeting_address` VARCHAR(255) DEFAULT NULL COMMENT '会议地点',
  `meeting_address_pos_x` VARCHAR(32) DEFAULT NULL COMMENT '会议地点横坐标',
  `meeting_address_pos_y` VARCHAR(32) DEFAULT NULL COMMENT '会议地点纵坐标',
  `country` TINYINT(2) DEFAULT NULL COMMENT '国家 0 国内 1国外',
  `province` VARCHAR(50) DEFAULT NULL COMMENT '省份',
  `city` VARCHAR(50) DEFAULT NULL COMMENT '城市',
  `town` VARCHAR(50) DEFAULT NULL COMMENT '城镇',
  `start_time` DATETIME DEFAULT NULL COMMENT '会议开会开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '会议开会结束时间',
  `meeting_type` INT(1) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '/** 会议类型 0：无主讲  1：有主讲*/',
  `meeting_status` INT(2) DEFAULT NULL COMMENT '会议状态	0：草稿，1：发起,2会议进行中，3会议结束',
  `is_secrecy` TINYINT(1) DEFAULT NULL COMMENT '是否保密',
  `member_count` INT(11) DEFAULT NULL COMMENT '会议人数',
  `meeting_desc` TEXT COMMENT '会议介绍',
  `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建人ID',
  `create_name` VARCHAR(255) DEFAULT NULL COMMENT '创建人名称',
  `task_id` VARCHAR(255) DEFAULT NULL COMMENT '附件id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1091 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_data` */



CREATE TABLE `tb_meeting_data` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '资料序号',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议序号',
  `data_name` VARCHAR(255) DEFAULT NULL COMMENT '资料名称',
  `data_id` BIGINT(20) DEFAULT NULL COMMENT '资料ID',
  `data_type` INT(11) DEFAULT NULL COMMENT '资料类型	0需求，1知识',
  `data_req_type` INT(11) DEFAULT NULL COMMENT '1-资讯，2-投融工具，3-行业，4-经典案例，5-图书报告，6-资产管理，7-宏观，8-观点，9-判例，10-法律法规，11-文章,12-投资，13-融资',
  `data_url` VARCHAR(255) DEFAULT NULL COMMENT '资料对应的url',
  `create_time` DATETIME DEFAULT NULL COMMENT '资料创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_data` (`meeting_id`),
  CONSTRAINT `fk_meeting_data` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_label` */



CREATE TABLE `tb_meeting_label` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `label_name` VARCHAR(255) DEFAULT NULL COMMENT '标签名字',
  `create_id` BIGINT(20) DEFAULT NULL COMMENT '标签创建者ID',
  `create_Name` VARCHAR(255) DEFAULT NULL COMMENT '标签创建者名字',
  `create_time` DATETIME DEFAULT NULL COMMENT '标签创建时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_member` */



CREATE TABLE `tb_meeting_member` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '成员序号',
  `member_id` BIGINT(20) DEFAULT NULL COMMENT '人员ID',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议序号',
  `member_type` INT(2) DEFAULT NULL COMMENT '会议角色	0：嘉宾，1：群众,2创建者',
  `member_name` VARCHAR(255) DEFAULT NULL COMMENT '人员姓名',
  `member_photo` VARCHAR(255) DEFAULT NULL COMMENT '人员图片',
  `excute_meet_sign` INT(11) DEFAULT '0' COMMENT '处理会议报名：0：未处理：1：同意报名，2：拒绝报名',
  `member_meet_status` INT(2) DEFAULT NULL COMMENT '人员处理会议的状态	0：默认，1：归档，2：删除',
  `attend_meet_status` INT(2) DEFAULT NULL COMMENT '参会状态	0.未答复 1接受邀请2拒绝邀请， 4 报名 5取消报名',
  `is_sign` TINYINT(2) DEFAULT '0' COMMENT '是否签到 0：未签，1：已签',
  `sign_distance` VARCHAR(255) DEFAULT NULL COMMENT '签到距离',
  `attend_meet_type` INT(2) DEFAULT NULL COMMENT '参会方式	0邀请，1报名',
  `attend_meet_time` DATETIME DEFAULT NULL COMMENT '参会时间',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_member` (`meeting_id`),
  CONSTRAINT `fk_meeting_member` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=15601 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_note` */



CREATE TABLE `tb_meeting_note` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '笔记序号',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议序号',
  `meeting_note_title` VARCHAR(255) DEFAULT NULL COMMENT '笔记标题',
  `creater` BIGINT(20) DEFAULT NULL COMMENT '所属人',
  `create_time` DATETIME DEFAULT NULL COMMENT '笔记创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_note` (`meeting_id`),
  CONSTRAINT `fk_meeting_note` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_note_detail` */


CREATE TABLE `tb_meeting_note_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '笔记明细id',
  `meeting_note_id` BIGINT(20) DEFAULT NULL COMMENT '笔记序号',
  `meeting_note_content` TEXT COMMENT '笔记内容',
  `meeting_chat_id` BIGINT(20) DEFAULT NULL COMMENT '引用聊天记录',
  `meeting_note_time` DATETIME DEFAULT NULL COMMENT '会议笔记明细时间',
  `task_id` VARCHAR(255) DEFAULT NULL COMMENT '附件id',
  PRIMARY KEY (`id`),
  KEY `tb_note_detail` (`meeting_note_id`),
  CONSTRAINT `tb_note_detail` FOREIGN KEY (`meeting_note_id`) REFERENCES `tb_meeting_note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_notice` */



CREATE TABLE `tb_meeting_notice` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `receiver_type` TINYINT(4) DEFAULT NULL COMMENT '接收人类型 0:发起者1：参会人',
  `receiver` BIGINT(20) DEFAULT NULL COMMENT '接收人',
  `receiver_name` VARCHAR(255) DEFAULT NULL COMMENT '接收人名字',
  `notice_type` TINYINT(4) DEFAULT NULL COMMENT '通知类型：0：修改议题，1：报名申请，2：报名通过，3：报名未通过，4：接受邀请，5：拒绝邀请，6同意报名，7拒绝报名',
  `is_show` TINYINT(2) DEFAULT NULL COMMENT '通知是否显示',
  `notice_content` VARCHAR(255) DEFAULT NULL COMMENT '通知内容',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议ID',
  `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
  `create_name` VARCHAR(255) DEFAULT NULL COMMENT '创建者名字',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_notice` (`meeting_id`),
  CONSTRAINT `fk_meeting_notice` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=662 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_people` */



CREATE TABLE `tb_meeting_people` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '人脉序号',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议序号',
  `people_id` BIGINT(20) DEFAULT NULL COMMENT '人员ID',
  `people_name` VARCHAR(255) DEFAULT NULL COMMENT '人员姓名',
  `people_photo` VARCHAR(255) DEFAULT NULL COMMENT '人员图片',
  `people_desc` VARCHAR(255) DEFAULT NULL COMMENT '人脉描述',
  `is_share` TINYINT(1) DEFAULT NULL COMMENT '是否分享',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_people` (`meeting_id`),
  CONSTRAINT `fk_meeting_people` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=570 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_pic` */

CREATE TABLE `tb_meeting_pic` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议',
  `pic_path` VARCHAR(255) DEFAULT NULL COMMENT 'nginx上图片路径，去掉nfs部分',
  `pic_name` VARCHAR(255) DEFAULT NULL COMMENT '图片名字 nginx生成的',
  `pic_real_name` VARCHAR(255) DEFAULT NULL COMMENT '图片的真实名字',
  `pic_desc` VARCHAR(255) DEFAULT NULL COMMENT '图片描述',
  `ishome_page` TINYINT(1) DEFAULT '0' COMMENT '是否把此图片作为封面 默认插入数据库时为0',
  `file_index_id` VARCHAR(255) DEFAULT NULL COMMENT '文件关联id',
  `create_user_id` BIGINT(20) DEFAULT NULL COMMENT '上传这个图片的用户id',
  `create_user_name` VARCHAR(255) DEFAULT NULL COMMENT '上传此图片的用户登录名',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `pic_status` CHAR(1) DEFAULT NULL COMMENT '图片状态：0-待生效 1-已生效 2-屏蔽',
  `task_id` VARCHAR(255) DEFAULT NULL COMMENT '会议图片taskId',
  `pic_del` CHAR(1) DEFAULT NULL COMMENT '图片待删除标记 1-待删除',
  `update_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_meeting_pic` (`meeting_id`),
  CONSTRAINT `fk_meeting_pic` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=141211182103265 DEFAULT CHARSET=utf8 COMMENT='会议图片';

/*Table structure for table `tb_meeting_sign_label` */


CREATE TABLE `tb_meeting_sign_label` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '会议报名必填标签',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议ID',
  `label_name` VARCHAR(255) DEFAULT NULL COMMENT '标签名字',
  `is_custom` TINYINT(2) DEFAULT NULL COMMENT '是否自定义',
  `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
  `create_Name` VARCHAR(255) DEFAULT NULL COMMENT '创建者名字',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_sign_label_data` */


CREATE TABLE `tb_meeting_sign_label_data` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户报名信息主键',
  `m_s_label_id` BIGINT(20) DEFAULT NULL COMMENT '会议必填标签ID',
  `label_content` VARCHAR(255) DEFAULT NULL COMMENT '用户必填信息',
  `member_id` BIGINT(20) DEFAULT NULL COMMENT '参会成员ID',
  `member_name` VARCHAR(255) DEFAULT NULL COMMENT '参会成员名字',
  `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
  `create_name` VARCHAR(255) DEFAULT NULL COMMENT '创建者名字',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建者时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_time` */


CREATE TABLE `tb_meeting_time` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '会议时间id',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议id',
  `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_time` (`meeting_id`),
  CONSTRAINT `fk_meeting_time` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_meeting_topic` */


CREATE TABLE `tb_meeting_topic` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '议题序号',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议序号',
  `topic_content` VARCHAR(255) DEFAULT NULL COMMENT '议题内容',
  `topic_start_time` DATETIME DEFAULT NULL COMMENT '议题开始时间',
  `topic_end_time` DATETIME DEFAULT NULL COMMENT '议题结束时间',
  `topic_desc` VARCHAR(255) DEFAULT NULL COMMENT '议题介绍',
  `task_id` VARCHAR(255) DEFAULT NULL COMMENT '附件id',
  `member_id` BIGINT(20) DEFAULT NULL COMMENT '主讲人ID',
  `member_name` VARCHAR(255) DEFAULT NULL COMMENT '主讲人名字',
  `member_pic` VARCHAR(255) DEFAULT NULL COMMENT '主讲人头像',
  `member_desc` VARCHAR(255) DEFAULT NULL COMMENT '主讲人描述',
  `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建人Id',
  `create_name` VARCHAR(255) DEFAULT NULL COMMENT '创建人名字',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_meeting_topic` (`meeting_id`),
  CONSTRAINT `fk_meeting_topic` FOREIGN KEY (`meeting_id`) REFERENCES `tb_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=979 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_notice_field` */

CREATE TABLE `tb_notice_field` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '通知字段id',
  `notice_id` BIGINT(20) DEFAULT NULL COMMENT '通知ID',
  `field` VARCHAR(255) DEFAULT NULL COMMENT '对应字段',
  PRIMARY KEY (`id`),
  KEY `fk_notice_field` (`notice_id`)
) ENGINE=INNODB AUTO_INCREMENT=2290 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_topic_chat` */


CREATE TABLE `tb_topic_chat` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '内容序号',
  `meeting_id` BIGINT(20) DEFAULT NULL COMMENT '会议序号',
  `sender_Type` INT(11) DEFAULT NULL COMMENT '0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考meeting_id',
  `topic_id` BIGINT(20) DEFAULT NULL COMMENT '议题序号',
  `chat_content` TEXT COMMENT '聊天内容',
  `chat_type` INT(11) DEFAULT NULL COMMENT '聊天类型	0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement',
  `message_Id` VARCHAR(255) DEFAULT NULL COMMENT '消息id串，客户端随机生成，每条记录唯一',
  `member_id` BIGINT(20) DEFAULT NULL COMMENT '人员ID',
  `jtfile_url` TEXT COMMENT '文件地址',
  `jtfile_suffix_name` VARCHAR(255) DEFAULT NULL COMMENT '后缀名	jpg,png,amr,pdf等',
  `jtfile_type` VARCHAR(255) DEFAULT NULL COMMENT '文件类型',
  `jtfile_name` VARCHAR(255) DEFAULT NULL COMMENT '文件名',
  `jtfile_size` INT(11) DEFAULT NULL COMMENT '文件大小',
  `jtFile_Module_Type` INT(11) DEFAULT NULL COMMENT '0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构',
  `jtfile_task_id` VARCHAR(255) DEFAULT NULL COMMENT '附件id',
  `publish_time` DATETIME DEFAULT NULL COMMENT '发布时间',
  `jtFile_Reserved1` VARCHAR(255) DEFAULT NULL COMMENT '备用1',
  `jtFile_Reserved2` VARCHAR(255) DEFAULT NULL COMMENT '备用2',
  `jtFile_Reserved3` VARCHAR(255) DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`),
  KEY `fk_topic_chat` (`topic_id`),
  CONSTRAINT `fk_topic_chat` FOREIGN KEY (`topic_id`) REFERENCES `tb_meeting_topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8;

/* Function  structure for function  `lat_long_distance` */

/*!50003 DROP FUNCTION IF EXISTS `lat_long_distance` */;
DELIMITER $$

/*!50003 CREATE  FUNCTION `lat_long_distance`(in_lat1 DOUBLE, in_long1 DOUBLE, in_lat2 DOUBLE, in_long2 DOUBLE) RETURNS double
    READS SQL DATA
    DETERMINISTIC
BEGIN 
DECLARE Distance DOUBLE; 
DECLARE RadLatBegin DOUBLE; 
DECLARE RadLatEnd DOUBLE; 
DECLARE RadLatDiff DOUBLE; 
DECLARE RadLngDiff DOUBLE; 
DECLARE EARTH_RADIUS DOUBLE; 
SET EARTH_RADIUS = 6378.137; 
SET RadLatBegin = in_lat1 *PI()/ 180.0 ; 
SET RadLatEnd = in_lat2 *PI()/ 180.0 ; 
SET RadLatDiff = RadLatBegin - RadLatEnd; 
SET RadLngDiff = in_long1 *PI()/ 180.0 - in_long2 *PI()/ 180.0 ; 
SET DISTANCE = 2 *ASIN( 
SQRT( 
POWER(SIN(RadLatDiff / 2), 2)+COS(RadLatBegin)*COS(RadLatEnd) 
*POWER(SIN(RadLngDiff / 2), 2) 
) 
); 
SET Distance = Distance * EARTH_RADIUS*1000; 
RETURN DISTANCE; 
END */$$
DELIMITER ;


