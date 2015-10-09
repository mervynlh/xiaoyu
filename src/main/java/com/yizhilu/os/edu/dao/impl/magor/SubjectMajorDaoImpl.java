package com.yizhilu.os.edu.dao.impl.magor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.magor.SubjectMajorDao;
import com.yizhilu.os.edu.entity.major.SubjectMajor;

@Repository("SubjectMajorDao")
public class SubjectMajorDaoImpl extends GenericDaoImpl implements SubjectMajorDao {

	@Override
	public List<SubjectMajor> querySubjectMajorByPage(
			SubjectMajor subjectMajor, PageEntity page) {
		return  this.queryForListPage("SubjectMajorMapper.querySubjectMajorByPage", subjectMajor, page);
	}

	@Override
	public void deleteSubejctMajorByIds(String ids) {
	
		this.delete("SubjectMajorMapper.deleteSubjectMajorBatch", ids);
	}

	@Override
	public void addSubjectMajorBatch(List<SubjectMajor> subjectMajor) {
		
		this.insert("SubjectMajorMapper.addSubjectMajorBatch",subjectMajor);
		
	}

	@Override
	public SubjectMajor querySubjectMajorById(long id) {
		// TODO Auto-generated method stub
		return selectOne("SubjectMajorMapper.querySubjectMajorById", id);
	}

	@Override
	public Map<Long,SubjectMajor> querySubjectMajorByIds(List<Long> list) {
		// TODO Auto-generated method stub
		Map<Long, SubjectMajor> result = new HashMap<Long,SubjectMajor>();
		List<SubjectMajor> subjectMajorList= selectList("SubjectMajorMapper.querySubjectMajorByIds", list);
		int i=0;
		for(SubjectMajor s:subjectMajorList){
			result.put(list.get(i), s);
			i++;
		}
		return result;
	}
	


	@Override
	public List<SubjectMajor> querySubejectMajorById(Long subjectId) {

		return this.selectList("SubjectMajorMapper.querySubejctMajorById", subjectId);
	}


	@Override
	public List<SubjectMajor> querySubjectMajorList(SubjectMajor subjectMajor) {
		// TODO Auto-generated method stub
		return selectList("SubjectMajorMapper.querySubjectMajorList", subjectMajor);
	}

	@Override
	public List<SubjectMajor> querySonMajorByParent(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return selectList("SubjectMajorMapper.querySonMajorByParent", map);
	}


}
