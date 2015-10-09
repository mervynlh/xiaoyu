package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.entity.user.User;

@Data
@EqualsAndHashCode(callSuper = false)
public class Course implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;//课程名称
    private Long isavaliable;//是否审核 ：0未审核，1审核
    private java.util.Date addtime;//添加时间
    private java.util.Date updateTime;//修改时间
    private String sellType;//开课类型：1.一对一 2.班课
    private String logo;//课程封面
    private String title;//课程简介
    private String context;//课程详情
    private Long addressId;//上课地址外键
    private int isPay;//0免费1付费
    private BigDecimal currentprice;//课程销售价格（实际支付价格）设置为0则可免费观看
    private String learningTarget;//学习目标
    private Long lessionnum=0l;//课时
    private String liveLink;//直播链接
    private String liveDetails;//直播信息
    private String courseModel;//上课方式
    private String isJoinClass;//是否可插班  （SURE 随时可插班  CANNOT 不可插班）
    private BigDecimal joinPrise;//插班价格
    private int peopleNum;//开班人数
    private int joinNum;//报班人数
    private int liveState;//直播状态
    private String suitStudent;//适合学员 top优等  common普通
    private String subjectMajorStr;//科目名称
    private Long subjectMajorId;
    private int statu;//状态 0 正常 1 删除
    private String coursePlan;//班课课程安排
    private String classImgs;//班课图片
    private String isFinsh;//是否完成 SUREJOIN可报名 NOJOIN不可报名 FINSHED已完成
    
    private Long subjectId;//专业科目关联id
    private long teacherId;//教师id
    private String teacherName;// 教师姓名
    private User teacher;//发布人信息
    private SubjectMajor subjectMajor;//专业科目信息
    private Map<String,String> courseModelMap;
}
