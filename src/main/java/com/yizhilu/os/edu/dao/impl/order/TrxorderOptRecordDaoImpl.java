package com.yizhilu.os.edu.dao.impl.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.order.TrxorderOptRecordDao;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecord;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecordDTO;

/**
 *
 * TrxorderOptRecord
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("trxorderOptRecordDao")
public class TrxorderOptRecordDaoImpl extends GenericDaoImpl implements TrxorderOptRecordDao{

    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        return this.insert("TrxorderOptRecordMapper.createTrxorderOptRecord",trxorderOptRecord);
    }

    public void deleteTrxorderOptRecordById(Long id){
        this.delete("TrxorderOptRecordMapper.deleteTrxorderOptRecordById",id);
    }

    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        this.update("TrxorderOptRecordMapper.updateTrxorderOptRecord",trxorderOptRecord);
    }

    public TrxorderOptRecordDTO getTrxorderOptRecordById(Long id) {
        return this.selectOne("TrxorderOptRecordMapper.getTrxorderOptRecordById",id);
    }

    public List<TrxorderOptRecordDTO> getTrxorderOptRecordList(TrxorderOptRecordDTO trxorderOptRecord,PageEntity page) {
        return this.queryForListPage("TrxorderOptRecordMapper.getTrxorderOptRecordList",trxorderOptRecord,page);
    }
}
