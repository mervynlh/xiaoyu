package com.yizhilu.os.edu.service.audition;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.audition.Audition;

/**
 * 约课记录接口
 * @author yzl268
 *
 */
public interface AuditionService {
	
	/**
	 * 增加约课记录
	 * @param audition
	 */
	public void addAudition(Audition audition);
	
	/**
	 * 根据id删除约课记录
	 * @param audition
	 */
	public void deleteAuditionById(Long id);
	
	/**
	 * 更新状态
	 * @param id id
	 * @param status 状态
	 */
	public void updateStatus(Long id,int status);
	
	/**
	 * 查询约课记录列表
	 * @param audition
	 * @return
	 */
	public List<Audition> getAuditionList(Audition audition,PageEntity page);
	
	/**
	 * 根据id查询约课记录
	 * @param audition
	 * @return
	 */
	public Audition getAuditionById(Long id);
	/**
	 * 根据年级查询约课记录
	 * @param grade
	 * @return
	 */
	public List<Audition> getAuditionByGrade(Long gradeId);
}
