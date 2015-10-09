package com.yizhilu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCourse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 4550896941810655734L;
    private Long id;
    private Long subjectId;
    private String name;
    private Long isavaliable;
    private java.util.Date addtime;
    private Integer isPay;
    private java.math.BigDecimal currentprice;
    private String title;
    private String context;
    private Long lessionnum;
    private java.util.Date updateTime;
    private Long membertype;
    private String freeurl;
    private Long teacherId;//查询课程传入教师id
    private int order;//查询课程传入排序标识
    private String sellType;//课程的销售方式,
    private List<Teacher> teacherList;//该课程 下的老师list
    private Long userId;		//学员id
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;//直播开始时间
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;//直播结束时间
    private int status;//直播状态 1未开始 2进行中 3已结束
    private String coursePlan;//班课课程安排
    private String classImgs;//班课图片
    private String teacherName;//教师名称
    
    private int courseType=-1;//课程分类0 线下 1 线上
    private long majorId;//科目id
}
