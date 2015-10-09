package com.yizhilu.os.edu.service.impl.major;

import java.util.List;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.magor.MajorDao;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.service.major.MajorService;



@Service("majorService")
public class MajorServiceImpl implements MajorService {
	
	@Autowired
	private MajorDao majorDao;
	MemCache memcache = MemCache.getInstance();
	
	/**
	 * 后台科目列表分页
	 */
	public List<Major> queryMagorListByPgae(Major major) {
	
		return majorDao.queryMagorListByPage(major);
	}

	/**
	 * 修改科目状态  0 正常  1冻结  2删除
	 */
	public void updateMagorById(Major major) {
		
		majorDao.update(major);
		memcache.remove(MemConstans.SEARCH_MAJOR_ALL);
	}


	/**
	 * 添加科目
	 */
	public void addMagor(Major magor) {
		
		majorDao.addMagor(magor);
		memcache.remove(MemConstans.SEARCH_MAJOR_ALL);
	}

	@Override
	public List<Major> queryMajorAllList() {
		List<Major> majorList = (List<Major>) memcache.get(MemConstans.SEARCH_MAJOR_ALL);
		if (ObjectUtils.isNull(majorList)) {
			majorList = majorDao.queryMajorAllList();
			memcache.set(MemConstans.SEARCH_MAJOR_ALL, majorList, MemConstans.SEARCH_MAJOR_ALL_TIME);
		}
		return majorList;
	}
	
	/**
	 * 根据专业ID查询科目列表
	 */
	public List<Major> queryMajorListBySubjectId(Long subjectId) {
		return majorDao.queryMajorListBySubjectId(subjectId);
	}

	@Override
	public Major queryMajorById(Long id) {
		// TODO Auto-generated method stub
		return majorDao.queryMajorById(id);
	}
}
