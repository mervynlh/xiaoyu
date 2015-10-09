package com.yizhilu.os.edu.entity.cashOut;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherExtendCashback implements Serializable{
    /**
	 * 教师推广返现
	 */
	private static final long serialVersionUID = 114545421484212545L;
	private Long id; // 主键ID
    private Long teacherId; // 推广教师ID
    private Long userId; // 用户ID
    private String userName; // 用户名
    private Long userType; // 用户身份、类型   0学生  1老师
    private String email; // 用户邮箱
    private java.math.BigDecimal cashbackMoney; // 返现金额
    private java.util.Date createTime; // 添加时间
    private String mobile;//用户手机号码
    
    private Date startTime; // 查询开始时间
    private Date endTime; // 查询结束时间
}
