package com.yizhilu.os.edu.dao.impl.magor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.magor.MajorDao;
import com.yizhilu.os.edu.entity.major.Major;


@Repository("majorDao")
public class MajorDaoImpl extends GenericDaoImpl  implements MajorDao {

	/**
	 * 科目列表分页
	 */
	public List<Major> queryMagorListByPage(Major major) {
		
		return this.selectList("MagorMapper.queryMajorListByPage", major);
	}

	
	/**
	 * 修改科目状态  0 正常  1冻结  2删除
	 */
	public void update(Major major) {
		
		this.update("MagorMapper.updateMajorById",major);
		
	}


	
	public void addMagor(Major major) {

		this.insert("MagorMapper.addMajor", major);
		
	}


	@Override
	public List<Major> queryMajorAllList() {
		// TODO Auto-generated method stub
		return selectList("MagorMapper.queryMajorAllList", null);
	}


	/**
	 * 根据专业ID查询科目列表
	 */
	public List<Major> queryMajorListBySubjectId(Long subjectId) {
		return this.selectList("MagorMapper.queryMajorListBySubjectId", subjectId);
	}


	@Override
	public Major queryMajorById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("MagorMapper.queryMajorById", id);
	}


	
}
