package com.yizhilu.os.edu.service.impl.coupon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.PropertyUtil;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.Security.PurseSecurityUtils;
import com.yizhilu.os.core.util.web.HttpUtil;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.constants.enums.CouponCodeStatusEnum;
import com.yizhilu.os.edu.dao.coupon.CouponCodeDao;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.coupon.CouponCodeStatus;
import com.yizhilu.os.edu.entity.coupon.QueryCouponCode;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.coupon.CouponService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * CouponCode管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("couponCodeService")
public class CouponCodeServiceImpl implements CouponCodeService {

	@Autowired
	private CouponCodeDao couponCodeDao;
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserService userService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private CourseService courseService;

	public boolean addCouponCode(Long userId, Long couponId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = sdf.format(new Date());
		StringBuffer val = new StringBuffer();

		String code = "1" + getFixString(couponId.intValue(), 4) + getFixString(1, 4) + StringUtils.getRandStr(4);
		val.append("(" + couponId + ",");// 优惠券id
		val.append("1,");// 状态 未使用
		val.append(0 + ",");// 订单id
		val.append("'',"); // 订单号
		val.append(userId + ",");// 使用者
		val.append("'" + code + "',");// code
		val.append("'" + createTime + "')");// 创建时间
		couponCodeDao.addCouponCode(val);// 生成优惠券编码
		Coupon coupon = couponService.getCouponById(couponId);
		couponService.updateCoupon(coupon);
		return true;
	}

	@Override
	public void addCouponCodeString(StringBuffer val) {
		couponCodeDao.addCouponCode(val);// 生成优惠券编码
	}

	/**
	 * 根据id删除一个CouponCode
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCouponCodeById(Long id) {
		couponCodeDao.deleteCouponCodeById(id);
	}

	/**
	 * 修改CouponCode
	 * 
	 * @param couponCode
	 *            要修改的CouponCode
	 */
	public void updateCouponCode(CouponCode couponCode) {
		couponCodeDao.updateCouponCode(couponCode);
	}

	/**
	 * 修改CouponCode支付时间
	 * 
	 * @param couponCode
	 *            要修改的CouponCode
	 */
	public void updateCouponCodePayTime(CouponCode couponCode) {
		couponCodeDao.updateCouponCodePayTime(couponCode);
	}

	/**
	 * 根据id获取单个CouponCode对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CouponCode
	 */
	public CouponCode getCouponCodeById(Long id) {
		return couponCodeDao.getCouponCodeById(id);
	}

	/**
	 * 根据优惠券编码获取单个CouponCode对象
	 * 
	 * @param code
	 *            要查询的code
	 * @return CouponCode
	 */
	public CouponCodeDTO getCouponCodeByCode(String code) {
		return couponCodeDao.getCouponCodeByCode(code);
	}

	/**
	 * 根据条件获取CouponCode列表
	 * 
	 * @param couponCode
	 *            查询条件
	 * @return List<CouponCode>
	 */
	public List<CouponCode> getCouponCodeListByCouponId(Long id) {
		return couponCodeDao.getCouponCodeListByCouponId(id);
	}

	/**
	 * 优惠券id查找优惠券编码
	 */
	public List<String> getStringCodeByCouponId(Long id) {
		return couponCodeDao.getStringCodeByCouponId(id);
	}

	/**
	 * 优惠券编码列表
	 * 
	 * @param queryCouponCode
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page) {
		return couponCodeDao.getCouponCodePage(queryCouponCode, page);
	}

	/**
	 * id查询优惠券编码
	 * 
	 * @param id
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id) {
		return couponCodeDao.getCouponCodeDTO(id);
	}

	/**
	 * 作废优惠券编码
	 * 
	 * @param ids
	 */
	public void wasteCouponCode(String ids) {
		couponCodeDao.wasteCouponCode(ids);
	}

	/**
	 * 作废优惠券下的未使用优惠编码
	 * 
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id) {
		couponCodeDao.wasteCodeByCouponId(id);
	}

	/**
	 * 过期的优惠编码改状态
	 */
	public void overdueCodeByTime() {
		couponCodeDao.overdueCodeByTime();
	}

