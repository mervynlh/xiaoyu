package com.yizhilu.os.edu.entity.card;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version Sep 25, 2014 1:49:55 PM
 * 卡附表 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CardCode implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long cardId;
    private String cardCode;
    private String cardCodePassword;
    private String status;
    private String userEmail;
    private Long userId;
    private Long trxorderId;
    private String requestId;
    private java.util.Date createTime;
    private java.util.Date useTime;
    private int type ; //课程卡类型  1课程卡  2充值卡
    private String name;
}
