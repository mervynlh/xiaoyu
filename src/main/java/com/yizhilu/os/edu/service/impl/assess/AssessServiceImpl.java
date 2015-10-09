package com.yizhilu.os.edu.service.impl.assess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.constants.enums.AssessTypeEnum;
import com.yizhilu.os.edu.dao.assess.AssessDao;
import com.yizhilu.os.edu.dao.teacher.TeacherProfileDao;
import com.yizhilu.os.edu.entity.assess.Assess;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.assess.AssessType;
import com.yizhilu.os.edu.entity.assess.QueryAssess;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.service.assess.AssessService;
import com.yizhilu.os.edu.service.order.TrxorderService;

/**
 * @author wangzhuang
 */
@Service("AssessService")
public class AssessServiceImpl implements AssessService {

	@Autowired
	private AssessDao assessDao;
	@Autowired
	private TeacherProfileDao teacherProfileDao;
	@Autowired
	private TrxorderService trxorderService;
	
	MemCache memCache = MemCache.getInstance();

	public Long addAssess(Assess assess) {
		assess.setCreateTime(new Date());
		Trxorder trxorder = new Trxorder();
		trxorder.setId(assess.getOrderId());
		if(assess.getStatus()==1){// 对学生评价
			trxorder.setAssessTeatostu(1L);
			trxorderService.updateTrxorderAssessTeaToStu(trxorder);
		}else if(assess.getStatus()==2){// 对老师评价
			teacherProfileDao.updateTeacherAssessAdd(assess.getTeacherId());
			if (assess.getType() == 2) {
				teacherProfileDao.updateTeacherMiddleNum(assess.getTeacherId());
			} else if (assess.getType() == 3) {
				teacherProfileDao.updateTeacherBadNum(assess.getTeacherId());
			} else {
				teacherProfileDao.updateTeacherGoodNum(assess.getTeacherId());
			}
			// 计算更新拓展表的评分
			teacherProfileDao.updateProfileDescriptionAttributeSpeed(assess.getTeacherId());
			// 计算更新拓展表的星级
			teacherProfileDao.updateTeacherStar(assess.getTeacherId());
			trxorder.setAssessStutotea(1L);
			trxorderService.updateTrxorderAssessStuToTea(trxorder);
		}
		return assessDao.addAssess(assess);
	}

	public List<Assess> getAssessListByType(Long type) {
		return assessDao.getAssessListByType(type);
	}

	public void deleteAssessById(String ids) {
		assessDao.deleteAssessById(ids);
	}

	@Override
	public Assess getAssessByOrderId(Long orderId) {
		return this.assessDao.getAssessByOrderId(orderId);
	}

	@Override
	public List<AssessDto> queryAssessListByCondition(QueryAssess query, PageEntity page) {
		// TODO Auto-generated method stub
		return assessDao.queryAssessListByCondition(query, page);
	}

	@Override
	public List<AssessDto> indexAssess(int num ,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		List<AssessDto> assessList = (List<AssessDto>) memCache.get(MemConstans.ASSESS_INDEX);
		if (ObjectUtils.isNull(assessList)) {

			// 从评论文件读取评论信息
			assessList = getAssessFileContent(request);

			if (ObjectUtils.isNotNull(assessList)) {
				memCache.set(MemConstans.ASSESS_INDEX, assessList, MemConstans.ASSESS_INDEX_TIME);
			}
		}
		return assessList;
	}

	/**
	 * 从评论文件中读取评论信息
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<AssessDto> getAssessFileContent(HttpServletRequest request) throws Exception {
		String encoding = "UTF-8";
		File file = new File(request.getSession().getServletContext().getRealPath("/")+CommonConstants.assessFile);
		String content = "";
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = "";
			while ((lineTxt = bufferedReader.readLine()) != null) {
				content += lineTxt;
			}
			read.close();
		}

		// 得到评论集合
		List<AssessDto> assessList = new ArrayList<>();
		if (StringUtils.isNotEmpty(content)) {
			String role = content;
			Pattern p = Pattern.compile("\\{.*?\\}");// 查找规则公式中大括号以内的字符
			Matcher m = p.matcher(role);
			while (m.find()) {// 遍历找到的所有大括号
				String param = m.group().replaceAll("\\{", "").replaceAll("\\}", "");// 去掉括号
				String[] attrValue = param.split(",");
				AssessDto assessDto = new AssessDto();
				assessDto.setNickname(attrValue[0]);
				assessDto.setAvatar(attrValue[1]);
				assessDto.setCourseName(attrValue[2]);
				assessDto.setCreateTime(DateUtils.strToDate(attrValue[3], "yyyy-MM-dd HH:mm:ss"));
				assessDto.setContent(attrValue[4]);
				assessList.add(assessDto);
			}

		}
		return assessList;
	}

	@Override
	public Map<String, Object> getCountByType(Long userId, Long teacherId, Long status) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapType = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("teacherId", teacherId);
		map.put("status", status);
		List<AssessType> list = this.assessDao.getCountByType(map);

		if (ObjectUtils.isNull(list)) {
			mapType.put("GOOD", 0);// 好评
			mapType.put("AVERAGE", 0);// 中评
			mapType.put("BAD", 0);// 差评
		} else {
			for (int i = 0; i < list.size(); i++) {
				mapType.put(list.get(i).getType().toString(), list.get(i).getCount());
			}
			mapType.put("GOOD", mapType.containsKey(AssessTypeEnum.GOOD) ? mapType.get(AssessTypeEnum.GOOD) : 0);// 好评
			mapType.put("AVERAGE",
					mapType.containsKey(AssessTypeEnum.AVERAGE) ? mapType.get(AssessTypeEnum.AVERAGE) : 0);// 中评
			mapType.put("BAD", mapType.containsKey(AssessTypeEnum.BAD) ? mapType.get(AssessTypeEnum.BAD) : 0);// 差评
		}

		return mapType;
	}

	@Override
	public List<AssessDto> getAssessListByTypeStatus(Long userId, Long teacherId, Long type, Long status,PageEntity page) {
		Assess assess = new Assess();
		assess.setUserId(userId);
		assess.setTeacherId(teacherId);
		assess.setType(type);
		assess.setStatus(status);
		return this.assessDao.getAssessListByTypeStatus(assess, page);
	}
}
