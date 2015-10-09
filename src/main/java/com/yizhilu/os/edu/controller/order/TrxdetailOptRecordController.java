package com.yizhilu.os.edu.controller.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.yizhilu.os.edu.service.order.TrxdetailOptRecordService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.order.TrxdetailOptRecord;
/**
 * TrxdetailOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
public class TrxdetailOptRecordController extends EduBaseController{

 	@Autowired
    private TrxdetailOptRecordService trxdetailOptRecordService;
    
    
    
    /**
     * 修改TrxdetailOptRecord
     * @param trxdetailOptRecord 要修改的TrxdetailOptRecord
     */
    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord){
     	trxdetailOptRecordService.updateTrxdetailOptRecord(trxdetailOptRecord);
    }

   
}