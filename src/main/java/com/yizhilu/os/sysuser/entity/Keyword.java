package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Keyword implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7871200987819633793L;
    private Long id;
    private String keyword;
}
