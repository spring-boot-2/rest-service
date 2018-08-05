
package com.dxc.ddc.springboot.data;

import java.io.Serializable;

import lombok.Data;

/**
 * 不带内容的返回结果.
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Data
public class GeneralResult implements Serializable {

	private static final long serialVersionUID = 908203438033789670L;

	private String resultCode;

	private String detailDescription;
}
