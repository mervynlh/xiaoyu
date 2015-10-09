package com.yizhilu.os.edu.constants.enums;

/**
 * @ClassName  com.yizhilu.os.edu.constant.enums.TrxOrderStatus
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-6-24 下午2:37:38
 */
public enum TrxOrderStatus {
    INIT,//未支付,初始化
    SUCCESS,//支付成功
    AUDIT,// 退款审核
    REFUND,//退款
    CANCEL,//取消cancel
    OVER,//已过期
    FINISH//已完成的
}
