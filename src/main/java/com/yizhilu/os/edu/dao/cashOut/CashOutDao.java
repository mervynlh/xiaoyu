package com.yizhilu.os.edu.dao.cashOut;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cashOut.CashOut;

/**
 * CashOut管理接口
 * User: WangKaiping
 * Date: 2015-08-11
 */
public interface CashOutDao {

    /**
     * 添加CashOut
     * @param CashOut 要添加的cashOut
     * @return id
     */
    public java.lang.Long createCashOut(CashOut cashOut);

    /**
     * 删除一个CashOut（更改状态）
     * @param cashOut 要删除的id
     */
    public void updateCashOutStatusById(CashOut cashOut);
    
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
}