package com.cdac.banking.model;

import java.io.Serializable;

public class TransactionRequest implements Serializable {

	private static final long serialVersionUID = -214520463007417759L;

	private String remarks;

	private long transferAmount;

	private int recipientAccountId;

	public TransactionRequest() {
	}

	public TransactionRequest(String remarks, long transferAmount, int recipientAccountId) {
		this.remarks = remarks;
		this.transferAmount = transferAmount;
		this.recipientAccountId = recipientAccountId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(long transferAmount) {
		this.transferAmount = transferAmount;
	}

	public int getRecipientAccountId() {
		return recipientAccountId;
	}

	public void setRecipientAccountId(int recipientAccountId) {
		this.recipientAccountId = recipientAccountId;
	}

}
