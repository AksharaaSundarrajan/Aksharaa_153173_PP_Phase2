package com.cg.mypaymentapp.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Transaction 
{
	String mobileNumber;
	String dateOfTransaction;
	String transactionType;
	String transactionStatus;
	BigDecimal amount;
	
	
	public Transaction() {
		
	}


	public Transaction(String mobileNumber, String dateOfTransaction, String transactionType, String transactionStatus,
			BigDecimal amount) {
		super();
		this.mobileNumber = mobileNumber;
		this.dateOfTransaction = dateOfTransaction;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.amount = amount;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getDateOfTransaction() {
		return dateOfTransaction;
	}


	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public String getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	@Override
	public String toString() {
		return "Transaction [mobileNumber=" + mobileNumber + ", dateOfTransaction=" + dateOfTransaction
				+ ", transactionType=" + transactionType + ", transactionStatus=" + transactionStatus + ", amount="
				+ amount + "]";
	}
	
	
	
}
