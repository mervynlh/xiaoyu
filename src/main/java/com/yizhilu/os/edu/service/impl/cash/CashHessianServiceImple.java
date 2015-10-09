package com.yizhilu.os.edu.service.impl.cash;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.util.EnumUtil;
import com.yizhilu.os.edu.constants.enums.AccountBizType;
import com.yizhilu.os.edu.constants.enums.AccountHistoryType;
import com.yizhilu.os.edu.constants.enums.AccountType;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.entity.cash.CashOrder;
import com.yizhilu.os.edu.entity.cash.CashOrderReqData;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.service.cash.CashHessianService;
import com.yizhilu.os.edu.service.cash.CashOrderService;
import com.yizhilu.os.edu.service.user.UserAccountService;

/**
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-9-19 上午10:53:19
 */
@Service("cashHessianService")
public class CashHessianServiceImple implements CashHessianService {

    private Logger logger = LoggerFactory.getLogger(CashHessianServiceImple.class);
    @Autowired
    private CashOrderService cashOrderService;
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 订单支付成功回调操作, 保证事务的一致性！！重要
     * 
     * @param sourceMap
     * @return
     */
    public Map<String, String> noCashOrderComplete(Map<String, String> sourceMap) {
        Map<String, String> res = new HashMap<String, String>();//
        try {
            logger.info("noCashOrderComplete param:"+sourceMap);
            Date date = new Date();
            String total_fee = sourceMap.get("total_fee");//交易金额,外界充值的金额，可能为0
            String requestId = sourceMap.get("requestId");//请求订单号
            String userId= sourceMap.get("userId");//用户id
            String payType=sourceMap.get("payType");
            String out_trade_no=sourceMap.get("out_trade_no");//提交到支付宝的请求号 
            //查询订单
            CashOrder cashOrder = cashOrderService.getCashOrderByRequestId(requestId);
            CashOrderReqData cashReqData = new CashOrderReqData();
            cashReqData.setBankAmount(new BigDecimal(total_fee));
            cashReqData.setRequestId(requestId);
            cashReqData.setCreateTime(date);
            cashReqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
            cashReqData.setUserId(Long.valueOf(userId));
            cashReqData.setOut_trade_no(out_trade_no);
            cashReqData.setCashOrderId(cashOrder.getId());
            try{
            	// 充值事务
                UserAccount userAccount =  userAccountService.getUserAccountByUserId(cashReqData.getUserId());
                userAccountService.credit(userAccount, cashReqData.getBankAmount(), AccountType.CASH, AccountHistoryType.CASHLOAD, cashReqData.getUserId(),cashReqData.getCashOrderId(), cashReqData.getRequestId(),
                		cashReqData.getOut_trade_no(),  new Date(), "", true, AccountBizType.CASH);//充值订单
                res.put(OrderConstans.BANKCODE, OrderConstans.SUCCESS);//充值成功给银行返回成功信息
                //更改充值订单状态为成功
                cashOrder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
                cashOrder.setPayTime(date);
                cashOrder.setPayType(cashReqData.getPayType().toString());
                cashOrderService.updateCashOrderStatusSuccess(cashOrder);
                res.put(OrderConstans.RESMSG, "账户充值成功！");
            }catch ( AccountException e1){
                e1.printStackTrace();
            }
            catch (  StaleObjectStateException e2){
                e2.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            res.put(OrderConstans.RESCODE, "error");
            res.put(OrderConstans.RESMSG, "操作异常，请稍后再试！");
            logger.error("nnoCashOrderComplete error", e);
            
        }

        return res;
    }
}
