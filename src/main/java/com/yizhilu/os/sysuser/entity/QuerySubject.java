package com.yizhilu.os.sysuser.entity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuerySubject extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -1912600357482790771L;
    /**
     *
     */
    /**
     * 专业id
     */
    private Long subjectId = -1L;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 状态
     */
    private int status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 父节点
     */
    private Long parentId = -1L;
    /**
     * 等级 1，2，3
     */
    // private int level;
    //图片
    private String image;
    /**
     * 专业id串
     */
    private String subjectIds;
}
