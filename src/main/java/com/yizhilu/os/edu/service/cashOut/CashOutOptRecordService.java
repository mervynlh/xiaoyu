package com.yizhilu.os.edu.service.cashOut;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cashOut.CashOutOptRecord;

/**
 * TrxorderOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CashOutOptRecordService {

	/**
     * 添加CashOutOptRecord
     * @param CashOutOptRecord 要添加的cashOutOptRecord
     * @return id
     */
    public java.lang.Long createCashOutOptRecord(CashOutOptRecord cashOutOptRecord);

    /**
     * 根据id删除一个CashOutOptRecord
     * @param id 要删除的id
     */
    public void deleteCashOutOptRecordById(Long id);

    /**
     * 修改CashOutOptRecord
     * @param CashOutOptRecord 要修改的cashOutOptRecord
     */
    public void updateCashOutOptRecord(CashOutOptRecord cashOutOptRecord);

    /**
     * 根据id获取单个CashOutOptRecord对象
     * @param id 要查询的id
     * @return CashOutOptRecord
     */
    public CashOutOptRecord getCashOutOptRecordById(Long id);

    /**
     * 根据条件获取CashOutOptRecord列表
     * @param CashOutOptRecord 查询条件
     * @return List<CashOutOptRecord>
     */
    public List<CashOutOptRecord> getCashOutOptRecordList(CashOutOptRecord cashOutOptRecord, PageEntity page);
}