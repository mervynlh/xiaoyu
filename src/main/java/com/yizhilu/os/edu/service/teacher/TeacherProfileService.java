package com.yizhilu.os.edu.service.teacher;

import com.yizhilu.os.edu.entity.teacher.TeacherProfile;

public interface TeacherProfileService {
	
	/**
	 * 根据Id查询教师拓展
	 */
	 
	public TeacherProfile queryTeacherProfileById(Long id);
	
	/**
	 * 修改教师拓展
	 */
	public void updateTeacherProfile(Long id,String type,int count);
	
	/**
	 * 添加教师拓展
	 */
	
	public void addTeacherProfile(TeacherProfile teacherProfile);
	/**
	 * 增加教师评价数
	 * @param teacherId 教师id
	 */
	public void updateTeacherAssessAdd(Long teacherId);
	
	/**
	 * 增加教师收藏数
	 * @param teacherId 教师id
	 */
	public void updateTeacherCollectAdd(Long teacherId);
	
	/**
	 * 减少教师收藏数
	 * @param teacherId 教师id
	 */
	public void updateTeacherCollectSub(Long teacherId);
	
	/**
	 * 查询教师评星等级
	 * @param teacherId
	 * @return
	 */
	public Double queryTeacherStar(Long teacherId);
	/**
	 * 查询教师拓展信息
	 * @param teacherId
	 * @return
	 */
	public TeacherProfile queryTeacherProfileByTeacher(long teacherId);
	/**
	 * 增加教师浏览量
	 * @param teacherId
	 */
	public void updateTeacherBrowseNum(Long teacherId);
	/**
	 * 增加教师好评
	 * @param teacherId
	 */
	public void updateTeacherGoodNum(Long teacherId);
	/**
	 * 增加教师中评
	 * @param teacherId
	 */
	public void updateTeacherMiddleNum(Long teacherId);
	/**
	 * 增加教师差评
	 * @param teacherId
	 */
	public void updateTeacherBadNum(Long teacherId);
	/**
	 * 教师评星
	 * @param teacherId
	 */
	public void updateTeacherStar(Long teacherId);
	/**
	 * 评价后计算更新教师拓展数据
	 * @param teacherId
	 */
	public void updateProfileDescriptionAttributeSpeed(Long teacherId);

	/**
	 * 教师资质审核（教师认证）
	 * @param teacherProfile
	 */
	public void teacherAptitudeAttestation(TeacherProfile teacherProfile);
	/**
	 * 教师的学生人数
	 * @param teacherId
	 * @param addOrsub "+" or "-"
	 */
	public void updateTeacherProfileStudentNum(Long teacherId,String addOrsub);
}
