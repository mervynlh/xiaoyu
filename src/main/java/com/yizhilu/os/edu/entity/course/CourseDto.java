package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName com.yizhilu.os.edu.entity.course.CourseDto
 * @description 课程综合信息
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-9-12 下午4:49:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseDto extends Course implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 9148002883869292944L;
    private String nickname;
}
