package com.yizhilu.os.sysuser.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.sysuser.dao.KeywordDao;
import com.yizhilu.os.sysuser.entity.Keyword;
import com.yizhilu.os.sysuser.service.KeywordService;

/**
 * Keyword管理接口 User: qinggang.liu Date: 2014-03-07
 */
@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {
    MemCache memCache = MemCache.getInstance();
    @Autowired
    private KeywordDao keywordDao;

    /**
     * 添加Keyword
     *
     * @param keyword 要添加的Keyword
     * @return id
     */
    public java.lang.Long addKeyword(Keyword keyword) {
        List<Keyword> list = keywordDao.getKeywordList(keyword);
        if (ObjectUtils.isNotNull(list)) {
            return 0L;
        }
        return keywordDao.addKeyword(keyword);
    }

    /**
     * 根据id删除一个Keyword
     *
     * @param id 要删除的id
     */
    public void deleteKeywordById(Long id) {
        keywordDao.deleteKeywordById(id);
    }

    /**
     * 修改Keyword
     *
     * @param keyword 要修改的Keyword
     */
    public void updateKeyword(Keyword keyword) {
        keywordDao.updateKeyword(keyword);
    }

    /**
     * 根据id获取单个Keyword对象
     *
     * @param id 要查询的id
     * @return Keyword
     */
    public Keyword getKeywordById(Long id) {
        return keywordDao.getKeywordById(id);
    }

    /**
     * 根据条件获取Keyword列表
     *
     * @param keyword 查询条件
     * @return List<Keyword>
     */
    public List<Keyword> getKeywordList(Keyword keyword, PageEntity page) {
        return keywordDao.getKeywordListPage(keyword, page);
    }

    /**
     * 获取全部过滤词
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Keyword> getAllKeywords() {
        List<Keyword> keywords = (List<Keyword>) memCache.get(MemConstans.MEM_KEYWORD);
        if (ObjectUtils.isNull(keywords)) {
            // 查数据库
            keywords = keywordDao.getKeywordList(null);
            if (ObjectUtils.isNotNull(keywords)) {
                memCache.set(MemConstans.MEM_KEYWORD, keywords, MemConstans.MEM_KEYWORD_TIME);
            }
        }
        return keywords;
    }

    /**
     * 过滤敏感词替换为空
     */
    public String doFilter(String str) {
        try {
            if (StringUtils.isEmpty(str)) {
                return "";
            }
            List<Keyword> keywords = getAllKeywords();
            if (ObjectUtils.isNull(keywords)) {
                return str;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("(");
            for (Keyword keyword : keywords) {
                buffer.append(keyword.getKeyword()).append("|");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append(")");
            Pattern pattern = Pattern.compile(buffer.toString(), Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(str);
            str = m.replaceAll("*");// 替换为*
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }

        return str;

    }
}