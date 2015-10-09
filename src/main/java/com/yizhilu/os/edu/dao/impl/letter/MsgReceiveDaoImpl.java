package com.yizhilu.os.edu.dao.impl.letter;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.letter.MsgReceiveDao;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.letter.QueryMsgReceive;

/**
 * @author xiaokun
 * @ClassName msgReceiveDaoImpl
 * @package com.yizhilu.open.sns.dao.impl.weibo
 * @description
 * @Create Date: 2013-12-10 下午4:21:21
 */
@Repository("msgReceiveDao")
public class MsgReceiveDaoImpl extends GenericDaoImpl implements MsgReceiveDao {
    /**
     * 添加站内信
     *
     * @param msgReceive 站内信实体
     */
    public Long addMsgReceive(MsgReceive msgReceive) {
        return this.insert("MsgReceiveMapper.addMsgReceive", msgReceive);// 添加站内信
    }

    /**
     * 通过站内信的id 添加站内信
     *
     * @param msgReceive 站内信实体
     */
    public MsgReceive queryMsgReceiveListById(int id) {
        List<MsgReceive> msgReceiveList = this.selectList("MsgReceiveMapper.queryMsgReceiveListById", id);// 根据站内信id查询站内信
        if (msgReceiveList != null && !msgReceiveList.isEmpty()) {// 判断查出的内容是否为空
            return msgReceiveList.get(0);
        } else {
            return null;
        }

    }

    /**
     * 查询站内信收件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信收件箱List
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception {
        return this.queryForListPage("MsgReceiveMapper.queryMsgReceiveByInbox", msgReceive, page);// 查询站内信收件箱
    }

    /**
     * 传来的receivingCusId的用户id 给我发送的站内信的历史记录
     *
     * @param msgReceive 站内信实体 传入receivingCusId
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信的list
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception {
        return this.queryForListPage("MsgReceiveMapper.queryMsgReceiveHistory", msgReceive, page);// 传来的receivingCusId的用户id
        // 给我发送的站内信的历史记录
    }

    /**
     * 查询站内信发件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信发件箱List
     * @throws Exception
     */
    public List<QueryMsgReceive> queryMsgReceiveByOutbox(MsgReceive msgReceive, PageEntity page) throws Exception {
        return this.queryForListPage("MsgReceiveMapper.queryMsgReceiveByOutbox", msgReceive, page);// 查询站内信发件箱
    }

    /**
     * 删除站内信
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public Long delMsgReceive(MsgReceive msgReceive) throws Exception {
        return this.delete("MsgReceiveMapper.delMsgReceive", msgReceive);// 删除站内信
    }

    /**
     * 删除站内信过期消息
     */
    public Long delMsgReceivePast(Date time) throws Exception {
        return this.delete("MsgReceiveMapper.delMsgReceivePast", time);
    }

    /**
     * 删除收件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    public Long delMsgReceiveInbox(MsgReceive msgReceive) throws Exception {
        return this.delete("MsgReceiveMapper.delMsgReceiveInbox", msgReceive);
    }

    /**
     * 更新收件箱所有信为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateAllReadMsgReceiveInbox(MsgReceive msgReceive) throws Exception {
        this.update("MsgReceiveMapper.updateAllReadMsgReceiveInbox", msgReceive);// 更新收件箱所有信为已读
    }

    /**
     * 更新某种类型的站内信状态为已读
     *
     * @param msgReceive 传入type和站内信收信人id
     * @throws Exception
     */
    public void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception {
        this.update("MsgReceiveMapper.updateAllMsgReceiveReadByType", msgReceive);// 更新某种类型的站内信状态为已读
    }

    /**
     * 更新发件箱所有信为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateAllReadMsgReceiveOutbox(MsgReceive msgReceive) throws Exception {

        this.update("MsgReceiveMapper.updateAllReadMsgReceiveOutbox", msgReceive);// 更新发件箱所有信为已读
    }

    /**
     * 通过站内信的id更新为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateReadMsgReceiveById(MsgReceive msgReceive) throws Exception {
        this.update("MsgReceiveMapper.updateReadMsgReceiveById", msgReceive);// 通过站内信的id更新为已读
    }

    /**
     * 查询系统消息
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 系统消息 list
     * @throws Exception
     */
    public List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception {
        return this.queryForListPage("MsgReceiveMapper.querysystemInform", msgReceive, page);// 查询系统消息
    }

    /**
     * 查询添加好友请求的消息
     *
     * @param msgReceive 传入receiving_cusId
     * @param page       分页参数
     * @return List<QueryMsgReceive>
     * @throws Exception
     */
    public List<QueryMsgReceive> queryFriendMessage(MsgReceive msgReceive, PageEntity page) throws Exception {
        return this.queryForListPage("MsgReceiveMapper.queryFriendMessage", msgReceive, page);// 查询添加好友请求的消息
    }


    /**
     * 通过站内信的id更新status
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    public void updateStatusReadLetterById(MsgReceive msgReceive) throws Exception {
        this.update("MsgReceiveMapper.updateStatusReadLetterById", msgReceive);// 通过站内信的id更新status
    }

    /**
     * 根据cusId和receivingCusId 更新
     *
     * @param status       要更新的状态
     * @param msgReceiveId 站内信id
     * @throws Exception
     */
    public void updateStatusReadLetterByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception {
        this.update("MsgReceiveMapper.updateStatusReadLetterByCusIdAndReceivingCusId", msgReceive);// 根据cusId和receivingCusId
        // 更新
    }

    /**
     * 批量添加消息
     *
     * @param letterList 消息的list
     */
    public Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList) {
        return this.insert("MsgReceiveMapper.addMsgReceiveBatch", msgReceiveList);// 批量添加消息
    }

    /**
     * 查询该用户未读消息数量
     *
     * @param content 要发送的内容
     * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
     * @throws Exception
     */
    public QueryMsgReceive queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception {
        List<QueryMsgReceive> queryMsgReceiveList = this.selectList("MsgReceiveMapper.queryUnReadLetterNumByCusId", cusId);// 查询该用户未读消息数量
        if (queryMsgReceiveList != null && queryMsgReceiveList.size() > 0) {// 如果返回的结果不为空且size大于0
            // 则返回第一条
            return queryMsgReceiveList.get(0);
        } else {// 没有查出数据返回null
            return null;
        }
    }

    /**
     * 清空站内信收件箱
     */
    public Long delAllOutbox(Long cusId) throws Exception {
        return this.insert("MsgReceiveMapper.delAllOutbox", cusId);
    }

    /**
     * 清空用户系统消息
     */
    public Long delAllMsgSys(Long cusId) throws Exception {
        return this.delete("MsgReceiveMapper.delAllMsgSys", cusId);
    }

    /**
     * 删除传入的ids
     */
    public Long delMsgReceiveByids(String ids) throws Exception {
        return this.delete("MsgReceiveMapper.delMsgReceiveByids", ids);
    }
}
