package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.course.CourseStudyhistory
 * @description 记录播放记录
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-9-26 上午10:25:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseStudyhistory implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 5434482371608343070L;
    private Long id;
    private Long userId;//播放次数
    private Long courseId;//播放次数
    private Long kpointId;//播放次数
    private Long playercount;//播放次数
    private String courseName;//课程名称
    private String kpointName;//节点名称
    private String databak;//playercount小于20时记录,备注观看的时间，叠加
    private java.util.Date updateTime;//更新时间
    private String logo;	//图片
    private String teacherName;	//教师名称
    
}