	/**
	 * 优惠编码使用限制
	 * 
	 * @param couponCodeDTO
	
	 * @return
	 */
	public Map<String, Object> checkCode(CouponCodeDTO couponCodeDTO, Long userId) {
		String resultMsg = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (couponCodeDTO == null) {
			resultMsg = "优惠券不存在";
		}else{
			if(couponCodeDTO.getUserId().longValue()!=userId.longValue()){//判断是否是本人的优惠券
				resultMsg = "您没有该优惠券！";
			}else if (couponCodeDTO.getStatus() == 2) {
				resultMsg = "优惠券已使用";
			} else if (couponCodeDTO.getStatus() == 3) {
				resultMsg = "优惠券已过期";
			} else if (couponCodeDTO.getStatus() == 4) {
				resultMsg = "优惠券已作废";
			} else {
				Date startDate = couponCodeDTO.getStartTime();
				Date endDate = couponCodeDTO.getEndTime();
				Date date = new Date();
				if (startDate.getTime() > date.getTime() || endDate.getTime() < date.getTime()) {
					resultMsg = "优惠券不在使用期限内";
				}else{
					resultMsg = "true";
				}
			}
		}
		if (resultMsg != "") {
			map.put("msg", resultMsg);
			return map;
		}
		return map;
	}


	/**
	 * 使用优惠券，改变状态码
	 */
	@Override
	public void useCouponCode(Long id) {
		this.couponCodeDao.useCouponCode(id);
	}

	@Override
	public List<CouponCodeDTO> getMyCouponList(Long userId, int status, int type,PageEntity page) {
		CouponCodeDTO couponCodeDTO = new CouponCodeDTO();
		couponCodeDTO.setUserId(userId);
		couponCodeDTO.setStatus(status);
		couponCodeDTO.setType(type);
		return this.couponCodeDao.getMyCouponList(couponCodeDTO, page);
	}

	@Override
	public Map<String, Object> getCouponCodeStatus(Long userId) {
		List<CouponCodeStatus> couponCodeStatusList = this.couponCodeDao.getCouponCodeStatus(userId);
		Map<String, Object> map = new HashMap<String, Object>();
 		if (ObjectUtils.isNull(couponCodeStatusList)) {
			map.put("INIT", 0);
			map.put("USED", 0);
			map.put("OVER", 0);
		} else {
			for (int i = 0; i < couponCodeStatusList.size(); i++) {
				map.put(couponCodeStatusList.get(i).getStatus(), couponCodeStatusList.get(i).getCount());
			}
			map.put("INIT", map.containsKey(CouponCodeStatusEnum.INIT) ? map.get(CouponCodeStatusEnum.INIT) : 0);
			map.put("USED", map.containsKey(CouponCodeStatusEnum.USED) ? map.get(CouponCodeStatusEnum.USED) : 0);
			map.put("OVER", map.containsKey(CouponCodeStatusEnum.OVER) ? map.get(CouponCodeStatusEnum.OVER) : 0);
		}
		return map;
	}

	/**
	 * 长度补冲，前面加0
	 *
	 * @param num
	 * @param len
	 * @return String
	 */
	public static String getFixString(int num, int len) {

		String tp = "" + num;
		if (len == 0) {
			return tp;
		}
		if (tp.length() == len)
			return tp;
		if (tp.length() > len)
			return tp.substring(0, len);
		for (int i = 0; i <= len / 4 + 1; i++) {
			tp = "00000" + tp;
		}
		return tp.substring(tp.length() - len);
	}

	@Override
	public CouponCode getCouponCodeByCouponIdAndUserId(Long couponId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("couponId", couponId);
		map.put("userId", userId);
		return this.couponCodeDao.getCouponCodeByCouponIdAndUserId(map);
	}
	/**
	 * 用户获取订单能使用的优惠券
	 * @param loginUserId
	 * @param teaId
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodeListByUserForOrder(Long loginUserId, Long teaId,BigDecimal money){
		return couponCodeDao.getCouponCodeListByUserForOrder(loginUserId,teaId,money);
	}

}
