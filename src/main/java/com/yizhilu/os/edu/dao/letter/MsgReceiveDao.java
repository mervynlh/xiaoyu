package com.yizhilu.os.edu.dao.letter;

import java.util.Date;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.letter.QueryMsgReceive;

/**
 * @author liuqinggang
 * @ClassName LetterDao
 * @package com.yizhilu.open.sns.dao.weibo
 * @description 站内信Dao的接口
 * @Create Date: 2013-12-10 下午4:19:50
 */

public interface MsgReceiveDao {
    /**
     * 添加站内信
     *
     * @param letter 站内信实体
     */
    public Long addMsgReceive(MsgReceive msgReceive);

    /**
     * 通过站内信的id 添加站内信
     *
     * @param msgReceiveList 站内信实体
     */
    public MsgReceive queryMsgReceiveListById(int id);

    /**
     * 查询站内信收件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信收件箱List
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 传来的用户id 给我发送的站内信的历史记录
     *
     * @param msgReceive 站内信实体 传入receivingCusId
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信的list
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 查询站内信发件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信发件箱List
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
     * 更新某种类型的站内信状态为已读
     *
     * @param msgReceive 传入type 传入type和站内信收信人id
     * @throws Exception
     */
    public void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception;

    /**
     * 更新发件箱所有信为已读
     *
     * @param msgReceive 站内信实体
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
     * @return List<QueryMsgReceive> 系统消息 list
     * @throws Exception
     */
    public List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 通过站内信的id更新status
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateStatusReadLetterById(MsgReceive msgReceive) throws Exception;

    /**
     * 根据cusId和receivingCusId 更新
     *
     * @param status       要更新的状态
     * @param msgReceiveId 站内信id
     * @throws Exception
     */
    public void updateStatusReadLetterByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception;

    /**
     * 批量添加消息
     *
     * @param msgReceiveList 消息的list
     */
    public Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList);

    /**
     * 查询该用户未读消息数量
     *
     * @param content 要发送的内容
     * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
     * @throws Exception
     */
    public QueryMsgReceive queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception;

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
}
