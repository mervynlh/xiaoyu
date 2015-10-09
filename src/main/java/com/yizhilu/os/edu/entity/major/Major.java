package com.yizhilu.os.edu.entity.major;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Major  implements Serializable{/**
	 * 
	 * 科目表
	 */
	private static final long serialVersionUID = 1L;
	private Long id;  //主键ID
	private String name; //科目名称
	private int status=-1; //状态 0正常1冻结2删除
	private int sort; // 排序 倒序
	
	private String checkSelected; // 判断该科目是否被选中

}
