package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFeedback implements Serializable{
    /**
	 * 查询 用户反馈 
	 * @author wangzhuang
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long userId;
    private String content;
    private String qq;
    private String mobile;
    private String email;
    private String name;
    private java.util.Date createTime;
    
    //在这里添加了新的字段
    private Long type;
    private String img;
}
