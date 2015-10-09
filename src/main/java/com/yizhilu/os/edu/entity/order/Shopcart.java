package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.edu.entity.course.Course;

@Data
@EqualsAndHashCode(callSuper = false)
public class Shopcart implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6757060331190184782L;
    private Long id;
    private Long goodsid;// 商品id
    private Long userid;
    private Long type;//1课程 2套餐（备用）
    private java.util.Date addTime;
    private Course course;
}
