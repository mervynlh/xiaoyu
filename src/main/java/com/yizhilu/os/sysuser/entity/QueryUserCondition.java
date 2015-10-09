package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.QueryUserCondition
 * @description 查询系统用户条件类
 * @Create Date : 2013-12-14 下午2:42:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryUserCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long userId;//用户id
    private Long groupId;//组id
    private int userType = -1;//用户类型
    private String searchKey;//页面关键字
    private String searchType;//页面类型

    List<SysGroup> groupList = new ArrayList<SysGroup>();
}