package com.yizhilu.os.edu.controller.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yizhilu.os.edu.service.order.TrxorderOptRecordService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecord;
/**
 * TrxorderOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
public class TrxorderOptRecordController extends EduBaseController{

 	@Autowired
    private TrxorderOptRecordService trxorderOptRecordService;
    
    
    
    /**
     * 修改TrxorderOptRecord
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord){
     	trxorderOptRecordService.updateTrxorderOptRecord(trxorderOptRecord);
    }

   
}