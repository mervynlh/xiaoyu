package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginOnline implements Serializable{
    private Long id;
    private Long userId;
    private String loginsid;
    private String type;
    private java.util.Date createTime;
}
