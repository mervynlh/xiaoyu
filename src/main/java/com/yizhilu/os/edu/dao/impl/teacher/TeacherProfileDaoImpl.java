package com.yizhilu.os.edu.dao.impl.teacher;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.teacher.TeacherProfileDao;
import com.yizhilu.os.edu.entity.teacher.TeacherProfile;

@Repository("teacherProfileDao")
public class TeacherProfileDaoImpl extends GenericDaoImpl implements TeacherProfileDao {

	@Override
	public TeacherProfile queryTeacherProfileById(Long id) {
		
		return this.selectOne("TeacherProfileMapper.queryTeacherProfileById", id) ;
	}

	@Override
	public void updateTeacherProfile(Long teacherId, String type, int count) {
		
		   Map<String, Object> map = new HashMap<String, Object>();
	       map.put("type", type);
	       map.put("count", count);
	       map.put("teacherId", teacherId);
		this.update("TeacherProfileMapper.updateTeacherProfile",map);
		
	}

	@Override
	public void addTeacherProfile(TeacherProfile teacherProfile) {
		this.insert("TeacherProfileMapper.addTeacherProfile", teacherProfile);
		
	}

	@Override
	public void updateTeacherAssessAdd(Long teacherId) {
		this.update("TeacherProfileMapper.updateTeacherAssessAdd", teacherId);
	}

	@Override
	public void updateTeacherCollectAdd(Long teacherId) {
		this.update("TeacherProfileMapper.updateTeacherCollectAdd", teacherId);
	}

	@Override
	public void updateTeacherCollectSub(Long teacherId) {
		this.update("TeacherProfileMapper.updateTeacherCollectSub", teacherId);
	}

	/**
	 * 查询教师评星等级
	 * @param teacherId
	 * @return
	 */
	public Double queryTeacherStar(Long teacherId){
		return this.selectOne("TeacherProfileMapper.queryTeacherStar", teacherId);
	}

	@Override
	public TeacherProfile queryTeacherProfileByTeacher(long teacherId) {
		// TODO Auto-generated method stub
		return selectOne("TeacherProfileMapper.queryTeacherProfileByTeacher", teacherId);
	}

	@Override
	public void updateTeacherBrowseNum(Long teacherId) {
		// TODO Auto-generated method stub
		update("TeacherProfileMapper.updateTeacherBrowseNum", teacherId);
	}

	@Override
	public void updateTeacherGoodNum(Long teacherId) {
		// TODO Auto-generated method stub
		update("TeacherProfileMapper.updateTeacherGoodNum",teacherId);
	}

	@Override
	public void updateTeacherMiddleNum(Long teacherId) {
		// TODO Auto-generated method stub
		update("TeacherProfileMapper.updateTeacherMiddleNum",teacherId);
	}

	@Override
	public void updateTeacherBadNum(Long teacherId) {
		// TODO Auto-generated method stub
		update("TeacherProfileMapper.updateTeacherBadNum",teacherId);
	}

	@Override
	public void updateTeacherStar(Long teacherId) {
		// TODO Auto-generated method stub
		update("TeacherProfileMapper.updateTeacherStar",teacherId);
	}

	@Override
	public void updateProfileDescriptionAttributeSpeed(Long teacherId) {
		// TODO Auto-generated method stub
		update("TeacherProfileMapper.updateProfileDescriptionAttributeSpeed",teacherId);
	}

	/**
	 * 教师资质审核（教师认证）
	 * @param teacherProfile
	 */
	public void teacherAptitudeAttestation(TeacherProfile teacherProfile){
		this.update("TeacherProfileMapper.teacherAptitudeAttestation", teacherProfile);
	}
	/**
	 * 修改教师的学生人数
	 * @param teacherId
	 * @param userId
	 * @param addOrsub
	 */
	public void updateTeacherProfileStudentNum(Long teacherId,String addOrsub){
		Map<String, Object> map = new HashMap<>();
		map.put("teacherId", teacherId);
		map.put("addOrsub", addOrsub);
		this.update("TeacherProfileMapper.updateTeacherProfileStudentNum", map);
	}

}
