package com.yizhilu.os.edu.entity.customer;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.edu.entity.user.UserExpandDto;

/**
 * @ClassName com.yizhilu.os.edu.entity.customer.QueryCustomerCourse
 * @description 查询自定义课程条件
 * @author guozhenzhou
 * @data   2014-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCustomerCourse  implements Serializable{


	private static final long serialVersionUID = -9138079017156243848L;
	 private Long id;
	    private String title;
	    private String content;
	    private String teacherName;
	    private String mobile;
	    private String email;
	    private Long status;
	    private java.util.Date createTime;
	    private Long createuserId;
	    private int joinNum;
	    private String remark;
	    private UserExpandDto userExpandDto;

}
