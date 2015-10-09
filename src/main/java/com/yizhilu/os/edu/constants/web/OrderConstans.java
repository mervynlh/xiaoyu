package com.yizhilu.os.edu.constants.web;

/**
 * 订单静态变量
 * 
 * @ClassName com.yizhilu.os.edu.constant.web.OrderConstans
 * @description
 * @author : xiaokun
 * @Create Date : 2014-6-23 下午12:03:10
 */
public class OrderConstans {
    public static final String SHOP_CART = "shopcart";// 购物车cookie

    // 支付宝常量 字符编码格式 目前支持 gbk 或 utf-8
    public static String alipay_input_charset = "utf-8";

    // 签名方式 不需修改
    public static String alipay_sign_type = "MD5";
    
    //返回值
    public static final String  RESCODE="rescode";
    
    //返回信息
    public static final String  RESMSG="msg";
    
    //成功信息
    public static final String  SUCCESS="success";
    //失败信息
    public static final String  FALSE="false";
    
    //公司英文名字，订单中显示
  	public static String companyName="orderno:";

    //返回值，判断是否给支付宝等返回成功信息,此值为success代表成功充值了 给银行返回成功信息
    public static final String  BANKCODE="bankcode";
}
