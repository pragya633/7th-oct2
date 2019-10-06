package com.cg.ibs.cardmanagement.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.CustomerBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;

public class DaoClass implements CustomerDao, BankDao {

	Boolean result;
	CaseIdBean caseIdObj = new CaseIdBean();
	DebitCardBean bean = new DebitCardBean();
	CreditCardBean bean1 = new CreditCardBean();
	CustomerBean bean2=new CustomerBean();
 
	private String caseIdTotal;
	private static Map<String, DebitCardTransaction> debitCardTransactionDetails = new HashMap<String, DebitCardTransaction>();
	private static Map<String, CreditCardTransaction> creditCardTransactionDetails = new HashMap<String, CreditCardTransaction>();
	private static Map<String, CaseIdBean> queryDetails = new HashMap<String, CaseIdBean>();
	private static Map<BigInteger, DebitCardBean> debitCardDetails = new HashMap<BigInteger, DebitCardBean>();
	private static Map<BigInteger, CreditCardBean> creditCardDetails = new HashMap<BigInteger, CreditCardBean>();
	private static Map<BigInteger, CustomerBean> customerDetails = new HashMap<BigInteger, CustomerBean>();

	static {

		DebitCardBean debit1 = new DebitCardBean(new BigInteger("1234567890"), new BigInteger("1234567891012131"), true,
				"Mohit Pursnani", 067, 1234, LocalDate.now(), "7894561239632587", "Platinum");
		DebitCardBean debit2 = new DebitCardBean(new BigInteger("1234567890"), new BigInteger("1234562391012233"), true,
				"Mohit Pursnani", 057, 1034, LocalDate.now(), "7894561239632587", "Gold");
		DebitCardBean debit3 = new DebitCardBean(new BigInteger("9012345678"), new BigInteger("3234567891016564"), true,
				"Pragya Goyal", 167, 2234, LocalDate.now(), "894561239832588", "Silver");

		debitCardDetails.put(debit1.getDebitCardNumber(), debit1);
		debitCardDetails.put(debit2.getDebitCardNumber(), debit2);
		debitCardDetails.put(debit3.getDebitCardNumber(), debit3);

		CreditCardBean credit1 = new CreditCardBean(new BigInteger("6789101213259898"), true, "Mohit Pursnani", 623, 9856,
				LocalDate.now(), "7894561239632587", "Silver", 200, new BigDecimal("1000000.00"), "gfdgfgfdgf");

		creditCardDetails.put(credit1.getCreditCardNumber(), credit1);

		CustomerBean cust1 = new CustomerBean(new BigInteger("1234567890"), "7894561239632587", "Mohit", "Pursnani",
				"mohit@gmail.com", "201965231351", "9774216545");
		CustomerBean cust2 = new CustomerBean(new BigInteger("9012345678"), "894561239832588", "Pragya", "Goyal",
				"goyal@gmail.com", "901265233351", "8881654314");
		customerDetails.put(cust1.getAccountNumber(), cust1);
		customerDetails.put(cust2.getAccountNumber(), cust2);

		DebitCardTransaction debittrans1 = new DebitCardTransaction("DEB101", "7894561239632587",
				new BigInteger("1234567890"), new BigInteger("1234567891012131"),
				LocalDateTime.of(2019, Month.OCTOBER, 04, 12, 54, 6), new BigDecimal("1563"), "Petrol", "Debit");
		DebitCardTransaction debittrans2 = new DebitCardTransaction("DEB102", "7894561239632587",
				new BigInteger("1234567890"), new BigInteger("1234567891012131"),
				LocalDateTime.of(2013, Month.JANUARY, 04, 12, 54, 6), new BigDecimal("20.45"), "Interest", "Credit");
		debitCardTransactionDetails.put(debittrans1.getTransactionId(), debittrans1);
		debitCardTransactionDetails.put(debittrans2.getTransactionId(), debittrans2);

		// Transactions for Mohit Platinum Credit Card
		CreditCardTransaction credittrans1 = new CreditCardTransaction("CRED101", "7894561239632587",
				new BigInteger("6789101213259898"), LocalDateTime.of(2019, Month.OCTOBER, 04, 12, 54, 6),
				new BigDecimal("5000"), "Shopping");
		CreditCardTransaction credittrans2 = new CreditCardTransaction("CRED102", "7894561239632587",
		new BigInteger("6789101213259898"), LocalDateTime.of(2018, Month.OCTOBER, 04, 12, 21, 6),
				new BigDecimal("5000"), "Movie");
		creditCardTransactionDetails.put(credittrans1.getTransactionid(), credittrans1);
		creditCardTransactionDetails.put(credittrans2.getTransactionid(), credittrans2);

	}

	public DaoClass() {
		super();
	}

	public DaoClass(CaseIdBean caseIdObj) {
		super();

		this.caseIdObj = caseIdObj;

	}

	public boolean verifyAccountNumber(BigInteger accountNumber) {

		result = customerDetails.containsKey(accountNumber);
		return result;
	}

	public boolean verifyDebitCardNumber(BigInteger debitCardNumber) {

		result = debitCardDetails.containsKey(debitCardNumber);
		return result;
	}

	public boolean verifyCreditCardNumber(BigInteger creditCardNumber) {

		result = creditCardDetails.containsKey(creditCardNumber);
		return result;
	}

	public BigInteger getAccountNumber(BigInteger debitCardNumber){
		BigInteger acc=debitCardDetails.get(debitCardNumber).getAccountNumber();
	return acc;
	}
	@Override
	public void newCreditCard(CaseIdBean caseIdObjId) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("New Credit Card applied successfully");

