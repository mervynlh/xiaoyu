package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.user.UserIntegral
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月28日 下午2:29:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegral implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -482835265931869949L;

	private Long id;// id
	private Long userId;// 用户id
	private Long currentScore;// 当前分数
	private Long totalScore;// 总分数
	private String email;// 用户邮箱
	private String mobile;//手机号码
	private String realname;//用户姓名
}
