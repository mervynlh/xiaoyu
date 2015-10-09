package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VedioLive implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String title;
    private String code;
    private String teacher;
    private java.util.Date liveTime;
    private Long joinNum;
    private String content;
    private java.util.Date endTime;
    private java.util.Date addTime;
    private java.util.Date updateTime;
    private String dateFalg;
}
