package com.yizhilu.os.edu.service.impl.letter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.web.LetterConstans;
import com.yizhilu.os.edu.dao.letter.MsgReceiveDao;
import com.yizhilu.os.edu.dao.letter.MsgSystemDao;
import com.yizhilu.os.edu.dao.user.UserExpandDao;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.letter.MsgSystem;
import com.yizhilu.os.edu.entity.letter.QueryMsgReceive;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.letter.MsgSenderService;
import com.yizhilu.os.edu.service.letter.MsgSystemService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * @author xiaokun
 * @ClassName msgReceiveServiceImpl
 * @package com.yizhilu.open.sns.service.impl.letter
 * @description 站内信的实现
 * @Create Date: 2013-12-10 下午4:14:32
 */
@Service("msgReceiveService")
public class MsgReceiveServiceImpl implements MsgReceiveService {
	// logger
	Logger logger = LoggerFactory.getLogger(MsgReceiveServiceImpl.class);
	@Autowired
	private MsgReceiveDao msgReceiveDao;
	@Autowired
	private UserExpandService userExpandService;

	@Autowired
	private MsgSenderService msgSenderService;
	@Autowired
	private UserService userService;

	private MemCache memCache = MemCache.getInstance();// 缓存
	@Autowired
	private MsgSystemDao msgSystemDao;
	@Autowired
	private MsgSystemService msgSystemService;

	/**
	 * 查询站内信收件箱
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @param page
	 *            分页参数
	 * @return List<QueryMsgReceive> 站内信的list
	 * @throws Exception
	 */
	public List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception {
		// 查询用户信息
		UserExpandDto userExpandDto = userExpandService.getUserExpandByUid(msgReceive.getReceivingCusId());

		Date lastTime = userExpandDto.getLastSystemTime();
		// 查询未读消息
		List<MsgSystem> MSlist = msgSystemService.queryMSListByLT(lastTime);
		if (ObjectUtils.isNotNull(MSlist)) {
			List<MsgReceive> msgrcList = new ArrayList<MsgReceive>();
			// 查出未读的系统消息插入到系统中 更新
			for (MsgSystem mgstm : MSlist) {
				MsgReceive msgReceive1 = new MsgReceive();
				msgReceive1.setContent(mgstm.getContent());
				msgReceive1.setAddTime(new Date());
				msgReceive1.setReceivingCusId(msgReceive.getReceivingCusId());
				msgReceive1.setStatus(LetterConstans.LETTER_STATUS_READ);
				msgReceive1.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
				msgReceive1.setUpdateTime(new Date());
				msgReceive1.setShowname(userExpandDto.getMyName());
				msgReceive1.setCusId(0L);
				if (userExpandDto.getUserType() == 1 && mgstm.getType() == 1) {
					continue;
				}
				msgrcList.add(msgReceive1);
			}
			if (ObjectUtils.isNotNull(msgrcList) || msgrcList.size() > 0){
				// 批量添加站内信
				this.addMsgReceiveBatch(msgrcList);
			}
		}

		List<QueryMsgReceive> queryMsgReceiveList = msgReceiveDao.queryMsgReceiveByInbox(msgReceive, page);
		Map<String, UserExpandDto> map = userExpandService
				.getUserExpandByUids(getMsgReceiveListCusId(queryMsgReceiveList));// 批量查询用户的信息
		if (queryMsgReceiveList != null && queryMsgReceiveList.size() > 0) {
			for (QueryMsgReceive queryMsgReceive : queryMsgReceiveList) {
				UserExpandDto userExpandDto1 = map.get(queryMsgReceive.getCusId() + "");// 查询用户的信息
				if (userExpandDto1 != null) {// 如果能够查到则set 头像信息
					queryMsgReceive.setUserExpandDto(userExpandDto1);
				}
			}
		}
		// 更新所有收件箱为已读
		updateAllReadMsgReceiveInbox(msgReceive);
		// 更新用户的上次统计系统消息时间
		 userExpandService.updateCusForLST(msgReceive.getReceivingCusId(),new Date());
		 //重置用户消息
		 userExpandService.updateUnReadMsgNumReset("msgNum",msgReceive.getReceivingCusId());
		return queryMsgReceiveList;
	}

