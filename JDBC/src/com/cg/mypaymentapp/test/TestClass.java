package com.cg.mypaymentapp.test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;


public class TestClass {

	String mobile ="9963242422";
	BigDecimal amount = new BigDecimal("100.00");
	WalletService service;
	
	@Before
	public void initData(){
		 service= new WalletServiceImpl();
		 Customer cust2=service.createAccount("Ajay", "9963242422",new BigDecimal(6000));
		 Customer cust1=service.createAccount("Amit", "9900112212",new BigDecimal(9000));
		 Customer cust3=service.createAccount("Yogini", "9922950519",new BigDecimal(7000));
		 
	}
	
	@After
	public void afterExec()
	{
		
	}
	//1
	@Test(expected=InvalidInputException.class)
	public void  testShowBalanceInvalidNumber()
	{
		String mobileNumber = "9898989";
		service.showBalance(mobileNumber);
	}
	//2
	@Test
	public void  testShowBalanceValidNumber()
	{
		Customer cust = service.showBalance("9963242422");
	}
	//22
	@Test
	public void createAccountValid()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		cust = service.createAccount(name, mobile, amount);
		
	}
	//3
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidName1()
	{
		Customer cust = new Customer();
		String name =" ";
		cust = service.createAccount(name, mobile, amount);
		
	}
	//4
	@Test(expected=NullPointerException.class)
	public void createAccountInvalidName2()
	{
		
		String name =null;
		service.createAccount(name, mobile, amount);
		
	}
	
	//5
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidMobileNo()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "99632";
		cust = service.createAccount(name, number, amount);
		
	}
	//6
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidAmount1()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		
		BigDecimal amount = new BigDecimal("0.0");
		cust = service.createAccount(name, mobile, amount);
		
	}	
	
	//7
	//Check!!!!
	/*@Test(expected=InvalidInputException.class)
	public void createAccountInvalidAmount2()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("twenty");
		cust = service.createAccount(name, number, amount);
		
	}*/
	//20
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidAmount3()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		
		BigDecimal amount = new BigDecimal("-100.00");
		cust = service.createAccount(name, mobile, amount);
		
	}
	//23
	@Test
	public void fundTransferValid()
	{
		String sourceMobile ="9900112212";
		String targetMobile = "9963242422";
		service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//8
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidSourceMobile()
	{
		Customer cust = new Customer();
		String sourceMobile ="909090";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("1000.00");
		cust = service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//9
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidTargetMobile()
	{
		Customer cust = new Customer();
		String sourceMobile ="9963242422";
		String targetMobile = "909090";
		BigDecimal amount = new BigDecimal("1000.00");
		cust = service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//10
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidAmount()
	{
		
		String sourceMobile ="9922950519";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("0");
		service.fundTransfer(sourceMobile, targetMobile, amount);
	}
	//21
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidAmount2()
	{
		String sourceMobile ="9922950519";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("-100.00");
		service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//11
	@Test(expected=InvalidInputException.class)
	public void fundTransferSourceEqualsTarget()
	{
		Customer cust = new Customer();
		String sourceMobile ="9922950519";
		String targetMobile = "9922950519";
		BigDecimal amount = new BigDecimal("100.00");
		cust = service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//12
	@Test(expected=InsufficientBalanceException.class)
	public void fundTransferInsufficientBalance()
	{
		String sourceMobile ="9922950519";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("1000000.00");
		service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//24
	@Test
	public void depositAmountValid()
	{
		
		BigDecimal amount = new BigDecimal("100.00");
		service.depositAmount(mobile, amount);
	}
	//13
	@Test(expected=InvalidInputException.class)
	public void depositAmountInvalidNumber()
	{
		Customer cust = new Customer();
		String mobile ="909090";
		BigDecimal amount = new BigDecimal("100.00");
		cust = service.depositAmount(mobile, amount);
	}
	//14
	@Test(expected=InvalidInputException.class)
	public void depositAmountInvalidAmount1()
	{
		
		
		BigDecimal amount = new BigDecimal("0");
		service.depositAmount(mobile, amount);
	}
	//15
	@Test(expected=InvalidInputException.class)
	public void depositAmountInvalidAmount2()
	{
		
		BigDecimal amount = new BigDecimal("100");
		service.depositAmount(mobile, amount.negate());
	}
	//25
	@Test
	public void withdrawAmountValid()
	{
		
		BigDecimal amount = new BigDecimal("100.00");
		service.withdrawAmount(mobile, amount);
	}
	//16
	@Test(expected=InvalidInputException.class)
	public void withdrawAmountInvalidNumber()
	{
		Customer cust = new Customer();
		String mobile ="909090";
		BigDecimal amount = new BigDecimal("100.00");
		cust = service.withdrawAmount(mobile, amount);
	}
	//17
	@Test(expected=InvalidInputException.class)
	public void withdrawAmountInvalidAmount1()
	{
		
		BigDecimal amount = new BigDecimal("0.00");
		service.withdrawAmount(mobile, amount);
	}
	//18
	@Test(expected=InvalidInputException.class)
	public void withdrawAmountInvalidAmount2()
	{
		
		BigDecimal amount = new BigDecimal("100.00");
		service.withdrawAmount(mobile, amount.negate());
	}
	//19
	@Test(expected=InsufficientBalanceException.class)
	public void withdrawAmountInsufficientBalance()
	{
		
		BigDecimal amount = new BigDecimal("1000000.00");
		service.withdrawAmount(mobile, amount);
	}
	

}
