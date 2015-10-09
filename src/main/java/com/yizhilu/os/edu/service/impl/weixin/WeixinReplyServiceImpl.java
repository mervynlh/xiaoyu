package com.yizhilu.os.edu.service.impl.weixin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.weixin.WeixinReplyDao;
import com.yizhilu.os.edu.entity.order.TrxorderDetailDTO;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserForm;
import com.yizhilu.os.edu.entity.weixin.QueryWeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinBind;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImage;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImageDTO;
import com.yizhilu.os.edu.entity.weixin.WeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinSetReply;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.edu.service.weixin.WeixinBindService;
import com.yizhilu.os.edu.service.weixin.WeixinReplyService;
import com.yizhilu.os.edu.service.weixin.WeixinSetService;



@Service("weixinReplyService")
public class WeixinReplyServiceImpl implements WeixinReplyService {
	@Autowired
    private WeixinReplyDao weixinDao;

	@Autowired
    private WeixinSetService weixinSetService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private WeixinBindService weixinBindService;
	@Autowired
	private UserService userService;
	
	public Long addWeixin(WeixinReply weixin) {
		
		return weixinDao.addWeixin(weixin);
	}

	
	public void updateWeixinReply(WeixinReply weixin) {
		weixinDao.updateWeixinReply(weixin);
		
	}

	
	public void deleteWeixin(Long wxId) {
		
		weixinDao.deleteWeixin(wxId);
	}

	
	public WeixinReply getWeixinById(Long wxId) {
		
		return weixinDao.getWeixinById(wxId);
	}

	
	public List<WeixinReply> getWeixinByMsgType(String msgType) {
		
		return weixinDao.getWeixinByMsgType(msgType);
	}

	
	public List<WeixinReply> getWeixinReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page) {
		
		return weixinDao.getWeixinReplyPageList(queryWeixinReply,page);
	}

	
	public List<WeixinReply> getImageTextReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page) {
		
		return weixinDao.getImageTextReplyPageList(queryWeixinReply, page);
	}

	
	public void addWeixinManyImageText(Long manyImageId,String ids) {
		List<WeixinManyImage> imageTextList=new ArrayList<WeixinManyImage>();
		String[] imageArray=ids.replace(",", " ").trim().split(" ");
		for(int i=0;i<imageArray.length;i++)//从属图文数组
		{
			WeixinManyImage weixinManyImage=new WeixinManyImage();//多图文和从属图文中间表
			weixinManyImage.setManyImageId(manyImageId);//多图文id
			weixinManyImage.setImageId(Long.parseLong(imageArray[i]));//从属图文id
			imageTextList.add(weixinManyImage);
		}
		weixinDao.addWeixinManyImageText(imageTextList);
	}

	
	public void delWeixinManyImageTextByManyId(Long id) {
		weixinDao.delWeixinManyImageTextByManyId(id);
		
	}

	
	public void delWeixinManyImageTextById(Long id) {
		
		weixinDao.delWeixinManyImageTextById(id);
	}

	
	public List<WeixinManyImageDTO> getWeixinManyImageTextByManyId(Long id) {
		
		return weixinDao.getWeixinManyImageTextByManyId(id);
	}

	public List<WeixinReply> getWeixinBywxKeywords(String eventKey) {
		
		return weixinDao.getWeixinBywxKeywords(eventKey);
	}

	/**
	 * 查询子图文
	 * @param id
	 * @return
	 */
	public List<WeixinReply> getWeixinReplyByManyId(Long id){
		return weixinDao.getWeixinReplyByManyId(id);
	}
	/**
	 *关键字回复
	 */
	public WeixinReply WeixinExit(String keyWords,String fromUserName,String toUserName)
	{
		WeixinReply weixinReply=getWeixinByEventKey(keyWords);//根据关键字找到回复
		return weixinReply;
	}
	/**
	 * 查询常规微信回复
	 * @return
	 */
	public WeixinReply getDefaultWeixin(String type)
	{
		WeixinSetReply weixinSetReply=weixinSetService.getWeixinSetReply(type);//查询常规回复设置
		if(weixinSetReply==null)
		{
			return null;
		}
		WeixinReply defaultWeixin=getWeixinById(weixinSetReply.getReplyId());//获得回复素材
		return defaultWeixin;
	}
	/**
	 * 根据关键字精确查找回复
	 * @param eventKey
	 * @return
	 */
	public WeixinReply getWeixinByEventKey(String eventKey)
	{
		List<WeixinReply> weixins=getWeixinBywxKeywords(eventKey);
		for(WeixinReply clickWeixin:weixins)
		{
			String[] keyWords=clickWeixin.getKeyword().split(" ");
			for(String keyWord:keyWords)
			{
				if(keyWord.equals(eventKey))
				{
					return clickWeixin;
				}
			}
		}
		return null;
	}
	/**
	 * 微信绑定用户回复
	 * @param bindMsg 绑定信息
	 * @param fromUserName  绑定微信号
	 * @param toUserName
	 * @return
	 */
	public WeixinReply WeixinBindCus(String bindMsg,String fromUserName,String toUserName)
	{
		String bindResult="";
		bindMsg=bindMsg.replace("#", " ").trim();
		String mobile=bindMsg.split(" ")[0];//待绑定的学员电话或邮箱
		String password=bindMsg.split(" ")[1];//待绑定的用户登录密码
		UserForm userForm=new UserForm();
		userForm.setPassword(password);
		userForm.setUserType(0);
		userForm.setMobile(mobile);
		User user=userService.checkUserValid(userForm);//验证用户有效性
		if(user==null){
			bindResult="绑定失败，输入的信息有误";
		}
		else{
			bindResult=checkWeixinBindCus(fromUserName,user);//查询是否已绑定
		}
		WeixinReply weixinReply=new WeixinReply();
		weixinReply.setMsgType(1);//文本类型
		weixinReply.setContent(bindResult);//结果内容
		return weixinReply;
	}
	
	/**
	 * 判断微信用户是否已绑定,未绑定则进行绑定
	 */
	public String checkWeixinBindCus(String fromUserName,User user)
	{
		String bindResult="";
		WeixinBind weixinBind1=new WeixinBind();
		weixinBind1.setOpenId(fromUserName);//微信Id
		weixinBind1.setUserId(user.getId());//用户Id
		WeixinBind weixinBind=weixinBindService.queryWeixinBind(weixinBind1);
		
		if(weixinBind!=null)//已绑定
		{
			bindResult="您已绑定该用户";
		}else//未绑定，进行绑定
		{
			WeixinBind weixinBind2=new WeixinBind();
			weixinBind2.setOpenId(fromUserName);//openId
			weixinBind=weixinBindService.queryWeixinBind(weixinBind2);//是否已经绑定其他学员
			if(weixinBind!=null){
				bindResult="一个微信号只能绑定一个用户";
			}else{
				weixinBind1.setCreateTime(new Date());
				weixinBindService.createWeixinBind(weixinBind1);
				bindResult="绑定成功";
			}
		}
		return bindResult;
	}
	/**
	 * 微信用户学习记录
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public WeixinReply WeixinStudyRecord(String fromUserName, String toUserName) {
		String bindResult="";
		WeixinBind weixinBind=new WeixinBind();
		weixinBind.setOpenId(fromUserName);//openId
		weixinBind=weixinBindService.queryWeixinBind(weixinBind);
		if(weixinBind==null)//没有绑定用户
		{
			bindResult="您未绑定用户";
		}else{
			//查询学习记录
			bindResult="最近学习情况：\n";
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<List<TrxorderDetailDTO>> studyhistorys=trxorderDetailService.queryMyStudyHistoryList(8l);
			if(ObjectUtils.isNotNull(studyhistorys)){
				for(List<TrxorderDetailDTO> yearStudyhistory : studyhistorys){
					if(ObjectUtils.isNotNull(studyhistorys)){
						for(TrxorderDetailDTO studyhistory:yearStudyhistory){
							bindResult+=studyhistory.getYear()+"共上课"+yearStudyhistory.size()+"节\n"+s.format(studyhistory.getLastUpdateTime())+"&nbsp;已学|"+studyhistory.getTitleName()+"-"+
									studyhistory.getCourseName()+"|"+(studyhistory.getCourseType().intValue()==2?"班课":"一对一")+"|"+studyhistory.getSubjectMajorName()+"|";
							//上课方式处理
							String couseModel="";
							switch (studyhistory.getCourseModel()) {
							case "TEACHERVISIT":
								couseModel="老师上门";
								break;
							case "STUDENTVISIT":
								couseModel="学生上门";
								break;
							case "TALKADDRESS":
								couseModel="协商地点";
								break;
							case "ONLINEOTO":
								couseModel="在线教学";
								break;
							case "ONLINECOU":
								couseModel="在线授课";
								break;
							case "LINEDOWNCOU":
								couseModel="线下面授";
								break;
							default:
								couseModel="其他";
							}
							bindResult+=couseModel;
							
							//地址显示
							if(!studyhistory.getCourseModel().equals("ONLINECOU")&&!studyhistory.getCourseModel().equals("ONLINEOTO")){
								bindResult+="|上课地址："+studyhistory.getStudyAddress();
							}
							bindResult+="|时间："+studyhistory.getStudyTime();
						}
					}
				}
			}else{
				bindResult="暂无学习记录！";
			}
		}
		WeixinReply weixinReply=new WeixinReply();
		weixinReply.setMsgType(1);//文本类型
		weixinReply.setContent(bindResult);//结果内容
		return weixinReply;
	}

	/**
	 * 微信解绑用户回复
	 * @param bindMsg 绑定信息
	 * @param fromUserName  绑定微信号
	 * @param toUserName
	 * @return
	 */
	public WeixinReply WeixinBindCusExit(String fromUserName,String toUserName)
	{
		String bindResult="";
		//查询微信是否绑定用户
		WeixinBind weixinBind1=new WeixinBind();
		weixinBind1.setOpenId(fromUserName);//openId
		WeixinBind weixinBind=weixinBindService.queryWeixinBind(weixinBind1);
		if(weixinBind==null)//未绑定用户
		{
			bindResult="您未绑定用户";
		}else{//已绑定用户，进行解绑操作
			weixinBindService.deleteWeixinBind(weixinBind1);
			bindResult="解绑成功";
		}
		WeixinReply weixinReply=new WeixinReply();
		weixinReply.setMsgType(1);//文本类型
		weixinReply.setContent(bindResult);//结果内容
		return weixinReply;
	}
	
		
}
