package com.yizhilu.os.edu.dao.impl.letter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.letter.MsgSystemDao;
import com.yizhilu.os.edu.entity.letter.MsgSystem;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.sns.dao.impl.letter.MsgSenderDaoImpl
 * @description 站内信发件箱的Dao 实现
 * @Create Date : 2014-1-26 下午2:00:50
 */
@Repository("MsgSystemDao")
public class MsgSystemDaoImpl extends GenericDaoImpl implements MsgSystemDao {
    /**
     * 添加系统消息
     *
     * @param msgSender
     * @return
     * @throws Exception
     */
    public Long addMsgSystem(MsgSystem msgSystem) throws Exception {
        return this.insert("MsgSystemMapper.addMsgSystem", msgSystem);
    }

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    public void addMsgSystemBatch(List<MsgSystem> msgSystemList) {
        this.insert("MsgSystemMapper.addMsgSystemBatch", msgSystemList);
    }

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception {
        return this.queryForListPage("MsgSystemMapper.queryMsgSystemList", msgSystem, page);
    }

    /**
     * 通过id删除系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public Long delMsgSystemById(Long id) throws Exception {
        return this.update("MsgSystemMapper.delMsgSystemById", id);
    }

    /**
     * 查询大于传入的时间的系统系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lastTime", lastTime);
        return this.selectList("MsgSystemMapper.queryMSListByLT", map);
    }

    /**
     * 更新过期的系统消息的字段为过期
     */
    public void updateMsgSystemPastTime(Date lastTime) throws Exception {
        this.update("MsgSystemMapper.updateMsgSystemPastTime", lastTime);
    }
}
