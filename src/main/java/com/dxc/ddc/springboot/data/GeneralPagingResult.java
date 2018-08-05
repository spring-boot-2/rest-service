package com.dxc.ddc.springboot.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带分页结果数据的返回结果.
 * 
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralPagingResult<T> extends GeneralContentResult<T> {
	
	private static final long serialVersionUID = 1540315626080625718L;
	
	/**
	 * pageInfo: For paging result ONLY.
	 */
	private PageInfo pageInfo;
}
