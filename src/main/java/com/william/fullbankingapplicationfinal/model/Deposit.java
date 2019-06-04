package com.william.fullbankingapplicationfinal.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Deposit {

	public Deposit(DepositStatus status, DepositMedium medium, DepositType type ){
		this.status = status;
		this.medium = medium;
		this.type = type;
	}

	@Column(name = "Deposit_id")
	private Long id;

	@Column(name = "Type_transaction")
	private DepositType type;
	private enum DepositType{
		P2P, DEPOSIT, WITHDRAWAL;
	}

	@Column(name = "Timestamp_execution")
	private String transaction_date;

	@Column(name = "Deposit_Status")
	private DepositStatus status;
	public enum DepositStatus{
		PENDING, CANCELLED, COMPLETED;
	}

	@Column(name = "Account_id")
	private Long payee_id;

	@Column(name = "Deposit_medium")
	private DepositMedium medium;
	public enum DepositMedium{
		BALANCE, REWARDS
	}

	@Column(name = "Deposit_amount")
	private Double amount;

	@Column(name = "Description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Long getPayee_id() {
		return payee_id;
	}

	public void setPayee_id(Long payee_id) {
		this.payee_id = payee_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DepositType getType() {
		return type;
	}

	public void setType(DepositType type) {
		this.type = type;
	}

	public DepositStatus getStatus() {
		return status;
	}

	public void setStatus(DepositStatus status) {
		this.status = status;
	}

	public DepositMedium getMedium() {
		return medium;
	}

	public void setMedium(DepositMedium medium) {
		this.medium = medium;
	}
}
