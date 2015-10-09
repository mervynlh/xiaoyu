package com.yizhilu.os.edu.service.major;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.major.SubjectMajor;

import java.util.List;
import java.util.Map;

public interface SubjectMajorService {
	
	public List<SubjectMajor> querySubjectMajorByPage(SubjectMajor subjectMajor,PageEntity page);
	
	public void deleteSubjectMajorByIds(String ids);
	
	public void addSubjectMajorBatch(List<SubjectMajor> subjectMajor);
	/**
	 * 根据id查询分类信息
	 * @param id
	 * @return
	 */
	public SubjectMajor querySubjectMajorById(long id);
	/**
	 * 根据id集合查询信息
	 * @param list
	 * @return
	 */
	public Map<Long,SubjectMajor> querySubjectMajorByIds(List<Long> list);

	
	
	/**
	 * 根据专业id查询科目名称
	 * @param list
	 * @return
	 */
	public List<SubjectMajor> querySubjectMajorBySubjectId(Long subjectId);

	/**
	 * 查询关联列表
	 * @param subjectMajor
	 * @return
	 */
	public List<SubjectMajor> querySubjectMajorList(SubjectMajor subjectMajor);
	/**
	 *  根据父专业查询子专业下及科目
	 * @param subjectId
	 * @return
	 */
	public List<SubjectMajor> querySonMajorByParent(Long subjectId,Long teacherId);
}
