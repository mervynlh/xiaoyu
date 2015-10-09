package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.QueryFunctionCondition
 * @description 查询功能对象所使用的条件类
 * @Create Date : 2013-12-14 下午2:38:09
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryFunctionCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7055484731686817657L;
    private Long parentFunctionId;// 父功能id
    private int functionTypeId;// 功能类型id
    private int functionApplyScopeId;// 功能适用范围id
    private Integer status;// 功能状态id

}