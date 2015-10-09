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
public class MsgSystem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private String content;// 信内容
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private int status;//
    private String addTimeStr;
    private int type;//消息类型 0普通消息 1优惠券消息

}
