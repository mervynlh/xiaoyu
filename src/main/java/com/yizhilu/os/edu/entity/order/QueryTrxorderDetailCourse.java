package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import com.yizhilu.os.edu.constants.enums.CourseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class QueryTrxorderDetailCourse extends TrxorderDetail implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
		private String teacherAvatar;// 老师头像
	    private String teacherName;// 老师姓名
	    private String teacherMobile;// 老师手机
	    private String studentAvatar;// 学生头像
	    private String studentName;// 学生姓名
	    private String studentMobile;// 学生手机
	    private String courseModel;// 上课方式
	    
	    public long getStartTimeLong(){
	    	if(this.getStartTime()!=null){
	    		return (this.getStartTime().getTime()-new Date().getTime())/1000;
	    	}else{
	    		return 0l;
	    	}
	    }
}