	/**
	 * 传来的receivingCusId的用户id 给我发送的站内信的历史记录
	 *
	 * @param msgReceive
	 *            站内信实体 传入receivingCusId
	 * @param page
	 *            分页参数
	 * @return List<QueryMsgReceive> 站内信的list
	 * @throws Exception
	 */
	public List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception {
		List<QueryMsgReceive> queryMsgReceiveList = msgReceiveDao.queryMsgReceiveHistory(msgReceive, page);// 传来的receivingCusId的用户id
		Map<String, UserExpandDto> map = userExpandService
				.getUserExpandByUids(getMsgReceiveListCusId(queryMsgReceiveList));// 批量查询用户的信息
		if (queryMsgReceiveList != null && queryMsgReceiveList.size() > 0) {
			for (QueryMsgReceive queryMsgReceive : queryMsgReceiveList) {
				if (queryMsgReceive.getCusId() == msgReceive.getCusId()) {
					UserExpandDto userExpandDto = map.get(queryMsgReceive.getCusId() + "");// 查询用户的信息
					if (userExpandDto != null) {// 如果能够查到则set 头像信息
						queryMsgReceive.setUserExpandDto(userExpandDto);
					}
				} else {
					UserExpandDto userExpandDto = map.get(queryMsgReceive.getCusId() + "");// 查询用户的信息
					if (userExpandDto != null) {// 如果能够查到则set 头像信息
						queryMsgReceive.setUserExpandDto(userExpandDto);
					}
					UserExpandDto userExpandDto2 = userExpandService
							.getUserExpandByUids(queryMsgReceive.getReceivingCusId());
					queryMsgReceive.setShowname(userExpandDto2.getShowname());// set
					// 发件人的用户名
				}
			}
		}
		return queryMsgReceiveList;// 传来的receivingCusId的用户id
		// 给我发送的站内信的历史记录
	}

	/**
	 * 查询站内信发件箱
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @param page
	 *            分页参数
	 * @return List<QueryMsgReceive> 站内信的list
	 * @throws Exception
	 */
	public List<QueryMsgReceive> queryMsgReceiveByOutbox(MsgReceive msgReceive, PageEntity page) throws Exception {
		return msgReceiveDao.queryMsgReceiveByOutbox(msgReceive, page);
	}

	/**
	 * 删除站内信
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @throws Exception
	 */
	public Long delMsgReceive(MsgReceive msgReceive) throws Exception {
		return msgReceiveDao.delMsgReceive(msgReceive);
	}

	/**
	 * 删除站内信过期消息
	 */
	public Long delMsgReceivePast(Date time) throws Exception {
		return msgReceiveDao.delMsgReceivePast(time);
	}

	/**
	 * 删除收件箱
	 *
	 * @param msgReceive
	 *            站内信实体 通过站内信的id
	 * @throws Exception
	 */
	public Long delMsgReceiveInbox(MsgReceive msgReceive) throws Exception {
		return msgReceiveDao.delMsgReceiveInbox(msgReceive);// 更新站内信的状态 删除收件箱
	}

	/**
	 * 更新收件箱所有信为已读
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @throws Exception
	 */
	public void updateAllReadMsgReceiveInbox(MsgReceive msgReceive) throws Exception {
		memCache.remove(MemConstans.MSGRECEIVE_UNREAD + msgReceive.getReceivingCusId());// 清除未读消息缓存
		msgReceiveDao.updateAllReadMsgReceiveInbox(msgReceive);// 更新收件箱所有信为已读
	}

	/**
	 * 更新发件箱所有信为已读
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @throws Exception
	 */
	public void updateAllReadMsgReceiveOutbox(MsgReceive msgReceive) throws Exception {
		memCache.remove(MemConstans.MSGRECEIVE_UNREAD + msgReceive.getCusId());// 清除未读消息缓存
		msgReceiveDao.updateAllReadMsgReceiveOutbox(msgReceive);// 更新发件箱所有信为已读
	}

