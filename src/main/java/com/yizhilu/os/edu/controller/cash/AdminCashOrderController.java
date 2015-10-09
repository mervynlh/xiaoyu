package com.yizhilu.os.edu.controller.cash;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yizhilu.os.common.util.FileExportImportUtil;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.entity.cash.CashOrderDTO;
import com.yizhilu.os.edu.service.cash.CashOrderService;

/**
 * cashOrder管理接口 User: qinggang.liu Date: 2014-09-26
 */
@Controller
@RequestMapping("/admin")
public class AdminCashOrderController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCashOrderController.class);
	@Autowired
	private CashOrderService cashOrderService;

	private static final String getCashOrders = getViewPath("/admin/cash/cash_order_list");
	private static final String cashOrderDetail = getViewPath("/admin/cash/cash_order_detail");

	// 创建群 绑定变量名字和属性，把参数封装到类


	@InitBinder("cashOrder")
	public void initBinderCashOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cashOrder.");
	}

	@InitBinder("cashOrderDTO")
	public void initBinderCashOrderDTO(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cashOrderDTO.");
	}



	/**
	 * 充值订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/cashOrder/page")
	public String getCashOrders(CashOrderDTO cashOrderDTO, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<CashOrderDTO> cashOrderDTOs = cashOrderService.getCashOrderPage(cashOrderDTO, page);
			request.setAttribute("cashOrderDTOs", cashOrderDTOs);
			request.setAttribute("page", this.getPage());
			
		} catch (Exception e) {
			logger.error("AdminMemberController.getcashOrders--充值订单列表出错", e);
			return setExceptionRequest(request, e);
		}
		return getCashOrders;
	}

	/**
	 * 充值订单详情
	 * 
	 * @return
	 */
	@RequestMapping("/cashOrder/detail/{id}")
	public String getcashOrderDetail(@PathVariable Long id, HttpServletRequest request) {
		try {
			CashOrderDTO cashOrderDTO = cashOrderService.getCashOrderDTOById(id);
			request.setAttribute("cashOrderDTO", cashOrderDTO);
			
		} catch (Exception e) {
			logger.error("AdminMemberController.getcashOrderDetail--充值订单详情出错", e);
			return setExceptionRequest(request, e);
		}
		return cashOrderDetail;
	}
	

	/**
	 * 导出充值订单
	 */
	@RequestMapping("/cashOrder/export")
	public void exportcashOrder(CashOrderDTO cashOrderDTO, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/excelfile/cashOrder");
			// 文件名
			String expName = "充值订单_" + DateUtils.getStringDateShort();
			// 表头信息
			String[] headName = { "ID", "订单编号 ", "用户email", "原始金额", "实付金额", "下单时间", "支付时间", "支付状态", "支付类型 " };
			// 拆分为一万条数据每Excel，防止内存使用太大
			PageEntity page = new PageEntity();
			page.setPageSize(10000);
			cashOrderService.getCashOrderPage(cashOrderDTO, page);
			int num = page.getTotalPageSize();// 总页数
			List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
			for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
				page.setCurrentPage(i);
				List<CashOrderDTO> cashOrderDTOs = cashOrderService.getCashOrderPage(cashOrderDTO, page);
				List<List<String>> list = cashOrderJoint(cashOrderDTOs);
				File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
				srcfile.add(file);
			}
			FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 充值订单excel格式拼接
	 * 
	 * @param cashOrders
	 * @return
	 */
	public List<List<String>> cashOrderJoint(List<CashOrderDTO> cashOrderDTOs) {
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 0; i < cashOrderDTOs.size(); i++) {
			CashOrderDTO cashOrderDTO = cashOrderDTOs.get(i);
			List<String> small = new ArrayList<String>();
			small.add(cashOrderDTO.getId().toString());
			small.add(cashOrderDTO.getRequestId());
			small.add(cashOrderDTO.getEmail());
			small.add(cashOrderDTO.getOrderAmount().toString());
			small.add(cashOrderDTO.getAmount().toString());
			small.add(DateUtil.formatDate(cashOrderDTO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			if (cashOrderDTO.getPayTime() != null) {
				small.add(DateUtil.formatDate(cashOrderDTO.getPayTime(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				small.add("");
			}
			if (cashOrderDTO.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {
				small.add("未支付");
			}
			if (cashOrderDTO.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {
				small.add("已支付");
			}
			small.add("支付宝");
			
			list.add(small);
		}
		return list;
	}

	
}