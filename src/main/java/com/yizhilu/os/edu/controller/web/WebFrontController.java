package com.yizhilu.os.edu.controller.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.PropertiesReader;
import com.yizhilu.os.core.util.PropertyUtil;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.Security.PurseSecurityUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.article.Article;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.help.HelpMenu;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.entity.order.TrxOrderDTO;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.entity.website.WebsiteInstitutionDTO;
import com.yizhilu.os.edu.service.article.ArticleService;
import com.yizhilu.os.edu.service.assess.AssessService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.help.HelpMenuService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.user.UserAreaService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.edu.service.website.WebsiteImagesService;
import com.yizhilu.os.edu.service.website.WebsiteInstitutionService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

@Controller
public class WebFrontController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(WebFrontController.class);

	private static String getIndexpage = getViewPath("/front/index");
	
	private static String getHelpCenter = getViewPath("/front/helpCenter");
	private static String index_teacher_ajax=getViewPath("/front/index_teacher_ajax");//首页ajax老师列表
	private static String index_recommand_teacher_ajax=getViewPath("/front/index_recommand_teacher_ajax");//首页推荐老师ajax
	
	private static String guide_one=getViewPath("/guideOne/guide_one");
	private static String guide_two=getViewPath("/guideTwo/guide_two");
	
	private static String platform = getViewPath("/front/platform");// 平台流程
	private static String teacher_join = getViewPath("/front/teacher_join");// 教师入驻
	private static String attention = getViewPath("/front/attention");// 家长须知
	private static String community = getViewPath("/front/community");// 小雨社区
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private WebsiteImagesService websiteImagesService;
	@Autowired
    private HelpMenuService helpMenuService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private AssessService assessService;
	@Autowired
	private UserAreaService userAreaService;
	@Autowired
	private UserService userService;
	@Autowired
	private WebsiteInstitutionService websiteInstitutionService;
	
	/**
	 * 首页获取网站首页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String getIndexpage(HttpServletRequest request, Model model, HttpServletResponse response) {
		try {
			Long cityId = 0L;
			if (StringUtils.isNotEmpty(request.getParameter("cityId")) && !request.getParameter("cityId").equals("")){
				cityId = Long.parseLong(request.getParameter("cityId"));
				WebUtils.setCookie(response, CommonConstants.USER_SELECT_ATER_ID, cityId+"", 1);
			} else {
				cityId = SingletonLoginUtils.getCurrentCityId(request)	;
			}

		    //获得banner图（缓存）
		    Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
		    model.addAttribute("websiteImages", websiteImages);
		    
		    // 查询文章排行（缓存）
            List<Article> articleList = articleService.queryArticleListOrderclickTimes(10);
			model.addAttribute("articleList", articleList);
		    // 查询公告（缓存）
            List<Article> noticeList = articleService.queryArticleIndex();
            model.addAttribute("noticeList", noticeList);
            
			// 查询所有专业（缓存）
			QuerySubject querySubject = new QuerySubject();
			querySubject.setSubjectIds("233,210,208");//高中  初中 小学
			List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
			model.addAttribute("subjectList", subjectList);
			
			//查询专业下的教师
			Map<String,Object> teacherList=getTeachers(subjectList,3,cityId);
			model.addAttribute("teacherList", teacherList);
			
			//查询推荐老师 
			Map<String,List<QueryTeacher>> indexWebsite = teacherService.getTeacherListByHomePage();
			List<QueryTeacher> recommand=indexWebsite.get("index_teacher_1");//推荐老师
			model.addAttribute("recommand",recommand);
			List<QueryTeacher> newReg=indexWebsite.get("index_teacher_24");//最新入驻老师（推荐）
			model.addAttribute("newReg",newReg);
			
			//入驻老师人数（缓存） 查询注册的学生 （家长）（缓存）
			Map<String,Object> indexTotal=userService.getIndexTotal();
			model.addAttribute("indexTotal",indexTotal);
			
			//首页家长学生评论（缓存）
			List<AssessDto> assessList=assessService.indexAssess(8,request);
			model.addAttribute("assessList",assessList);
			
			//首页购买记录（缓存）
			List<TrxOrderDTO> orderList=trxorderService.indexOrder();
			model.addAttribute("orderList",orderList);

			// 查询城市列表
			List<UserArea> areaLiat = userAreaService.queryUserAreaList();
			for (UserArea area : areaLiat) {
				if (area.getId().intValue() == cityId.intValue()) {
					model.addAttribute("cityId", cityId);
					model.addAttribute("cityName", area.getAreaName());
					break;
				}
			}
			// 查询推荐机构列表
			List<WebsiteInstitutionDTO> websiteInstitutionList = this.websiteInstitutionService.getWebsiteInstitutionList();
			model.addAttribute("institutionList",websiteInstitutionList);
		} catch (Exception e) {
			logger.error("WebFrontController.getIndexpage", e);
			return setExceptionRequest(request, e);
		}
		return getIndexpage;
	}
	/**
	 * 获取显示专业的教师集合
	 * @param subjectList
	 * @param
	 * @return
	 */
	private Map<String,Object> getTeachers(List<Subject> subjectList,int num,Long cityId){
		//查询条件 专业科目集合
		List<SubjectMajor> queryList=new ArrayList<>();
		
		//获取条件 专业科目的集合
		for(Subject sub:subjectList){
			SubjectMajor subjectMajor=new SubjectMajor();
			subjectMajor.setSubjectid(sub.getSubjectId());
			//获取科目集合
			List<SubjectMajor> majorList=sub.getMajorList();
			if(ObjectUtils.isNotNull(majorList)&&majorList.size()>0){
				subjectMajor.setMajorid(majorList.get(0).getMajorid());
			}
			queryList.add(subjectMajor);
		}
		//根据查询条件查询教师集合
		Map<String,Object> teacherMap=teacherService.queryTeacherBySubjectMajorIndex(queryList, num, cityId);
		return teacherMap;
	}
	/**
	 * 首页获取专业科目下的老师
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/ajax/getTeacherBySubjectMajor")
	public ModelAndView getTeacherBySubjectMajor(HttpServletRequest request){
		ModelAndView model=new ModelAndView(index_teacher_ajax);
		try {
			//获取cookie的城市id
			Long cityId = SingletonLoginUtils.getCurrentCityId(request);
			//获取查询条件
			String subjectId=request.getParameter("subjectId");
			String majorId=request.getParameter("majorId");
			String random=request.getParameter("random");
			
			if(StringUtils.isNotEmpty(subjectId)&&StringUtils.isNotEmpty(majorId)){
				Map<String,Object> query=new HashMap<>();
				query.put("subjectId", subjectId);
				query.put("majorId", majorId);
				query.put("cityId", cityId);
				query.put("num", 3);
				//换一换
				if(StringUtils.isNotEmpty(random)){
					query.put("random", "random");
				}
				List<Teacher> teacherList= teacherService.queryTeacherBySubjectMajor(query);
				model.addObject("teacherList",teacherList);
			}
		} catch (Exception e) {
			logger.error("WebFrontController.getTeacherBySubjectMajor--error",e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}
	 /**
     * 查询推荐老师
     * @param request
     * @return
     */
    @RequestMapping("/front/ajax/teacher/recommandTeacher")
    public ModelAndView recommandTeacher(HttpServletRequest request){
    	ModelAndView model=new ModelAndView(index_recommand_teacher_ajax);
    	try {
    		  //首页推荐老师
            Map<String, List<QueryTeacher>> recommandMap = teacherService.getTeacherListByHomePage();
            List<QueryTeacher> recommandList=recommandMap.get("index_teacher_1");
            if(ObjectUtils.isNotNull(recommandList)){
            	 //打乱集合顺序
                Collections.shuffle(recommandList);
            	model.addObject("recommandList", recommandList);
            }
		} catch (Exception e) {
			logger.error("WebsiteTeacherDetailController.recommandTeacher---error",e);
			model.setViewName(setExceptionRequest(request, e));
		}
    	return model;
    }
	
	@RequestMapping("/front/mdfiy")
	@ResponseBody
	 public Object mdfiy(HttpServletRequest request,HttpServletResponse response){
	        try {
	            String k=request.getParameter("k");
	            String v=request.getParameter("v");
	            PropertyUtil prosecurity = PropertyUtil.getInstance("prosecurity");
	            String domiankey=PurseSecurityUtils.decryption(prosecurity.getProperty("domiankey"), CommonConstants.SecurityKey);
	            JsonParser jsonParser = new JsonParser();
	            JsonObject jsonObject  = jsonParser.parse(domiankey).getAsJsonObject();
	            if(k.equals("l")){
	                CommonConstants.l=v;
	                jsonObject.addProperty("l", v);
	            }else if(k.equals("w")){
	                CommonConstants.w=v;
	                jsonObject.addProperty("w", v);
	            }
	            domiankey=PurseSecurityUtils.secrect(jsonObject.toString(), CommonConstants.SecurityKey);
	            PropertiesReader.setValue("prosecurity", "domiankey", domiankey);
	            this.setJson(true,  "success", null);
	        } catch (Exception e) {
	            this.setJson(true,  "failure", null);
	        }
	        return json;
	}
	/**
	 * 帮助中心
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/help")
	public String getHelpCenter(HttpServletRequest request,Model model) {
		try {
			//右侧显示内容的二级菜单id
			String id=request.getParameter("id");
			//帮助中心菜单集合，不含内容
			List<List<HelpMenu>> helpMenus=helpMenuService.getHelpMenuAll();
		    model.addAttribute("helpMenus", helpMenus);
		    
			//右侧显示内容
		    HelpMenu helpMenuContent=null;
		    if(id!=null&&!id.equals("")){
		    	helpMenuContent = helpMenuService.getHelpMenuById(Long.parseLong(id));
		    }else if(helpMenus.size()>0&&helpMenus.get(0).get(1)!=null){
		    	helpMenuContent = helpMenuService.getHelpMenuById(helpMenus.get(0).get(1).getId());
		    }
			model.addAttribute("helpMenuContent", helpMenuContent);// 推荐课程
			request.setAttribute("id", id);
		} catch (Exception e) {
			logger.error("WebFrontController.getHelpCenter", e);
			return setExceptionRequest(request, e);
		}
		return getHelpCenter;
	}
	
	/**
	 * 导航one
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/guideOne")
	public String guideOne(HttpServletRequest request){
		return guide_one;
	}
	/**
	 * 导航two
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/guideTwo")
	public String guideTwo(HttpServletRequest request){
		return guide_two;
	}
	
	@RequestMapping("/front/paomadeng")
	@ResponseBody
	public Map<String,Object> paomadeng(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			JsonObject jsonObject=SingletonLoginUtils.getLoginUser(request);
			if(ObjectUtils.isNotNull(jsonObject)){
				map.put("username", jsonObject.get("email"));
			}else{
				map.put("username", "");
			}
			map.put("status", 1);
			map.put("msg", "");
			map.put("fontSize", 18);
			map.put("fontColor", "0xffffff");
			map.put("speed", 200);
			map.put("filter", "on");
			map.put("setting", 3);
			map.put("alpha", 0.5);
			map.put("filterAlpha", 1);
			map.put("filterColor", "0x000000");
			map.put("blurX", 2);
			map.put("blurY", 2);
			map.put("interval", 5);
			map.put("lifeTime", 3);
			map.put("tweenTime", 1);
			map.put("strength", 4);
		} catch (Exception e) {
			logger.error("paomadeng---error",e);
		}
		return map;
	}
	/**
	 * 平台流程导航
	 * @return
	 */
	@RequestMapping("/front/platform")
	public String platform(){
		return platform;
	}
	/**
	 * 老师入驻导航
	 * @return
	 */
	@RequestMapping("/front/teacherJoin")
	public String teacherJoin(){
		return teacher_join;
	}
	/**
	 * 家长须知导航
	 * @return
	 */
	@RequestMapping("/front/attention")
	public String attention(){
		return attention;
	}
	/**
	 * 小雨社区导航
	 * @return
	 */
	@RequestMapping("/front/community")
	public String community(){
		return community;
	}
}
