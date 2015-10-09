package com.yizhilu.os.edu.entity.letter;

import java.io.Serializable;
import java.util.Date;

import com.yizhilu.os.edu.entity.user.UserExpandDto;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.core.entity.letter.QueryMsgReceive
 * @description 站内信查询类
 * @Create Date : 2013-12-13 下午12:45:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryMsgReceive implements Serializable {
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
    private int type;// 类型1系统通知2上课通知 3支付信息 4评价信息
    private int status;// 0未读1已读
    private String cusName;// 用户名
    private Long groupId;// 小组组id申请加入小组组时用到
    private String remarks;// 好友备注
    private String groupName;// 小组名称
    private int systemNum;// 系统消息未读数量
    private int courseMsgNum;// 上课未读通知数量
    private int orderMsgNum;// 支付信息未读通知数量
    private int assessMsgNum;// 评价信息未读通知数量
    private int unReadNum;// 用户全部未读数量
    private String showname;// 会员名
    private UserExpandDto userExpandDto;// 用户信息
}