	/**
	 * 通过站内信的id更新为已读
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @throws Exception
	 */
	public void updateReadMsgReceiveById(MsgReceive msgReceive) throws Exception {
		msgReceiveDao.updateReadMsgReceiveById(msgReceive);
	}

	/**
	 * 查询系统消息
	 *
	 * @param msgReceive
	 *            站内信实体
	 * @param page
	 *            分页参数
	 * @return List<QueryMsgReceive> 站内信的list
	 * @throws Exception
	 */
	public List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception {
		msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);// set
		// type为系统消息
		this.updateAllMsgReceiveReadByType(msgReceive);// 更新消息为已读
		return msgReceiveDao.querysystemInform(msgReceive, page);
	}

	public String getMsgReceiveListCusId(List<QueryMsgReceive> queryMsgReceiveList) {// 获得用户ids
		String ids = "";
		if (queryMsgReceiveList != null && queryMsgReceiveList.size() > 0) {
			for (QueryMsgReceive queryMsgReceive : queryMsgReceiveList) {
				ids += queryMsgReceive.getCusId() + ",";
			}
		}
		return ids;
	}

	/**
	 * 通过站内信的id更新status
	 *
	 * @param status
	 *            msgReceive 的状态id
	 * @param msgReceiveId
	 *            msgReceive的id
	 * @throws Exception
	 */
	public void updateStatusReadMsgReceiveById(int status, Long msgReceiveId, Long receivingCusId) throws Exception {
		MsgReceive msgReceive = new MsgReceive();
		msgReceive.setId(msgReceiveId);// set 消息的id
		msgReceive.setStatus(status);// set状态
		msgReceive.setReceivingCusId(receivingCusId);// 当前登陆人的用户id
		msgReceiveDao.updateStatusReadLetterById(msgReceive);// 更新 消息的专题
	}

	/**
	 * 根据cusId和receivingCusId 更新状态
	 *
	 * @param status
	 *            要更新的状态
	 * @param msgReceive
	 *            传入cusId和receivingCusId
	 * @throws Exception
	 */
	public void updateStatusReadMsgReceiveByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception {
		msgReceiveDao.updateStatusReadLetterByCusIdAndReceivingCusId(status, msgReceive);// 根据cusId和receivingCusId
		// 更新状态
	}

	/**
	 * 发送系统消息
	 *
	 * @param content
	 *            要发送的内容
	 * @param cusId
	 *            用户id
	 * @throws Exception
	 */
	public String addSystemMessageByCusId(String content, Long cusId) throws Exception {

		UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(cusId);

		MsgReceive msgReceive = new MsgReceive();
		msgReceive.setContent(content);// 添加站内信的内容
		msgReceive.setCusId(Long.valueOf(0));
		msgReceive.setReceivingCusId(cusId);// 要发送的用户id
		msgReceive.setStatus(LetterConstans.LETTER_STATUS_UNREAD);// 消息未读状态
		msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);// 系统消息
		msgReceive.setUpdateTime(new Date());// 更新时间s
		msgReceive.setAddTime(new Date());// 添加时间
		if (userExpandDto != null && userExpandDto.getShowname() != null) {// 如果不为空则set
																			// showname
			msgReceive.setShowname(userExpandDto.getShowname());// 会员名
		} else {// 如果为空则set 空字符串
			msgReceive.setShowname("");// 会员名
		}
		try {
			msgReceiveDao.addMsgReceive(msgReceive);
		} catch (Exception e) {
			logger.error("addSystemMessageByCusId---send message is error", e);
		}

		return "success";
	}

	/**
	 * 查询该用户未读消息数量
	 *
	 * @param content
	 *            要发送的内容
	 * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
	 * @throws Exception
	 */
	public Map<String, String> queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) memCache.get(MemConstans.MSGRECEIVE_UNREAD + cusId);// 从缓存中读取
		if (ObjectUtils.isNull(map)) {// 如果为null则从库中读取
			map = new HashMap<String, String>();
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUid(cusId);
			// 未读信息数
			int mNum = userExpandDto.getMsgNum();
			// 上次查询系统消息时间
			Date lastTime = userExpandDto.getLastSystemTime();
			//未读系统消息数
			List<MsgSystem> MSlist = msgSystemService.queryMSListByLT(lastTime);

			if (ObjectUtils.isNotNull(MSlist)) {
				int number = 0;
				for (int i = 0; i < MSlist.size(); i++) {
					if (userExpandDto.getUserType() == 1&&MSlist.get(i).getType() == 1) {
						continue;
					}
					number++;
				}
				map.put("SMNum", mNum + number + "");
			} else {
				map.put("SMNum", mNum + "");
			}
			memCache.set(MemConstans.MSGRECEIVE_UNREAD + cusId, map, MemConstans.MSGRECEIVE_UNREAD_TIME);// 把数据放入缓存中
		}
		return map;// 返回查好的数据
	}

	/**
	 * 更新某种类型的站内信状态为已读
	 *
	 * @param msgReceive
	 *            传入type 传入type和站内信收信人id
	 * @throws Exception
	 */
	public void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception {
		memCache.remove(MemConstans.MSGRECEIVE_UNREAD + msgReceive.getReceivingCusId());// 清除未读消息缓存
		msgReceiveDao.updateAllMsgReceiveReadByType(msgReceive);// 更新消息为已读
	}

	/**
	 * 批量添加消息
	 *
	 * @param msgReceiveList
	 *            消息的list
	 */
	public Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList) {
		return msgReceiveDao.addMsgReceiveBatch(msgReceiveList);
	}

	/**
	 * 清空站内信收件箱
	 */
	public Long delAllOutbox(Long cusId) throws Exception {
		return msgReceiveDao.delAllOutbox(cusId);
	}

	/**
	 * 清空用户系统消息
	 */
	public Long delAllMsgSys(Long cusId) throws Exception {
		return msgReceiveDao.delAllMsgSys(cusId);
	}

	/**
	 * 删除传入的ids
	 */
	public Long delMsgReceiveByids(String ids) throws Exception {
		if(",".equals(ids.substring(ids.length()-1))){
			ids = ids.substring(0, ids.length()-1);
		}
		return msgReceiveDao.delMsgReceiveByids(ids);
	}

	/**
	 * 教师个人中心首页查询未读消息数量
	 */
	public QueryMsgReceive getUnReadMsgReceiveNumByCusId(Long receiveCusId) throws Exception{
		return msgReceiveDao.queryUnReadMsgReceiveNumByCusId(receiveCusId);
	}
	/**
	 * 
	 * @param msg ReceivingCusId Content Type
	 * @return
	 * @throws Exception
	 */
    public String addOtherMessageByCusId(MsgReceive msg) throws Exception {
        MsgReceive msgReceive = new MsgReceive();
        msgReceive.setContent(msg.getContent());// 添加站内信的内容
        msgReceive.setCusId(Long.valueOf(0));
        msgReceive.setReceivingCusId(msg.getReceivingCusId());// 接收消息的用户id
        msgReceive.setStatus(LetterConstans.LETTER_STATUS_UNREAD);// 消息未读状态
        msgReceive.setType(msg.getType());// 消息类型
        msgReceive.setUpdateTime(new Date());// 更新时间s
        msgReceive.setAddTime(new Date());// 添加时间
        msgReceive.setShowname(msg.getShowname());
        try{
        	msgReceiveDao.addMsgReceive(msgReceive);
        	userExpandService.updateUnReadMsgNumAddOne("msgNum",msg.getReceivingCusId());
        }catch(Exception e){
        	logger.error("addOtherMessageByCusId---send message is error", e);
        }
        return "success";
    }
    

}
