package com.yizhilu.os.edu.service.impl.cashOut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cashOut.CashOutOptRecord;
import com.yizhilu.os.edu.dao.cashOut.CashOutOptRecordDao;
import com.yizhilu.os.edu.service.cashOut.CashOutOptRecordService;
/**
 * TrxorderOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("cashOutOptRecordService")
public class CashOutOptRecordServiceImpl implements CashOutOptRecordService{

	@Autowired
	private CashOutOptRecordDao cashOutOptRecordDao;
	
	/**
	 * 添加操作记录
	 */
	public Long createCashOutOptRecord(CashOutOptRecord cashOutOptRecord) {
		return cashOutOptRecordDao.createCashOutOptRecord(cashOutOptRecord);
	}

	/**
	 * 删除操作记录
	 */
	public void deleteCashOutOptRecordById(Long id) {
		cashOutOptRecordDao.deleteCashOutOptRecordById(id);
	}

	/**
	 * 修改操作记录
	 */
	public void updateCashOutOptRecord(CashOutOptRecord cashOutOptRecord) {
		cashOutOptRecordDao.updateCashOutOptRecord(cashOutOptRecord);
	}

	/**
	 * 根据ID查询操作记录
	 */
	public CashOutOptRecord getCashOutOptRecordById(Long id) {
		return cashOutOptRecordDao.getCashOutOptRecordById(id);
	}

	@Override
	public List<CashOutOptRecord> getCashOutOptRecordList(CashOutOptRecord cashOutOptRecord, PageEntity page) {
		return cashOutOptRecordDao.getCashOutOptRecordList(cashOutOptRecord, page);
	}
}