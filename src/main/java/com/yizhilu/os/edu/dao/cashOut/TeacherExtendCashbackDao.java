package com.yizhilu.os.edu.dao.cashOut;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cashOut.TeacherExtendCashback;

/**
 * TeacherExtendCashback管理接口
 * User: qinggang.liu
 * Date: 2015-08-15
 */
public interface TeacherExtendCashbackDao {

    /**
     * 添加TeacherExtendCashback
     * @param teacherExtendCashback 要添加的TeacherExtendCashback
     * @return id
     */
    public java.lang.Long addTeacherExtendCashback(TeacherExtendCashback teacherExtendCashback);

    /**
     * 根据条件获取TeacherExtendCashback列表
     * @param teacherExtendCashback 查询条件
     * @return List<TeacherExtendCashback>
     */
    public List<TeacherExtendCashback> getTeacherExtendCashbackList(TeacherExtendCashback teacherExtendCashback, PageEntity page);
    /**
     * 查询教师推广返现的列表
     * @param teacherId
     * @param page
     * @return
     */
    public List<TeacherExtendCashback> queryTeacherCashBackByCondition(TeacherExtendCashback teacherExtendCashback,PageEntity page);
    /**
     * 查询教师推广返现的总数
     * @param teacherId
     * @return
     */
    public Map<String,Object> queryTeacherCashBackToTal(Long teacherId);
}