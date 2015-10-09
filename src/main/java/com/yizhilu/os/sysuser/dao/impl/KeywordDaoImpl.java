package com.yizhilu.os.sysuser.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.sysuser.dao.KeywordDao;
import com.yizhilu.os.sysuser.entity.Keyword;

/**
 * Keyword User: qinggang.liu voo@163.com Date: 2014-03-07
 */
@Repository("keywordDao")
public class KeywordDaoImpl extends GenericDaoImpl implements KeywordDao {

    public java.lang.Long addKeyword(Keyword keyword) {
        return this.insert("KeywordMapper.createKeyword", keyword);
    }

    public void deleteKeywordById(Long id) {
        this.delete("KeywordMapper.deleteKeywordById", id);
    }

    public void updateKeyword(Keyword keyword) {
        this.update("KeywordMapper.updateKeyword", keyword);
    }

    public Keyword getKeywordById(Long id) {
        return this.selectOne("KeywordMapper.getKeywordById", id);
    }

    public List<Keyword> getKeywordList(Keyword keyword) {
        return this.selectList("KeywordMapper.getKeywordList", keyword);
    }

    /**
     * @param keyword
     * @param page
     * @return
     */
    public List<Keyword> getKeywordListPage(Keyword keyword, PageEntity page) {
        return this.queryForListPage("KeywordMapper.getKeywordListPage", keyword, page);
    }
}
