package com.cdac.banking.model;

public class LoginResponse extends GenericResponse{

	private int accountId;
	
	public LoginResponse(String status,int accountId) {
		super(status);
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
