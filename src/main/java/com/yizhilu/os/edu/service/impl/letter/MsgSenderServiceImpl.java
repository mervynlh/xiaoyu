package com.yizhilu.os.edu.service.impl.letter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.letter.MsgSenderDao;
import com.yizhilu.os.edu.entity.letter.MsgSender;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.letter.MsgSenderService;
import com.yizhilu.os.edu.service.user.UserExpandService;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.sns.service.impl.letter.MsgSenderServiceImpl
 * @description 站内信的发件箱service的实现
 * @Create Date : 2014-1-26 下午1:55:11
 */
@Service("msgSenderService")
public class MsgSenderServiceImpl implements MsgSenderService {

    @Autowired
    private MsgSenderDao msgSendwerDao;
    
    @Autowired
    private UserExpandService userExpandService;
    /**
     * 添加站内信发件箱
     *
     * @param msgSender 站内信实体
     * @throws Exception
     */
    public Long addMsgSender(MsgSender msgSender) throws Exception {
        return msgSendwerDao.addMsgSender(msgSender);
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
        List<MsgSender> msgSenderList = msgSendwerDao.queryMsgSenderByOutbox(msgSender, page);// 查询站内信发件箱

        Map<String, UserExpandDto> map = userExpandService.getUserExpandByUids(getReceivingCusId(msgSenderList));// 查询用户的信息
        if (msgSenderList != null && msgSenderList.size() > 0) {
            for (MsgSender msgSender1 : msgSenderList) {
                UserExpandDto userExpandDto = map.get(msgSender1.getReceivingCusId() + "");// 查询用户的信息
                if (userExpandDto != null) {// 如果能够查到则set 头像信息
                    msgSender1.setUserExpandDto(userExpandDto);
                }
            }
        }
        return msgSenderList;// 查询站内信发件箱
    }

    public String getReceivingCusId(List<MsgSender> list) {// 获得用户ids
        String ids = "";
        if (list != null && list.size() > 0) {
            for (MsgSender msgSender : list) {
                ids += msgSender.getReceivingCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 删除发件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    public Long delLetterOutbox(MsgSender msgSender) throws Exception {
        return msgSendwerDao.delLetterOutbox(msgSender);// 更新站内信的状态 删除发件箱
    }

    /**
     * 删除站内信过期消息
     */
    public Long delMsgSenderPast(Date time) throws Exception {
        return msgSendwerDao.delMsgSenderPast(time);
    }

    /**
     * 清空站内信发件箱
     */
    public Long delAllOutbox(Long cusId) throws Exception {
        return msgSendwerDao.delAllOutbox(cusId);
    }

    /**
     * 删除传入的ids
     */
    public Long delMsgSenderByids(String ids) throws Exception {
        return msgSendwerDao.delMsgSenderByids(ids);
    }
}
