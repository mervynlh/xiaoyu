/*积分收货地址 后台加两字段*/
ALTER TABLE `eduplat`.`edu_user_integral_record` ADD COLUMN `address_id` INT(11) DEFAULT 0 NULL COMMENT '收货地址id' AFTER `status`, ADD COLUMN `address` VARCHAR(200) DEFAULT '' NULL COMMENT '收货地址' AFTER `address_id`; 

/*增加登陆user_login_log   浏览器 操作系统*/
ALTER TABLE `eduplat`.`user_login_log` ADD COLUMN `osname` VARCHAR(20) NULL COMMENT '操作系统' AFTER `address`, ADD COLUMN `user_agent` VARCHAR(20) NULL COMMENT '浏览器信息' AFTER `osname`; 

/*增加登陆sys_login_log   浏览器 操作系统*/
ALTER TABLE `eduplat`.`sys_login_log` ADD COLUMN `osname` VARCHAR(20) DEFAULT '' NULL COMMENT '操作系统' AFTER `address`, ADD COLUMN `user_agent` VARCHAR(20) DEFAULT '' NULL COMMENT '浏览器名称' AFTER `osname`;

/*  会员前台添加提示  关闭*/
ALTER TABLE `eduplat`.`edu_member_record` ADD COLUMN `status` TINYINT(2) DEFAULT 0 NULL COMMENT '0正常1关闭' AFTER `description`;

-- 修改视频节点数据
ALTER TABLE `eduplat`.`edu_course_kpoint` CHANGE `videojson` `videojson` TEXT NULL COMMENT 'json格式辅助56';

/* 网站统计*/
DROP TABLE IF EXISTS `edu_statistics_day`;

CREATE TABLE `edu_statistics_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `statistics_time` datetime NOT NULL COMMENT '统计日期',
  `login_num` int(11) NOT NULL DEFAULT '0' COMMENT '登录人数（活跃人数 ）',
  `register_num` int(11) NOT NULL DEFAULT '0' COMMENT '新注册人数',
  `user_pay_member_num` int(11) NOT NULL DEFAULT '0' COMMENT '支付成功人数（会员）',
  `user_pay_course_num` int(11) NOT NULL DEFAULT '0' COMMENT '支付成功人数（课程）',
  `user_pay_num` int(11) NOT NULL DEFAULT '0' COMMENT '支付成功人数（课程+会员的合计）',
  `course_num` int(11) NOT NULL DEFAULT '0' COMMENT '课程下单总数量',
  `course_pay_num` int(11) NOT NULL DEFAULT '0' COMMENT '课程支付成功数量',
  `course_pay_amount` int(11) NOT NULL DEFAULT '0' COMMENT '课程支付成功金额',
  `course_refund_num` int(11) NOT NULL DEFAULT '0' COMMENT '课程退费订单量',
  `member_num` int(11) NOT NULL DEFAULT '0' COMMENT '会员订单下单总数量',
  `member_pay_num` int(11) NOT NULL DEFAULT '0' COMMENT '会员支付成功数量',
  `member_pay_amount` int(11) NOT NULL DEFAULT '0' COMMENT '会员支付成功金额',
  `weibo_num` int(11) NOT NULL DEFAULT '0' COMMENT '发表观点数',
  `blog_num` int(11) NOT NULL DEFAULT '0' COMMENT '博文数',
  `asses_num` int(11) NOT NULL DEFAULT '0' COMMENT '课程评论数',
  `ques_num` int(11) NOT NULL DEFAULT '0' COMMENT '问题数',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`),
  KEY `statistics_time` (`statistics_time`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='网站统计日数据';

/* 微信消息加密 */
update edu_website_profile set desciption='{"wxAppID":"wx3aeac8262ed1","wxToken":"yizhilu","wxAppSecret":"b2fc6a2dd00ad5f1eb142928","encodingAESKey":"GLybO3moaatyWTyeNIFTkhhkP2XsOo"}' where type='weixin';
/* 微信常规回复修改关键字*/
update edu_weixin_set_reply set type='tolerate' where type='default';
/*添加乐视云配置*/
INSERT INTO edu_website_profile (edu_website_profile.`type`,edu_website_profile.`desciption`,edu_website_profile.`explain`) VALUES('letv','{"user_unique":"ce04f7a","secret_key":"cf40f8164b358a732c1630ab51b3"}','乐视云');
/*更新开关管理*/
UPDATE edu_website_profile SET edu_website_profile.`desciption`='{"verifyLogin":"ON","verifyRegister":"ON","verifyAlipay":"ON","verifySensitive":"ON","verifyEmail":"OFF","verifyPhone":"OFF","verifykq":"OFF","verifyExam":"OFF","verifySns":"OFF"}' WHERE edu_website_profile.`type`='keyword';
/*图书添加字段*/
ALTER TABLE `edu_book` 
	ADD COLUMN `freight` float NULL AFTER `book_info`,
	ADD COLUMN `freight` float NULL COMMENT '运费' AFTER `book_pay_url`;
/*图书订单*/	
	CREATE TABLE `edu_book_order` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(55) NOT NULL DEFAULT '' COMMENT '订单编号',
  `address` varchar(255) NOT NULL COMMENT '收货地址',
  `buy_num` int(11) NOT NULL COMMENT '购买数量',
  `order_money` float DEFAULT '0' COMMENT '订单金额',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `cancel_time` timestamp NULL DEFAULT NULL COMMENT '取消时间',
  `status` varchar(50) NOT NULL DEFAULT '0' COMMENT '默认0未支付，1已支付，3已退费，4已取消',
  `pay_type` varchar(50) NOT NULL DEFAULT '0' COMMENT '支付类型：',
  `book_id` int(11) DEFAULT NULL COMMENT '书籍编号',
  `mail_id` varchar(50) DEFAULT NULL COMMENT '邮件编号',
  `mail_name` varchar(255) DEFAULT NULL COMMENT '快递名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `mail_status` int(11) NOT NULL DEFAULT '0' COMMENT '是否发货 0未发货 1已发货',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `freight` float DEFAULT NULL COMMENT '运费',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='图书订单表'
/*课程答疑*/
CREATE TABLE `edu_answer_question` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '创建者ID',
  `type` varchar(10) DEFAULT NULL COMMENT '类型 视频或是考试',
  `content` varchar(255) DEFAULT NULL COMMENT '问题内容',
  `add_time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `parent_id` int(11) DEFAULT NULL COMMENT '课程ID',
  `son_id` int(11) DEFAULT NULL COMMENT '节点ID',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `reply_count` int(11) DEFAULT NULL COMMENT '回复次数',
  `is_reply` int(11) DEFAULT NULL COMMENT '是否已经有老师回复',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='答疑提问表'

CREATE TABLE `edu_answer_reply` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `answer_id` varchar(255) DEFAULT NULL COMMENT '回复的问题Id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '回复者Id',
  `show_name` varchar(255) DEFAULT NULL COMMENT '回复者姓名（后台老师）',
  `add_time` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='答疑回复表'

