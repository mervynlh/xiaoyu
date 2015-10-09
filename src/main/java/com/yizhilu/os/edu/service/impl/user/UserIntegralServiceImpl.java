package com.yizhilu.os.edu.service.impl.user;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.entity.user.UserIntegral;
import com.yizhilu.os.edu.entity.user.UserIntegralRecord;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;
import com.yizhilu.os.edu.entity.user.UserLevel;
import com.yizhilu.os.edu.constants.enums.IntegralKeyword;
import com.yizhilu.os.edu.dao.user.UserIntegralDao;
import com.yizhilu.os.edu.service.user.UserIntegralRecordService;
import com.yizhilu.os.edu.service.user.UserIntegralService;
import com.yizhilu.os.edu.service.user.UserIntegralTemplateService;
import com.yizhilu.os.edu.service.user.UserLevelService;

/**
 * UserIntegral管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("userIntegralService")
public class UserIntegralServiceImpl implements UserIntegralService {

	@Autowired
	private UserIntegralDao userIntegralDao;
	@Autowired
	private UserLevelService userLevelService;
	@Autowired
	private UserIntegralTemplateService userIntegralTemplateService;
	@Autowired
	private UserIntegralRecordService userIntegralRecordService;

	/**
	 * 添加积分
	 * 
	 * @param keyWord
	 *            关键字
	 * @param userId
	 *            用户id
	 * @param other
	 *            辅助id
	 * @param fromUserId
	 *            来源id
	 * @param otherScore
	 *            其它分数 辅助
	 */
	public boolean addUserIntegral(String keyWord, Long userId, Long other, Long fromUserId, String otherScorel) {
        //获得积分模板
        UserIntegralTemplate userIntegralTemplate=checkScore(userId, keyWord, other, fromUserId);
        if(ObjectUtils.isNotNull(userIntegralTemplate)){//积分模板不为空
            if(StringUtils.isNotEmpty(otherScorel)){//传入分数为主（特殊） 一般积分模板算
                userIntegralTemplate.setScore(new BigDecimal(otherScorel));
            }
            //获得用户积分详情
            UserIntegral userIntegral=this.getUserIntegralByUserId(userId);
            if(ObjectUtils.isNull(userIntegral)){//积分为空 添加用户积分
                userIntegral=new UserIntegral();
                userIntegral.setUserId(userId);//用户id
                userIntegral.setCurrentScore(userIntegralTemplate.getScore().longValue());//当前积分
                userIntegral.setTotalScore(userIntegralTemplate.getScore().longValue());//总积分
                userIntegralDao.addUserIntegral(userIntegral);//添加用户积分
            }else{
                if(userIntegralTemplate.getScore().longValue()>0){//经验值只增加 小于0则不变
                    userIntegral.setTotalScore(userIntegralTemplate.getScore().longValue()+userIntegral.getCurrentScore());
                }
                userIntegral.setCurrentScore(userIntegralTemplate.getScore().longValue()+userIntegral.getCurrentScore());
                userIntegralDao.updateUserIntegral(userIntegral);//进行更新操作
            }
            UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
            userIntegralRecord.setUserId(userId);//用户id
            userIntegralRecord.setScore(userIntegralTemplate.getScore().longValue());//变更分数
            if(ObjectUtils.isNull(userIntegral)){//如果用户积分为null
                userIntegralRecord.setCurrentScore(userIntegralTemplate.getScore().longValue());
            }else{
                userIntegralRecord.setCurrentScore(userIntegral.getCurrentScore());//最新分数
            }
            userIntegralRecord.setFromUserId(fromUserId);//来源id
            userIntegralRecord.setIntegralType(userIntegralTemplate.getId());//积分类型
            userIntegralRecord.setCreateTime(new Date());//添加时间
            userIntegralRecord.setOther(other);
            userIntegralRecord.setDescription(userIntegralTemplate.getName());//描述
            userIntegralRecord.setStatus(0L);
            userIntegralRecord.setAddressId(0L);
            userIntegralRecord.setAddress("");
            
            //添加历史记录
            userIntegralRecordService.addUserIntegralRecord(userIntegralRecord);
            return true;
        }else {
        	return false;
        }
       
	}

	/**
	 * 根据id删除一个UserIntegral
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralById(Long id) {
		userIntegralDao.deleteUserIntegralById(id);
	}

	/**
	 * 修改UserIntegral
	 * 
	 * @param userIntegral
	 *            要修改的UserIntegral
	 */
	public void updateUserIntegral(UserIntegral userIntegral) {
		userIntegralDao.updateUserIntegral(userIntegral);
	}

	/**
	 * 根据id获取单个UserIntegral对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegral
	 */
	public UserIntegral getUserIntegralById(Long id) {
		return userIntegralDao.getUserIntegralById(id);
	}

	/**
	 * 根据条件获取UserIntegral列表
	 * 
	 * @param userIntegral
	 *            查询条件
	 * @return List<UserIntegral>
	 */
	public List<UserIntegral> getUserIntegralList(UserIntegral userIntegral) {
		return userIntegralDao.getUserIntegralList(userIntegral);
	}

	/**
	 * 查詢用戶积分列表分頁
	 * 
	 * @param userIntegral
	 * @param page
	 * @return
	 */
	public List<UserIntegral> getUserIntegralListPage(UserIntegral userIntegral, PageEntity page) {
		return userIntegralDao.getUserIntegralListPage(userIntegral, page);
	}
	/**
	 * 根据用户Id获得积分
	 * 
	 * @param userId
	 * @return
	 */
	public UserIntegral getUserIntegralByUserId(Long userId) {
		return userIntegralDao.getUserIntegralByUserId(userId);
	}
	/**
	 * 获得用户积分和等级
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUserIntegralAndLevel(Long userId) {
		Map<String,Object> map =new HashMap<String,Object>();
		//获得当前用户当前积分和总积分
		UserIntegral userIntegral=getUserIntegralByUserId(userId);
		//获得等级规则
		List<UserLevel> userLevelist=userLevelService.getUserLevelList();
		if(ObjectUtils.isNotNull(userLevelist)&&userLevelist.size()>0&&ObjectUtils.isNotNull(userIntegral)){
			for (int i=0;i<userLevelist.size();i++) {
				//高于最高经验值
				if(userIntegral.getTotalScore().intValue()>userLevelist.get(userLevelist.size()-1).getExp().intValue()){
					map.put("currentExp", userIntegral.getTotalScore());//当前经验
					map.put("nextExp", userLevelist.get(i).getExp());//下级升级经验
					map.put("level", userLevelist.get(i).getLevel());//当前级别
					map.put("levelTitle",userLevelist.get(i).getTitle());//头衔
					map.put("currentIntegral", userIntegral.getCurrentScore());//当前积分
					break;
				}
				//条件 当用户总积分大于等于当前积分规则 并且小于下一级积分规则
				if(userIntegral.getTotalScore().intValue()>=userLevelist.get(i).getExp().intValue()&&userIntegral.getTotalScore().intValue()<userLevelist.get(i+1).getExp().intValue()){
					map.put("currentExp", userIntegral.getTotalScore());//当前经验
					map.put("nextExp", userLevelist.get(i+1).getExp());//下级升级经验
					map.put("level", userLevelist.get(i).getLevel());//当前级别
					map.put("levelTitle",userLevelist.get(i).getTitle());//头衔
					map.put("currentIntegral", userIntegral.getCurrentScore());//当前积分
					break;
				}
			}
		}
		return map;
	}
	/**
	 * 查询积分模板，检查用户操作类型
	 * 
	 * @param userId
	 *            用户id
	 * @param keyWord
	 *            关键字
	 * @param other
	 *            其它id
	 * @param fromUserId
	 *            来源id
	 * @return
	 */
	public UserIntegralTemplate checkScore(Long userId, String keyWord, Long other, Long fromUserId) {
		//获得积分模板
		UserIntegralTemplate userIntegralTemplate=userIntegralTemplateService.getUserIntegralTemplateByKeyword(keyWord);
		//积分模板不存在或者已停用返回null不做操作
		if(ObjectUtils.isNull(userIntegralTemplate)||userIntegralTemplate.getStatus()!=0){
			return null;
		}
		UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
		userIntegralRecord.setUserId(userId);//当前用户
		userIntegralRecord.setIntegralType(userIntegralTemplate.getId());//积分类型
		userIntegralRecord.setOther(other);
		userIntegralRecord.setFromUserId(fromUserId);
		boolean flag=false;
		if(keyWord.equals(IntegralKeyword.login.toString())){//用户登陆操作
			//判断用户是否登陆 登陆返回true
			flag=userIntegralRecordService.getUserScoreByToday(userIntegralRecord);
		}else if(keyWord.equals(IntegralKeyword.sign_in.toString())){
			//判断用户是否签到 登陆返回true
			flag=userIntegralRecordService.getUserScoreByToday(userIntegralRecord);
		}else {
			//判断用户其它操作 如果操作返回true
			flag=userIntegralRecordService.getUserScoreByOther(userIntegralRecord);
		}
		if(flag){
			return null;
		}
		return userIntegralTemplate;
	}
}