package com.net.banking.dto;

import lombok.Data;

@Data
public class AccountDto {

	private Long id;
	private String AccHolderName;
	private double balance;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccHolderName() {
		return AccHolderName;
	}
	public void setAccHolderName(String accHolderName) {
		AccHolderName = accHolderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public AccountDto() {
		super();
	}
	public AccountDto(Long id, String accHolderName, double balance) {
		super();
		this.id = id;
		AccHolderName = accHolderName;
		this.balance = balance;
	}
	
	
}
