package com.yizhilu.os.edu.entity.userprofile;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile implements Serializable{
    private int id;
    private String name;
    private String value;
    private String profiletype;
    private Long userid;
    private java.util.Date profiledate;
}
