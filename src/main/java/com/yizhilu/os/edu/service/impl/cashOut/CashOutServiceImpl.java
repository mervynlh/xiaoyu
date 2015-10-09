package com.yizhilu.os.edu.service.impl.cashOut;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.enums.AccountBizType;
import com.yizhilu.os.edu.constants.enums.AccountHistoryType;
import com.yizhilu.os.edu.constants.enums.AccountType;
import com.yizhilu.os.edu.dao.cashOut.CashOutDao;
import com.yizhilu.os.edu.entity.cashOut.CashOut;
import com.yizhilu.os.edu.entity.cashOut.TeacherExtendCashback;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.service.cashOut.CashOutService;
import com.yizhilu.os.edu.service.cashOut.TeacherExtendCashbackService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserExpandService;
/**
 * CashOutService管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("cashOutService")
public class CashOutServiceImpl implements CashOutService{

	
	private static final Logger logger = LoggerFactory.getLogger(CashOutServiceImpl.class);
	@Autowired
	private CashOutDao cashOutDao;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private TeacherExtendCashbackService teacherExtendCashbackService;

	/**
	 * 添加提现
	 * @throws AccountException 
	 * @throws StaleObjectStateException 
	 */
	public Long createCashOut(CashOut cashOut) throws AccountException, StaleObjectStateException {
		Long row = 0L;
		// 根据用户ID查询用户账号
		UserAccount userAccount = userAccountService.getUserAccountByUserId(cashOut.getUserId());
		if (userAccount.getBalance().compareTo(cashOut.getCashoutMoney()) >= 0) {
			// 冻结提现金额,设置账户余额
			userAccount.setBalance(userAccount.getBalance().subtract(cashOut.getCashoutMoney())); // 账户余额
			userAccount.setForzenAmount(cashOut.getCashoutMoney()); // 冻结金额
			// 更新账户信息
			userAccountService.updateUserAccount(userAccount);
			userAccount = userAccountService.getUserAccountByUserId(cashOut.getUserId());
			// 账户冻结金额
			userAccountService.congeal(userAccount, cashOut.getCashoutMoney(), AccountHistoryType.CASHOUT, cashOut.getUserId(), cashOut.getCreatetime(), true, AccountBizType.CASHOUT);
			// 添加提现记录
			row = cashOutDao.createCashOut(cashOut);
		} else {
			throw new AccountException(AccountException.ACCOUNT_NOT_ENOUGH);// 账户余额不足
		}
		return row;
	}

	/**
	 * 修改提现状态  1正常  2后台确认   3用户取消提现
	 * @throws AccountException 
	 */
	public void updateCashOutStatusById(CashOut cashOut) throws AccountException, StaleObjectStateException {
		// 根据用户ID查询用户账号
		UserAccount userAccount = userAccountService.getUserAccountByUserId(cashOut.getUserId());
		if (cashOut.getStatus() == 2) { // 后台确认提现   冻结金额 - 提现金额
			userAccount.setForzenAmount(userAccount.getForzenAmount().subtract(cashOut.getCashoutMoney())); // 重新设置冻结金额
		} else if (cashOut.getStatus() == 3){ // 用户取消提现
			userAccount.setForzenAmount(userAccount.getForzenAmount().subtract(cashOut.getCashoutMoney())); // 重新设置冻结金额
			userAccount.setBalance(userAccount.getBalance().add(cashOut.getCashoutMoney()));
			// 添加账户操作记录
			userAccountService.clearCongeal(userAccount,cashOut.getCashoutMoney(),AccountHistoryType.REFUND,cashOut.getUserId(),new Date(),true,AccountBizType.CASHOUT);
		}
		userAccount = userAccountService.getUserAccountByUserId(cashOut.getUserId());
		// 更新账户信息
		userAccountService.updateUserAccount(userAccount);
		// 更新提现状态
		cashOutDao.updateCashOutStatusById(cashOut);
	}

	/**
	 * 根据ID获取单个提现对象
	 */
	public CashOut getCashOutById(Long id) {
		return cashOutDao.getCashOutById(id);
	}

	/**
	 * 根据条件分页查询提现列表
	 */
	public List<CashOut> getCashOutList(CashOut cashOut, PageEntity page) {
		return cashOutDao.getCashOutList(cashOut, page);
	}

	/**
	 * 教师账户充值
	 * @throws StaleObjectStateException 
	 * @throws AccountException 
	 */
	@Override
	public void updateteacherAccountRecharge(TrxorderDetail trxorderDetail) throws AccountException, StaleObjectStateException {
		// 获得教师信息
		Teacher teacher = teacherService.getTeacherById(trxorderDetail.getTeacherId());
		// 获得教师对应的账户信息
		UserAccount userAccount = userAccountService.getUserAccountByUserId(teacher.getUserId());
		logger.info("---------------教师账户：" + userAccount.getId());
		// 教师账户充值
		BigDecimal trxAmount = trxorderDetail.getCurrentPrice().multiply(new BigDecimal(CommonConstants.TEACHER_INCOME_RATIO));
		logger.info("---------------充值金额：" + trxAmount);
		userAccountService.credit(userAccount, trxAmount.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH, AccountHistoryType.CASHLOAD, teacher.getUserId(), trxorderDetail.getTrxorderId(), trxorderDetail.getRequestId(),
				"", new Date(), "", true, AccountBizType.COURSE);//课程订单
		// 获得学生详细信息
		UserExpand user = userExpandService.getUserExpandByUserId(trxorderDetail.getUserId());
		// 判断该用户是否拥有推广号，若有，给推广者的用户账号充值
		if (ObjectUtils.isNotNull(user.getExtendCord()) && user.getExtendCord().intValue() != 0) {
			BigDecimal trxAmount_1 = trxorderDetail.getCurrentPrice().multiply(new BigDecimal(CommonConstants.TEACHER_EXTEND_STUDENT_RATIO));
			if (user.getExtendCord().intValue() == teacher.getUserId().intValue()) {
				userAccount = userAccountService.getUserAccountByUserId(teacher.getUserId());
				userAccountService.credit(userAccount, trxAmount_1.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH, AccountHistoryType.EXTENDRETURN, user.getExtendCord(), trxorderDetail.getTrxorderId(), trxorderDetail.getRequestId(),
						"",  new Date(), "", true, AccountBizType.COURSE);//课程订单
				// 添加教师推广返现记录
				TeacherExtendCashback teacherExtendCashback = new TeacherExtendCashback();
				teacherExtendCashback.setCashbackMoney(trxAmount_1); // 返现金额
				teacherExtendCashback.setTeacherId(teacher.getId()); // 推广教师ID
				teacherExtendCashback.setCreateTime(new Date()); // 添加时间
				teacherExtendCashback.setUserId(trxorderDetail.getUserId()); // 用户ID
				teacherExtendCashbackService.addTeacherExtendCashback(teacherExtendCashback);
			} else {
				UserAccount userAccount_1 = userAccountService.getUserAccountByUserId(user.getExtendCord());
				logger.info("---------------教师账户：" + userAccount_1.getId());
				logger.info("---------------推广返现金额：" + trxAmount_1);
				userAccountService.credit(userAccount_1, trxAmount_1.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH, AccountHistoryType.EXTENDRETURN, user.getExtendCord(), trxorderDetail.getTrxorderId(), trxorderDetail.getRequestId(),
		        		"",  new Date(), "", true, AccountBizType.COURSE);//课程订单
				// 添加教师推广返现记录
				teacher = teacherService.getTeacherByUserId(user.getExtendCord());
				TeacherExtendCashback teacherExtendCashback = new TeacherExtendCashback();
				teacherExtendCashback.setCashbackMoney(trxAmount_1); // 返现金额
				teacherExtendCashback.setTeacherId(teacher.getId()); // 推广教师ID
				teacherExtendCashback.setCreateTime(new Date()); // 添加时间
				teacherExtendCashback.setUserId(trxorderDetail.getUserId()); // 用户ID
				teacherExtendCashbackService.addTeacherExtendCashback(teacherExtendCashback);
			}
		}
		// 获得教师详细信息
		UserExpand user_1 = userExpandService.getUserExpandByUserId(teacher.getUserId());
		// 判断该老师是否其他人推广来的，若是，给推广人账户充值
		if (ObjectUtils.isNotNull(user_1.getExtendCord()) && user_1.getExtendCord().intValue() != 0) {
			UserAccount userAccount_2 = userAccountService.getUserAccountByUserId(user_1.getExtendCord());
			logger.info("---------------教师账户：" + userAccount_2.getId());
			BigDecimal trxAmount_2 = trxorderDetail.getCurrentPrice().multiply(new BigDecimal(CommonConstants.TEACHER_EXTEND_TEACHER_RATIO));
			logger.info("---------------推广返现金额：" + trxAmount_2);
			userAccountService.credit(userAccount_2, trxAmount_2.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH, AccountHistoryType.EXTENDRETURN, user_1.getExtendCord(), trxorderDetail.getTrxorderId(), trxorderDetail.getRequestId(),
	        		"",  new Date(), "", true, AccountBizType.COURSE);//课程订单
			// 添加教师推广返现记录
			teacher = teacherService.getTeacherByUserId(user_1.getExtendCord());
			TeacherExtendCashback teacherExtendCashback = new TeacherExtendCashback();
			teacherExtendCashback.setCashbackMoney(trxAmount_2); // 返现金额
			teacherExtendCashback.setTeacherId(teacher.getId()); // 推广教师ID
			teacherExtendCashback.setCreateTime(new Date()); // 添加时间
			teacherExtendCashback.setUserId(user_1.getId()); // 用户ID
			teacherExtendCashbackService.addTeacherExtendCashback(teacherExtendCashback);
		}
	}
}