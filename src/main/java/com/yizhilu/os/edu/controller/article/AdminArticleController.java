package com.yizhilu.os.edu.controller.article;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.common.util.DateEditor;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.article.Article;
import com.yizhilu.os.edu.service.article.ArticleService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.controller.article.AdminArticleController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月19日 上午9:28:51
 */
@Controller
@RequestMapping("/admin")
public class AdminArticleController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminArticleController.class);

	private static final String toAddArticle = getViewPath("/admin/article/addArticle");// 添加资讯页面
	private static final String toArticleList = getViewPath("/admin/article/article_list");// 资讯列表
	private static final String toUpdateArticle = getViewPath("/admin/article/article_update");// 资讯列表
	@Autowired
	private ArticleService articleService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("article")
	public void initBinderArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("article.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 跳转添加资讯页面
	 * 
	 * @return
	 */
	@RequestMapping("/article/toAdd")
	public String toAddArticle() {
		return toAddArticle;
	}

	/**
	 * 添加资讯
	 * 
	 * @param request
	 * @param article
	 * @return
	 */
	@RequestMapping("/article/add")
	public String addArticle(HttpServletRequest request, @ModelAttribute("article") Article article) {
		try {
			if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
				article.setUpdateTime(new Date());
				articleService.addArticle(article);// 添加资讯
			}
		} catch (Exception e) {
			logger.error("AdminArticleController.addArticle", e);
		}
		return "redirect:/admin/article/list";
	}

	/**
	 * 查询资讯列表
	 * 
	 * @param model
	 * @param article
	 * @param page
	 * @return
	 */
	@RequestMapping("/article/list")
	public String queryArticleList(Model model, @ModelAttribute Article article, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页
			this.setPage(page);
			this.getPage().setPageSize(10);
			// 查询资讯列表
			List<Article> articleList = articleService.queryArticleListPage(article, this.getPage());
			// 把返回的数据放到model中返回
			model.addAttribute("articleList", articleList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("AdminArticleController.queryArticleList", e);
		}
		return toArticleList;
	}

	/**
	 * 跳转到更新页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/article/toUpdate/{id}")
	public String toUpdateArticle(Model model, @PathVariable("id") Long id) {
		try {
			// 查询资讯详情
			Article article = articleService.getArticleById(id);
			model.addAttribute("article", article);
		} catch (Exception e) {
			logger.error("AdminArticleController.toUpdateArticle", e);
		}
		return toUpdateArticle;
	}

	/**
	 * 更新资讯
	 * 
	 * @param article
	 * @return
	 */
	@RequestMapping("/article/updateArticle")
	public String updateArticle(HttpServletRequest request, @ModelAttribute Article article) {
		try {
			if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
				article.setUpdateTime(new Date());
				articleService.updateArticle(article);
			}
		} catch (Exception e) {
			logger.error("AdminArticleController.updateArticle", e);
		}
		return "redirect:/admin/article/list";
	}

	/**
	 * 批量删除资讯
	 * 
	 * @param request
	 * @param artIds
	 * @return
	 */
	@RequestMapping("/article/delArticleBatch")
	@ResponseBody
	public Map<String, Object> delArticleBatch(HttpServletRequest request, @RequestParam("artIds") String artIds) {
		try {
			if(StringUtils.isNotEmpty(artIds)){
				//批量刪除
				articleService.delArticleBatch(artIds);
				this.setJson(true, "success", null);
			}else{
				this.setJson(false, "false", null);
			}
		} catch (Exception e) {
			logger.error("AdminArticleController.delArticleBatch", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
}
