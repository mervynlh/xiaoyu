package com.yizhilu.os.edu.service.impl.cashOut;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.cashOut.TeacherExtendCashback;
import com.yizhilu.os.edu.dao.cashOut.TeacherExtendCashbackDao;
import com.yizhilu.os.edu.service.cashOut.TeacherExtendCashbackService;
/**
 * TeacherExtendCashback管理接口
 * User: qinggang.liu
 * Date: 2015-08-15
 */
@Service("teacherExtendCashbackService")
public class TeacherExtendCashbackServiceImpl implements TeacherExtendCashbackService{

 	@Autowired
    private TeacherExtendCashbackDao teacherExtendCashbackDao;
    
    /**
     * 添加TeacherExtendCashback
     * @param teacherExtendCashback 要添加的TeacherExtendCashback
     * @return id
     */
    public java.lang.Long addTeacherExtendCashback(TeacherExtendCashback teacherExtendCashback){
    	return teacherExtendCashbackDao.addTeacherExtendCashback(teacherExtendCashback);
    }
    
    /**
     * 根据条件获取TeacherExtendCashback列表
     * @param teacherExtendCashback 查询条件
     * @return List<TeacherExtendCashback>
     */
    public List<TeacherExtendCashback> getTeacherExtendCashbackList(TeacherExtendCashback teacherExtendCashback, PageEntity page){
    	return teacherExtendCashbackDao.getTeacherExtendCashbackList(teacherExtendCashback, page);
    }

	@Override
	public List<TeacherExtendCashback> queryTeacherCashBackByCondition(
			TeacherExtendCashback teacherExtendCashback, PageEntity page) {
		// TODO Auto-generated method stub
		return teacherExtendCashbackDao.queryTeacherCashBackByCondition(teacherExtendCashback, page);
	}

	@Override
	public Map<String, Object> queryTeacherCashBackToTal(Long teacherId) {
		// TODO Auto-generated method stub
		return teacherExtendCashbackDao.queryTeacherCashBackToTal(teacherId);
	}

}