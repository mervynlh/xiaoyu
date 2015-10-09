package com.yizhilu.os.edu.dao.impl.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.order.TrxdetailOptRecordDao;
import com.yizhilu.os.edu.entity.order.TrxdetailOptRecord;
import com.yizhilu.os.edu.entity.order.TrxdetailOptRecordDTO;

/**
 *
 * TrxdetailOptRecord
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("trxdetailOptRecordDao")
public class TrxdetailOptRecordDaoImpl extends GenericDaoImpl implements TrxdetailOptRecordDao{

    public java.lang.Long addTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        return this.insert("TrxdetailOptRecordMapper.createTrxdetailOptRecord",trxdetailOptRecord);
    }

    public void deleteTrxdetailOptRecordById(Long id){
        this.delete("TrxdetailOptRecordMapper.deleteTrxdetailOptRecordById",id);
    }

    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        this.update("TrxdetailOptRecordMapper.updateTrxdetailOptRecord",trxdetailOptRecord);
    }

    public TrxdetailOptRecordDTO getTrxdetailOptRecordById(Long id) {
        return this.selectOne("TrxdetailOptRecordMapper.getTrxdetailOptRecordById",id);
    }

    public List<TrxdetailOptRecordDTO> getTrxdetailOptRecordList(TrxdetailOptRecord trxdetailOptRecord,PageEntity page) {
        return this.queryForListPage("TrxdetailOptRecordMapper.getTrxdetailOptRecordList",trxdetailOptRecord,page);
    }
}
