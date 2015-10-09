package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Data
 * @ClassName  com.yizhilu.os.edu.entity.user.UserLevel
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月27日 下午2:16:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLevel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;//主键自增
    private Long level;//等级
    private String title;//头衔
    private Long exp;//经验值
    
    List<UserLevel> userLevel= new ArrayList<UserLevel>();
}
