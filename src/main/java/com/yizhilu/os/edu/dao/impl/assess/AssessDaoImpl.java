package com.yizhilu.os.edu.dao.impl.assess;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.assess.AssessDao;
import com.yizhilu.os.edu.entity.assess.Assess;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.assess.AssessType;
import com.yizhilu.os.edu.entity.assess.QueryAssess;

/**
 * 
 * @author wangzhuang
 */
@Repository("AssessDao")
public class AssessDaoImpl extends GenericDaoImpl implements AssessDao{

	public java.lang.Long addAssess(Assess assess){
		return this.insert("AssessMapper.addAssess",assess);
	}


	public List<Assess> getAssessListByType(Long type) {
		return this.selectList("AssessMapper.getAssessListByType", type);
	}

	public void deleteAssessById(String ids) {
		this.delete("AssessMapper.deleteAssessById",ids);
	}

	@Override
	public Assess getAssessByOrderId(Long orderId) {
		return this.selectOne("AssessMapper.getAssessByOrderId", orderId);
	}


	@Override
	public List<AssessDto> queryAssessListByCondition(QueryAssess query,
			PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("AssessMapper.queryAssessListByCondition", query, page);
	}


	@Override
	public List<AssessType> getCountByType(Map<String, Object> map) {
		return this.selectList("AssessMapper.getCountByType", map);
	}
	
	@Override
	public List<AssessDto> getAssessListByTypeStatus(Assess Assess, PageEntity page) {
		return this.queryForListPage("AssessMapper.getAssessListByTypeStatus", Assess, page);
	}
}
