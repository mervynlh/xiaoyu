package com.yizhilu.os.edu.dao.impl.customer;

import java.util.List;
import com.yizhilu.os.edu.entity.customer.CustomerCourse;
import com.yizhilu.os.edu.entity.customer.CustomerCourseRecord;
import com.yizhilu.os.edu.entity.customer.QueryCusCourseRecord;
import com.yizhilu.os.edu.entity.customer.QueryCustomerCourse;
import com.yizhilu.os.edu.dao.customer.CustomerCourseDao;
import org.springframework.stereotype.Repository;
import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * CustomerCourse
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-09-24
 */
 @Repository("customerCourseDao")
public class CustomerCourseDaoImpl extends GenericDaoImpl implements CustomerCourseDao{

    public java.lang.Long addCustomerCourse(CustomerCourse customerCourse) {
        return this.insert("CustomerCourseMapper.createCustomerCourse",customerCourse);
    }

    public void deleteCustomerCourseById(Long id){
        this.delete("CustomerCourseMapper.deleteCustomerCourseById",id);
    }

    public void updateCustomerCourse(CustomerCourse customerCourse) {
        this.update("CustomerCourseMapper.updateCustomerCourse",customerCourse);
    }

    public CustomerCourse getCustomerCourseById(Long id) {
        return this.selectOne("CustomerCourseMapper.getCustomerCourseById",id);
    }

    public List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse,PageEntity page) {
        return this.queryForListPage("CustomerCourseMapper.getCustomerCourseList",queryCustomerCourse,page);
    }
    
    public List<CustomerCourse> getCustomCourseRankings(int number){
    	
    	return this.selectList("CustomerCourseMapper.getCustomCourseRankings", number);
    }
    
    public List<QueryCustomerCourse> getPageQueryCusCourseList(int number){
    	return this.selectList("CustomerCourseMapper.getWebCustomerCourseList", number);
    }
    
    public int queryCusCourseJoinNum(){
    	
    	return this.selectOne("CustomerCourseMapper.selectJoinCustomer", null);
    }
    
    public List<CustomerCourseRecord> queryCustomerCourseRecordList(QueryCusCourseRecord queryCusCourseRecord){
    	
    	return this.selectList("CustomerCourseMapper.queryCusCourseRecordForList", queryCusCourseRecord);
    }
    
    public void addnewCusCourseRecord(CustomerCourseRecord customerCourseRecord){
    	
    	this.insert("CustomerCourseMapper.createcusCourseRecord", customerCourseRecord);
    }
}
