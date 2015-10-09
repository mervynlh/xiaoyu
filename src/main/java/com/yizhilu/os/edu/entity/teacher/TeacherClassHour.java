package com.yizhilu.os.edu.entity.teacher;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherClassHour implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long teacherId; // 教师ID
    private java.util.Date dateDay; // 日期  （年-月-日）
    private String time; // 时间段
    private int status; // 状态  1未发布   2已发布(可预约)  3已预约4(待确认) 5班课发布
    private Long userId; // 约课学员ID
    
    private Date monday; // 周一日期
    private Date sunday; // 周日日期
    
    private String nickname;
    private String mobile;
    private String email;
}
