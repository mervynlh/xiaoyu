package com.yizhilu.os.edu.dao.impl.cashOut;

import java.util.List;

import com.yizhilu.os.edu.entity.cashOut.CashOut;
import com.yizhilu.os.edu.entity.cashOut.CashOutOptRecord;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecord;
import com.yizhilu.os.edu.dao.cashOut.CashOutDao;
import com.yizhilu.os.edu.dao.cashOut.CashOutOptRecordDao;
import com.yizhilu.os.edu.dao.order.TrxorderOptRecordDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * TrxorderOptRecord
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("cashOutDao")
public class CashOutDaoImpl extends GenericDaoImpl implements CashOutDao{

	/**
	 * 添加提现
	 */
	public Long createCashOut(CashOut cashOut) {
		return this.insert("CashOutMapper.createCashOut", cashOut);
	}

	/**
	 * 更改提现状态
	 */
	public void updateCashOutStatusById(CashOut cashOut) {
		this.update("CashOutMapper.updateCashOutStatusById", cashOut);
	}

	/**
	 * 根据ID查询提现详情
	 */
	public CashOut getCashOutById(Long id) {
		return this.selectOne("CashOutMapper.getCashOutById", id);
	}

	/**
	 * 根据条件分页查询提现列表
	 */
	public List<CashOut> getCashOutList(CashOut cashOut, PageEntity page) {
		return this.queryForListPage("CashOutMapper.getCashOutList", cashOut, page);
	}

	
}
