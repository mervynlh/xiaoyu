package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.yizhilu.os.edu.entity.teacher.TeacherMajor;
import com.yizhilu.os.edu.entity.teacher.TeacherSubject;
import com.yizhilu.os.edu.entity.user.UserExpand;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.course.Teacher
 * @description 讲师实体
 * @author : XuJunBao
 * @Create Date : 2014年9月15日 下午10:03:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 838162101564081713L;
    private Long id;// 主键自增
    private String name;// 教师名称
    private int sex = -1; // 教师性别  0男 1女
    private String picPath;// 头像
    private Long status;// 状态:-1教师未完善资料  1正常2未审核（冻结、删除）
    private java.util.Date createTime;// 创建时间
    private Long userId; // 关联用户ID
    private String degree; // 学历
    private String finishSchool; // 毕业院校
    private String profession; // 专业
    private Long seniority; // 教龄
    private String career;// 讲师简介
    private String peculiarity; // 教学特点
    private String card; // 身份证认证
    private String education; // 学历认证图片
    private String teaching; // 教师证认证
    private String specialty; // 专业资质认证
    private int isProfessor; // 是否为韩教授认证教师
    private Long soft; // 排序字段
    private BigDecimal lowPrice=new BigDecimal(0);// 最低价
    private String provinceName;//省份名称（订单地图显示）
    private Long cityId; // 城市ID
    private String cityName; // 城市名称
    private Long districtId; // 区县ID
    private String districtName;// 区县名
    private String address; // 教师详细地址

    private String lng;
    private String lat;
    
    private int studentNum;//学生人数
    private Double star = 0d; // 教师评星
    private Long assessNum=0l;// 评价总数

    private UserExpand userExpand;
    
    private Date birthday;// 出生日期
    
    private Long recommendId;
    private String suitStudent;// 适合学员
    private long satisfy;//满意度
    private long showStar;//满意度
    private int visitnum;//浏览量
    
    private String teacherSubject; // 教师教授阶段ID字符串集合
    private String teacherMajor; // 教师教授科目ID字符串集合
    private List<TeacherMajor> teacherMajors;//所教授科目的集合
    private List<TeacherSubject> teacherSubjects;//教师所教授的专业集合
    private int totalAssess;//总数
    private int goodAssess;//好评数
    private int middleAssess;//中评数
    private int badAssess;//差评数
    private String goodPercent="0";//好评率
    private String middlePercent="0";//中评率
    private String badPercent="0";//差评率
    
    private String teachingEnounce; // 教学宣言
    private String teachingLive; // 教学经历
    private String teachingSuccess; // 教学成功经历
    
    private String area;//教师地区
    
    private Date auditingDate; // 教师认证通过时间
    
	private BigDecimal description;//描述相符得分
	private BigDecimal attribute;//教学态度得分
	private BigDecimal speed;//相应速度得分

    private Long courseNum; // 课程数量
    private Long addressNum; // 教学地址数量
    private Long majorsNum; // 科目数量
    private Long subjectsNum; // 年级数量

    private Long classhourNum; // 可预约课时数量（状态为2）

    private int cardStatus; // 身份证认证状态  1审核中  2审核通过  3审核未通过
    private int educationStatus; // 学历认证状态 1审核中  2审核通过  3审核未通过
    private int teachingStatus; // 教师证认证状态  1审核中  2审核通过  3审核未通过
    private int specialtyStatus; // 专业资质认证状态 1审核中  2审核通过  3审核未通过

    private String subjects; // 教师教授年级字符串
    private String majors; // 教师教授科目字符串

    private String zoomMeetingUserId; // 教师创建直播对接视频接口返回的用户ID

    //获取百分比
    public long getSatisfy(){
    	if(satisfy>0){
    		return Math.round(getStar()/5*100);
    	}else{
    		return satisfy;
    	}
    }
    public long getShowStar(){
    	if(showStar>0){
    		return Math.round(getStar());
    	}else{
    		return showStar;
    	}
    }

}
