package com.yizhilu.os.edu.service.impl.audition;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.audition.AuditionDao;
import com.yizhilu.os.edu.entity.audition.Audition;
import com.yizhilu.os.edu.service.audition.AuditionService;

@Service("auditionService")
public class AuditionServiceImpl implements AuditionService{
	
	@Autowired AuditionDao auditionDao;
	

	@Override
	public void addAudition(Audition audition) {
		audition.setCreateTime(new Date());
		audition.setStatus(0L);// 默认未处理
		this.auditionDao.addAudition(audition);
	}
	@Override
	public void deleteAuditionById(Long id) {
		this.auditionDao.deleteAuditionById(id);
	}
	@Override
	public List<Audition> getAuditionList(Audition audition,PageEntity page) {
		return this.auditionDao.getAuditionList(audition,page);
	}
	@Override
	public Audition getAuditionById(Long id) {
		return this.auditionDao.getAuditionById(id);
	}
	@Override
	public List<Audition> getAuditionByGrade(Long gradeId) {
		return this.auditionDao.getAuditionByGrade(gradeId);
	}
	@Override
	public void updateStatus(Long id,int status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("status", status);
		this.auditionDao.updateAudition(map);
	}

}
