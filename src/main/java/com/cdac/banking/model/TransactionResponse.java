package com.cdac.banking.model;

public class TransactionResponse extends GenericResponse {

	private String failureReason;

	private int transactionId;

	public TransactionResponse(String status, String failureReason, int transactionId) {
		super(status);

		this.failureReason = failureReason;
		this.transactionId = transactionId;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
}
