package com.conygre.spring.boot.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity @Table(name="account_details")
public class AccountDetails implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name = "account_type") private String accountType;
	@Column(name="balance") private Double balance;
	@Column(name="currency") private String currency;
	@Column(name="user_id") private Integer userId;

	public AccountDetails() {}

	public AccountDetails(Double balance, String currency, Integer userId){
		this.balance = balance;
		this.currency = currency;
		this.userId = userId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return this.id;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public Double getBalance() {
		return this.balance;
	}
	
	public String getCurrency() {
		return this.currency;
	}

	public Integer getUserId() {
		return this.userId;
	}
}
