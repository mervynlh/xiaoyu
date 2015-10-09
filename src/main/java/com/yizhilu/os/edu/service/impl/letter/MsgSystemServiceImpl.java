package com.yizhilu.os.edu.service.impl.letter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.letter.MsgSystemDao;
import com.yizhilu.os.edu.entity.letter.MsgSystem;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.letter.MsgSenderService;
import com.yizhilu.os.edu.service.letter.MsgSystemService;
import com.yizhilu.os.edu.service.user.UserExpandService;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.sns.service.impl.letter.MsgSystemServiceImpl
 * @description 系统消息
 * @Create Date : 2014-1-26 下午1:55:11
 */
@Service("msgSystemService")
public class MsgSystemServiceImpl implements MsgSystemService {

    @Autowired
    private MsgSystemDao msgSystemDao;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private MsgSenderService msgSenderService;
    @Autowired
    private UserExpandService userExpandService;

    public Long addMsgSystem(MsgSystem msgSystem) throws Exception {
        return msgSystemDao.addMsgSystem(msgSystem);
    }

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    public void addMsgSystemBatch(List<MsgSystem> msgSystemList) {
        msgSystemDao.addMsgSystemBatch(msgSystemList);
    }

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception {
        return msgSystemDao.queryMsgSystemList(msgSystem, page);
    }

    /**
     * 通过id删除系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public Long delMsgSystemById(Long id) throws Exception {
        return msgSystemDao.delMsgSystemById(id);
    }

    /**
     * 查询大于传入的时间的系统系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception {
        return msgSystemDao.queryMSListByLT(lastTime);
    }

    /**
     * 检查系统消息过期更新字段 删除过期的站内信
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public void updatePast() throws Exception {
        System.out.println("**********************updatePast***************");
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 180);
        Date endDate = dft.parse(dft.format(date.getTime()));
        msgReceiveService.delMsgReceivePast(endDate);
        msgSenderService.delMsgSenderPast(endDate);
        msgSystemDao.updateMsgSystemPastTime(endDate);
       
    }

    
}
