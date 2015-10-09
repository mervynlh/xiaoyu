package com.yizhilu.os.edu.dao.impl.coupon;

import java.util.List;
import com.yizhilu.os.edu.entity.coupon.CouponLimit;
import com.yizhilu.os.edu.dao.coupon.CouponLimitDao;
import org.springframework.stereotype.Repository;
import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * CouponLimit
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("couponLimitDao")
public class CouponLimitDaoImpl extends GenericDaoImpl implements CouponLimitDao{

    public java.lang.Long addCouponLimit(CouponLimit couponLimit) {
        return this.insert("CouponLimitMapper.createCouponLimit",couponLimit);
    }

    public void deleteCouponLimitById(Long id){
        this.delete("CouponLimitMapper.deleteCouponLimitById",id);
    }

    public void updateCouponLimit(CouponLimit couponLimit) {
        this.update("CouponLimitMapper.updateCouponLimit",couponLimit);
    }

    public CouponLimit getCouponLimitById(Long id) {
        return this.selectOne("CouponLimitMapper.getCouponLimitById",id);
    }

    public List<CouponLimit> getCouponLimitList(CouponLimit couponLimit) {
        return this.selectList("CouponLimitMapper.getCouponLimitList",couponLimit);
    }
}
