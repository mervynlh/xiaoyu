package com.yizhilu.os.edu.dao.impl.order;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.order.TrxorderDetailAssessDao;
import com.yizhilu.os.edu.entity.order.TrxorderDetailAssess;
/**
 * 流水评价Dao实现类
 * @author dingkai
 * @date 2015年8月24日
 */
@Repository("yrxorderDetailAssessDao")
public class TrxorderDetailAssessDaoImpl extends GenericDaoImpl implements TrxorderDetailAssessDao {
	
	@Override
	public void addDetailAssess(TrxorderDetailAssess detailAssess) {
		this.insert("TrxorderDetailAssessMapper.addDetailAssess", detailAssess);
	}

	@Override
	public TrxorderDetailAssess getDetailAssessInfoByDetailId(Long detailId){
		return this.selectOne("TrxorderDetailAssessMapper.getDetailAssessInfoByDetailId", detailId);
	}
}
