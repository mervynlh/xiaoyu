package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseKpoint implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -2252970709827434582L;
    private Long id;
    private Long courseId;
    private String name;
    private Long parentId;
    private Long type;
    private Long status;
    private java.util.Date addTime;
    private Long sort;
    private Long playcount;
    private Long isfree;
    private String videotype;
    private String videourl;
    private Long teacherId;
    private Long courseMinutes;
    private Long courseSeconds;
    private String videojson;
    private Long personNum;
    private String courseName;
    private List<CourseKpoint> childKpoints=new ArrayList<CourseKpoint>();
}
