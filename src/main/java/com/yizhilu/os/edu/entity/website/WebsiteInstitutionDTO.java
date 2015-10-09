package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构推荐
 * @author dingkai
 * @date 2015年9月22日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteInstitutionDTO extends WebsiteInstitution implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;// 机构名称
	private String applicant;// 申请人
	private String mobile;// 手机号
	private String email;// 公司邮箱
	private String instPictureUrl;// 机构图片
}
