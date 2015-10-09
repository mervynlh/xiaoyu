package com.yizhilu.os.edu.entity.customer;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerCourse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -8861715951056637331L;
    private Long id;
    private String title;
    private String content;
    private String teacherName;
    private String mobile;
    private String email;
    private Long status;
    private java.util.Date createTime;
    private Long createuserId;
    private Long joinNum;
    private String remark;
}
