package com.yizhilu.os.edu.dao.impl.letter;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.letter.MsgSenderDao;
import com.yizhilu.os.edu.entity.letter.MsgSender;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.sns.dao.impl.letter.MsgSenderDaoImpl
 * @description 站内信发件箱的Dao 实现
 * @Create Date : 2014-1-26 下午2:00:50
 */
@Repository("msgSenderDao")
public class MsgSenderDaoImpl extends GenericDaoImpl implements MsgSenderDao {
    /**
     * 添加站内信发件箱
     *
     * @param msgSender 站内信实体
     * @throws Exception
     */
    public Long addMsgSender(MsgSender msgSender) throws Exception {
        return this.insert("MsgSenderMapper.addMsgSender", msgSender);// 添加站内信发件箱
    }

    /**
     * 查询站内信发件箱
     *
     * @param MsgSender 站内信发件箱实体
     * @param page      分页参数
     * @return List<QueryLetter> 站内信的list
     * @throws Exception
     */
    public List<MsgSender> queryMsgSenderByOutbox(MsgSender msgSender, PageEntity page) throws Exception {
        return this.queryForListPage("MsgSenderMapper.queryMsgSenderByOutbox", msgSender, page);
    }

    /**
     * 删除发件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    public Long delLetterOutbox(MsgSender msgSender) throws Exception {
        return this.delete("MsgSenderMapper.delLetterOutbox", msgSender);// 删除发件箱
    }

    /**
     * 删除站内信过期消息
     */
    public Long delMsgSenderPast(Date time) throws Exception {
        return this.delete("MsgSenderMapper.delMsgSenderPast", time);// 删除发件箱
    }

    /**
     * 清空站内信发件箱
     */
    public Long delAllOutbox(Long cusId) throws Exception {
        return this.delete("MsgSenderMapper.delAllOutbox", cusId);// 删除发件箱
    }

    /**
     * 删除传入的ids
     */
    public Long delMsgSenderByids(String ids) throws Exception {
        return this.delete("MsgSenderMapper.delMsgSenderByids", ids);
    }
}
