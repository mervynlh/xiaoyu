package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminLoginLog  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long userId;
    private String loginIp;
    private java.util.Date loginTime;
    private String address;
}
