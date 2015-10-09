package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.QueryGroupCondition
 * @description 查询Group条件辅助类
 * @Create Date : 2013-12-14 下午2:38:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryGroupCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long groupId;//组id
    private Long thirdGroupId;//组子id
}