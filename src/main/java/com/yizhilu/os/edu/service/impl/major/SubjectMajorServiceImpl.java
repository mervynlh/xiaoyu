package com.yizhilu.os.edu.service.impl.major;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.magor.SubjectMajorDao;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.service.major.SubjectMajorService;

@Service("subjectMajorService")
public class SubjectMajorServiceImpl implements SubjectMajorService{
	
	@Autowired
	private SubjectMajorDao subjectMajorDao;

	@Override
	public List<SubjectMajor> querySubjectMajorByPage(
			SubjectMajor subjectMajor, PageEntity page) {
		return subjectMajorDao.querySubjectMajorByPage(subjectMajor, page);
	}

	@Override
	public void deleteSubjectMajorByIds(String ids) {
		
		 subjectMajorDao.deleteSubejctMajorByIds(ids);
	}

	@Override
	public void addSubjectMajorBatch(List<SubjectMajor> subjectMajor) {
		
		subjectMajorDao.addSubjectMajorBatch(subjectMajor);
		
	}

	@Override
	public SubjectMajor querySubjectMajorById(long id) {
		// TODO Auto-generated method stub
		return subjectMajorDao.querySubjectMajorById(id);
	}

	@Override
	public Map<Long,SubjectMajor> querySubjectMajorByIds(List<Long> list) {
		
		return subjectMajorDao.querySubjectMajorByIds(list);
	}


	@Override
	public List<SubjectMajor> querySubjectMajorBySubjectId(Long subjectId) {

		return subjectMajorDao.querySubejectMajorById(subjectId);
	}


	public List<SubjectMajor> querySubjectMajorList(SubjectMajor subjectMajor) {
		
		return subjectMajorDao.querySubjectMajorList(subjectMajor);
	}

	@Override
	public List<SubjectMajor> querySonMajorByParent(Long subjectId,Long teacherId) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("subjectId", subjectId);
		map.put("teacherId", teacherId);
		return subjectMajorDao.querySonMajorByParent(map);
	}

}
