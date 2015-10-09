package com.yizhilu.os.edu.service.coupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.coupon.CouponDTO;
import com.yizhilu.os.edu.entity.coupon.QueryCouponCode;
import com.yizhilu.os.edu.entity.course.Course;

/**
 * CouponCode管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CouponCodeService {

	/**
	 * 获取优惠券
	 * @param userId 用户id
	 * @param couponId 优惠券id
	 * @return 是否已获取
	 */
    public boolean addCouponCode(Long userId,Long couponId);

    /**
     * 添加优惠券记录
     * @param val
     */
    public void addCouponCodeString(StringBuffer val);
    
    /**
     * 根据id删除一个CouponCode
     * @param it 要删除的id
     */
    public void deleteCouponCodeById(Long id);

    /**
     * 修改CouponCode
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCode(CouponCode couponCode);
    /**
     * 修改CouponCode支付时间
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCodePayTime(CouponCode couponCode);
    /**
     * 根据id获取单个CouponCode对象
     * @param it 要查询的id
     * @return CouponCode
     */
    public CouponCode getCouponCodeById(Long id);
    /**
     * 根据优惠券编码获取单个CouponCode对象
     * @param it 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code);

    /**
     * 根据条件获取CouponCode列表
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
    public List<CouponCode> getCouponCodeListByCouponId(Long id);
    /**
     * 优惠券id查找优惠券编码
     * @param id
     * @return
     */
	public List<String> getStringCodeByCouponId(Long id);
	/**
	 * 优惠券编码列表
	 * @param queryCoupon
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page);
	/**
	 * id查询优惠券编码
	 * @param queryCoupon
	 * @param page
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id);
	
	/**
	 * 使用优惠券改变状态码
	 * @param id
	 */
	public void useCouponCode(Long id);
	/**
	 * 作废优惠券编码
	 * @param id
	 */
	public void wasteCouponCode(String ids);
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id);
	/**
	 * 过期的优惠编码改状态
	 * @param id
	 */
	public void overdueCodeByTime();
	/**
	 * 优惠编码使用限制
	 * @param couponCodeDTO
	 * @param userId
	 * @return
	 */
	public Map<String,Object> checkCode(CouponCodeDTO couponCodeDTO,Long userId);
  
	/**
	 * 获取我的优惠券
	 * @param userId 用户id
	 * @param status 状态
	 * @return
	 */
    public List<CouponCodeDTO> getMyCouponList(Long userId,int status,int type,PageEntity page);
    /**
     * 优惠券状态与数量
     * @param userId 用户id
     * @return
     */
    public Map<String,Object> getCouponCodeStatus(Long userId);

    /**
     * 根据优惠券id和用户id获取优惠券记录
     * @param couponId 优惠券id
     * @param userId 用户id
     * @return
     */
    public CouponCode getCouponCodeByCouponIdAndUserId(Long couponId,Long userId);
    /**
	 * 用户获取订单能使用的优惠券
	 * @param loginUserId
	 * @param teaId
     * @param money 
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodeListByUserForOrder(Long loginUserId, Long teaId, BigDecimal money);
}