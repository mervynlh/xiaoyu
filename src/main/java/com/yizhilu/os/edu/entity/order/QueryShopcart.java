package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryShopcart implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6757060331190184782L;
    private Long id;
    private Long goodsid;
    private Long userid;
    private Long type;
    private java.util.Date addTime;
}
