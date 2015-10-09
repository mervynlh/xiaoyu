package com.yizhilu.os.edu.dao.impl.coupon;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.coupon.CouponCodeDao;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.coupon.CouponCodeStatus;
import com.yizhilu.os.edu.entity.coupon.CouponDTO;
import com.yizhilu.os.edu.entity.coupon.QueryCouponCode;

/**
 *
 * CouponCode
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("couponCodeDao")
public class CouponCodeDaoImpl extends GenericDaoImpl implements CouponCodeDao{

	/**
     * 批量添加添加CouponCode
     * @param couponCode 要添加的CouponCode
     * @return id
     */
    public void addCouponCode(StringBuffer val){
    	this.insert("CouponCodeMapper.createCouponCode", val.toString());
    }

    public void deleteCouponCodeById(Long id){
        this.delete("CouponCodeMapper.deleteCouponCodeById",id);
    }

    public void updateCouponCode(CouponCode couponCode) {
        this.update("CouponCodeMapper.updateCouponCode",couponCode);
    }
    /**
     * 修改CouponCode支付时间
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCodePayTime(CouponCode couponCode){
    	this.update("CouponCodeMapper.updateCouponCodePayTime",couponCode);
    }
    public CouponCode getCouponCodeById(Long id) {
        return this.selectOne("CouponCodeMapper.getCouponCodeById",id);
    }

    public List<CouponCode> getCouponCodeListByCouponId(Long id) {
        return this.selectList("CouponCodeMapper.getCouponCodeListByCouponId",id);
    }
    /**
     * 优惠券id查找优惠券编码
     */
	public List<String> getStringCodeByCouponId(Long id){
		return this.selectList("CouponCodeMapper.getStringCodeListByCouponId",id);
	}
	
	/**
	 * 优惠券编码列表
	 * @param queryCoupon
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page) {
		return this.queryForListPage("CouponCodeMapper.getCouponCodePage", queryCouponCode, page);
	}
	/**
	 * id查询优惠券编码
	 * @param queryCoupon
	 * @param page
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id){
		return this.selectOne("CouponCodeMapper.getCouponCodeDTO",id);
	}
	/**
     * 根据优惠券编码获取单个CouponCode对象
     * @param it 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code){
    	return this.selectOne("CouponCodeMapper.getCouponCodeDTOByCode",code);
    }
	/**
	 * 作废优惠券编码
	 * @param id
	 */
	public void wasteCouponCode(String ids){
		this.update("CouponCodeMapper.wasteCouponCode", ids);
	}
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id){
		this.update("CouponCodeMapper.wasteCodeByCouponId", id);
	}
	/**
	 * 过期的优惠编码改状态
	 * @param id
	 */
	public void overdueCodeByTime(){
		this.update("CouponCodeMapper.overdueCodeByTime", 0);
	}

	/**
	 * 使用优惠券，修改状态
	 */
	@Override
	public void useCouponCode(Long id) {
		this.update("CouponCodeMapper.useCouponCode", id);
	}

	@Override
	public List<CouponCodeDTO> getMyCouponList(CouponCodeDTO couponCodeDTO,PageEntity page) {
		return this.queryForListPage("CouponCodeMapper.getMyCouponList",couponCodeDTO,page);
	}

	@Override
	public List<CouponCodeStatus> getCouponCodeStatus(Long userId) {
		return this.selectList("CouponCodeMapper.getCouponCodeStatus", userId);
	}

	@Override
	public CouponCode getCouponCodeByCouponIdAndUserId(Map<String, Object> map) {
		return this.selectOne("CouponCodeMapper.getCouponCodeByCouponIdAndUserId", map);
	}
	/**
	 * 用户获取订单能使用的优惠券
	 * @param loginUserId
	 * @param teaId
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodeListByUserForOrder(Long loginUserId, Long teaId,BigDecimal money){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", loginUserId);
		map.put("teaId", teaId);
		map.put("money", money);
		return this.selectList("CouponCodeMapper.getCouponCodeListByUserForOrder",map);
	}
}
