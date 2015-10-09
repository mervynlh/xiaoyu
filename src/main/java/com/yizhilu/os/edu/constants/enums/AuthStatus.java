package com.yizhilu.os.edu.constants.enums;

/**
 * @ClassName  com.yizhilu.os.edu.constant.enums.AuthStatus
 * @description 订单明细的状态（是否可以观看用此字段）
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-7-24 下午5:16:06
 */
public enum AuthStatus {
    INIT,//初始化 未开始
    SUCCESS,//支付成功
    REFUND,//退费,
    CLOSED,//关闭，后台手动关闭
    LOSED//已过期，系统定时过期
}
