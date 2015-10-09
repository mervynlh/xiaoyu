package com.yizhilu.os.edu.dao.impl.cashOut;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.cashOut.TeacherExtendCashbackDao;
import com.yizhilu.os.edu.entity.cashOut.TeacherExtendCashback;

/**
 *
 * TeacherExtendCashback
 * User: WangKaiping
 * Date: 2015-08-15
 */
 @Repository("teacherExtendCashbackDao")
public class TeacherExtendCashbackDaoImpl extends GenericDaoImpl implements TeacherExtendCashbackDao{

	 /**
	  * 添加返现记录
	  */
    public java.lang.Long addTeacherExtendCashback(TeacherExtendCashback teacherExtendCashback) {
        return this.insert("TeacherExtendCashbackMapper.createTeacherExtendCashback",teacherExtendCashback);
    }
    
    /**
     * 根据条件分页查询返现记录
     */
    public List<TeacherExtendCashback> getTeacherExtendCashbackList(TeacherExtendCashback teacherExtendCashback, PageEntity page) {
        return this.queryForListPage("TeacherExtendCashbackMapper.getTeacherExtendCashbackList",teacherExtendCashback, page);
    }

	@Override
	public List<TeacherExtendCashback> queryTeacherCashBackByCondition(
			TeacherExtendCashback teacherExtendCashback, PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("TeacherExtendCashbackMapper.queryTeacherCashBackByCondition", teacherExtendCashback,page);
	}

	@Override
	public Map<String, Object> queryTeacherCashBackToTal(Long teacherId) {
		// TODO Auto-generated method stub
		return selectOne("TeacherExtendCashbackMapper.queryTeacherCashBackToTal", teacherId);
	}
}
