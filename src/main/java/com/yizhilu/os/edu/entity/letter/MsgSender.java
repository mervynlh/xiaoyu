package com.yizhilu.os.edu.entity.letter;

import java.io.Serializable;
import java.util.Date;

import com.yizhilu.os.edu.entity.user.UserExpandDto;

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
public class MsgSender implements Serializable {
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
    private int type;// 类型2站内信
    private int status;//
    private String showname;// 会员名
    private UserExpandDto userExpandDto;// 用户信息
}
