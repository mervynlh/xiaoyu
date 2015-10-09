package com.yizhilu.os.edu.controller.cashOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.common.util.FileExportImportUtil;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.cashOut.CashOut;
import com.yizhilu.os.edu.entity.cashOut.CashOutOptRecord;
import com.yizhilu.os.edu.entity.cashOut.TeacherExtendCashback;
import com.yizhilu.os.edu.service.cashOut.CashOutOptRecordService;
import com.yizhilu.os.edu.service.cashOut.CashOutService;
import com.yizhilu.os.edu.service.cashOut.TeacherExtendCashbackService;

/**
 * 后台提现接口管理 AdminCashOutController
 * 
 * @author guozhenzhou
 * @date 2014-09-26
 */
@Controller
@RequestMapping("/admin")
public class AdminCashOutController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminCashOutController.class);

	private static final String cashOutList = getViewPath("/admin/cashout/cashout_list");
	private static final String cashOutInfo = getViewPath("/admin/cashout/cashout_info");
	private static final String cashbackList = getViewPath("/admin/cashout/extend_cashback_list"); // 教师推广返现列表
	@Autowired
	private CashOutService cashOutService;
	@Autowired
	private CashOutOptRecordService cashOutOptRecordService;
	@Autowired
	private TeacherExtendCashbackService teacherExtendCashbackService;
	// 绑定属性 封装参数
	@InitBinder("cashOut")
	public void initCashOut(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("cashOut.");
	}
	@InitBinder("teacherExtendCashback")
	public void initTeacherExtendCashback(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("teacherExtendCashback.");
	}
	/**
	 * 查询提现分页列表
	 *
	 * @param queryTrxorder
	 * @return
	 */
	@RequestMapping("/cashout/cashoutList")
	public ModelAndView queryCashOutListByCondition(HttpServletRequest request, @ModelAttribute("cashOut") CashOut cashOut, @ModelAttribute("page") PageEntity page) {
		ModelAndView model = new ModelAndView();
		model.setViewName(cashOutList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<CashOut> cashoutList = cashOutService.getCashOutList(cashOut, page);
			model.addObject("cashoutList", cashoutList);
			model.addObject("page", this.getPage());
			model.addObject("cashOut", cashOut);
		} catch (Exception e) {
			logger.error("queryCashOutListByCondition.error------", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return model;
	}
	
	/**
	 * 根据ID获得提现详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/cashout/info/{id}")
	public ModelAndView getCashOutInfoById(HttpServletRequest request, @PathVariable("id")Long id){
		ModelAndView model = new ModelAndView();
		model.setViewName(cashOutInfo);
		try {
			if (ObjectUtils.isNotNull(id) && id.intValue() > 0) {
				CashOut cashOut = cashOutService.getCashOutById(id);
				model.addObject("cashOut", cashOut);
			} 
		} catch (Exception e) {
			logger.error("getCashOutInfoById.error------", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return model;
	}
	
	/**
	 * 修改提现状态  1正常  2确认提现  3取消
	 * @param request
	 * @param cashOut
	 * @return
	 */
	@RequestMapping("/cashout/updatestatus")
	@ResponseBody
	public Map<String, Object> updateCashOutStatus(HttpServletRequest request, @ModelAttribute CashOut cashOut){
		try {
			CashOut cash = cashOutService.getCashOutById(cashOut.getId());
			cash.setStatus(cashOut.getStatus());
			CashOutOptRecord cashOutOptRecord = new CashOutOptRecord();
			cashOutOptRecord.setCreatetime(new Date()); // 操作时间
			cashOutOptRecord.setCashoutId(cash.getId()); // 提现记录ID
			cashOutOptRecord.setOptuser(SingletonLoginUtils.getSysUserId(request));
			cashOutOptRecord.setOptusername(this.getSysLoginLoginName(request));
			if (cashOut.getStatus() == 2) {
				cashOutOptRecord.setType("确认提现");
				cashOutOptRecord.setDesc("提现申请编号：" + cash.getId() + " 已提现");
			} else if (cashOut.getStatus() == 3) {
				cashOutOptRecord.setType("取消提现");
				cashOutOptRecord.setDesc("提现申请编号：" + cash.getId() + " 已取消提现");
			}
			cashOutService.updateCashOutStatusById(cash);
			cashOutOptRecordService.createCashOutOptRecord(cashOutOptRecord);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("updateCashOutStatus.error------", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	
	/**
	 * 导出提现列表
	 */
	@RequestMapping("/cashout/export")
	public void exportCashout(@ModelAttribute CashOut cashout, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/excelfile/cashout");
			// 文件名
			String expName = "账户提现_" + DateUtils.getStringDateShort();
			// 表头信息
			String[] headName = { "ID", "用户名", "注册用户手机", "提现方式", "银行", "开户行名称", "银行卡号", "支付宝账号", "提现金额", "提现状态", "添加时间" };
			// 拆分为一万条数据每Excel，防止内存使用太大
			PageEntity page = new PageEntity();
			page.setPageSize(10000);
			cashOutService.getCashOutList(cashout, page);
			int num = page.getTotalPageSize();// 总页数
			List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
			for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
				page.setCurrentPage(i);
				List<CashOut> cashoutList = cashOutService.getCashOutList(cashout, page);
				List<List<String>> list = cashoutJoint(cashoutList);
				File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
				srcfile.add(file);
			}
			FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会员订单excel格式拼接
	 * 
	 * @param MemberOrders
	 * @return
	 */
	public List<List<String>> cashoutJoint(List<CashOut> cashoutList) {
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 0; i < cashoutList.size(); i++) {
			CashOut cashout = cashoutList.get(i);
			List<String> small = new ArrayList<String>();
			small.add(cashout.getId().toString());
			small.add(cashout.getOpenBankPerson());
			if (ObjectUtils.isNull(cashout.getUserMobile())) {
				small.add("");
			} else {
				small.add(cashout.getUserMobile());
			}
			if (cashout.getCashType().equalsIgnoreCase("BANK")) {
				small.add("银行卡");
				small.add(cashout.getBankName());
				small.add(cashout.getOpenBankName());
				small.add(cashout.getBankCard());
				small.add("");
			} else if (cashout.getCashType().equalsIgnoreCase("ALIPAY")) {
				small.add("支付宝");
				small.add("");
				small.add("");
				small.add("");
				small.add(cashout.getAlipayAccount());
			}
			small.add(cashout.getCashoutMoney().toString());
			if (cashout.getStatus() == 1) {
				small.add("未提现");
			} else if (cashout.getStatus() == 2) {
				small.add("已提现");
			} else if (cashout.getStatus() == 3) {
				small.add("取消提现");
			}
			small.add(DateUtil.formatDate(cashout.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
			list.add(small);
		}
		return list;
	}
	
	/**
	 * 根据教师ID查询教师推荐返现列表
	 * @param request
	 * @param teacherId
	 * @param teacherExtendCashback
	 * @param page
	 * @return
	 */
	@RequestMapping("/extend/cashback/list/{teacherId}")
	public String teacherExtendCashbackList(HttpServletRequest request, @PathVariable("teacherId")Long teacherId, 
			@ModelAttribute TeacherExtendCashback teacherExtendCashback, PageEntity page){
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<TeacherExtendCashback> list = teacherExtendCashbackService.getTeacherExtendCashbackList(teacherExtendCashback, this.getPage());
			request.setAttribute("cashbackList", list);
			request.setAttribute("page", page);
			request.setAttribute("teacherId", teacherId);
		} catch (Exception e) {
			logger.error("AdminCashOutController.teacherExtendCashbackList---------error", e);
			return setExceptionRequest(request, e);
		}
		return cashbackList;
	}
	
}
