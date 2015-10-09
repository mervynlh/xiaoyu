package com.yizhilu.os.edu.service.cashOut;

import java.util.List;

import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cashOut.CashOut;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;

/**
 * TrxorderOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CashOutService {

	/**
     * 添加CashOut
     * @param CashOut 要添加的cashOut
     * @return id
     */
    public java.lang.Long createCashOut(CashOut cashOut) throws AccountException, StaleObjectStateException;

    /**
     * 根据id删除一个CashOut（更改状态为取消）
     * @param id 要删除的id
     */
    public void updateCashOutStatusById(CashOut cashOut) throws AccountException, StaleObjectStateException;
    
    /**
     * 根据id获取单个CashOut对象
     * @param id 要查询的id
     * @return CashOut
     */
    public CashOut getCashOutById(Long id);

    /**
     * 根据条件获取CashOut列表
     * @param CashOut 查询条件
     * @return List<CashOut>
     */
    public List<CashOut> getCashOutList(CashOut cashOut, PageEntity page);
    
    /**
     * 教师账户充值
     * @param deail
     */
    public void updateteacherAccountRecharge(TrxorderDetail deail) throws AccountException, StaleObjectStateException;
}