package com.yizhilu.os.common.service;

import com.yizhilu.os.core.service.cache.MemCache;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.yizhilu.os.common.service.BaseService
 * @description
 * @Create Date : 2014-6-13 上午8:59:59
 */
public class BaseService {
    protected MemCache memCache = MemCache.getInstance();
}
