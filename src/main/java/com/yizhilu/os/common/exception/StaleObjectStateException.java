package com.yizhilu.os.common.exception;

import com.yizhilu.os.core.common.exception.BaseException;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.yizhilu.os.common.exception.StaleObjectStateException
 * @description 事务，乐观锁异常
 * @Create Date : 2014-9-20 上午9:16:30
 */
public class StaleObjectStateException extends BaseException {
    /**
     *
     */
    private static final long serialVersionUID = 2731984201900157341L;

    /**
     * 乐观锁异常
     */
    public static final int OPTIMISTIC_LOCK_ERROR = 9001; // 乐观锁异常
    public static final int OPTIMISTIC_UPDATE_NONE = 9002; // 更新数据响应行数为0
    public static final int OPTIMISTIC_OUTTRADENO_EXIT = 9003; // 账户历史已存在该支付宝订单号


    public StaleObjectStateException() {

        super();
    }

    public StaleObjectStateException(int code) {

        super(code);
    }

    public StaleObjectStateException(String errorMsg) {

        super(errorMsg);
    }
}
