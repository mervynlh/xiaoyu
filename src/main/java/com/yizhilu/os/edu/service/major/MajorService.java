package com.yizhilu.os.edu.service.major;

import java.util.List;

import com.yizhilu.os.edu.entity.major.Major;

public interface MajorService {
	/**
	 * 后台科目列表分页
	 */
	public List<Major> queryMagorListByPgae(Major major);
	/**
	 * 修改科目状态  0 正常  1冻结  2删除
	 */
	public void updateMagorById(Major major);
	
	/**
	 * 添加科目
	 */
	public void addMagor(Major major);
	/**
	 * 获取所有科目
	 * @return
	 */
	public List<Major> queryMajorAllList();
	
	/**
	 * 根据专业ID查询科目列表
	 */
	public List<Major> queryMajorListBySubjectId(Long subjectId);
	/**
	 * 根据id查询科目
	 * @param id
	 * @return
	 */
	public Major queryMajorById(Long id);
}
