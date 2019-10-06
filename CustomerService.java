package com.cg.ibs.cardmanagement.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;
import com.cg.ibs.cardmanagement.dao.CustomerDao;
import com.cg.ibs.cardmanagement.dao.DaoClass;

public interface CustomerService {

	public boolean verifyDebitCardNumber(BigInteger debitCardNumber);

	public List<DebitCardBean> viewAllDebitCards();

	public List<CreditCardBean> viewAllCreditCards();

	void applyNewDebitCard(BigInteger accountNumber);

	String verifyDebitcardType(BigInteger debitCardNumber);

	void requestDebitCardUpgrade(BigInteger debitCardNumber, int myChoice);

	void resetDebitPin(BigInteger debitCardNumber, int newPin);

	public boolean verifyDebitPin(int pin, BigInteger debitCardNumber);

	boolean verifyCreditCardNumber(BigInteger creditCardNumber);

	void resetCreditPin(BigInteger creditCardNumber, int newPin);

	public boolean verifyCreditPin(int pin, BigInteger creditCardNumber);

	void applyNewCreditCard();

	void resetCreditPin(BigInteger creditCardNumber, long currentCreditPin);

	void resetDebtitPin(BigInteger debitCardNumber, long currentDebitPin);

	void requestDebitCardLost(BigInteger debitCardNumber);

	void requestCreditCardLost(BigInteger creditCardNumber);

		String verifyCreditcardType(BigInteger creditCardNumber) ;
	void requestCreditCardUpgrade(BigInteger creditCardNumber,int myChoice);
	// public List<DebitCardTransaction> showDebitCardStatement(BigInteger
	// debitCardNumber,LocalDateTime fromDate , LocalDateTime today );

//	public List<CreditCardTransaction> showCreditCardStatement(Date fromDate , Date toDate , BigInteger creditCardNumber);
	void raiseDebitMismatchTicket(String transactionId);

	public void raiseCreditMismatchTicket(String transactionId);
	public List<DebitCardTransaction> getDebitTrns(int dys, BigInteger debitCardNumber);
	public boolean verifyDate(LocalDateTime fromDate2, LocalDateTime toDate2);

	public List<CreditCardTransaction> getCreditTrans(int days,BigInteger creditCardNumber);
	

}
