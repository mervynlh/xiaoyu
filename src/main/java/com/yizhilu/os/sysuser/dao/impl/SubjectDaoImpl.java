package com.yizhilu.os.sysuser.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.sysuser.dao.SubjectDao;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;

/**
 * Subject对象操作实现类
 *
 * @author guoqiang.liu
 */
@Repository("subjectDao")
public class SubjectDaoImpl extends GenericDaoImpl implements SubjectDao {
    /**
     * 根据父id查询专业
     *
     * @param parentId
     * @return
     */
    public List<Subject> getSubjectList(int parentId) {
        return this.selectList("SubjectMapper.getSubjectList", parentId);
    }

    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public Long addOneSubject(Subject subject) {
        return new Long(this.insert("SubjectMapper.createSubject", subject));
    }


    public List<Subject> getSubjectList(QuerySubject querySubject) {
        return this.selectList("SubjectMapper.getSubjectList", querySubject);
    }

    public void delSubjects(List<Subject> subjectList) {
        this.delete("SubjectMapper.deleteSubjectByIdBatch", subjectList);
    }


    public Subject getSubjectBySubjectId(Subject subject) {
        return this.selectOne("SubjectMapper.getSubjectBySubjectId", subject);
    }


    public void updateSubjectBySubjectId(Subject subject) {
        this.update("SubjectMapper.updateSubjectBySubjectId", subject);
    }

    public List<Subject> getSubjectOneList() {

        return this.selectList("SubjectMapper.getSubjectOneList", 0);
    }

    /**
     * 根据父级ID查找子项目集合
     */
    public List<Subject> getSubjectListByOne(Long subjectId) {

        return this.selectList("SubjectMapper.getSubjectListByOne", subjectId);
    }

    /**
     * 查询所有二级项目
     *
     * @return
     */
    public List<Subject> getSubjectTwoList(){
        return this.selectList("SubjectMapper.getSubjectTwoList", 0);
    }
}
