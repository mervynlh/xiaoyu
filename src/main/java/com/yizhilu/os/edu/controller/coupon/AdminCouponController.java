package com.yizhilu.os.edu.controller.coupon;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yizhilu.os.core.util.StringUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.common.util.FileExportImportUtil;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.coupon.CouponDTO;
import com.yizhilu.os.edu.entity.coupon.CouponLimit;
import com.yizhilu.os.edu.entity.coupon.QueryCoupon;
import com.yizhilu.os.edu.entity.coupon.QueryCouponCode;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.coupon.CouponService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;
/**
 * Coupon管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class AdminCouponController extends EduBaseController{
	private static final Logger logger = LoggerFactory.getLogger(AdminCouponController.class);
 	@Autowired
    private CouponService couponService;
 	@Autowired
    private SubjectService subjectService;
 	@Autowired
    private CouponCodeService couponCodeService;
	private static final String getcoupons = getViewPath("/admin/coupon/Coupon_list");
	private static final String getcouponsList = getViewPath("/admin/coupon/couponsList");
	
	private static final String addCoupon = getViewPath("/admin/coupon/Coupon_add");
	private static final String couponDetail = getViewPath("/admin/coupon/Coupon_detail");
	private static final String getcouponCodes = getViewPath("/admin/coupon/CouponCode_list");
	private static final String couponCodeDetail = getViewPath("/admin/coupon/CouponCode_detail");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("coupon")
	public void initBinderCoupon(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("coupon.");
	}
	@InitBinder("queryCoupon")
	public void initBinderQueryCoupon(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCoupon.");
	}
	@InitBinder("queryCouponCode")
	public void initBinderQueryCouponCode(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCouponCode.");
	}
	/**
	 * 添加优惠券
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/add")
	public String addCoupon(Coupon coupon,HttpServletRequest request) {
		try {
			if (coupon != null) {
				coupon.setCreateTime(new Date());
				coupon.setOptuserName(this.getSysLoginLoginName(request));
				coupon.setCreateType(0);
				coupon.setTeacherId(0L);
				couponService.addCouponBySys(coupon);//添加优惠券
			}
		} catch (Exception e) {
			logger.error("AdminCouponController.addCoupon--添加优惠券出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/coupon/page";
	}
	
	
	/**
	 * 跳转添加优惠券
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/doadd")
	public ModelAndView doAddCoupon(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addCoupon);
		try {
			//查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
		} catch (Exception e) {
			logger.error("AdminCouponController.doAddCoupon--跳转添加优惠券出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 查看优惠券
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/detail/{id}")
	public ModelAndView couponDetail(@PathVariable Long id,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(couponDetail);
		try {
			CouponDTO couponDTO=couponService.getCouponDTOById(id);
			modelAndView.addObject("couponDTO",couponDTO);
		} catch (Exception e) {
			logger.error("AdminCouponController.couponDetail--查看优惠券出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 查看优惠券ajax
	 */
	@RequestMapping("/ajaxCoupon/detail/{id}")
	@ResponseBody
	public Map<String,Object> ajaxCouponDetail(@PathVariable Long id,HttpServletRequest request) {
		try {
			CouponDTO couponDTO=couponService.getCouponDTOById(id);
			this.setJson(true, "success", couponDTO);
		} catch (Exception e) {
			logger.error("AdminCouponController.couponDetail--查看优惠券出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 优惠券分页列表
	 * @return
	 */
	@RequestMapping("/coupon/page")
	public ModelAndView getCouponPage(QueryCoupon queryCoupon,@ModelAttribute("page") PageEntity page,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getcoupons);
		try{
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<CouponDTO> couponDTOList = couponService.getCouponPage(queryCoupon, page);
			modelAndView.addObject("couponDTOList",couponDTOList);
			modelAndView.addObject("page",this.getPage());
		}catch (Exception e) {
			logger.error("AdminCouponController.getCouponPage--优惠券分页列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	/**
	 * 优惠券分页列表
	 * @return
	 */
	@RequestMapping("/coupon/pagelist")
	public ModelAndView getCouponPageList(QueryCoupon queryCoupon,@ModelAttribute("page") PageEntity page,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getcouponsList);
		try{
			String createType = request.getParameter("createType");
			if(StringUtils.isNotEmpty(createType)&&"0".equals(createType)){
				queryCoupon.setCreateType(0L);
			}
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<CouponDTO> couponDTOList = couponService.getCouponPage(queryCoupon, page);
			modelAndView.addObject("couponDTOList",couponDTOList);
			modelAndView.addObject("page",this.getPage());
		}catch (Exception e) {
			logger.error("AdminCouponController.getCouponPageList--优惠券分页列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	/**
	 * 生成优惠券编码
	 */
	@RequestMapping("/coupon/createcode/{id}")
	public String createCoding(@PathVariable Long id,HttpServletRequest request){
		try{
			//优惠卷ID
			Coupon coupon =couponService.getCouponById(id);
			//生成数量
			Long totalNum =coupon.getTotalNum();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(new Date());
			StringBuffer val = new StringBuffer();
			for (int j = 0; j <totalNum; j++) {
               String code="1"+getFixString(coupon.getId().intValue(),4)+getFixString(j+1,4)+ StringUtils.getRandStr(4);
				//并数据
				val.append("("+coupon.getId()+",");
				val.append("1,");
				val.append(0+",");
				val.append("'',");
				val.append(0+",");
				val.append("'"+code+"',");
				val.append("'"+createTime+"')");
				if(j != totalNum-1){
					val.append(",");
				}
		 	}
			couponCodeService.addCouponCodeString(val);//生成优惠券编码
			couponService.updateCoupon(coupon);//更新优惠券的编码状态为已生成
			
		}catch (Exception e) {
			logger.error("AdminCouponController.createCoding--生成优惠券编码出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/coupon/detail/"+id+"#intro";
	}
	/**
	 * 导出优惠券编码
	 */
	@RequestMapping("/coupon/exportcode")
	public void exportCouponCode(QueryCouponCode queryCouponCode,HttpServletRequest request,HttpServletResponse response) {
		try {
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/excelfile/couponCode");
			//文件名
			String expName="优惠券_"+DateUtils .getStringDateShort();
			// 表头信息
	        String[] headName = { "ID", "优惠券类型","优惠券名称","优惠券编码", "次数限制","使用开始时间","使用截止时间","编码状态","使用限额"};
	        //拆分为一万条数据每Excel，防止内存使用太大
			PageEntity page=new PageEntity();
			page.setPageSize(10000);
			couponCodeService.getCouponCodePage(queryCouponCode, page);
			int num=page.getTotalPageSize();//总页数
			List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
			for(int i=1;i<=num;i++){//循环生成num个xls文件
				page.setCurrentPage(i);
				List<CouponCodeDTO> couponCodes = couponCodeService.getCouponCodePage(queryCouponCode, page);
				List<List<String>> list=couponCodeJoint(couponCodes);
				File file = FileExportImportUtil.createExcel(headName, list, expName+"_"+i,dir);
				srcfile.add(file);
			}
	        FileExportImportUtil.createRar( response,dir, srcfile, expName);//生成的多excel的压缩包
		}catch (Exception e){   
		     e.printStackTrace();   
		}   
	}
	/**
	 * 优惠券excel格式拼接
	 * @param couponCodes
	 * @return
	 */
	public List<List<String>> couponCodeJoint(List<CouponCodeDTO> couponCodes){
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 0; i < couponCodes.size(); i++) {
			CouponCodeDTO couponCodeDTO = couponCodes.get(i);
			List<String> small = new ArrayList<String>();
			small.add(couponCodeDTO.getId().toString());
			if (couponCodeDTO.getType() == 1){
				small.add("折扣券");
			}
			if (couponCodeDTO.getType() == 2){
				small.add("定额券");
			}
			if (couponCodeDTO.getType() == 3){
				small.add("会员定额券");
			}
			small.add(couponCodeDTO.getTitle());
			small.add(couponCodeDTO.getCouponCode());
			small.add(DateUtil.formatDate(couponCodeDTO.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
			small.add(DateUtil.formatDate(couponCodeDTO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
			if (couponCodeDTO.getStatus() == 1){
				small.add("未使用");
			}
			if (couponCodeDTO.getStatus() == 2){
				small.add("已使用");
			}
			if (couponCodeDTO.getStatus() == 3){
				small.add("过期");
			}
			if (couponCodeDTO.getStatus() == 4){
				small.add("作废");
			}
			small.add(couponCodeDTO.getLimitAmount().toString());
			list.add(small);
		}
		return list;
	}
	/**
	 * 查看优惠编码
	 * 
	 * @return
	 */
	@RequestMapping("/couponcode/detail/{id}")
	public ModelAndView couponCodeDetail(@PathVariable Long id,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(couponCodeDetail);
		try {
			CouponCode couponCode=couponCodeService.getCouponCodeById(id);
			CouponDTO couponDTO=couponService.getCouponDTOById(couponCode.getCouponId());
			modelAndView.addObject("couponCode",couponCode);
			modelAndView.addObject("couponDTO",couponDTO);
		} catch (Exception e) {
			logger.error("AdminCouponController.couponCodeDetail--查看优惠券编码出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 优惠券编码分页列表
	 * @return
	 */
	@RequestMapping("/couponcode/page")
	public ModelAndView getCouponCodePage(QueryCouponCode queryCouponCode,@ModelAttribute("page") PageEntity page,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getcouponCodes);
		try{
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<CouponCodeDTO> couponCodeDTOList = couponCodeService.getCouponCodePage(queryCouponCode, page);
			modelAndView.addObject("couponCodeDTOList",couponCodeDTOList);
			modelAndView.addObject("page",this.getPage());
		}catch (Exception e) {
			logger.error("AdminCouponController.getCouponPage--优惠券编码分页列表", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 作废优惠券下的未使用优惠编码
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/wastecoupon/{id}")
	@ResponseBody
	public Map<String,Object> wasteCodeByCouponId(@PathVariable Long id,HttpServletRequest request) {
		try {
			couponCodeService.wasteCodeByCouponId(id);
			this.setJson(true, "true", null);
		} catch (Exception e) {
			logger.error("AdminCouponController.wasteCouponCodel--作废优惠券下的未使用优惠编码", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	/**
	 * 作废优惠编码
	 * 
	 * @return
	 */
	@RequestMapping("/couponcode/waste")
	@ResponseBody
	public Map<String,Object> wasteCouponCode(HttpServletRequest request) {
		try {
			String ids=request.getParameter("ids");
			couponCodeService.wasteCouponCode(ids);
			this.setJson(true, "true", null);
		} catch (Exception e) {
			logger.error("AdminCouponController.wasteCouponCodel--作废优惠券编码出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
    /**
     * 长度补冲，前面加0
     *
     * @param num
     * @param len
     * @return String
     */
    static String getFixString(int num, int len) {

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
}