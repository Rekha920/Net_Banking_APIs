package com.cdac.banking.model;

public class RegistrationResponse extends GenericResponse {

	private int registrationId;

	public RegistrationResponse(String status, int registrationId) {
		super(status);
		this.registrationId = registrationId;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

}
