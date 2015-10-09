package com.yizhilu.os.edu.dao.audition;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.audition.Audition;

/**
 * 约课记录Dao接口
 * @author yzl268
 *
 */
public interface AuditionDao {
	
	/**
	 * 增加约课记录
	 * @param audition 约课记录信息
	 */
	public void addAudition(Audition audition);
	
	/**
	 * 根据id删除约课记录
	 * @param id 约课记录id
	 */
	public void deleteAuditionById(Long id);
	
	/**
	 * 更新状态
	 * @param audition
	 */
	public void updateAudition(Map<String,Object> map);
	
	/**
	 * 查询约课记录列表
	 * @param audition
	 * @return List<Audition> 约课记录列表
	 */
	public List<Audition> getAuditionList(Audition audition,PageEntity page);
	
	/**
	 * 根据id查询约课记录
	 * @param audition
	 * @return Audition 约课记录
	 */
	public Audition getAuditionById(Long id);

	/**
	 * 根据年级查询约课记录
	 * @param grade 年级id
	 * @return List<Audition> 约课记录列表
	 */
	public List<Audition> getAuditionByGrade(Long gradeId);
}
