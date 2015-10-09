package com.yizhilu.os.edu.entity.card;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课程卡和课程中间表
 * @author  xinYing.shan
 * @version Sep 25, 2014 1:49:30 PM
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CardCourse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;		
    private Long cardId;
    private Long courseId;
}
