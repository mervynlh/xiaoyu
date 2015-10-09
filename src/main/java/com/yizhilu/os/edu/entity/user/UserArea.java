package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserArea implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; // 主键
    private Long parentId=-1l; 
    private String areaName; // 地区名
    private String areaNameEn; // 地区英文名
    private int areaType; // 区域类型  0 中国   1省份  2市区
    private int checkShow; // 是否显示  1不显示   2显示

    private List<UserArea> areaList; // 首页展示区县列表
    private String proName; // 省份名
}
