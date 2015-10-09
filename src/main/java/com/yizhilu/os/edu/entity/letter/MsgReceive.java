package com.yizhilu.os.edu.entity.letter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.core.entity.weibo.Letter
 * @description 站内信
 * @Create Date : 2013-12-13 下午12:43:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MsgReceive implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private Long cusId;// 发信人用户id
    private String content;// 信内容
    private Long receivingCusId;// 收信人id
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private int type;// 类型1系统通知2上课通知3支付信息
    private int status;// 0未读1已读
    private Long groupId;// 小组组id申请加入小组组时用到
    private String showname;// 会员名
}
