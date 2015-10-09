package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;

import lombok.Data;

/**
 * 易宝支付参数
 * @author Administrator
 * 需要按照指定的顺序加密 ，否则会出现错误
 *
 */
@Data
public class YbParamInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String payUrl ;	//支付地址
	private String p0_Cmd ;	//业务类型
	private String p1_MerId ; //商户编号
	private String p2_Order ;//订单编号	//用于项目中的回调
	private String p3_Amt ;	//支付金额
	private String p4_Cur ;//交易币种
	private String p5_Pid ;	//商品名称
	private String p6_Pcat ;	//考试类别
	private String p7_Pdesc ;//商品描述
	private String p8_Url ;	//回调地址
	private String p9_SAF ; //送货地址
	private String pa_MP ;	//商户扩展信息
	private String pd_FrpId ;//支付通道编码
	private String pm_Period ;//订单有效期
	private String pn_Unit ;  //订单有效期单位
	private String pr_NeedResponse; //应答机制
	private String pt_UserName ;	//用户名称
	private String pt_PostalCode ;	//身份证号
	private String pt_Address ;	//报考序号
	private String pt_TeleNo ;	//地区
	private String pt_Mobile ;	//手机号
	private String pt_Email ;	//电子邮箱
	private String hmac ;//签名属性
	
	
	
	
}
