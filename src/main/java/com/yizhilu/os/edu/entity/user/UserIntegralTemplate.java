package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.user.UserIntegralTemplate
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-9-27 上午9:19:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegralTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6096408895443588442L;

	private Long id;// 主键自增
	private String name;// 模板名称
	private Long type;// 0学员
	private String keyword;// 使用场景
	private java.math.BigDecimal score;// 功能分数
	private Long status;// 0正常1停用
	private java.util.Date createTime;// 创建时间
	private java.util.Date updateTime;// 更新时间
	private String createUser;// 创建人
	private int showScore;

	public int getShowScore() {// 显示整数
		showScore = score.intValue();//转化为int
		return showScore;
	}
}
