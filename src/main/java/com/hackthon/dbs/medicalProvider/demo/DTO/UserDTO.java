package com.hackthon.dbs.medicalProvider.demo.DTO;

import java.util.UUID;

public class UserDTO {
	
	private UUID id;
	private Boolean success = false;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	

}
