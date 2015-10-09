package com.yizhilu.os.edu.dao.coupon;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.coupon.CouponDTO;
import com.yizhilu.os.edu.entity.coupon.CouponLimit;
import com.yizhilu.os.edu.entity.coupon.QueryCoupon;
import com.yizhilu.os.edu.entity.course.Course;

/**
 * Coupon管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CouponDao {

    /**
     * 添加Coupon
     * @param coupon 要添加的Coupon
     * @return id
     */
    public Long addCoupon(Coupon coupon);

    /**
     * 根据id删除一个Coupon
     * @param id 要删除的id
     */
    public void delCouponById(Long id);

    /**
     * 修改Coupon生成编码状态
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon);
    /**
     * 修改Coupon使用数量
     * @param coupon 要修改的Coupon
     */
    public void updateCouponUserNum(Long id);
    /**
     * 修改Coupon支付数量
     * @param coupon 要修改的Coupon
     */
    public void updateCouponPayNum(Long id);

    /**
     * 根据id获取单个Coupon对象
     * @param id 要查询的id
     * @return Coupon
     */
    public Coupon getCouponById(Long id);

    /**
     * 根据条件获取Coupon列表
     * @param coupon 查询条件
     * @return List<Coupon>
     */
    public List<Coupon> getCouponList(Coupon coupon);
    
    /**
     * 根据条件获取CouponPage
     * @param coupon 查询条件
     * @return List<Coupon>
     */
    public List<CouponDTO> getCouponPage(QueryCoupon queryCoupon,PageEntity page);
    /**
     * 优惠券课程限制
     * @param couponLimits
     */
	public void addCouponLimitCourse(List<CouponLimit> couponLimits);

	/**
	 * 查看优惠券
	 * @param id
	 * @return
	 */
	public CouponDTO getCouponDTOById(Long id);

	/**
	 * 优惠券id查找限制课程
	 * @param id
	 * @return
	 */
	public List<Course> getCouponLimitCourseById(Long id);
	
	/**
	 * 根据用户id查询数量
	 * @param optUserName
	 * @return
	 */
	public Long getCountByTeacherId(Long teacherId);
	/**
	 * 根据用户id查询优惠券
	 * @param userId
	 * @return
	 */
	public List<Coupon> getCouponListByTeacherId(Long teacherId);
	/**
	 * 用户获取订单能使用的优惠券
	 * @param loginUserId
	 * @param teaId
	 * @return
	 */
	public List<Coupon> getCouponListByUserForOrder(Long loginUserId, Long teaId);
}