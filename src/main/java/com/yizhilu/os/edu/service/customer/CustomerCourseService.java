package com.yizhilu.os.edu.service.customer;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.customer.CustomerCourse;
import com.yizhilu.os.edu.entity.customer.CustomerCourseRecord;
import com.yizhilu.os.edu.entity.customer.QueryCusCourseRecord;
import com.yizhilu.os.edu.entity.customer.QueryCustomerCourse;

/**
 * CustomerCourse管理接口
 * User: qinggang.liu
 * Date: 2014-09-24
 */
public interface CustomerCourseService {

    /**
     * 添加CustomerCourse
     * @param customerCourse 要添加的CustomerCourse
     * @return id
     */
    public java.lang.Long addCustomerCourse(CustomerCourse customerCourse);

    /**
     * 根据id删除一个CustomerCourse
     * @param id 要删除的id
     */
    public void deleteCustomerCourseById(Long id);

    /**
     * 修改CustomerCourse
     * @param customerCourse 要修改的CustomerCourse
     */
    public void updateCustomerCourse(CustomerCourse customerCourse);

    /**
     * 根据id获取单个CustomerCourse对象
     * @param id 要查询的id
     * @return CustomerCourse
     */
    public CustomerCourse getCustomerCourseById(Long id);

    /**分页查询
     * 根据条件获取CustomerCourse列表
     * @param queryCustomerCourse 查询条件
     * @return List<QueryCustomerCourse>
     */
    public List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse,PageEntity page)throws Exception;
    
    /**
     * 根据查询记录 获取前台  自定义课程排行榜
     * @param number
     * @return List<CustomerCourse>
     * @throws Exception 
     * @author guozhenhzou
     */
    public List<CustomerCourse> getCustomCourseRankings(int number) throws Exception;
    
    /**
     * 前台查询自定义课程根据人数最多
     * @param number 记录数
     * @return List<QueryCustomerCourse>
     * @author guozhenzhou
     */
    public List<QueryCustomerCourse> getPageQueryCusCourseList(int number) throws Exception;
    
    
    
    /**
     * 查询参与自定义课程人数
     * 
     */
    public int queryJoinNum();
    
    
    /**
     * 学员是否已经投票过自定义课程
     * @return boolean
     * @author guozhenzhou
     */
    public boolean queryuserIsjoinCourse(QueryCusCourseRecord queryCusCourseRecord);
    
    /**
     * 添加自定义课程投票记录
     * @param customerCourseRecord
     * @version 2014-09-26
     * @author guozhenzhou
     */
    public void createCustomerCourseRecord(CustomerCourseRecord customerCourseRecord);
}