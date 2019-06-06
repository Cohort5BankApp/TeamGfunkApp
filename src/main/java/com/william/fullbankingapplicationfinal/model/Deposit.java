package com.william.fullbankingapplicationfinal.model;

import javax.persistence.*;

//@Embeddable
@Entity
public class Deposit {

//	public Deposit(DepositStatus status, DepositMedium medium, DepositType type ){
//		this.status = status;
//		this.medium = medium;
//		this.type = type;
//	}

	public Deposit(Long id, DepositType type, String transaction_date, DepositStatus status, Long account_id, DepositMedium medium, Double amount, String description) {
		this.id = id;
		this.type = type;
		this.transaction_date = transaction_date;
		this.status = status;
		this.account_id = account_id;
		this.medium = medium;
		this.amount = amount;
		this.description = description;
	}

	@Id
	@GeneratedValue
	private Long id;

	private DepositType type;
	private enum DepositType{
		P2P, DEPOSIT, WITHDRAWAL;
	}

	//@Column(name = "Timestamp_execution")
	private String transaction_date;

	//@Column(name = "Deposit_Status")
	//@Embedded
	private DepositStatus status;
	public enum DepositStatus{
		PENDING, CANCELLED, COMPLETED;
	}

	//@Column(name = "Account_id")
	private Long account_id;

	//@Column(name = "Deposit_medium")
	//@Embedded
	private DepositMedium medium;
	public enum DepositMedium{
		BALANCE, REWARDS
	}

	//@Column(name = "Deposit_amount")
	private Double amount;

	//@Column(name = "Description")
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

	@Override
	public String toString() {
		return "Deposit{" +
				"id=" + id +
				", type=" + type +
				", transaction_date='" + transaction_date + '\'' +
				", status=" + status +
				", account_id=" + account_id +
				", medium=" + medium +
				", amount=" + amount +
				", description='" + description + '\'' +
				'}';
	}
}
