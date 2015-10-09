package com.yizhilu.os.edu.entity.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName  com.yizhilu.os.edu.entity.common.Dialog
 * @description 弹出窗类
 * @author :xujunbao
 * @Create Date : 2014年11月6日 上午10:35:44
 */
@Data
@EqualsAndHashCode
public class Dialog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6927228323162399318L;
	
	private String title;//窗口标题
	private String content;//窗口内容
	private Long index;//索引
	private String url;//确认窗口的确定按钮路径
	private String params;//参数
}
