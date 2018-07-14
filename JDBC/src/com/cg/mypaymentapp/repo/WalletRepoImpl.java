package com.cg.mypaymentapp.repo;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transaction;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.util.DBUtil;

public class WalletRepoImpl implements WalletRepo{
	Customer customer = new Customer();
	int id =0;
	//private Map<String, Customer> data; 
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		//this.data = data;
	}

	public WalletRepoImpl() 
	{
		//data = new HashMap<String, Customer>(); 
	}

	public boolean save(Customer customer) 
	{
	if(findOne(customer.getMobileNo())==null)
		
	{
		try (Connection con = DBUtil.getConnection())
		{				
			PreparedStatement pstm = con.prepareStatement("INSERT INTO CUSTOMER VALUES(?,?,?)");
			pstm.setString(1, customer.getMobileNo());
			pstm.setString(2, customer.getName());
			Wallet wallet = customer.getWallet();
			BigDecimal balance = wallet.getBalance();
			pstm.setBigDecimal(3, balance);
			pstm.execute();
			
		} 
		catch (InvalidInputException |ClassNotFoundException |SQLException e) 
		{
			e.getMessage();
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	else 
	{
		throw new InvalidInputException("A user with this number already exists, enter a new number.");
		
	}
	
	
			
	}

	public Customer findOne(String mobileNo) 
	{	
		Customer customer = null;
		
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM CUSTOMER WHERE id=?");
			pstm.setString(1, mobileNo);
			ResultSet res = pstm.executeQuery();
			
			if(res.next()==false)
				throw new InvalidInputException("Could not get details of customer with number "+mobileNo);
			
			customer = new Customer();
			customer.setMobileNo(res.getString(1));
			customer.setName(res.getString(2));
			Wallet wallet = new Wallet();
			wallet.setBalance(res.getBigDecimal(3));
			customer.setWallet(wallet);
			return customer;
		} 
		catch (InvalidInputException | SQLException |ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} 
		
		
	return null;
		
	}

	@Override
	public void updateBalance(String number,BigDecimal balance) {
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("UPDATE CUSTOMER SET BALANCE = ? WHERE id=?");
			pstm.setBigDecimal(1, balance);
			pstm.setString(2, number);
			ResultSet res = pstm.executeQuery();
			
			if(res.next()==false)
				throw new InvalidInputException("Could not update details of customer with number"+number);
			

				//System.out.println("Balance updated!");
		} 
		catch (InvalidInputException | SQLException |ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} 
		
	}

	@Override
	public List<Transaction> getTransactions(String mobilenumber) throws InvalidInputException{
		List<Transaction> trans = new ArrayList<Transaction>();
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM TRANSACTIONS WHERE mobile_number=?");
			pstm.setString(1, mobilenumber);
			ResultSet res = pstm.executeQuery();
			
			if(res.next()==false)
			{
				throw new InvalidInputException("Could not get details of customer with number "+mobilenumber);
			}
			else
			{	
				System.out.println("In else");
				trans.add(new Transaction(res.getString(2),res.getString(3) , res.getString(4), res.getString(5), res.getBigDecimal(6)));
				System.out.println("after add");
				while(res.next())
				{
					trans.add(new Transaction(res.getString(2),res.getString(3), res.getString(4), res.getString(5), res.getBigDecimal(6)));
				}
			}
		} 
		catch (InvalidInputException | SQLException |ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} 
		catch(Exception e)
		{
			e.getMessage();
		}
		
		
	return trans;
	}

	/*public LocalDate convertToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate localdate = LocalDate.parse(date, formatter);
		return localdate;
		
	}*/
	
	@Override
	public void setTransactions(Transaction transaction) {
		try (Connection con = DBUtil.getConnection())
		{	
						
			PreparedStatement pstm1 = con.prepareStatement("INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?)");
			pstm1.setString(1, transaction.getMobileNumber());
			pstm1.setString(2, transaction.getDateOfTransaction());
			pstm1.setString(3, transaction.getTransactionType());
			pstm1.setString(4, transaction.getTransactionStatus());
			pstm1.setString(5, transaction.getAmount().toString());
			pstm1.execute();
			
			
		} 
		catch (InvalidInputException |ClassNotFoundException |SQLException e) 
		{
			e.getMessage();
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
}
