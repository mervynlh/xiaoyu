package com.yizhilu.os.edu.dao.magor;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.major.SubjectMajor;

public interface SubjectMajorDao {
	
	/**
	 *  专业课程分页
	 */
	
	public  List<SubjectMajor> querySubjectMajorByPage(SubjectMajor subjectMajor,PageEntity page);
	
	/**
	 * 根据Id批量删除专业科目
	 */
	public void deleteSubejctMajorByIds(String ids);
	
	/** 
	 * 批量添加专业科目
	 */
	public void  addSubjectMajorBatch(List<SubjectMajor> subjectMajor);
	
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

	
	public List<SubjectMajor>  querySubejectMajorById(Long subjectId);

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
	public List<SubjectMajor> querySonMajorByParent(Map<String,Object> map);
}
