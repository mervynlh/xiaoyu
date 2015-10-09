package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserCode implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -6209796220531576226L;
    private Long id;
    private Long userId;
    private Long type;//0找回密码，1邮件激活（备用） 2帐号激活（备用）
    private String context;//未加密内容
    private java.util.Date createTime;
    private Long status;//状态0初始，1已经使用
}
