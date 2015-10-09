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
public class WebsiteInstitution implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7279227254155212524L;
	
	private Long id;// 主键
	private Long institutionId;// 机构id
	private Long sort;// 排序
}
