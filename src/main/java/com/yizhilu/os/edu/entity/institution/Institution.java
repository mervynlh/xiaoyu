package com.yizhilu.os.edu.entity.institution;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Institution implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;//机构名称
	private int status;//机构状态：0 未审核，1 审核通过
	private int sort;//排序
	private String instPictureUrl;//机构图片地址
	private String description;//简介
	private Date createTime;//创建时间
	private String idPictureUrl;//身份证图片地址
	private String licensePictureUrl;//营业执照图片地址
	private String applicant;//申请人
	private String mobile;//手机号
	private String email;//邮箱
	private Long userId;//用户id
}
