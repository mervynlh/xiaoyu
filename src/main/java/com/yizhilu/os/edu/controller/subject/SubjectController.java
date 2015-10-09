package com.yizhilu.os.edu.controller.subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * @author liuqinggang
 * @ClassName SubjectAction
 * @package com.yizhilu.os.exam.controller.subject
 * @description 项目分类管理
 * @Create Date: 2013-9-7 下午3:46:03
 */
@Controller
@RequestMapping("/subj")
public class SubjectController extends EduBaseController {

    private static final Logger logger = Logger.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;
    @Getter
    @Setter
    private Subject subject;
    @Getter
    @Setter
    private QuerySubject querySubject;

    private MemCache memCache = MemCache.getInstance();

    // 返回路径
    private String showAllSubject = getViewPath("/subject/allSubject");
    private String addSubjectCookies = "redirect:/quest/toQuestionitemList";

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("subject")
    public void initBinderSubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("querySubject")
    public void initBinderquerySubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("querySubject.");
    }

    // 查询一级项目
    @RequestMapping("/showTopSubject")
    public String showTopSubject(
            @ModelAttribute("querySubject") QuerySubject querySubject,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
            logger.error("SubjectAction.showTopSubject", e);
            return setExceptionRequest(request, e);
        }
        return showAllSubject;
    }

    // 通过pid查找subjectList
    @RequestMapping("/querySubjectByPid")
    @ResponseBody
    public Map<String, Object> querySubjectByPid(
            @ModelAttribute("querySubject") QuerySubject querySubject) {
        try {
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            if (subjectList != null && subjectList.size() > 0) {
                this.setJson(true, "", subjectList);
            } else {
                this.setJson(false, "", null);
            }
        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectByPid", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 通过项目ID查询项目
    // 通过id查找subject
    @RequestMapping("/querySubjectById")
    @ResponseBody
    public Map<String, Object> querySubjectById(HttpServletRequest request,
                                                HttpServletResponse response) {
        try {
            Subject subject = (Subject) memCache.get(MemConstans.MEM_SUBECJT
                    + this.getLoginSubjectId(request));
            if (subject == null) {
                subject = new Subject();
                subject.setSubjectId(this.getLoginSubjectId(request));
                subject = subjectService.getSubjectBySubjectId(subject);
                if (subject != null) {
                    memCache.set(
                            MemConstans.MEM_SUBECJT + this.getLoginSubjectId(request),
                            subject, MemConstans.MEM_COMMON_TIME);
                }
            }
            this.setJson(true, "", subject);

        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectById", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 查询所有项目
    @RequestMapping("/showAllSubject")
    public String showAllSubject(@ModelAttribute("querySubject") QuerySubject querySubject,
    		HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
            logger.error("SubjectAction.showAllSubject", e);
            return setExceptionRequest(request, e);
        }
        return showAllSubject;
    }

    /**
     * 跳转到某一个项目页面 将选的专业存到cookie中
     *
     * @return
     */
    @RequestMapping("/addSubjectCookies")
    public String addSubjectCookies(@ModelAttribute("subject") Subject subject,
                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            //通过登陆用户的id 更新该用户的最后一次选择的专业
        	Map<String, String> map = new HashMap<String, String>();
        	// 用户id
            map.put("userId", getLoginUserId(request) + "");
            //专业id
            map.put("subjectId",subject.getSubjectId()+"");
            //把选择的专业生成cookie
            WebUtils.setCookie(response, CommonConstants.COOKIE_SUBJECTID_KEY,
                    subject.getSubjectId() + "", 1);
        } catch (Exception e) {
            logger.error("SubjectAction.addSubjectCookies", e);
        }
        // 跳转到页面/quest/toQuestionitemList
        return addSubjectCookies;
    }

    /**
     * 获取所有的专业直接返回jsp ajaxload
     *
     * @param querySubject
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ajax/getSubject")
    public String getajaxtext(HttpServletRequest request) {
        try {
            List<Subject> subjectList = subjectService.getAllSubjectList();
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
            logger.error("SubjectAction.getajaxtext", e);
        }
        return getViewPath("/ajax/subject");
    }

}
