package com.yizhilu.os.edu.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yizhilu.os.edu.entity.order.Shopcart;

/**
 * @ClassName com.yizhilu.os.edu.service.impl.order.ShopCartUtil
 * @description 购物车工具类
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2014-6-25 下午3:01:20
 */
public class ShopCartUtil {

    /**
     * @param args
     */
    private static final String Goods_ID = "goodsid";
    private static final String TYPE = "type";

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    public static JsonParser jsonParser = new JsonParser();

    /**
     * 添加ShopItem 到json, 然后转换为json字符串
     * 
     * @param shopItem
     * @param json
     * @return
     */
    public static String addItem(Shopcart shopItem, String json) {

        List<Shopcart> itemList = null;
        if (isNull(json)) {
            itemList = new ArrayList<Shopcart>();
            itemList.add(shopItem);
        } else {
            itemList = query(json);//json转换为list
            if (itemList == null) {
                return json; // 异常发生
            }
            int itemIndex = queryIndex(shopItem.getGoodsid(), json, shopItem.getType());
            if (itemIndex == -2) { // -2表示查找不到
                itemList.add(shopItem);
            }
        }
        return writeJson(itemList);
    }

    /**
     * 从json 串中获取相应ShopItem 的索引值
     * 
     * @param goodsId
     * @param json
     * @return
     */
    public static int queryIndex(Long goodsId, String json, Long type) {

        if (null == json || goodsId == null) {
            return -1; // 数据 异常
        }
        JsonArray array = jsonParser.parse(json).getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            if ((object.get(Goods_ID).getAsInt() == goodsId.intValue()) && (type == object.get(TYPE).getAsInt()))
                return i; // 正确的索引值
        }
        return -2; // 查找不到
    }

    /**
     * 转换json 为List<Shopcart> 的集合
     * 
     * @param json
     * @return
     */
    public static List<Shopcart> query(String json) {

        if (isNull(json)) {
            return null;
        }
        List<Shopcart> itemList = new ArrayList<Shopcart>();
        JsonArray array = jsonParser.parse(json).getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            Shopcart item = new Shopcart();
            item.setId(0L);
            item.setGoodsid(object.get(Goods_ID).getAsLong());
            item.setType(object.get(TYPE).getAsLong());
            itemList.add(item);
        }
        if ((itemList != null && itemList.size() == 0) || itemList == null) {
            return null;
        }
        return itemList;
    }

    /**
     * 转换list<ShopItem> 为JSONArray 的形式,然后再转为字符串
     * 
     * @param itemList
     * @return
     */
    public static String writeJson(List<Shopcart> itemList) {
        if (null == itemList || itemList.size() == 0) {
            return null;
        }
        return gson.toJson(itemList).toString();
    }

    
    /**
     * 根据goodsId 来从json中移除一个Shopcart
     * 
     * @param goodsId
     * @param json
     * @return
     */
    public static String remove(Long goodsId, Long type,String json) {

        int itemIndex = queryIndex(goodsId, json,type);
        List<Shopcart> itemList = query(json);
        if (itemList == null) {
            return json;
        }
        if (itemIndex != -1 && itemIndex != -2) {
            itemList.remove(itemIndex);
        }
        return writeJson(itemList);
    }
    /**
     * 删除全部的ShopItem
     * 
     * @param json
     * @return
     */
    public static String removeAll(String json) {
        return writeJson(null);
    }
    
    /**
     * 转义Json, 去掉Json中多余的转义符
     * 
     * @param json
     * @return
     */
    public static String escape(String json) {

        if (null == json || "".equals(json) || json.trim() == null
                || json.trim() == "[]") {
            return null;
        }
        return json.replace("\\", "");
    }
    
    /**
     * 主要是用来判断Json 字符串是否为空值
     * 
     * @param string
     * @return
     */
    public static boolean isNull(String string) {

        if (null == string || "".equals(string) || string.trim() == null
                || string.trim() == "[]") {
            return true;
        }
        return false;
    }
    
}
