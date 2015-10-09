package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.KeyValueDTO
 * @description 键值类
 * @Create Date : 2013-12-14 下午2:37:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KeyValueDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7773297939894138862L;
    private int key;
    private String value;
}
