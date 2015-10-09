package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老师课表
 * @author Administrator
 * 2015-8-5
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherTimeTable implements Serializable {
    private static final long serialVersionUID = 838162101564081713L;
    private long id;
    private long teacherId;//教师id
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private int status;//0 未预约 1 被预约
}
