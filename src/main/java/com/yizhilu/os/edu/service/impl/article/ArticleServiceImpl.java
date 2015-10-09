package com.yizhilu.os.edu.service.impl.article;

import java.util.List;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.dao.article.ArticleDao;
import com.yizhilu.os.edu.entity.article.Article;
import com.yizhilu.os.edu.service.article.ArticleService;

/**
 * Article管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
    MemCache memCache = MemCache.getInstance();

	/**
	 * 添加Article
	 * 
	 * @param article
	 *            要添加的Article
	 * @return id
	 */
	public java.lang.Long addArticle(Article article) {
		String desc = WebUtils.replaceTagHTML(article.getContent());// 添加简介
		if (StringUtils.isNotEmpty(desc)) {
			desc = StringUtils.getLength(desc, 200);// 截取前200字
			article.setDescription(desc);
		} else {
			article.setDescription("");
		}
		
        memCache.remove(MemConstans.HOME_INDEX_ARTILE);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE_TOP);
		return articleDao.addArticle(article);
	}

	/**
	 * 根据id删除一个Article
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteArticleById(Long id) {
		articleDao.deleteArticleById(id);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE_TOP);
	}

	/**
	 * 修改Article
	 * 
	 * @param article
	 *            要修改的Article
	 */
	public void updateArticle(Article article) {
		String desc = WebUtils.replaceTagHTML(article.getContent());// 添加简介
		if (StringUtils.isNotEmpty(desc)) {
			desc = StringUtils.getLength(desc, 200);// 截取前200字
			article.setDescription(desc);
		} else {
			article.setDescription("");
		}
		// 修改成功清除memCache缓存
		memCache.remove(MemConstans.HOME_INDEX_ARTILE);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE_TOP);
		articleDao.updateArticle(article);
	}

	/**
	 * 根据id获取单个Article对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Article
	 */
	public Article getArticleById(Long id) {
		return articleDao.getArticleById(id);
	}

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	public List<Article> getArticleList(Article article) {
		return articleDao.getArticleList(article);
	}

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	public List<Article> queryArticleListPage(Article article, PageEntity page) {
		return articleDao.queryArticleListPage(article, page);
	}

    /**
     * 首页公告
     *
     * @return List<Article>
     */
    public List<Article> queryArticleIndex(){
        Article article = new Article();
        article.setType(2L);
        PageEntity page = new PageEntity();
        page.setPageSize(6);
        List<Article> list =(List<Article>)memCache.get(MemConstans.HOME_INDEX_ARTILE);
        if(ObjectUtils.isNull(list)){
            list= articleDao.queryArticleListPage(article, page);
            if(ObjectUtils.isNotNull(list)){
                memCache.set(MemConstans.HOME_INDEX_ARTILE,list,MemConstans.HOME_INDEX_ARTILE_TIME);
            }
        }
        return list;
    }
	/**
	 * 根据点击量排行
	 * 
	 * @return List<Article>
	 */
	public List<Article> queryArticleListOrderclickTimes(int num) {
        List<Article> list = (List<Article>)memCache.get(MemConstans.HOME_INDEX_ARTILE_TOP);
        if(ObjectUtils.isNull(list)){
            list=articleDao.queryArticleListOrderclickTimes(num);
            if(ObjectUtils.isNotNull(list)){
                memCache.set(MemConstans.HOME_INDEX_ARTILE_TOP,list,MemConstans.HOME_INDEX_ARTILE_TIME);
            }
        }
		return list;
	}

	/**
	 * 查询上一篇下一篇
	 * 
	 * @return List<Article>
	 */
	public Article queryArticleUpOrDown(Article article) {
		return articleDao.queryArticleUpOrDown(article);
	}

	/**
	 * 修改Article
	 * 
	 * @param id
	 *            要修改的Article访问数量
	 */
	public void updateArticleClickTimes(Long id) {
        memCache.remove(MemConstans.HOME_INDEX_ARTILE);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE_TOP);
        articleDao.updateArticleClickTimes(id);
	}

	/**
	 * 批量删除资讯
	 * 
	 * @param artIds
	 */
	public void delArticleBatch(String artIds) {
		artIds = artIds.substring(0, artIds.length() - 1);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE);
        memCache.remove(MemConstans.HOME_INDEX_ARTILE_TOP);
		articleDao.delArticleBatch(artIds);
	}
}