package com.yizhilu.os.edu.dao.impl.cashOut;

import java.util.List;

import com.yizhilu.os.edu.entity.cashOut.CashOutOptRecord;
import com.yizhilu.os.edu.dao.cashOut.CashOutOptRecordDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * TrxorderOptRecord
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("cashOutOptRecordDao")
public class CashOutOptRecordDaoImpl extends GenericDaoImpl implements CashOutOptRecordDao{

	/**
	 * 添加操作记录
	 * @param cashOutOptRecord
	 * @return
	 */
	public Long createCashOutOptRecord(CashOutOptRecord cashOutOptRecord) {
		return this.insert("CashOutOptRecordMapper.createCashOutOptRecord", cashOutOptRecord);
	}

	/**
	 * 根据ID删除操作记录
	 * @param id
	 */
	public void deleteCashOutOptRecordById(Long id) {
		this.delete("CashOutOptRecordMapper.deleteCashOutOptRecordById", id);
	}

	/**
	 * 更新提现操作记录
	 * @param cashOutOptRecord
	 */
	public void updateCashOutOptRecord(CashOutOptRecord cashOutOptRecord) {
		this.update("CashOutOptRecordMapper.updateCashOutOptRecord", cashOutOptRecord);
	}

	/**
	 * 根据ID查询提现详情
	 * @param id
	 * @return
	 */
	public CashOutOptRecord getCashOutOptRecordById(Long id) {
		return this.selectOne("CashOutOptRecordMapper.getCashOutOptRecordById", id);
	}

	/**
	 * 根据条件分页查询提现操作记录
	 * @param cashOutOptRecord
	 * @param page
	 * @return
	 */
	public List<CashOutOptRecord> getCashOutOptRecordList(CashOutOptRecord cashOutOptRecord, PageEntity page) {
		return this.queryForListPage("CashOutOptRecordMapper.getCashOutOptRecordList", cashOutOptRecord, page);
	}

    
}
