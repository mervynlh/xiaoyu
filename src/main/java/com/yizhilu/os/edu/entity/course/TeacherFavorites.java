package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherFavorites implements Serializable{
    private Long id;
    private Long teacherId;
    private Long userId;
    private java.util.Date addTime;
}
