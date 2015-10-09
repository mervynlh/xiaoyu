package com.yizhilu.os.edu.controller.user;


import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.common.util.FileExportImportUtil;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.ReqChanle;
import com.yizhilu.os.edu.constants.enums.UserOptType;
import com.yizhilu.os.edu.constants.web.LetterConstans;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.QueryCourse;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.letter.MsgSystem;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserEmailMsg;
import com.yizhilu.os.edu.entity.user.UserLoginLog;
import com.yizhilu.os.edu.entity.user.UserMobileMsg;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.letter.MsgSystemService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.user.EmailThread;
import com.yizhilu.os.edu.service.user.SmsBatchThread;
import com.yizhilu.os.edu.service.user.UserEmailMsgService;
import com.yizhilu.os.edu.service.user.UserLoginLogService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * @ClassName com.yizhilu.os.user.controller.user.AdminUserController
 * @description 用户管理
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-4-12 下午1:08:41
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	private static final String toUserList = getViewPath("/admin/user/user_list");// 返回学员列表
	private static final String toSelectUserList = getViewPath("/admin/user/select_user_list");// 短信发送 查询学员列表
	private static final String toUpdatePwd = getViewPath("/admin/user/updatePwd");// 修改密码页面
	private static final String toSendMobileMsg = getViewPath("/admin/user/to_sendMobileMsg");// 发送短信页面
	private static final String toSendEmailMsg = getViewPath("/admin/user/to_sendEmailMsg");// 发送邮件页面
	private static final String sendMobileMsgList = getViewPath("/admin/user/sendMobile_list");// 短信管理页面
	private static final String sendEmailMsgList = getViewPath("/admin/user/sendEmail_list");// 短信管理页面
	private static final String sendMobileMsgInfo = getViewPath("/admin/user/sendMobile_info");// 短信管理页面
	private static final String sendEmailMsgInfo = getViewPath("/admin/user/sendEmail_info");// 邮件管理页面
	private static final String batchOpen = getViewPath("/admin/user/batchOpen");// 批量开通界面
	private static final String studyHistory = getViewPath("/admin/user/study_history");// 学习记录
	private static final String courseList = getViewPath("/admin/user/course_list");//
	private static final String toUserLoginLog = getViewPath("/admin/user/user_login_log");// 学员登陆log
	private static final String senSystemMessages = getViewPath("/admin/user/to_send_systemMessage");// 发送系统消息页面
	private static final String sendCoupon = getViewPath("/admin/user/to_send_coupon");// 发送优惠券页面
	private static final String progressbar = getViewPath("/admin/user/progressbar");// 进度跳
    private static final String optRecordList = getViewPath("/admin/user/optrecord_list");// 进度跳
	@Autowired
	private UserService userService;
	@Autowired
	private UserMobileMsgService userMobileMsgService;
	@Autowired
	private UserEmailMsgService userEmailMsgService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MsgSystemService msgSystemService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private UserLoginLogService userLoginLogService;
	@Autowired
	private MsgReceiveService msgReceiveService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("user")
	public void initBinderUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("userMobileMsg")
	public void initBinderMsg(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userMobileMsg.");
	}
	@InitBinder("userEmailMsg")
	public void initBinderEmail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userEmailMsg.");
	}

	// 课程查询检索
	@InitBinder("queryCourse")
	public void initBinderqueryCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCourse.");
	}

	// 课程查询检索
	@InitBinder("userLoginLog")
	public void initBinderUserLoginLog(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userLoginLog.");
	}
	// 课程查询检索
	@InitBinder("userOptRecord")
	public void initBinderUserOptRecord(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userOptRecord.");
	}

	/**
	 * 用户列表集合
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/list")
	public ModelAndView userList(@ModelAttribute User user, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelandView = new ModelAndView();
		// 设置返回页面
		modelandView.setViewName(toUserList);
		try {
			// 设置分页 ，默认每页10
			this.setPage(page);
			// 查询学员列表
			List<User> list = userService.getUserListByCondition(user, this.getPage());
			// 把参数放到modelAndView中
			modelandView.addObject("list", list);
			modelandView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("AdminUserController.userList", e);
		}
		return modelandView;
	}
	
	/**
	 * 短信邮件发送    查询用户列表集合
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/select_userlist/{type}")
	public ModelAndView selectUserList(@ModelAttribute User user, @ModelAttribute("page") PageEntity page,@PathVariable("type")int type) {
		ModelAndView modelandView = new ModelAndView();
		// 设置返回页面
		modelandView.setViewName(toSelectUserList);
		try {
			if(type==3){
				page.setPageSize(5);
			}
			// 设置分页 ，默认每页10
			this.setPage(page);
			// 查询学员列表
			List<User> list = userService.getUserListAndCourse(user, this.getPage());
			// 把参数放到modelAndView中
			modelandView.addObject("list", list);
			modelandView.addObject("page", this.getPage());
			modelandView.addObject("type",type);//1 短信  2邮箱  3系统消息
		} catch (Exception e) {
			logger.error("AdminUserController.userList", e);
		}
		return modelandView;
	}
	
	/**
	 * 导入Excel 解析
	 */
	@RequestMapping("/user/importMsgExcel/{type}")
	@ResponseBody
    public Map<String, Object> importExcel(HttpServletRequest request, @RequestParam("myFile") MultipartFile myFile,@PathVariable("type")int type) {
        try {
            logger.info("myFile:" + myFile.getName());
            HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
			//只读取sheet1
            HSSFSheet sheet = wookbook.getSheet("Sheet1");
            int rows = sheet.getLastRowNum();// Excel行数
            String mobileStr = "";
            for (int i = 1; i <= rows; i++) {
                // 读取左上端单元格
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                	// **读取cell**
                	HSSFCell cell=row.getCell((short) 0);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_FORMULA:
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                            	DecimalFormat df = new DecimalFormat("#");
                            	mobileStr+=df.format(cell.getNumericCellValue())+","; 
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                            	mobileStr+=cell.getStringCellValue().trim()+","; 
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
          //获得页面的
            if (request.getParameter("numerStr")!=null && request.getParameter("numerStr")!="") {
            	mobileStr+=request.getParameter("numerStr");
			}
            Map<String, Object> returnMap=null;
            if (type==1) {//短信页面
            	returnMap=checkMobile(mobileStr);
                if (Boolean.parseBoolean(returnMap.get("flag").toString())==false){
    				this.setJson(false, returnMap.get("errorMobile").toString(), "");
                    return json;
    			}
    			else {
    				this.setJson(true,"",returnMap.get("mobileList"));
    			}
			}else {//邮箱页面
				returnMap=checkEmail(mobileStr);
                if (Boolean.parseBoolean(returnMap.get("flag").toString())==false){
    				this.setJson(false, returnMap.get("errorMessage").toString(), "");
                    return json;
    			}
    			else {
    				this.setJson(true,"",returnMap.get("returnList"));
    			}
			}
        } catch (Exception e) {
            logger.error("importExcel", e);
            this.setJson(false, "Excel导入错误", null);
            return json;
        }
        return json;
    }

	/**
	 * 用户导出
	 */
	@RequestMapping("/user/export")
	public void userListExport(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) {
		try {
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/excelfile/user");
			//文件名
			String expName = "学员信息_" + DateUtils.getStringDateShort();
			//表头信息
	        String[] headName = { "ID","昵称","真实姓名","学员邮箱","学员手机", "注册时间","状态"};

	        //拆分为一万条数据每Excel，防止内存使用太大
			PageEntity page=new PageEntity();
			page.setPageSize(10000);
			userService.getUserListByCondition(user, page);
			int num=page.getTotalPageSize();//总页数
			List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
			for(int i=1;i<=num;i++){//循环生成num个xls文件
				page.setCurrentPage(i);
				List<User> userList = userService.getUserListByCondition(user, page);
				List<List<String>> list=userJoint(userList);
				File file = FileExportImportUtil.createExcel(headName, list, expName+"_"+i,dir);
				srcfile.add(file);
			}
	        FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 学员信息excel格式拼接
	 * @return
	 */
	public List<List<String>> userJoint(List<User> userList){
		List<List<String>> list = new ArrayList<List<String>>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < userList.size(); i++) {
			List<String> small = new ArrayList<String>();
			small.add(userList.get(i).getId() + "");
			small.add(userList.get(i).getNickname());
			small.add(userList.get(i).getRealname());
			small.add(userList.get(i).getEmail());
			small.add(userList.get(i).getMobile());
			small.add(format.format(userList.get(i).getCreatedate()));
			if (userList.get(i).getIsavalible() == 0) {
				small.add("正常");
			} else if (userList.get(i).getIsavalible() == 1) {
				small.add("冻结");
			}
			list.add(small);
		}
		return list;
	}
	/**
	 * 禁用学员账号(冻结)
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/updateIsavalible")
	@ResponseBody
	public Map<String, Object> updateIsavalible(HttpServletRequest request, @ModelAttribute User user) {
		try {
			userService.updateUserForIsavalibleById(user);
			this.setJson(true, "success", null);

			// 记录系统用户操作
			Map<String, Object> descMap = new HashMap<String, Object>();
			descMap.put("optuser", "操作id_" + SingletonLoginUtils.getSysUserId(request));
			descMap.put("optType", user.getIsavalible() == 1 ? "禁用" : "正常");
			descMap.put("userId", "用户id_" + user.getId());
			userService.addUserOptRecord(user.getId(), user.getIsavalible() == 1 ? UserOptType.DISABLE.toString() : UserOptType.ACTIVE.toString(), SingletonLoginUtils.getSysUserId(request), this.getSysLoginLoginName(request),
					user.getId(), gson.toJson(descMap));
		} catch (Exception e) {
			logger.error("AdminUserController.updateIsavalible", e);
		}
		return json;
	}

	/**
	 * 跳转到开通页面
	 * 
	 * @return
	 */
	@RequestMapping("/user/forward")
	public ModelAndView forwardImpot() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/user/user_importProcess");
		return modelAndView;
	}

	/**
	 * 跳转到修改密码
	 * 
	 * @return
	 */
	@RequestMapping("/user/toupdatepwd/{id}")
	public ModelAndView toUpdatePwd(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(toUpdatePwd);
		try {
			User user = userService.getUserById(id);
			modelAndView.addObject("user", user);
		} catch (Exception e) {
			logger.error("AdminUserController.toUpdatePwd", e);
		}
		return modelAndView;
	}


	// 璁㈠崟娴佹按淇℃伅
	public void addOrderMsg(Long userId, String courseId) throws Exception {
		// 鎷艰鍙傛暟
		Map<String, String> sourceMap = new HashMap<String, String>();
		sourceMap.put("type", "1");// 涓嬪崟绫诲瀷
		sourceMap.put("couponcode", "");// 浼樻儬鍗风紪鐮�
		sourceMap.put("userid", userId + "");
		sourceMap.put("reqchanle", ReqChanle.WEB.toString());
		sourceMap.put("reqIp", "");
		sourceMap.put("courseId", courseId);
		sourceMap.put("payType", "FREE");
		trxorderService.addFreeTrxorder(sourceMap);
	}

	/**
	 * 获取课程列表
	 * **/
	@RequestMapping("/user/courseList")
	public ModelAndView toCourseForClass(@ModelAttribute("queryCourse") QueryCourse queryCourse, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(courseList);
		QuerySubject querySubject = new QuerySubject();
		try {
			this.setPage(page);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			modelAndView.addObject("courseList", courseList);
			modelAndView.addObject("subjectList", gson.toJson(subjectList));
			modelAndView.addObject("page", page);
			modelAndView.addObject("userId", queryCourse.getUserId());
		} catch (Exception e) {
		}
		return modelAndView;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("/user/updatepwd")
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request, @ModelAttribute User user) {
		try {
			userService.updatePwdById(user, null);
			this.setJson(true, "success", null);

			// 记录系统用户操作
			Map<String, Object> descMap = new HashMap<String, Object>();
			descMap.put("optuser", "操作id_" + SingletonLoginUtils.getSysUserId(request));
			descMap.put("optType", "操作_修改用户密码");
			descMap.put("userId", "用户id_" + user.getId());
			userService.addUserOptRecord(user.getId(), UserOptType.CHANGEPWD.toString(), SingletonLoginUtils.getSysUserId(request), this.getSysLoginLoginName(request), user.getId(), gson.toJson(descMap));
		} catch (Exception e) {
			logger.error("AdminUserController.updatePwd", e);
		}
		return json;
	}


	/**
	 * 把邮箱密码放到list
	 * 
	 * @param content
	 * @return list
	 */
	private List<Map<String, String>> content2ListMap(String content) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] info = content.split("\n");
		for (String string : info) {
			string = string.trim().replaceAll("  ", " ").replaceAll("  ", " ");
			String[] record = string.split(" ");
			Map<String, String> map = new HashMap<String, String>();
			map.put("email", record[0]);
			map.put("password", record[1]);
			list.add(map);
		}
		return list;
	}

	/**
	 * 检测邮箱是否重复，邮箱格式是否正确
	 * 
	 * @param list
	 * @return Boolean
	 */
	private Boolean checkEmails(List<Map<String, String>> list) {
		try {
			// 判断Email是否重复
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> emailMap = list.get(i);
				String email = (String) emailMap.get("email");
				if (!email.matches("^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$")) {
					return false;
				}
				for (int j = i + 1; j < list.size(); j++) {
					Map<String, String> tmpMap = list.get(j);
					String tmpEmail = (String) tmpMap.get("email");
					if (email.equals(tmpEmail)) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 跳转发送短信页面
	 * 
	 * @return
	 */
	@RequestMapping("/user/toMsg")
	public String toSendMoblieMsg() {
		return toSendMobileMsg;
	}
	/**
	 * 跳转发送邮件页面
	 * 
	 * @return
	 */
	@RequestMapping("/user/toEmailMsg")
	public String toSendEmailMsg() {
		return toSendEmailMsg;
	}
	
	

	/**
	 * 发送短信
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/sendMsg")
	@ResponseBody
	public Map<String, Object> sendMobileMsg(HttpServletRequest request) {
		try {

            List<UserMobileMsg> msgList = new ArrayList<UserMobileMsg>();

            String linksman = request.getParameter("linksman");// 获取联系人
			String content = request.getParameter("content");// 获取内容
			Integer type=Integer.parseInt(request.getParameter("sendType"));//发送方式

            Date now = new Date();
			Date sendTime = now;
			if(type==2){//定时发送

				if(StringUtils.isEmpty(request.getParameter("sendTime"))){
					this.setJson(false, "定时发送时间不能为空", "");
					return json;
				}
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sendTime=df.parse(request.getParameter("sendTime"));//定时发送时间
			}
			if (StringUtils.isNotEmpty(linksman) && StringUtils.isNotEmpty(content)) {
				//验证手机
				Map<String, Object> returnMap=checkMobile(linksman);
				//错误信息
				String errorMobile = returnMap.get("errorMobile").toString();
				if (Boolean.parseBoolean(returnMap.get("flag").toString())==false){
					this.setJson(false, errorMobile, "");
                    return json;
				}
				else {
					//添加发送记录
					JsonObject user =   SingletonLoginUtils.getSysUser(request);
					UserMobileMsg userMobileMsg = new UserMobileMsg();
					userMobileMsg.setSendTime(sendTime);
					if(type==1){//正常发送
						userMobileMsg.setStatus(1);
					}else{//定时发送
						userMobileMsg.setStatus(2);
					}
					userMobileMsg.setId(0);
					userMobileMsg.setType(type);
					
					userMobileMsg.setContent(content);
					userMobileMsg.setMobile(returnMap.get("mobileList").toString());
					if (ObjectUtils.isNotNull(user)) {
						userMobileMsg.setUserId(user.get("userId").getAsLong());
					} else {
						userMobileMsg.setUserId(0L);
					}
					userMobileMsg.setCreateTime(now);
					msgList.add(userMobileMsg);
					//添加记录 暂不发送
					userMobileMsgService.addUserMobileMsg(msgList);
                    if(type==1){//正常发送
                        userMobileMsgService.batchSendMobileMsg(content, returnMap.get("mobileList").toString().split(","), 3);
                    }
					errorMobile = "发送成功";
				}
				this.setJson(Boolean.parseBoolean(returnMap.get("flag").toString()), errorMobile, "");
			} else {
				this.setJson(false, "联系人或内容不能为空", null);
			}
		} catch (Exception e) {
			logger.error("sendMobileMsg", e);
		}
		return json;
	}
	
	/**
	 * 验证手机格式 去掉重复的方法
	 * @param mobileArr 字符串
	 */
	public Map<String, Object> checkMobile(String mobileArr) {
		Map<String, Object> returnMap=new HashMap<String, Object>();
		mobileArr = mobileArr.replaceAll("\r\n", "");// 去除空格回车
		mobileArr = mobileArr.replaceAll(" ", "");// 去除空格回车
		String[] lm = mobileArr.split(",");// 定义数组
		List list = new ArrayList();// new一个arralist
		Set set = new HashSet();// new 一个hashset
		set.addAll(Arrays.asList(lm));// 将数组转为list并存入set中，就可以去掉重复项了
		for (Iterator it = set.iterator(); it.hasNext();) {
			list.add(it.next());// 遍历set 将所有元素键入list中
		}
		String noRepeatList = list.toString();
		noRepeatList = noRepeatList.replace("[", "");
		noRepeatList = noRepeatList.replace("]", "");
		noRepeatList = noRepeatList.replace(" ", "");
		noRepeatList = noRepeatList.trim();
		
		String flag = "true";
		String errorMobile = "";
		String[] lms = noRepeatList.split(",");
		if (lms.length > 0) {
			for (int i = 0; i < lms.length; i++) {
				if (!lms[i].trim().matches("^1[0-9]{10}$")) {
					flag = "false";
					errorMobile = lms[i] + "格式有误";
					break;
				}
			}
		}
		returnMap.put("flag", flag);
		returnMap.put("mobileList", noRepeatList);
		returnMap.put("errorMobile", errorMobile);
		return returnMap;
	}

	
	/**
	 * 发送邮件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/sendEmailMsg") @ResponseBody
	public Map<String, Object> sendEmailMsg(HttpServletRequest request) {
		try {
			String linksman = request.getParameter("linksman");// 获取联系人
			String title=request.getParameter("title");//获取标题
			String content = request.getParameter("content");// 获取内容

            int type = Integer.valueOf(request.getParameter("type"));// 邮件类型
            String startTime = request.getParameter("startTime");//发送时间
            Date starttime = new Date();
            //如果是定时短信发送时间要大于当前时间
            if(type==2){
                if("".equals(startTime)){
                    this.setJson(false, "请输入发送时间", "");
                    return json;
                }
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                starttime = formatDate.parse(startTime);
                if(!starttime.after(new Date())){
                    this.setJson(false, "定时发送的时间要大于当前日期", "");
                    return json;
                }
            }

			if (StringUtils.isNotEmpty(linksman) && StringUtils.isNotEmpty(content) && StringUtils.isNotEmpty(title)) {
				//验证邮箱
				Map<String, Object> returnMap=checkEmail(linksman);
				//错误信息
				String errorEmail = returnMap.get("errorMessage").toString();
				if (Boolean.parseBoolean(returnMap.get("flag").toString())==false){
					this.setJson(false, errorEmail, "");
                    return json;
				}
				else {
					JsonObject user =   SingletonLoginUtils.getSysUser(request);
					List<UserEmailMsg> emailMsgList = new ArrayList<UserEmailMsg>();
                    UserEmailMsg userEmailMsg = new UserEmailMsg();
                    userEmailMsg.setId(0);
                    userEmailMsg.setTitle(title);
                    userEmailMsg.setContent(content);
                    userEmailMsg.setEmail(returnMap.get("returnList").toString());
                    userEmailMsg.setType(type);
                    userEmailMsg.setSendTime(starttime);
                    if(type==1){
                        //发送邮件
                        userEmailMsgService.batchSendEmail(returnMap.get("returnList").toString().split(","), content, title,3);
                        //emailService.sendBatchMail(returnMap.get("returnList").toString().split(","), content, title);
                        userEmailMsg.setStatus(1);
                    }else{
                        userEmailMsg.setStatus(2);

                    }
                    if (ObjectUtils.isNotNull(user)) {
                        userEmailMsg.setUserId(user.get("userId").getAsLong());
                    } else {
                        userEmailMsg.setUserId(0L);
                    }
                    userEmailMsg.setCreateTime(new Date());
                    emailMsgList.add(userEmailMsg);
					userEmailMsgService.addUserEmailMsg(emailMsgList);
					errorEmail = "发送成功";
				}
				this.setJson(Boolean.parseBoolean(returnMap.get("flag").toString()), errorEmail, "");
			} else {
				this.setJson(false, "联系人、标题或内容不能为空", null);
			}
		} catch (Exception e) {
			logger.error("sendEmailMsg", e);
		}
		return json;
	}

    /**
     * 进度条
     */
    @RequestMapping("/user/progressbar")
    public String progressbar(HttpServletRequest request,@RequestParam("type")int type) {
        try {
            request.setAttribute("type",type);
        } catch (Exception e) {
            logger.error("progressbar", e);
        }
        return progressbar;
    }
    /**
     * 查询进度
     */
    @RequestMapping("/query/progressbar")
    @ResponseBody
    public Object queryprogressbar(HttpServletRequest request,@RequestParam("type")int type) {
        try {
            if(type==1){
                EmailThread emailThread = new EmailThread();
                double sumNum = Double.valueOf(emailThread.getSumNum());
                double listNum = Double.valueOf(emailThread.getList().size());
                Map map = new HashMap();
                map.put("sumNum",sumNum);
                map.put("listNum",listNum);
                setJson(true, "",map);
            }else{
				SmsBatchThread smsBatchThread = new SmsBatchThread();
                double sumNum = Double.valueOf(smsBatchThread.getSumNum());
                double listNum = Double.valueOf(smsBatchThread.getList().size());
                Map map = new HashMap();
                map.put("sumNum",sumNum);
                map.put("listNum",listNum);
                setJson(true, "",map);
            }
        } catch (Exception e) {
            logger.error("queryprogressbar", e);
        }
        return json;
    }
	/**
	 * 验证邮箱格式 去重
	 * @param emailStr
	 */
	public Map<String, Object> checkEmail(String emailStr) {
		Map<String, Object> returnMap=new HashMap<String, Object>();
		emailStr = emailStr.replaceAll("\r\n", "");// 去除空格回车
		emailStr = emailStr.replaceAll(" ", "");// 去除空格回车
		String[] lm = emailStr.split(",");// 定义数组

		List list = new ArrayList();// new一个arralist
		Set set = new HashSet();// new 一个hashset
		set.addAll(Arrays.asList(lm));// 将数组转为list并存入set中，就可以去掉重复项了
		for (Iterator it = set.iterator(); it.hasNext();) {
			list.add(it.next());// 遍历set 将所有元素键入list中
		}
		String noRepeatList = list.toString();
		noRepeatList = noRepeatList.replace("[", "");
		noRepeatList = noRepeatList.replace("]", "");
		noRepeatList = noRepeatList.replace(" ", "");
		noRepeatList = noRepeatList.trim();

		boolean flag = true;
		String errorMessage = "";
		String[] lms = noRepeatList.split(",");
		if (lms.length > 0) {
			for (int i = 0; i < lms.length; i++) {
				if (!lms[i].trim().matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
					flag = false;
					errorMessage = lms[i] + "格式有误";
					break;
				}
			}
		}
		
		returnMap.put("flag", flag);
		returnMap.put("returnList", noRepeatList);
		returnMap.put("errorMessage", errorMessage);
		return returnMap;
	}
	
	/**
	 * 短信管理
	 * 
	 * @param model
	 * @param request
	 * @param userMobileMsg
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/sendMsglist")
	public String querySendMobileMsgList(Model model, HttpServletRequest request, @ModelAttribute("userMobileMsg") UserMobileMsg userMobileMsg, @ModelAttribute("page") PageEntity page) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<UserMobileMsg> list = userMobileMsgService.queryUserMobileMsgList(userMobileMsg, this.getPage());
			model.addAttribute("list", list);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("querySendMobileMsgList", e);
		}
		return sendMobileMsgList;
	}
	
	/**
	 * 邮箱管理
	 */
	@RequestMapping("/user/sendEmaillist")
	public String querySendEmailMsgList(Model model, HttpServletRequest request, @ModelAttribute("userEmailMsg") UserEmailMsg userEmailMsg, @ModelAttribute("page") PageEntity page) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<UserEmailMsg> list = userEmailMsgService.queryUserEmailMsgList(userEmailMsg, this.getPage());
			model.addAttribute("list", list);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("querySendEmailMsgList", e);
		}
		return sendEmailMsgList;
	}

    /**
     * 邮箱管理
     */
    @RequestMapping("/sendEmail/del")
    public String delSendEmailMsg(Model model, HttpServletRequest request,@RequestParam("id") Long id ) {
        try {
            userEmailMsgService.delUserEmailMsgById(id);
        } catch (Exception e) {
            logger.error("delSendEmailMsg", e);
        }
        return "redirect:/admin/user/sendEmaillist";
    }
	/**
	 * 删除短信
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/delMsgById/{id}")
	@ResponseBody
	public Map<String, Object> delUserMobileMsgById(Model model, @PathVariable("id") Long id) {
		try {
			userMobileMsgService.delUserMobileMsg(id);
			this.setJson(true, "删除成功", null);
		} catch (Exception e) {
			logger.error("delUserMobileMsgById", e);
			this.setJson(false, "删除失败！", null);
		}
		return json;
	}
	
	/**
	 * 查询短信详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/sendMsgInfo/{id}")
	public String querySendMobleMsgInfo(Model model, @PathVariable("id") Long id) {
		try {
			UserMobileMsg userMobileMsg = userMobileMsgService.queryUserMobileMsgById(id);
			model.addAttribute("userMobileMsg", userMobileMsg);
		} catch (Exception e) {
			logger.error("querySendMobleMsgInfo", e);
		}
		return sendMobileMsgInfo;
	}
	
	/**
	 * 修改短信
	 * @return
	 */
	@RequestMapping("/user/updateUserMsg")
	@ResponseBody
	public Map<String, Object> updateUserMsg(HttpServletRequest request) {
		try {
			UserMobileMsg userMobileMsg=new UserMobileMsg();
			userMobileMsg.setId(Integer.parseInt(request.getParameter("msgId")));
			userMobileMsg.setContent(request.getParameter("content"));
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			userMobileMsg.setSendTime(df.parse(request.getParameter("sendTime")));
			userMobileMsgService.updateUserMobileMsg(userMobileMsg);
			this.setJson(true, "修改成功", null);
		} catch (Exception e) {
			logger.error("updateUserMsg", e);
			this.setJson(false, "修改失败", null);
		}
		return json;
	}
	
	/**
	 * 查询邮件详情
	 */
	@RequestMapping("/user/sendEmailMsgInfo/{id}")
	public String querySendEmailMsgInfo(Model model, @PathVariable("id") Long id) {
		try {
			UserEmailMsg userEmailMsg = userEmailMsgService.queryUserEmailMsgById(id);
			model.addAttribute("userEmailMsg", userEmailMsg);
		} catch (Exception e) {
			logger.error("querySendMobleMsgInfo", e);
		}
		return sendEmailMsgInfo;
	}
    /**
     * 修改定时未发送的邮件
     */
    @RequestMapping("/sendEmailMsg/update")
    @ResponseBody
    public Object updateSendEmailMsgInfo(HttpServletRequest request,UserEmailMsg userEmailMsg) {
        try {
            String sendTime = request.getParameter("sendTime");
            if("".equals(sendTime)){
                this.setJson(false, "请输入发送时间", "");
                return json;
            }
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sendtime = formatDate.parse(sendTime);
            if(!sendtime.after(new Date())){
                this.setJson(false, "定时发送的时间要大于当前日期", "");
                return json;
            }

            userEmailMsgService.updateUserEmailMsgById(userEmailMsg);
            this.setJson(true,"成功","");
        } catch (Exception e) {
            logger.error("querySendMobleMsgInfo", e);
        }
        return json;
    }
	/**
	 * 查询登陆log日志
	 * 
	 * @param request
	 * @param model
	 * @param userLoginLog
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/loginlog")
	public String queryUserLoginLogList(HttpServletRequest request, Model model, @ModelAttribute UserLoginLog userLoginLog, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页
			this.setPage(page);
			this.getPage().setPageSize(10);
			// 查询登陆log日志
			List<UserLoginLog> userLoginLogList = userLoginLogService.getUserLoginLogListPage(userLoginLog, this.getPage());
			model.addAttribute("userLoginLogList", userLoginLogList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("queryUserLoginLogList", e);
			return setExceptionRequest(request, e);
		}
		return toUserLoginLog;
	}
	/**
     * 跳转到小组发消息页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/toSendSystemMessages")
    public ModelAndView senSystemMessages(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(senSystemMessages);
        return modelAndView;
    }
	
	/**
     * 发送系统消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/sendJoinGroup")
    @ResponseBody
    public Map<String, Object> sendSystemInform(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String content = request.getParameter("content");// 发送系统消息的内容
            String type = request.getParameter("type");//发送类型0 普通消息 1优惠券消息
            MsgSystem msgReceive = new MsgSystem();
            msgReceive.setType(Integer.parseInt(type));
            msgReceive.setContent(content);// 添加站内信的内容
            msgReceive.setUpdateTime(new Date());// 更新时间s
            msgReceive.setAddTime(new Date());// 添加时间
            msgSystemService.addMsgSystem(msgReceive);
			//msgReceiveService.addSystemMessage(content);
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemInform", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
    /**
     * 发送优惠券
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/toSendCoupon")
    public ModelAndView toSendCoupon(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(sendCoupon);
        return modelAndView;
    }
    /**
     * 批量发送  系统消息
     *
     * @param request
     * @param content
     * @param cusId
     * @return
     */
    @RequestMapping(value = "/letter/sendSystemInfoByCusIds")
    @ResponseBody
    public Map<String, Object> sendSystemInfoByCusIds(@ModelAttribute User user,HttpServletRequest request,@ModelAttribute("page")PageEntity page) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	page.setPageSize(10000);
        	// 查询学员列表
        	List<User> list =userService.getUserListAndCourse(user,page);
        	
        	String content = request.getParameter("messsageContent");// 发送系统消息的内容
        	if (list==null||list.size()==0) {
        		map.put("message", "没有符合条件的会员");
			}else {
				List<MsgReceive> msgrcList = new ArrayList<MsgReceive>();
				for (int i = 0; i < list.size(); i++) {
	                 MsgReceive msgReceive1 = new MsgReceive();
	                 msgReceive1.setContent(content);
	                 msgReceive1.setAddTime(new Date());
	                 msgReceive1.setReceivingCusId(list.get(i).getId());
	                 msgReceive1.setStatus(LetterConstans.LETTER_STATUS_READ);
	                 msgReceive1.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
	                 msgReceive1.setUpdateTime(new Date());
	                 msgReceive1.setShowname("");
	                 msgReceive1.setCusId(0L);
	                 msgrcList.add(msgReceive1);
				}
				//发送
				msgReceiveService.addMsgReceiveBatch(msgrcList);
				map.put("message", "success");
			}
            
        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemInfoByCusIds", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
	
	/**
	 * 获取用户信息 ById
	 * @return
	 */
	@RequestMapping("/user/getUserInfo/{id}")
	public String getUserInfo(Model model,@PathVariable("id") Long id){
		try{
			User user =userService.getUserById(id);
			model.addAttribute("user", user);
		}catch (Exception e) {
			logger.error("getUserInfo:"+e);
		}
		return "路径";
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/updateUserInfo")
	@ResponseBody
	public Map<String,Object> updateUserInfo(@ModelAttribute("user") User user){
		try{
			// 修改用户信息
			if(ObjectUtils.isNull(user)){
				this.setJson(false, "信息错误", null);
				return json;
			}
			
			userService.updateUser(user);
			this.setJson(true,"修改成功",null);
		}catch (Exception e) {
			logger.error("updateUserInfo:"+e);
		}
		return json;
	}
}