		List<CreditCardBean> creditCards = new ArrayList<>();

	}

	@Override
	public void newDebitCard(CaseIdBean caseIdObj, BigInteger accountNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("New Debit Card applied successfully");
	}

	@Override
	public List<CaseIdBean> viewAllQueries() {
		List<CaseIdBean> query = new ArrayList();

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			query.add(entry.getValue());
		}

		return query;

	}

	@Override
	public List<DebitCardBean> viewAllDebitCards() {
		List<DebitCardBean> debitCards = new ArrayList();

		for (Entry<BigInteger, DebitCardBean> entry : debitCardDetails.entrySet()) {
			debitCards.add(entry.getValue());
		}
		return debitCards;

	}

	public List<CreditCardBean> viewAllCreditCards() {
		List<CreditCardBean> creditCards = new ArrayList<>();

		for (Entry<BigInteger, CreditCardBean> entry : creditCardDetails.entrySet()) {
			creditCards.add(entry.getValue());
		}
		return creditCards;

	}

	public void requestDebitCardLost(CaseIdBean caseIdObj, BigInteger debitCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");

	}

	public void requestCreditCardLost(CaseIdBean caseIdObj, BigInteger creditCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");

	}

	@Override
	public List<DebitCardTransaction> showDebitCardStatement(BigInteger debitCardNumber, LocalDateTime fromDate,
			LocalDateTime toDate) {

		List<DebitCardTransaction> dummyDebitCardTransaction = new ArrayList<>();
		List<DebitCardTransaction> debitCardTransaction = new ArrayList<>();

		for (Entry<String, DebitCardTransaction> entry : debitCardTransactionDetails.entrySet()) {
			System.out.println(entry.getValue().getDate());
			if ((entry.getValue().getDate().compareTo(fromDate))
					+ (entry.getValue().getDate().compareTo(fromDate)) == 0) {
				debitCardTransaction.add(entry.getValue());

			}

		}
		return debitCardTransaction;

	}

	public String getdebitCardType(BigInteger debitCardNumber) {
		String type = debitCardDetails.get(debitCardNumber).getDebitCardType();
		return type;

	}

	public void requestDebitCardUpgrade(CaseIdBean caseIdObj, BigInteger debitCardNumber) {
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");

	}

	public int getDebitCardPin(BigInteger debitCardNumber) {
		int pin = debitCardDetails.get(debitCardNumber).getDebitCurrentPin();
		return pin;

	}

	public void setNewDebitPin(BigInteger debitCardNumber, int newPin) {
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCurrentPin(newPin);
		debitCardDetails.replace(debitCardNumber, bean);
	}

	public int getCreditCardPin(BigInteger creditCardNumber) {
		int pin = creditCardDetails.get(creditCardNumber).getCreditCurrentPin();
		return pin;

	}

	public void setNewCreditPin(BigInteger creditCardNumber, int newPin) {
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCurrentPin(newPin);
		creditCardDetails.replace(creditCardNumber, bean1);
	}

	public void requestCreditCardUpgrade(CaseIdBean caseIdObj, BigInteger creditCardNumber) {
		// TODO Auto-generated method stub
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");
		
	}


	public String getCreditCardType(BigInteger creditCardNumber) {
		// TODO Auto-generated method stub
		String type = creditCardDetails.get(creditCardNumber).getCreditCardType();
		return type;
	}

	@Override
	public boolean verifyTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		result = debitCardTransactionDetails.containsKey(transactionId);
		return result;
	}

	@Override
	public void raiseDebitMismatchTicket(CaseIdBean caseIdObj,String transactionId) {
		// TODO Auto-generated method stub
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");
		
		
	}

	@Override
	public void raiseCreditMismatchTicket(CaseIdBean caseIdObj, String transactionId) {
		// TODO Auto-generated method stub
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		System.out.println("TICKET RAISED SUCCESSFULLY");
		
	}
	
	public List<DebitCardTransaction> getDebitTrans(int dys, BigInteger debitCardNumber) {
		
		
		List<DebitCardTransaction> debitCardsList = new ArrayList();
        LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(dys);
		for (Entry<String, DebitCardTransaction> entry : debitCardTransactionDetails.entrySet()) {
			if(entry.getValue().getDate().isAfter(dLocalDateTime))
			 debitCardsList.add(entry.getValue());
		}
		
		return debitCardsList;
	}

	@Override
	public List<CreditCardTransaction> getCreditTrans(int days, BigInteger creditCardNumber) {
		// TODO Auto-generated method stub
		List<CreditCardTransaction> creditCardsList = new ArrayList();
        LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(days);
		for (Entry<String, CreditCardTransaction> entry : creditCardTransactionDetails.entrySet()) {
			if(entry.getValue().getDate().isAfter(dLocalDateTime))
			 creditCardsList.add(entry.getValue());
		}
		
		return creditCardsList;
	}

	@Override
	public boolean verifyQueryId(String queryId) {
		// TODO Auto-generated method stub
		result = queryDetails.containsKey(queryId);
		System.out.println(result);
		return result;

	}

	@Override
	public void setQueryStatus(String queryId, String newStatus) {
		// TODO Auto-generated method stub
		caseIdObj = queryDetails.get(queryId);
		caseIdObj.setStatusOfQuery(newStatus);
		queryDetails.replace(newStatus, caseIdObj);

	}
	
	
	
	

}