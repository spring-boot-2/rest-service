
package com.dxc.ddc.springboot.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带普通结果数据的返回结果
 * 
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper=true)
@Data
public class GeneralContentResult<T> extends GeneralResult {
    
    private static final long serialVersionUID = -8104955278209569617L;

    private T resultContent;
}
