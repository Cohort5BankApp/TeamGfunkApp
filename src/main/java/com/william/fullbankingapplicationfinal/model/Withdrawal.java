package com.william.fullbankingapplicationfinal.model;

import javax.persistence.Column;

public class Withdrawal {

	public Withdrawal(WithdrawalStatus status, WithdrawalMedium medium, WithdrawalType type ){
		this.status = status;
		this.medium = medium;
		this.type = type;
	}

	@Column(name = "Deposit_id")
	private Long id;

	@Column(name = "Type_transaction")
	private WithdrawalType type;
	private enum WithdrawalType{
		P2P,DEPOSIT,WITHDRAWAL;
	}

	@Column(name = "Timestamp_execution")
	private String transaction_date;

	@Column(name = "Deposit_Status")
	private WithdrawalStatus status;
	public enum WithdrawalStatus{
		PENDING,CANCELLED,COMPLETED;
	}

	@Column(name = "Account_id")
	private Long account_id;

	@Column(name = "Deposit_medium")
	private WithdrawalMedium medium;
	public enum WithdrawalMedium{
		BALANCE,REWARDS
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

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
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

	public WithdrawalType getType() {
		return type;
	}

	public void setType(WithdrawalType type) {
		this.type = type;
	}

	public WithdrawalStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawalStatus status) {
		this.status = status;
	}

	public WithdrawalMedium getMedium() {
		return medium;
	}

	public void setMedium(WithdrawalMedium medium) {
		this.medium = medium;
	}
}
