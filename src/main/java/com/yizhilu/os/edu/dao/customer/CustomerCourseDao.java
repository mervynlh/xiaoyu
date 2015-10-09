package com.yizhilu.os.edu.dao.customer;
import java.util.List;

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
public interface CustomerCourseDao {

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

    /**
     * 根据条件获取CustomerCourse列表
     * @param QueryCustomerCourse 查询条件
     * @return List<QueryCustomerCourse>
     */
    public List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse,PageEntity page);
    
    /**
     * 根据查询记录 获取前台  自定义课程排行榜
     * @param number
     * @return List<CustomerCourse>
     */
    public List<CustomerCourse> getCustomCourseRankings(int number);
    
    /**
     * 前台查询自定义课程根据人数最多
     * @param number 记录数
     * @return List<QueryCustomerCourse>
     */
    public List<QueryCustomerCourse> getPageQueryCusCourseList(int number);
    /**
     * 查询参与自定义课程的人数
     * @return number
     */
    
    public int queryCusCourseJoinNum();
    
    /**
     * 查询投票记录  根据条件
     * @param queryCusCourseRecord
     * @return list
     */
    
    public List<CustomerCourseRecord> queryCustomerCourseRecordList(QueryCusCourseRecord queryCusCourseRecord);
    
    /**
     * 添加自定义投票记录
     * @param customerCourseRecord
     */
    public void addnewCusCourseRecord(CustomerCourseRecord customerCourseRecord);
    
}