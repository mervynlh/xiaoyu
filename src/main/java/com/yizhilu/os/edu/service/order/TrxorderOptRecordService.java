package com.yizhilu.os.edu.service.order;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecord;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecordDTO;

/**
 * TrxorderOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface TrxorderOptRecordService {

    /**
     * 添加TrxorderOptRecord
     * @param trxorderOptRecord 要添加的TrxorderOptRecord
     * @return id
     */
    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord);

    /**
     * 根据id删除一个TrxorderOptRecord
     * @param id 要删除的id
     */
    public void deleteTrxorderOptRecordById(Long id);

    /**
     * 修改TrxorderOptRecord
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord);

    /**
     * 退款审核通过
     * @param trxorderOptRecord
     * @throws Exception
     */
    public void updateTrxorderOptRecordSuccess(TrxorderOptRecord trxorderOptRecord)throws Exception;
    
    /**
     * 退款审核拒绝
     * @param trxorderOptRecord
     * @throws Exception
     */
    public void updateTrxorderOptRecordCancel(TrxorderOptRecord trxorderOptRecord)throws Exception;
    /**
     * 根据id获取单个TrxorderOptRecord对象
     * @param id 要查询的id
     * @return TrxorderOptRecord
     */
    public TrxorderOptRecordDTO getTrxorderOptRecordById(Long id);

    /**
     * 根据条件获取TrxorderOptRecord列表
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    public List<TrxorderOptRecordDTO> getTrxorderOptRecordList(TrxorderOptRecordDTO trxorderOptRecord,PageEntity page);
}