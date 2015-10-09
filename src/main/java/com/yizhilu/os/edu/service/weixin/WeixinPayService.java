package com.yizhilu.os.edu.service.weixin;

/**
 * 微信常规回复设置service
 * @author Administrator
 *
 */
public interface WeixinPayService {
	/**
     * 微信支付请求
     *
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    public String getWxpayUrl(String requestId, String type);
}


