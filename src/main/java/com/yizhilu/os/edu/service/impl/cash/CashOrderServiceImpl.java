package com.yizhilu.os.edu.service.impl.cash;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.EnumUtil;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.dao.cash.CashOrderDao;
import com.yizhilu.os.edu.entity.cash.CashOrder;
import com.yizhilu.os.edu.entity.cash.CashOrderDTO;
import com.yizhilu.os.edu.entity.cash.CashOrderReqData;
import com.yizhilu.os.edu.service.cash.CashOrderService;
/**
 * CashOrder管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Service("cashOrderService")
public class CashOrderServiceImpl implements CashOrderService{
	
	@Autowired
	private CashOrderDao cashOrderDao;
	
	/** 
	 * 添加MemberOrder
	 * @param CashOrder
	 *            要添加的MemberOrder
	 * @return id
	 */
	public Map<String, Object> addCashOrder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		// 检查参数
		CashOrderReqData cashOrderReqData = checkCashOrderParam(sourceMap);
		if (cashOrderReqData == null||cashOrderReqData.getPayCash() == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		if (cashOrderReqData.getUserId() == null) {// 未登录
			result.put("msg", "userId");
			return result;
		}
		if (cashOrderReqData.getPayCash() == null) {// 充值金额错误
			result.put("msg", "payCash");
			return result;
		}

		// 拼装订单数据
		CashOrder cashOrder = new CashOrder();
		// 下单时间
		Date date = new Date();
		cashOrder.setCreateTime(date);// 下单时间
		cashOrder.setUserId(cashOrderReqData.getUserId());
		cashOrder.setRequestId(this.getCashOrderNum(cashOrderReqData.getUserId()));// 交易请求号
		cashOrder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
		cashOrder.setPayType(cashOrderReqData.getPayType().toString());// 支付类型
		cashOrder.setReqChannel(cashOrderReqData.getReqChannel());// 请求渠道
		cashOrder.setReqIp(cashOrderReqData.getReqIp());
		cashOrder.setDescription(new Gson().toJson(sourceMap));
		BigDecimal orderAmount=cashOrderReqData.getPayCash();
		cashOrder.setOrderAmount(orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP)); // 原始金额,保留两位小数
		cashOrder.setAmount(orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP));// 实际支付金额,保留两位小数
		// 添加订单
		cashOrderDao.addCashOrder(cashOrder);
		result.put("cashOrder", cashOrder);
		return result;
	}
	
 	/**
	 * 订单检查请求参数
	 * 
	 * @param sourceMap
	 * @return
	 */
	public CashOrderReqData checkCashOrderParam(Map<String, String> sourceMap) {
		CashOrderReqData reqData = new CashOrderReqData();
		String paycash = sourceMap.get("paycash");//充值金额
		if (StringUtils.isNotEmpty(paycash) && isRightCash(paycash)) {
			reqData.setPayCash(new BigDecimal(paycash));
		} else {
			return null;
		}
		
		String userid = sourceMap.get("userid");// 用户id
		if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
			reqData.setUserId(Long.valueOf(userid));
		} else {
			return null;
		}

		String reqchanle = sourceMap.get("reqchanle");// 用户id
		if (StringUtils.isNotEmpty(reqchanle)) {
			reqData.setReqChannel(reqchanle);
		} else {
			return null;
		}
		
		String payType = sourceMap.get("payType");// 支付类型
		if (StringUtils.isNotEmpty(payType)) {
			reqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
		} else {
			return null;
		}

		String reqIp = sourceMap.get("reqIp");// 用户reqIp
		if (StringUtils.isNotEmpty(reqIp)) {
			reqData.setReqIp(reqIp);
		} else {
			reqData.setReqIp("");
		}
		return reqData;
	}
	/**
	 * 判断充值金额是否正确,0.01~?
	 * @param str
	 * @return
	 */
	public static boolean isRightCash(String str){
		
		return str.matches("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
	}
	
	/**
	 * 生成订单号 当前用户id+毫秒数
	 * 
	 * @return
	 */
	public String getCashOrderNum(Long userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}

    /**
     * 根据id删除一个CashOrder
     * @param id 要删除的id
     */
    public void delCashOrderById(Long id){
    	cashOrderDao.delCashOrderById(id);
    }

    /**
     * 修改CashOrder
     * @param CashOrder 要修改的CashOrder
     */
    public void updateCashOrder(CashOrder cashOrder){
    	cashOrderDao.updateCashOrder(cashOrder);
    }

    /**
     * 根据id获取单个CashOrder对象
     * @param id 要查询的id
     * @return CashOrder
     */
    public CashOrder getCashOrderById(Long id){
    	return cashOrderDao.getCashOrderById(id);
    }
    
    /**
     * 会员订单列表
     * @param queryCashOrder
     * @param page
     * @return
     */
	public List<CashOrderDTO> getCashOrderPage(CashOrderDTO cashOrderDTO, PageEntity page){
		return cashOrderDao.getCashOrderPage(cashOrderDTO, page);
	}

	 /**
     * 根据requestId获取单个CashOrder对象
     * @param id
     * @return
     */
    public CashOrder getCashOrderByRequestId(String requestId){
    	return cashOrderDao.getCashOrderByRequestId(requestId);
    }
    
    /**
     * 更改订单成功后的状态
     * @param CashOrder
     * @return
     */
	public Long updateCashOrderStatusSuccess(CashOrder cashOrder){
		return cashOrderDao.updateCashOrderStatusSuccess(cashOrder);
	}
	
	/**
     * 根据id获取单个CashOrderDTO对象
     * @param id 要查询的id
     * @return CashOrderDTO
     */
    public CashOrderDTO getCashOrderDTOById(Long id){
    	return cashOrderDao.getCashOrderDTOById(id);
    }

}