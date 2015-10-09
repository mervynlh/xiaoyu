package com.yizhilu.os.edu.controller.subject;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * @author liuqinggang
 * @ClassName AdminSubjectAction
 * @package com.yizhilu.os.exam.controller.subject
 * @description
 * @Create Date: 2013-9-7 下午3:46:17
 */
@Controller
@RequestMapping("/admin")
public class AdminSubjectController extends EduBaseController {

    private static final Logger logger = Logger.getLogger(AdminSubjectController.class);
    @Autowired
    private SubjectService subjectService;

    @Getter
    @Setter
    private Subject subject;

    // 返回路径
    private String saveSubjectInit = getViewPath("/admin/subject/subject_add");
    private String toSubjectList = getViewPath("/admin/subject/subject_list");
    private String delSubjects = "redirect:/admin/subj/toSubjectList";
    private String saveSubject = "redirect:/admin/subj/toSubjectList";
    private String updateSubject = "redirect:/admin/subj/toSubjectList";
    private String toUpdateSubject = getViewPath("/admin/subject/subject_update");

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("subject")
    public void initBinderSubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }

    // 保存初始化
    @RequestMapping("/subj/saveSubjectInit")
    public String saveSubjectInit(HttpServletRequest request, HttpServletResponse response) {
        try {
            subject = new Subject();
            subject.setLevel(1);
            QuerySubject querySubject = new QuerySubject();
            // querySubject.setLevel(1);
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList).toString());
        } catch (Exception e) {
            logger.error("SubjectAction.saveSubjectInit", e);
            return setExceptionRequest(request, e);
        }
        return saveSubjectInit;
    }

    // 通过pid查找subjectList
    @RequestMapping("/subj/querySubjectByPid")
    @ResponseBody
    public Map<String, Object> querySubjectByPid(
            @ModelAttribute("subject") Subject subject, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(subject.getParentId());
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            if (subjectList != null && subjectList.size() > 0) {
                this.setJson(true, "", subjectList);
            } else {
                this.setJson(false, "", null);
            }
        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectByPid", e);
        }
        return json;
    }

    // 通过id查找subject
    @RequestMapping("/subj/querySubjectById")
    @ResponseBody
    public Map<String, Object> querySubjectById(
            @ModelAttribute("subject") Subject subject, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            subject = subjectService.getSubjectBySubjectId(subject);
            this.setJson(true, "", subject);
        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectById", e);
        }
        return json;
    }

    @RequestMapping("/subj/toUpdateSubject")
    public String toUpdateSubject(@ModelAttribute("subject") Subject subject,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {

            // 获得一级的专业列表
            Subject subjectByList = new Subject();
            subjectByList.setLevel(1);
            QuerySubject querySubject = new QuerySubject();
            // querySubject.setLevel(1);
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            // 获得专业信息
            subject = subjectService.getSubjectBySubjectId(subject);
            // 获得父级节点
            Subject parentSubject;
            if (subject.getParentId() == 0) {
                parentSubject = new Subject();
                parentSubject.setSubjectName("根目录");
            } else {
                parentSubject = new Subject();
                parentSubject.setSubjectId(subject.getParentId());
                parentSubject = subjectService.getSubjectBySubjectId(parentSubject);
            }
            request.setAttribute("parentSubject", parentSubject);
            request.setAttribute("thissubject", subject);
            request.setAttribute("subjectList", gson.toJson(subjectList));

        } catch (Exception e) {
            logger.error("SubjectAction.toUpdateSubject", e);
            return setExceptionRequest(request, e);
        }
        return toUpdateSubject;
    }

    @RequestMapping("/subj/updateSubject")
    public String updateSubject(@ModelAttribute("subject") Subject subject,
                                HttpServletRequest request, HttpServletResponse response) {
        try {
            if (subject != null) {
                // 检查本次的
                subjectService.updateSubjectBySubjectId(subject);
            }
        } catch (Exception e) {
            logger.error("SubjectAction.updateSubject", e);
            return setExceptionRequest(request, e);
        }
        return updateSubject;
    }

    // 到项目列表页面
    @RequestMapping("/subj/toSubjectList")
    public String toSubjectList(HttpServletRequest request, HttpServletResponse response) {
        try {
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList).toString());
        } catch (Exception e) {
            logger.error("SubjectAction.toSubjectList", e);
            return setExceptionRequest(request, e);
        }
        return toSubjectList;
    }

    // 保存subject
    @RequestMapping("/subj/saveSubject")
    public String saveSubject(@ModelAttribute("subject") Subject subject,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            subject.setCreateTime(new Date()); // 默认为当前时间
            subject.setUpdateTime(new Date()); // 默认为当前时间
            subjectService.addOneSubject(subject);
        } catch (Exception e) {
            logger.error("SubjectAction.saveSubject", e);
            return setExceptionRequest(request, e);
        }
        return saveSubject;
    }

    // 保存subject
    @RequestMapping("/subj/delSubjects")
    public String delSubjects(@RequestParam("ids") List<Long> ids,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            subjectService.delSubjects(ids);
        } catch (Exception e) {
            logger.error("SubjectAction.delSubjects", e);
            return setExceptionRequest(request, e);
        }
        return delSubjects;
    }

    /**
     * 后台获得所有项目
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/subj/getAllSubjectList")
    @ResponseBody
    public Map<String, Object> getAllSubjectList(HttpServletRequest request,
                                                 HttpServletResponse response) {
        try {
            // 获得所有项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            this.setJson(true, "", subjectList);
        } catch (Exception e) {
            logger.error("SubjectAction.getAllSubjectList", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

}
