package com.cdac.banking.model;

import java.util.List;

import com.cdac.banking.entity.UserTransaction;

public class AccountResponse extends GenericResponse {

	public AccountResponse(String status) {
		super(status);
	}
	
	private int accountId;
	
	private String customerName;

	private String accountType;

	private long balance;
	
	private int loginId;
	
	private List<UserTransaction> transactions;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public List<UserTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<UserTransaction> transactions) {
		this.transactions = transactions;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
}
