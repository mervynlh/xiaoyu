package com.yizhilu.os.sysuser.dao;

import java.util.List;

import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;

/**
 * @author :xujunbao
 * @ClassName com.yizhilu.os.sysuser.dao.SubjectDao
 * @description
 * @Create Date : 2014年6月9日 上午10:09:28
 */
public interface SubjectDao {
    /**
     * 根据父id查询专业
     *
     * @param parentId
     * @return
     */
    public List<Subject> getSubjectList(int parentId);

    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public Long addOneSubject(Subject subject);

    /**
     * 根据Pid返回SubjectList
     */
    public List<Subject> getSubjectList(QuerySubject querySubject);

    /**
     * 批量删除除subject
     */
    public void delSubjects(List<Subject> subjectList);

    /**
     * 通过subjectId获得subject
     */
    public Subject getSubjectBySubjectId(Subject subject);

    /**
     * 通过subjectId更新subject
     */
    public void updateSubjectBySubjectId(Subject subject);

    /**
     * 查询所有一级项目
     *
     * @return
     */
    public List<Subject> getSubjectOneList();

    /**
     * 父级ID查询子项目
     *
     * @return
     */
    public List<Subject> getSubjectListByOne(Long subjectId);

    /**
     * 查询所有二级项目
     *
     * @return
     */
    public List<Subject> getSubjectTwoList();

}