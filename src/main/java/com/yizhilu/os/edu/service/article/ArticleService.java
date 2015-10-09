package com.yizhilu.os.edu.service.article;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.article.Article;

/**
 * Article管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface ArticleService {

	/**
	 * 添加Article
	 * 
	 * @param article
	 *            要添加的Article
	 * @return id
	 */
	public java.lang.Long addArticle(Article article);

	/**
	 * 根据id删除一个Article
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteArticleById(Long id);

	/**
	 * 修改Article
	 * 
	 * @param article
	 *            要修改的Article
	 */
	public void updateArticle(Article article);

	/**
	 * 根据id获取单个Article对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Article
	 */
	public Article getArticleById(Long id);

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	public List<Article> getArticleList(Article article);

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	public List<Article> queryArticleListPage(Article article, PageEntity page);

    /**
     * 首页公告
     *
     * @return List<Article>
     */
    public List<Article> queryArticleIndex();

	/**
	 * 根据点击量排行
	 * 
	 * @return List<Article>
	 */
	public List<Article> queryArticleListOrderclickTimes(int num);

	/**
	 * 查询上一篇下一篇
	 * 
	 * @return List<Article>
	 */
	public Article queryArticleUpOrDown(Article article);

	/**
	 * 修改Article
	 * 
	 * @param article
	 *            要修改的Article访问数量
	 */
	public void updateArticleClickTimes(Long id);

	/**
	 * 批量删除资讯
	 * 
	 * @param artIds
	 */
	public void delArticleBatch(String artIds);
}