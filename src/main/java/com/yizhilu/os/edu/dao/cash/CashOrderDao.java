package com.yizhilu.os.edu.dao.cash;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cash.CashOrder;
import com.yizhilu.os.edu.entity.cash.CashOrderDTO;
/**
 * CashOrder管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
public interface CashOrderDao {

    /**
     * 添加CashOrder
     * @param CashOrder 要添加的CashOrder
     * @return id
     */
    public Long addCashOrder(CashOrder cashOrder);

    /**
     * 根据id删除一个CashOrder
     * @param id 要删除的id
     */
    public void delCashOrderById(Long id);

    /**
     * 修改CashOrder
     * @param CashOrder 要修改的CashOrder
     */
    public void updateCashOrder(CashOrder cashOrder);

    /**
     * 根据id获取单个CashOrder对象
     * @param id 要查询的id
     * @return CashOrder
     */
    public CashOrder getCashOrderById(Long id);
    
    /**
     * 会员订单列表
     * @param queryCashOrder
     * @param page
     * @return
     */
	public List<CashOrderDTO> getCashOrderPage(CashOrderDTO cashOrderDTO, PageEntity page);

	 /**
     * 根据requestId获取单个CashOrder对象
     * @param id
     * @return
     */
    public CashOrder getCashOrderByRequestId(String requestId);
    
    /**
     * 更改订单成功后的状态
     * @param CashOrder
     * @return
     */
	public Long updateCashOrderStatusSuccess(CashOrder cashOrder);
	
	/**
     * 根据id获取单个CashOrderDTO对象
     * @param id 要查询的id
     * @return CashOrderDTO
     */
    public CashOrderDTO getCashOrderDTOById(Long id);
}