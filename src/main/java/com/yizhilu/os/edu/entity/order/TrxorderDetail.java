package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.Teacher;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderDetail implements Serializable{
    /**
     * 流水
     */
    private static final long serialVersionUID = 7081379317366445288L;
    private Long id;
    private Long userId;//用户id
    private Long courseId;//相关联的课程id（前台快照）
    private Long trxorderId;//交易订单ID
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功时间
    private java.math.BigDecimal sourcePrice;//原价格（前台快照）
    private java.math.BigDecimal currentPrice;//销售价格（前台快照）(退款应退金额)平均算出来的金额
    private String courseName;//课时名称
    private String trxStatus;//订单状态（前台goods快照）
    private String authStatus;//课程状态（INIT，SUCCESS，REFUND，CLOSED，LOSED）
    private String requestId;//订单请求号
    private String description;//描述
    private Long version=1L;//乐观锁版本号
    private java.util.Date lastUpdateTime;//最后更新时间
    private java.util.Date refundTime;// 退课时间
    private List<Teacher> teacherList;//教师列表
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private Long currentLession;//当前课时
    private Long lessionNum;//总课时
    private Long status;//状态  常量类CourseStatus  1.待确认时间，2待上课，3待确认课酬4.待评价5.已评价6.已上课7.已取消 
    private String ifConfirm;//是否确认时间 ALREADYCONFIRM 已确认 TEAUNCONFIRM 教师未确认 STUUNCONFIRM 学生未确认
    private Long courseType;//课程类型 1.一对一，2大小班
    private Date authTime;//上课后流水的的过期时间
    private Long teacherId;//教师id 
    private Course course;//课程信息
    private Long addressId;// 上课地址id

    private String teacherSummary;// 教师小结
    private String userSummary;// 学生小结
    private String courseModel; // 上课方式
    private String studyAddress;// 上课地址
    
    private Long subjectId;// 阶段id
    private Long gradeId;// 年级id
    private Long majorId;// 科目id
    
    private String subjectName;//阶段名称
    private String gradeName;//年级名称
    private String majorName;//科目名称
    

}
