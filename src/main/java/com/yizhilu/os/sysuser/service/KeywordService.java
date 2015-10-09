package com.yizhilu.os.sysuser.service;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.sysuser.entity.Keyword;

/**
 * Keyword管理接口 User: qinggang.liu Date: 2014-03-07
 */
public interface KeywordService {

    /**
     * 添加Keyword
     *
     * @param keyword 要添加的Keyword
     * @return id
     */
    public java.lang.Long addKeyword(Keyword keyword);

    /**
     * 根据id删除一个Keyword
     *
     * @param id 要删除的id
     */
    public void deleteKeywordById(Long id);

    /**
     * 修改Keyword
     *
     * @param keyword 要修改的Keyword
     */
    public void updateKeyword(Keyword keyword);

    /**
     * 根据id获取单个Keyword对象
     *
     * @param id 要查询的id
     * @return Keyword
     */
    public Keyword getKeywordById(Long id);

    /**
     * 根据条件获取Keyword列表
     *
     * @param keyword 查询条件
     * @return List<Keyword>
     */
    public List<Keyword> getKeywordList(Keyword keyword, PageEntity page);

    /**
     * 过滤处理
     *
     * @param str
     * @return
     */
    public String doFilter(String str);

    /**
     * 获取全部过滤词
     *
     * @return
     */
    public List<Keyword> getAllKeywords();

}