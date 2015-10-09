package com.yizhilu.os.sysuser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.PropertyUtil;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.Security.PurseSecurityUtils;
import com.yizhilu.os.core.util.web.HttpUtil;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.dao.magor.SubjectMajorDao;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.sysuser.dao.SubjectDao;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * @author :xujunbao
 * @ClassName com.yizhilu.os.sysuser.service.impl.SubjectServiceImpl
 * @description
 * @Create Date : 2014年6月9日 上午10:32:04
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private SubjectMajorDao subjectMajorDao;

    private MemCache memCache = MemCache.getInstance();

    @Override
    public Long addOneSubject(Subject subject) {
        // 添加时，如果上级目录 没有子的项目。需要把父目录以前的考点，添加到本项目下.
        QuerySubject querySubject = new QuerySubject();
        querySubject.setParentId(subject.getParentId());
        // 查找是否有子项目
        List<Subject> list = subjectDao.getSubjectList(querySubject);
        if (list == null || list.size() == 0) {
            // 添加项目
            subjectDao.addOneSubject(subject);
        } else {// 以前已经有子项目直接添加
            subjectDao.addOneSubject(subject);
        }
        memCache.remove(MemConstans.MEM_ALL_SUBECJT);
        memCache.remove(MemConstans.MEM_ALL_SUBECJT+"0");
        memCache.remove(MemConstans.SEARCH_SUBJECT);
        memCache.remove(MemConstans.ALL_SUBJECT_GRADE);
        return 0L;
    }

    // 查询所有项目
    public List<Subject> getSubjectList(QuerySubject querySubject) {
        return subjectDao.getSubjectList(querySubject);
    }

    /**
     * 查询所有一级项目
     *
     * @return
     */
    public List<Subject> getSubjectOneList() {
        List<Subject> subjectList = (List<Subject>)memCache.get(MemConstans.SEARCH_SUBJECT);
        if (ObjectUtils.isNull(subjectList) || subjectList.size() == 0) {
            subjectList = subjectDao.getSubjectOneList();
            memCache.set(MemConstans.SEARCH_SUBJECT, subjectList, MemConstans.SEARCH_SUBJECT_TIME);
        }
        return subjectList;
    }

    /**
     * 根据父级ID查找子项目集合
     */
    public List<Subject> getSubjectListByOne(Long subjectId) {

        return subjectDao.getSubjectListByOne(subjectId);
    }

    /**
     * 根据parentId，parentId查询
     * @param querySubject：parentId，subjectId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Subject> getSubjectListByLevel(QuerySubject querySubject) {
    	 List<Subject> subjectList =null;
    	//查询指定专业的缓存
    	if(StringUtils.isNotEmpty(querySubject.getSubjectIds())){
    		subjectList =(List<Subject>) memCache.get(MemConstans.MEM_ALL_SUBECJT+"IDS");
    	}else{//查询所有专业的缓存
    		subjectList =(List<Subject>) memCache.get(MemConstans.MEM_ALL_SUBECJT);
    	}
        if (ObjectUtils.isNull(subjectList)) {
            //查询专业
        	subjectList = subjectDao.getSubjectList(querySubject);
            //查询专业下的科目集合
            for(Subject sub:subjectList){
            	 SubjectMajor subjectMajor=new SubjectMajor();
                 subjectMajor.setSubjectid(sub.getSubjectId());
                 List<SubjectMajor> majorAllList=subjectMajorDao.querySubjectMajorList(subjectMajor);
                 sub.setMajorList(majorAllList);
            }
            //获取一级专业
            List<Subject> list=new ArrayList<>();
    		for(Subject sub:subjectList){
    			if(sub.getParentId()==0){
    				list.add(sub);
    			}
    		}
    		//获取二级专业
    		for(Subject parentSub:list){
    			List<Subject> childSubjectList=new ArrayList<>();
    			for(Subject sonSub:subjectList){
    				if(sonSub.getParentId().intValue()==parentSub.getSubjectId().intValue()){
    					childSubjectList.add(sonSub);
    				}
    			}
    			parentSub.setChildSubjectList(childSubjectList);
    		}
    		
            if (!ObjectUtils.isNull(list)) {
            	//指定专业的缓存
            	if(StringUtils.isNotEmpty(querySubject.getSubjectIds())){
            		 memCache.set(MemConstans.MEM_ALL_SUBECJT+"IDS", list, MemConstans.MEM_COMMON_TIME);
            	}else{//所有专业的缓存
            		 memCache.set(MemConstans.MEM_ALL_SUBECJT, list, MemConstans.MEM_COMMON_TIME);
            	}
            }
        }
        return subjectList;
    }
    /**
     * 获得所有的专业
     * @return List<Subject>
     */
    public List<Subject> getAllSubjectList(){
        List<Subject> subjectList = (List<Subject>) memCache.get(MemConstans.MEM_ALL_SUBECJT);

        if (ObjectUtils.isNull(subjectList)) {
            subjectList = subjectDao.getSubjectList(new QuerySubject()); //查询出的必须是按父级排序
            if (!ObjectUtils.isNull(subjectList)) {
                Map<String, Subject> parentMap_one = new HashMap<String, Subject>(subjectList.size());
                Map<String, Subject> parentMap_two = new HashMap<String, Subject>(subjectList.size());//二级的
                Map<String, Subject> parentMap_three = new HashMap<String, Subject>(subjectList.size());//三级的

                //第一次循环把parentId=0的筛选出来，并删除掉(生成了1级的)
                //System.out.println("subjectList1111:"+subjectList.size());
                for (int i=0,len=subjectList.size();i<len;i++) {
                    Subject subject=  subjectList.get(i);
                    String thiskey = subject.getSubjectId().toString();
                    if (subject.getParentId().longValue() == 0) {
                        parentMap_one.put(thiskey, subject);
                        subjectList.remove(i);
                        i--;len--;
                    }
                }
                //生成二级的初始list,三级的初始list
                for (Subject subject : subjectList) {
                    String thiskey = subject.getSubjectId().toString();
                    if (subject.getParentId().longValue() == 0) {
                        parentMap_one.put(thiskey, subject);
                    } else {
                        Subject parentSubject = parentMap_one.get( subject.getParentId().toString());
                        if (parentSubject != null) {//代表父级别是1级
                            parentMap_two.put( subject.getSubjectId().toString(), subject);
                        } else {
                            parentMap_three.put(subject.getSubjectId().toString(), subject);
                        }
                    }
                }
                //三级的放到二级的子级中
                for (String key : parentMap_three.keySet()) {
                    Subject subject = parentMap_three.get(key);
                    Subject parentSubject = parentMap_two.get( subject.getParentId().toString());
                    if (parentSubject != null) {
                        List<Subject> childSubjectList = parentSubject.getChildSubjectList();
                        if (ObjectUtils.isNull(childSubjectList)) {
                            childSubjectList = new ArrayList<Subject>();
                        }
                        childSubjectList.add(subject);
                        parentSubject.setChildSubjectList(childSubjectList);
                        parentMap_two.put( parentSubject.getSubjectId().toString(), parentSubject);
                    }
                }
                //二级的放到1级中
                for (String key : parentMap_two.keySet()) {
                    Subject subject = parentMap_two.get(key);
                    Subject parentSubject = parentMap_one.get( subject.getParentId().toString());
                    if (parentSubject != null) {
                        List<Subject> childSubjectList = parentSubject.getChildSubjectList();
                        if (ObjectUtils.isNull(childSubjectList)) {
                            childSubjectList = new ArrayList<Subject>();
                        }
                        childSubjectList.add(subject);
                        parentSubject.setChildSubjectList(childSubjectList);
                        parentMap_one.put("" + parentSubject.getSubjectId(), parentSubject);
                    }
                }
                subjectList.clear();
                subjectList.addAll(parentMap_one.values());
                if (!ObjectUtils.isNull(subjectList)) {
                    memCache.set(MemConstans.MEM_ALL_SUBECJT, subjectList,MemConstans.MEM_ALL_SUBECJT_TIME);
                }
            }
        }
        return subjectList;
    };
    @Override
    public void delSubjects(List<Long> ids) {
        if (ids.size() > 0) {
            List<Subject> subjectList = new ArrayList<Subject>();
            for (Long id : ids) {
                Subject subject = new Subject();
                subject.setSubjectId(id);
                subjectList.add(subject);
                memCache.remove(MemConstans.MEM_SUBECJT+id);
            }
            memCache.remove(MemConstans.MEM_ALL_SUBECJT);
            memCache.remove(MemConstans.MEM_ALL_SUBECJT+"0");
            memCache.remove(MemConstans.SEARCH_SUBJECT);
            memCache.remove(MemConstans.ALL_SUBJECT_GRADE);
            subjectDao.delSubjects(subjectList);

        }
    }

    /**
     * id查询项目
     */
    public Subject getSubjectBySubjectId(Subject subject) {
        return subjectDao.getSubjectBySubjectId(subject);
    }

    @Override
    public void updateSubjectBySubjectId(Subject subject) {
        subjectDao.updateSubjectBySubjectId(subject);
        memCache.remove(MemConstans.MEM_SUBECJT + subject.getSubjectId());
        memCache.remove(MemConstans.MEM_ALL_SUBECJT);
        memCache.remove(MemConstans.MEM_ALL_SUBECJT+"0");
        memCache.remove(MemConstans.ALL_SUBJECT_GRADE);
    }

    /**
     * 查询所有二级项目
     *
     * @return
     */
    public List<Subject> getSubjectTwoList(){
        List<Subject> twoList = (List<Subject>)memCache.get(MemConstans.ALL_SUBJECT_GRADE);
        if (ObjectUtils.isNull(twoList) || twoList.size() == 0) {
            twoList = subjectDao.getSubjectTwoList();
            memCache.set(MemConstans.ALL_SUBJECT_GRADE, twoList, MemConstans.ALL_SUBJECT_GRADE_TIME);
        }
        return twoList;
    }

}
