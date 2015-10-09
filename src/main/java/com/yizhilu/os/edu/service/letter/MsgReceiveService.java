package com.yizhilu.os.edu.service.letter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.letter.QueryMsgReceive;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.sns.service.letter.MsgReceiveService
 * @description 站内信的service
 * @Create Date : 2014-1-26 下午1:53:16
 */
public interface MsgReceiveService {
    
    /**
     * 查询站内信收件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 传来的receivingCusId的用户id 给我发送的站内信的历史记录
     *
     * @param msgReceive 站内信实体 传入receivingCusId
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 查询站内信发件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveByOutbox(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 删除站内信
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public Long delMsgReceive(MsgReceive msgReceive) throws Exception;

    /**
     * 删除站内信过期消息
     */
    public Long delMsgReceivePast(Date time) throws Exception;

    /**
     * 删除收件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    public Long delMsgReceiveInbox(MsgReceive msgReceive) throws Exception;

    /**
     * 更新收件箱所有信为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateAllReadMsgReceiveInbox(MsgReceive msgReceive) throws Exception;

    /**
     * 更新发件箱所有信为已读
     *
     * @param letter 站内信实体
     * @throws Exception
     */
    public void updateAllReadMsgReceiveOutbox(MsgReceive msgReceive) throws Exception;

    /**
     * 通过站内信的id更新为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateReadMsgReceiveById(MsgReceive msgReceive) throws Exception;

    /**
     * 查询系统消息
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    public List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 通过站内信的id更新status
     *
     * @param status       msgReceive 的状态id
     * @param msgReceiveId msgReceive的id
     * @throws Exception
     */
    public void updateStatusReadMsgReceiveById(int status, Long msgReceiveId, Long receivingCusId) throws Exception;

    /**
     * 根据cusId和receivingCusId 更新站内信状态
     *
     * @param status 要更新的状态
     * @param letter 传入cusId和receivingCusId
     * @throws Exception
     */
    public void updateStatusReadMsgReceiveByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception;

    /**
     * 发送系统消息
     *
     * @param content 要发送的内容
     * @param cusId   用户id
     * @throws Exception
     */
    public String addSystemMessageByCusId(String content, Long cusId) throws Exception;

    /**
     * 查询该用户未读消息数量
     *
     * @param content 要发送的内容
     * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
     * @throws Exception
     */
    public Map<String, String> queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception;

    /**
     * 更新某种类型的站内信状态为已读
     *
     * @param msgReceive 传入type 传入type和站内信收信人id
     * @throws Exception
     */
    public void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception;

    /**
     * 批量添加消息
     *
     * @param msgReceiveList 消息的list
     */
    public Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList);

    /**
     * 清空站内信收件箱
     */
    public Long delAllOutbox(Long cusId) throws Exception;

    /**
     * 清空用户系统消息
     */
    public Long delAllMsgSys(Long cusId) throws Exception;

    /**
     * 删除传入的ids
     */
    public Long delMsgReceiveByids(String ids) throws Exception;

    /**
     * 教师个人中心首页查询未读消息数量
     */
    public QueryMsgReceive getUnReadMsgReceiveNumByCusId(Long receiveCusId) throws Exception;
    /**
  	 * 
  	 * @param msg ReceivingCusId Content Type
  	 * @return
  	 * @throws Exception
  	 */
      public String addOtherMessageByCusId(MsgReceive msg) throws Exception ;


}
