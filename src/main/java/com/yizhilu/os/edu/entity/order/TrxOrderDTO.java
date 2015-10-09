package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrxOrderDTO  extends Trxorder implements  Serializable{
	private static final long serialVersionUID = 1L;
	private List<TrxorderDetailDTO> trxorderDetailList;
	
	
}
