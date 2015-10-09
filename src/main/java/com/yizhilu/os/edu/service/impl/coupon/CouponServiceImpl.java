package com.yizhilu.os.edu.service.impl.coupon;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.coupon.CouponDTO;
import com.yizhilu.os.edu.entity.coupon.CouponLimit;
import com.yizhilu.os.edu.entity.coupon.QueryCoupon;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.dao.coupon.CouponDao;
import com.yizhilu.os.edu.service.coupon.CouponService;
/**
 * Coupon管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService{

 	@Autowired
    private CouponDao couponDao;
 	
    /**
     * 添加Coupon
     * @param coupon 要添加的Coupon
     */
    public void addCoupon(Coupon coupon){
		// 获取当前用户创建的优惠券数量
		Long l = this.getCountByTeacherId(coupon.getTeacherId());
		// 判断优惠券数量是否超过3个
		if (l < 3) {
			if (ObjectUtils.isNotNull(coupon)) {
				coupon.setCreateTime(new Date());
				couponDao.addCoupon(coupon);// 添加优惠券
			}
		}
    }
    public void addCouponBySys(Coupon coupon){
		// 获取当前用户创建的优惠券数量
			if (ObjectUtils.isNotNull(coupon)) {
				coupon.setCreateTime(new Date());
				couponDao.addCoupon(coupon);// 添加优惠券
			}
    }
    /**
     * 根据id删除一个Coupon
     * @param id 要删除的id
     */
    public void deleteCouponById(Long id){
    	 couponDao.delCouponById(id);
    }

    /**
     * 修改Coupon
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon){
     	couponDao.updateCoupon(coupon);
    }
    /**
     * 修改Coupon使用数量
     * @param coupon 要修改的Coupon
     */
    public void updateCouponUserNum(Long id){
    	couponDao.updateCouponUserNum(id);
    }
    /**
     * 根据id获取单个Coupon对象
     * @param id 要查询的id
     * @return Coupon
     */
    public Coupon getCouponById(Long id){
    	return couponDao.getCouponById( id);
    }

    /**
     * 根据条件获取Coupon列表
     * @param coupon 查询条件
     * @return List<Coupon>
     */
    public List<Coupon> getCouponList(Coupon coupon){
    	return couponDao.getCouponList(coupon);
    }
    /**
     * 优惠券分页列表
     * @param queryCoupon
     * @param page
     * @return
     */
    public List<CouponDTO> getCouponPage(QueryCoupon queryCoupon, PageEntity page){
    	return couponDao.getCouponPage(queryCoupon, page);
    }

	/**
	 * 优惠券课程限制
	 */
	public void addCouponLimitCourse(List<CouponLimit> couponLimits) {
		couponDao.addCouponLimitCourse(couponLimits);
		
	}

	/**
	 * 查看优惠券
	 * @param id
	 * @return
	 */
	public CouponDTO getCouponDTOById(Long id) {
		return couponDao.getCouponDTOById(id);
	}
	/**
	 * 优惠券id查找限制课程
	 * @param id
	 * @return
	 */
	public List<Course> getCouponLimitCourseById(Long id){
		return couponDao.getCouponLimitCourseById(id);
	}

	/**
	 * 根据用户id查询数量
	 */
	@Override
	public Long getCountByTeacherId(Long teacherId) {
		return this.couponDao.getCountByTeacherId(teacherId);
	}

	@Override
	public List<Coupon> getCouponListByTeacherId(Long teacherId) {
		return this.couponDao.getCouponListByTeacherId(teacherId);
	}
	/**
	 * 用户获取订单能使用的优惠券
	 * @param loginUserId
	 * @param teaId
	 * @return
	 */
	public List<Coupon> getCouponListByUserForOrder(Long loginUserId, Long teaId){
		return couponDao.getCouponListByUserForOrder(loginUserId,teaId);
	}
}