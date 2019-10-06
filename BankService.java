package com.cg.ibs.cardmanagement.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;

public interface BankService   {


	
	 public List<CaseIdBean> viewQueries() ;

	public List<DebitCardTransaction> getDebitTrns(int days, BigInteger debitCardNumber);
	

	public List<CreditCardTransaction> getCreditTrans(int days, BigInteger creditCardNumber);

	public boolean verifyQueryId(String queryId);

	public void setQueryStatus(String queryId, String newStatus);
	
	
}
