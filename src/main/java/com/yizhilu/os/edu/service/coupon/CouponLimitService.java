package com.yizhilu.os.edu.service.coupon;

import java.util.List;
import com.yizhilu.os.edu.entity.coupon.CouponLimit;

/**
 * CouponLimit管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CouponLimitService {

    /**
     * 添加CouponLimit
     * @param couponLimit 要添加的CouponLimit
     * @return id
     */
    public java.lang.Long addCouponLimit(CouponLimit couponLimit);

    /**
     * 根据id删除一个CouponLimit
     * @param id 要删除的id
     */
    public void deleteCouponLimitById(Long id);

    /**
     * 修改CouponLimit
     * @param couponLimit 要修改的CouponLimit
     */
    public void updateCouponLimit(CouponLimit couponLimit);

    /**
     * 根据id获取单个CouponLimit对象
     * @param id 要查询的id
     * @return CouponLimit
     */
    public CouponLimit getCouponLimitById(Long id);

    /**
     * 根据条件获取CouponLimit列表
     * @param couponLimit 查询条件
     * @return List<CouponLimit>
     */
    public List<CouponLimit> getCouponLimitList(CouponLimit couponLimit);
}