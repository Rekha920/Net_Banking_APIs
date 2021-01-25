package com.cdac.banking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYEE")
public class Payee {

	@Id
	@Column(name = "PAYEE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payeeId;

	@Column(name = "PAYEE_ACCOUNT_ID")
	private int payeeAccountNumber;

	@Column(name = "PAYEE_ACCOUNT_NAME")
	private String payeeName;

	@Column(name = "LINKED_ACCOUNT_ID")
	private int linkedAccountId;

	public Payee() {
	}

	public int getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(int payeeId) {
		this.payeeId = payeeId;
	}

	public int getPayeeAccountNumber() {
		return payeeAccountNumber;
	}

	public void setPayeeAccountNumber(int payeeAccountNumber) {
		this.payeeAccountNumber = payeeAccountNumber;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public int getLinkedAccountId() {
		return linkedAccountId;
	}

	public void setLinkedAccountId(int linkedAccountId) {
		this.linkedAccountId = linkedAccountId;
	}
}
