package com.cdac.banking.model;

public class PayeeRequest {

	private String payeeName;

	private int payeeAccountNo;

	public PayeeRequest() {
	}

	public PayeeRequest(String payeeName, int payeeAccountNo) {
		this.payeeName = payeeName;
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public int getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(int payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

}
