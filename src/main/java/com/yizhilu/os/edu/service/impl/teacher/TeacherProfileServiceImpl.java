package com.yizhilu.os.edu.service.impl.teacher;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.teacher.TeacherProfileDao;
import com.yizhilu.os.edu.entity.teacher.TeacherProfile;
import com.yizhilu.os.edu.service.teacher.TeacherProfileService;

@Service("teacherProfileService")
public class TeacherProfileServiceImpl implements TeacherProfileService {

	@Autowired
	private TeacherProfileDao teacherProfileDao;
	@Override
	public TeacherProfile queryTeacherProfileById(Long id) {
		// TODO Auto-generated method stub
		return teacherProfileDao.queryTeacherProfileById(id);
	}

	@Override
	public void updateTeacherProfile(Long id, String type, int count) {
		teacherProfileDao.updateTeacherProfile(id, type, count);
		
	}

	@Override
	public void addTeacherProfile(TeacherProfile teacherProfile) {
		teacherProfile.setStar(5);
		teacherProfile.setDescription(new BigDecimal(5l));
		teacherProfile.setAttribute(new BigDecimal(5l));
		teacherProfile.setSpeed(new BigDecimal(5l));
		teacherProfileDao.addTeacherProfile(teacherProfile);
	}

	@Override
	public void updateTeacherAssessAdd(Long teacherId) {
		this.teacherProfileDao.updateTeacherAssessAdd(teacherId);
	}

	@Override
	public void updateTeacherCollectAdd(Long teacherId) {
		this.teacherProfileDao.updateTeacherCollectAdd(teacherId);
	}

	@Override
	public void updateTeacherCollectSub(Long teacherId) {
		this.teacherProfileDao.updateTeacherCollectSub(teacherId);
	}

	@Override
	public Double queryTeacherStar(Long teacherId) {
		return teacherProfileDao.queryTeacherStar(teacherId);
	}

	@Override
	public TeacherProfile queryTeacherProfileByTeacher(long teacherId) {
		// TODO Auto-generated method stub
		return teacherProfileDao.queryTeacherProfileByTeacher(teacherId);
	}

	@Override
	public void updateTeacherBrowseNum(Long teacherId) {
		// TODO Auto-generated method stub
		teacherProfileDao.updateTeacherBrowseNum(teacherId);
	}

	@Override
	public void updateTeacherGoodNum(Long teacherId) {
		// TODO Auto-generated method stub
		teacherProfileDao.updateTeacherGoodNum(teacherId);
	}

	@Override
	public void updateTeacherMiddleNum(Long teacherId) {
		// TODO Auto-generated method stub
		teacherProfileDao.updateTeacherMiddleNum(teacherId);
	}

	@Override
	public void updateTeacherBadNum(Long teacherId) {
		// TODO Auto-generated method stub
		teacherProfileDao.updateTeacherBadNum(teacherId);
	}

	@Override
	public void updateTeacherStar(Long teacherId) {
		// TODO Auto-generated method stub
		teacherProfileDao.updateTeacherStar(teacherId);
	}

	@Override
	public void updateProfileDescriptionAttributeSpeed(Long teacherId) {
		// TODO Auto-generated method stub
		teacherProfileDao.updateProfileDescriptionAttributeSpeed(teacherId);
	}

	/**
	 * 教师资质审核（教师认证）
	 * @param teacherProfile
	 */
	public void teacherAptitudeAttestation(TeacherProfile teacherProfile){
		teacherProfileDao.teacherAptitudeAttestation(teacherProfile);
	}
	/**
	 * 教师的学生人数
	 * @param teacherId
	 * @param userId
	 * @param string
	 */
	public void updateTeacherProfileStudentNum(Long teacherId, String addOrsub){
		teacherProfileDao.updateTeacherProfileStudentNum(teacherId,addOrsub);
	}
}
