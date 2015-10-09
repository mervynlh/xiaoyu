package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseFavorites implements Serializable{
    private Long id;
    private Long courseId;
    private Long userId;
    private java.util.Date addTime;
}
