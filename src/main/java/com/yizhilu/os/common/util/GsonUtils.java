/**
 * 
 */
package com.yizhilu.os.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Gson 工具类
 */
public class GsonUtils {
    /**
     * 字符串转map
     */
    public static Map<String, Object> StringToMaps(String jsonString) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //判断字符串为空
            if(StringUtils.isEmpty(jsonString)&&"[]".equals(jsonString)){
                return map;
            }
            //转map
            Gson gson = new Gson();
            map = gson.fromJson(jsonString, new TypeToken<Map<String,Object>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
