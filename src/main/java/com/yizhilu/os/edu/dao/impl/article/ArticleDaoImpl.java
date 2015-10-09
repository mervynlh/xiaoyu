package com.yizhilu.os.edu.dao.impl.article;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.article.ArticleDao;
import com.yizhilu.os.edu.entity.article.Article;

/**
 * 
 * Article User: qinggang.liu voo@163.com Date: 2014-05-27
 */
@Repository("articleDao")
public class ArticleDaoImpl extends GenericDaoImpl implements ArticleDao {
	/**
	 * 添加Article
	 * 
	 * @param article
	 *            要添加的Article
	 * @return id
	 */
	public java.lang.Long addArticle(Article article) {
	    Date date =new Date();
	    article.setCreateTime(date);
	    article.setUpdateTime(date);
		return this.insert("ArticleMapper.createArticle", article);
	}
	/**
	 * 根据id删除一个Article
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteArticleById(Long id) {
		this.delete("ArticleMapper.deleteArticleById", id);
	}
	/**
	 * 根据id获取单个Article对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Article
	 */
	public void updateArticle(Article article) {
	    article.setUpdateTime(new Date());
		this.update("ArticleMapper.updateArticle", article);
	}
	/**
	 * 根据id获取单个Article对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Article
	 */
	public Article getArticleById(Long id) {
		return this.selectOne("ArticleMapper.getArticleById", id);
	}
	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	public List<Article> getArticleList(Article article) {
		return this.selectList("ArticleMapper.getArticleList", article);
	}

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	public List<Article> queryArticleListPage(Article article, PageEntity page) {
		return this.queryForListPage("ArticleMapper.queryArticleListPage", article, page);
	}

	/**
	 * 根据点击量排行
	 * 
	 * @return List<Article>
	 */
	public List<Article> queryArticleListOrderclickTimes(int num) {
		return this.selectList("ArticleMapper.queryArticleListOrderclickTimes", num);
	}

	/**
	 * 查询上一篇下一篇
	 * 
	 * @return List<Article>
	 */
	public Article queryArticleUpOrDown(Article article) {
		return this.selectOne("ArticleMapper.queryArticleUpOrDown", article);
	}

	/**
	 * 修改Article
	 * 
	 * @param article
	 *            要修改的Article访问数量
	 */
	public void updateArticleClickTimes(Long id) {
		this.update("ArticleMapper.updateArticleClickTimes", id);
	}
	/**
	 * 批量删除资讯
	 * 
	 * @param artIds
	 */
	public void delArticleBatch(String artIds) {
		this.delete("ArticleMapper.delArticleBatch", artIds.replaceAll(" " , "").split(","));
	}
}
