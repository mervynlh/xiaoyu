package com.yizhilu.os.edu.service.coupon;

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
public interface CouponService {

    /**
     * 添加Coupon
     * @param coupon 要添加的Coupon
     */
    public void addCoupon(Coupon coupon);

    /**
     * 系统添加优惠券
     * @param coupon
     */
    public void addCouponBySys(Coupon coupon);
    /**
     * 根据id删除一个Coupon
     * @param id 要删除的id
     */
    public void deleteCouponById(Long id);

    /**
     * 修改Coupon
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon);
    /**
     * 修改Coupon使用数量
     * @param coupon 要修改的Coupon
     */
    public void updateCouponUserNum(Long id);

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
     * 优惠券分页列表
     * @param queryCoupon
     * @param page
     * @return
     */
    public List<CouponDTO> getCouponPage(QueryCoupon queryCoupon, PageEntity page);
    /**
     * 添加优惠券课程限制
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
}