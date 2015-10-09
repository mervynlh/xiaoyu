package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryTeacher extends Teacher implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -2260935476109762530L;
    
    private Long subjectId;//专业ID
    private Long majorId;//科目id
    private Long cityId;//城市id
    
    private int showNum;// 显示数量
    private BigDecimal minPrice; // 查询最低价
    private BigDecimal maxPrice; // 查询最高价
    private Long minSeniority; // 查询最低教龄
    private Long maxSeniority; // 查询最高教龄

    private String mobile; // 教师查询手机号

    private String courseModel;//上课方式 1.线上
    private String sellType;//开课类型：1.一对一 2.班课
    private int orderby;
    
    private String order; // 排序值  human：人气倒序   assess：评论倒序   price：价格升序
}
