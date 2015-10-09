package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//Id
    private Long userId;//用户Id
    private String loginIp;//登陆ip
    private java.util.Date loginTime;//登陆时间
    private String address;//地址
    private String osname;//操作系统
    private String userAgent;//浏览器
    private String email;//邮箱
    private String startDate;//开始登陆时间
    private String endDate;//开始登陆时间
}
