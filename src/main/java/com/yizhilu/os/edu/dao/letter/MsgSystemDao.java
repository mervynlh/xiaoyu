package com.yizhilu.os.edu.dao.letter;

import java.util.Date;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.letter.MsgSystem;


/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.sns.dao.letter.MsgSenderDao
 * @description 站内信发件箱的Dao
 * @Create Date : 2014-1-26 下午1:58:49
 */
public interface MsgSystemDao {
    /**
     * 添加系统消息
     *
     * @param msgSender
     * @return
     * @throws Exception
     */
    public Long addMsgSystem(MsgSystem msgSystem) throws Exception;

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    public void addMsgSystemBatch(List<MsgSystem> msgSystemList);

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception;

    /**
     * 通过id删除系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public Long delMsgSystemById(Long id) throws Exception;

    /**
     * 查询大于传入的时间的系统系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception;

    /**
     * 更新过期的系统消息的字段为过期
     */
    public void updateMsgSystemPastTime(Date lastTime) throws Exception;
}
