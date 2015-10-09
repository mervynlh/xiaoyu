package com.yizhilu.os.edu.controller.article;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.article.Article;
import com.yizhilu.os.edu.service.article.ArticleService;
import com.yizhilu.os.edu.service.website.WebsiteImagesService;

/**
 * Article管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class ArticleController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    // 资讯列表
    private String articlelist = getViewPath("/article/article-list");
    // 资讯详情
    private String toArticle = getViewPath("/article/article-infor");
    @Autowired
    private WebsiteImagesService websiteImagesService;
    @Autowired
    private ArticleService articleService;

    /**
     * 资讯列表
     * 
     * @return
     */
    @RequestMapping("/front/articlelist/{type}")
    public ModelAndView articlelist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long type) {
        ModelAndView modelAndView = new ModelAndView(articlelist);
        try {
        	//获得banner图
		    Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
		    modelAndView.addObject("websiteImages", websiteImages);
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(10);
            // 资讯列表
            Article article = new Article();
            article.setType(type);
            List<Article> articleList = articleService.queryArticleListPage(article, page);
            // 点击量资讯排行
            List<Article> articleListOrderclickTimes = articleService.queryArticleListOrderclickTimes(10);
            modelAndView.addObject("articleList", articleList);
            modelAndView.addObject("articleListOrderclickTimes", articleListOrderclickTimes);
            modelAndView.addObject("article", article);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("ArticleController.articlelist", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 资讯列表
     * 
     * @return
     */
    @RequestMapping("/front/toArticle/{id}")
    public String toArticle(HttpServletRequest request, @PathVariable Long id,
    		RedirectAttributes redirectAttributes,Model model) {
        try {
            Article article = articleService.getArticleById(id);
            if(ObjectUtils.isNull(article)){
				redirectAttributes.addAttribute("msg","对不起该资讯不存在或者已删除");
		        return "redirect:/front/success";
			}
            articleService.updateArticleClickTimes(article.getId());//更新浏览次数
            // 页面传来的数据放到page中
            List<Article> articleListOrderclickTimes = articleService.queryArticleListOrderclickTimes(10);
            Article queryArticle = new Article();
            queryArticle.setId(id);
            queryArticle.setType(1l);
            //上一篇
            Article upArticle = articleService.queryArticleUpOrDown(queryArticle);
            queryArticle.setType(2l);
            //上一篇
            Article downArticle = articleService.queryArticleUpOrDown(queryArticle);
            model.addAttribute("articleListOrderclickTimes", articleListOrderclickTimes);
            model.addAttribute("article", article);
            model.addAttribute("upArticle", upArticle);
            model.addAttribute("downArticle", downArticle);
        } catch (Exception e) {
            logger.error("ArticleController.teacherlist", e);
            return setExceptionRequest(request, e);
        }
        return toArticle;
    }
}