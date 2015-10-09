package com.yizhilu.os.edu.dao.impl.audition;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.audition.AuditionDao;
import com.yizhilu.os.edu.entity.audition.Audition;

@Repository("auditionDao")
public class AuditionDaoImpl extends GenericDaoImpl implements AuditionDao {

	@Override
	public void addAudition(Audition audition) {
		this.insert("AuditionMapper.addAudition", audition);
	}
	@Override
	public void deleteAuditionById(Long id) {
		this.delete("AuditionMapper.deleteAuditionById", id);
	}
	@Override
	public List<Audition> getAuditionList(Audition audition,PageEntity page) {
		return this.queryForListPage("AuditionMapper.getAuditionList", audition,page);
	}
	@Override
	public Audition getAuditionById(Long id) {
		return this.selectOne("AuditionMapper.getAuditionById", id);
	}
	@Override
	public List<Audition> getAuditionByGrade(Long gradeId) {
		return this.selectList("AuditionMapper.getAuditionByGrade", gradeId);
	}
	@Override
	public void updateAudition(Map<String,Object> map) {
		this.update("AuditionMapper.updateAudition", map);
	}

}
