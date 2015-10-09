package com.yizhilu.os.sysuser.service;

import java.util.List;

import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;

/**
 * @author :xujunbao
 * @ClassName com.yizhilu.os.sysuser.service.SubjectService
 * @description
 * @Create Date : 2014年6月9日 上午10:32:11
 */
public interface SubjectService {
    /**
     * ①单条增加 返回主键(可以不返回)
     *
     * @param subject
     * @return
     */
    public Long addOneSubject(Subject subject);

    /**
     * 根据Pid返回SubjectList
     *
     * @param querySubject
     * @return
     */
    public List<Subject> getSubjectList(QuerySubject querySubject);

    /**
     * 批量删除除subject
     *
     * @param ids
     */
    public void delSubjects(List<Long> ids);

    /**
     * 通过subjectId获得subject
     *
     * @param subject
     * @return
     */
    public Subject getSubjectBySubjectId(Subject subject);

    /**
     * 通过subjectId获得subject
     *
     * @param subject
     */
    public void updateSubjectBySubjectId(Subject subject);

    /**
     * @param querySubject
     * @return List<Subject>
     */
    public List<Subject> getSubjectListByLevel(QuerySubject querySubject);

    /**
     * 获得所有的专业
     * @return List<Subject>
     */
    public List<Subject> getAllSubjectList();


    /**
     * 查询所有一级项目
     *
     * @return
     */
    public List<Subject> getSubjectOneList();

    /**
     * 根据父级ID查找子项目集合
     */
    public List<Subject> getSubjectListByOne(Long subjectId);

    /**
     * 查询所有二级项目
     *
     * @return
     */
    public List<Subject> getSubjectTwoList();
}
