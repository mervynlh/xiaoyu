package com.yizhilu.os.edu.dao.impl.cash;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.cash.CashOrderDao;
import com.yizhilu.os.edu.entity.cash.CashOrder;
import com.yizhilu.os.edu.entity.cash.CashOrderDTO;

/**
 *
 * CashOrder
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-09-26
 */
 @Repository("cashOrderDao")
public class CashOrderDaoImpl extends GenericDaoImpl implements CashOrderDao{

    public Long addCashOrder(CashOrder cashOrder) {
        return this.insert("CashOrderMapper.createCashOrder",cashOrder);
    }

    public void delCashOrderById(Long id){
        this.delete("CashOrderMapper.delCashOrderById",id);
    }

    public void updateCashOrder(CashOrder cashOrder) {
        this.update("CashOrderMapper.updateCashOrder",cashOrder);
    }

    public CashOrder getCashOrderById(Long id) {
        return this.selectOne("CashOrderMapper.getCashOrderById",id);
    }
    /**
     * 根据id获取单个CashOrderDTO对象
     * @param id 要查询的id
     * @return CashOrderDTO
     */
    public CashOrderDTO getCashOrderDTOById(Long id){
    	return this.selectOne("CashOrderMapper.getCashOrderDTOById",id);
    }

    /**
     * 充值订单列表
     * @param queryCashOrder
     * @param page
     * @return
     */
	public List<CashOrderDTO> getCashOrderPage(CashOrderDTO cashOrderDTO, PageEntity page){
		return this.queryForListPage("CashOrderMapper.getCashOrderPage", cashOrderDTO, page);
	}
	
	 /**
     * 根据requestId获取单个CashOrder对象
     * @param id
     * @return
     */
    public CashOrder getCashOrderByRequestId(String requestId){
    	return this.selectOne("CashOrderMapper.getCashOrderByRequestId",requestId);
    }
    
    /**
     * 更新订单状态为成功
     * @param trxorder
     */
    public Long updateCashOrderStatusSuccess(CashOrder cashOrder){
        return this.update("CashOrderMapper.updateCashOrderStatusSuccess",cashOrder);
    }
}
