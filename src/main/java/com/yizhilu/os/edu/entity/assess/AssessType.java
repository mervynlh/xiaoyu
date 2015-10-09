package com.yizhilu.os.edu.entity.assess;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssessType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long type;// 类型1好评2中评3差评
	private Long count;// 数量
}