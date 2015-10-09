package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserForm implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7125341534305390109L;
    private int id;// 主键 id
    private String email;// 邮件
    private char emailIsavalible = 0;// 邮件是否验证
    private String mobile;// 手机号
    private char mobileIsavalible = 0;// 手机号是否验证
    private String password;
    private String confirmPassword;
    private char isavalible = 1;// 是否激活(0冻结 1已激活)
    private String customerkey;
    private Date createdate;
    private String userip;
    private int userType;//用户类型
    
    private Long extendCord;// 推广号
}
