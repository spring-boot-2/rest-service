package com.dxc.ddc.springboot.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public class UserUpdate extends UserInfo {
	
	private static final long serialVersionUID = -5134489882210491339L;
	
	@Override
	@JsonIgnore
	public String getId() {
		return super.getId();
	}

	@Override
	@JsonIgnore
	public String getStatusName() {
		return super.getStatusName();
	}
	
	@Override
	@JsonIgnore
	public LocalDateTime getCreateDate() {
		return super.getCreateDate();
	}
	
	@Override
	@JsonIgnore
	public LocalDateTime getUpdateDate() {
		return super.getUpdateDate();
	}
	
}
