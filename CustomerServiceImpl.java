package com.cg.ibs.cardmanagement.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.CustomerBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;
import com.cg.ibs.cardmanagement.dao.BankDao;
import com.cg.ibs.cardmanagement.dao.CustomerDao;
import com.cg.ibs.cardmanagement.dao.DaoClass;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao = new DaoClass();

	CaseIdBean caseIdObj = new CaseIdBean();
	CustomerBean customObj= new CustomerBean();
	CustomerService customService ;
	String UCI="7894561239632587";
	
	Scanner scan = new Scanner(System.in);

	String caseIdGenOne = " ";
	String caseIdTotal = " ";
	static int caseIdGenTwo = 0;
	LocalDateTime timestamp;
	LocalDateTime fromDate;
	LocalDateTime toDate;

	String addToQueryTable(String caseIdGenOne) {
		caseIdTotal = caseIdGenOne + caseIdGenTwo;
		caseIdGenTwo++;
		return caseIdTotal;
	}

	public CustomerServiceImpl() {
		super();
	}

	public boolean verifyDate(LocalDateTime fromDate2, LocalDateTime toDate2) {
		boolean checkDate = false;
		if (fromDate2.isBefore(toDate2)) {
			checkDate = true;
		}

		return checkDate;
	}

	@Override
	public void applyNewDebitCard(BigInteger accountNumber) {
		boolean check = customerDao.verifyAccountNumber(accountNumber);
		if (check) {

			caseIdGenOne = "ANDC";
			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(customObj.getUCI());
			customerDao.newDebitCard(caseIdObj, accountNumber);
		}

		else {
			System.out.println("Something wrong !! Try Again");

		}

	}

	@Override
	public void applyNewCreditCard() {
		caseIdGenOne = "ANCD";
		timestamp = LocalDateTime.now();

		caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
		caseIdObj.setCaseTimeStamp(timestamp);
		caseIdObj.setStatusOfQuery("Pending");
		caseIdObj.setUCI(caseIdObj.getUCI());

		customerDao.newCreditCard(caseIdObj);

	}

	@Override
	public void resetCreditPin(BigInteger creditCardNumber, long currentCreditPin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetDebtitPin(BigInteger debitCardNumber, long currentDebitPin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestDebitCardLost(BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);

		if (check) {


			caseIdGenOne = "RDCL";

			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(caseIdObj.getUCI());

			customerDao.requestDebitCardLost(caseIdObj, debitCardNumber);
		} else {
			System.out.println("INVALID DEBIT CARD NUMBER");

		}
	}

	@Override
	public void requestCreditCardLost(BigInteger creditCardNumber) {
		boolean check = customerDao.verifyCreditCardNumber(creditCardNumber);
		if (check) {


			caseIdGenOne = "RDCL";

			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(caseIdObj.getUCI());

			customerDao.requestCreditCardLost(caseIdObj, creditCardNumber);
		} else {
			System.out.println("INVALID CREDIT CARD NUMBER");

		}
	}

	@Override
	
	/*
	 * public TransactionBean showDebitCardStatement(Date fromDate, Date toDate,
	 * BigInteger debitCardNumber) { // TODO Auto-generated method stub return
	 * null; }
	 */

	public void raiseDebitMismatchTicket(String transactionId) {
		// TODO Auto-generated method stub
		boolean transactionResult = customerDao.verifyTransactionId(transactionId);
		if (transactionResult) {

			caseIdGenOne = "RDMT";

			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(caseIdObj.getUCI());
			

			customerDao.raiseDebitMismatchTicket(caseIdObj, transactionId);
		} else {
			System.out.println("INVALID TRANSACTION ID");

		}
		
	}

	public List<DebitCardBean> viewAllDebitCards() {

		return customerDao.viewAllDebitCards();
	}

	@Override
	public List<CreditCardBean> viewAllCreditCards() {

		return customerDao.viewAllCreditCards();

	}

	/*@Override
	public List<DebitCardTransaction> showDebitCardStatement(BigInteger debitCardNumber, LocalDateTime fromDate,LocalDateTime toDate) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		if (check){
			boolean dateCheck = customServiceTwo.verifyDate(fromDate, toDate);
			if (dateCheck) {
				return customerDao.showDebitCardStatement(debitCardNumber, fromDate, toDate);

			} }
			else
			
			return new ArrayList<DebitCardTransaction>();

		
	}
	

	@Override
	public List<CreditCardTransaction> showCreditCardStatement(Date fromDate, Date toDate,
			BigInteger creditCardNumber) {

		return null;
	}

	public String verifyDebitcardType(BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		if (check) {
			String type = customerDao.getdebitCardType(debitCardNumber);
			return type;
		} else {
			System.out.println("INVALID DEBIT CARD NUMBER");
			return null;
		}

	}*/

	@Override
	public void requestDebitCardUpgrade(BigInteger debitCardNumber,int myChoice) {
		// TODO Auto-generated method stub

		
		caseIdGenOne = "RDCU";
		timestamp = LocalDateTime.now();

		caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
		caseIdObj.setCaseTimeStamp(timestamp);
		caseIdObj.setStatusOfQuery("Pending");
		caseIdObj.setUCI(customObj.getUCI());
		if (myChoice == 1) {
			caseIdObj.setDefineQuery("Upgrade to Gold");
			customerDao.requestDebitCardUpgrade(caseIdObj, debitCardNumber);
		} else if (myChoice == 2) {
			caseIdObj.setDefineQuery("Upgrade to Platinum");
			customerDao.requestDebitCardUpgrade(caseIdObj, debitCardNumber);
		} else {
			System.out.println("Choose a valid option");
		}

	}

	@Override
	public String verifyDebitcardType(BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		if (check) {
			String type = customerDao.getdebitCardType(debitCardNumber);
			return type;
		} else {
			System.out.println("INVALID DEBIT CARD NUMBER");
			return null;
		}

	}
	public boolean verifyDebitCardNumber(BigInteger debitCardNumber) {

		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		return (check);
	}
	
	public boolean verifyDebitPin(int pin, BigInteger debitCardNumber) {
		if (pin == customerDao.getDebitCardPin(debitCardNumber)) {

			return true;
		} else {
			return false;
		}
	}
	
	public void resetDebitPin(BigInteger debitCardNumber, int newPin) {

		customerDao.setNewDebitPin(debitCardNumber, newPin);
		System.out.println("PIN UPDATED SUCCESSFULLY!");
	}
	
	public boolean verifyCreditCardNumber(BigInteger creditCardNumber) {

		boolean check1 = customerDao.verifyCreditCardNumber(creditCardNumber);
		return (check1);
	}
	
	public boolean verifyCreditPin(int pin, BigInteger creditCardNumber) {
		if (pin == customerDao.getCreditCardPin(creditCardNumber)) {

			return true;
		} else {
			return false;
		}
	}
	
	public void resetCreditPin(BigInteger creditCardNumber, int newPin) {

		customerDao.setNewCreditPin(creditCardNumber, newPin);
		System.out.println("PIN UPDATED SUCCESSFULLY!");
	}

	@Override
	public void requestCreditCardUpgrade(BigInteger creditCardNumber,int myChoice) {
		// TODO Auto-generated method stub

		
		caseIdGenOne = "RCCU";
		timestamp = LocalDateTime.now();

		caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
		caseIdObj.setCaseTimeStamp(timestamp);
		caseIdObj.setStatusOfQuery("Pending");
		caseIdObj.setUCI(customObj.getUCI());
		if (myChoice == 1) {
			caseIdObj.setDefineQuery("Upgrade to Gold");
			customerDao.requestCreditCardUpgrade(caseIdObj, creditCardNumber);
		} else if (myChoice == 2) {
			caseIdObj.setDefineQuery("Upgrade to Platinum");
			customerDao.requestCreditCardUpgrade(caseIdObj, creditCardNumber);
		} else {
			System.out.println("Choose a valid option");
		}

	}

	@Override
	public String verifyCreditcardType(BigInteger creditCardNumber) {
		boolean check = customerDao.verifyCreditCardNumber(creditCardNumber);
		if (check) {
			String type = customerDao.getCreditCardType(creditCardNumber);
			return type;
		} else {
			System.out.println("INVALID CREDIT CARD NUMBER");
			return null;
		}

	}



	@Override
	public void raiseCreditMismatchTicket(String transactionId) {
		// TODO Auto-generated method stub
		boolean transactionResult = customerDao.verifyTransactionId(transactionId);
		if (transactionResult) {

			caseIdGenOne = "RCMT";

			timestamp = LocalDateTime.now();

			caseIdObj.setCaseIdTotal(addToQueryTable(caseIdGenOne));
			caseIdObj.setCaseTimeStamp(timestamp);
			caseIdObj.setStatusOfQuery("Pending");
			caseIdObj.setUCI(caseIdObj.getUCI());
			

			customerDao.raiseCreditMismatchTicket(caseIdObj, transactionId);
		} else {
			System.out.println("INVALID TRANSACTION ID");

		}
		
	}
	
	
	public List<DebitCardTransaction> getDebitTrns(int dys, BigInteger debitCardNumber) {
		boolean check = customerDao.verifyDebitCardNumber(debitCardNumber);
		if (check){
		    if(dys>=1) {
			    
		    	return customerDao.getDebitTrans(dys, debitCardNumber);
		    }
		    else {
		    	System.out.println("invalid days format");
		    	return null;
		    }
		
	    }
		else {
			System.out.println("debit card does not exist");
		return null;
	   }
	}

	@Override
	public List<CreditCardTransaction> getCreditTrans(int days, BigInteger creditCardNumber) {
		// TODO Auto-generated method stub
		boolean check = customerDao.verifyCreditCardNumber(creditCardNumber);
		if (check){
		    if(days>=1) {
			    
		    	return customerDao.getCreditTrans(days, creditCardNumber);
		    }
		    else {
		    	System.out.println("invalid days format");
		    	return null;
		    }
		
	    }
		else {
			System.out.println("credit card does not exist");
		return null;
	   }
	}

}
