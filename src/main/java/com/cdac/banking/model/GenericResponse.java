package com.cdac.banking.model;

public class GenericResponse {

	private String status;

	public GenericResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
