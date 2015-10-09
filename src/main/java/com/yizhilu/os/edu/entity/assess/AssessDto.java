package com.yizhilu.os.edu.entity.assess;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.edu.constants.enums.CourseModel;

/**
 * @author :wangzhuang
 * 学生对教师的评论实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AssessDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;//主键自增
	private long userId;//用户id 学生id
	private long orderId;//订单id
	private long teacherId;//教师id
	private java.util.Date createTime;//评价时间
	private String content;//评价内容
	private String imgs;//上传的图片
	private long status;//状态
	private long type;//评价类型1好评2中评3差评
	private long description;//描述相关得分
	private long attribute;//教学态度得分
	private long speed;//响应态度得分
	private String courseModel;//上课方式
	private String subjectMajorName;//专业科目名称
	private int star;//评分
	
	private String nickname;//评论人昵称
	private String email;//评论人邮箱
	private String avatar;//评论人头像
	
	private String teacherName;//教师名称
	private String studentName;//学生名称
	
	public int getStar(){
		if(this.star==0){
			return 5;
		}else{
			return this.star;
		}
	}
	
	public String getCourseModel(){
		if((CourseModel.TEACHERVISIT+"").equals(courseModel)){
			return "老师上门";
		}else if((CourseModel.STUDENTVISIT+"").equals(courseModel)){
			return "学生上门";
		}else if((CourseModel.TALKADDRESS+"").equals(courseModel)){
			return "协商地点";
		}else if((CourseModel.ONLINEOTO+"").equals(courseModel)){
			return "在线教学";
		}else if((CourseModel.ONLINECOU+"").equals(courseModel)){
			return "在线授课";
		}else if((CourseModel.LINEDOWNCOU+"").equals(courseModel)){
			return "线下面授";
		}else{
			return null;
		}
	}
	private String courseName;//课程名称
	
}
