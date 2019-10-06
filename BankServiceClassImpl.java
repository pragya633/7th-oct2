package com.cg.ibs.cardmanagement.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;
import com.cg.ibs.cardmanagement.dao.BankDao;
import com.cg.ibs.cardmanagement.dao.DaoClass;

public class BankServiceClassImpl implements BankService {
	
	
	BankDao bankDao = new DaoClass();
	CaseIdBean caseIdObj= new CaseIdBean();
	



	@Override
	public List<CaseIdBean> viewQueries() {
	
		List<CaseIdBean> caseBeans= bankDao.viewAllQueries();
		

		
		return bankDao.viewAllQueries();
		
		
		
	}




	@Override
	public List<DebitCardTransaction> getDebitTrns(int days, BigInteger debitCardNumber) {
		// TODO Auto-generated method stub
		boolean check = bankDao.verifyDebitCardNumber(debitCardNumber);
		if (check){
		    if(days>=1) {
			    
		    	return bankDao.getDebitTrans(days, debitCardNumber);
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
		
		boolean check = bankDao.verifyCreditCardNumber(creditCardNumber);
		if (check){
		    if(days>=1) {
			    
		    	return bankDao.getCreditTrans(days, creditCardNumber);
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




	@Override
	public boolean verifyQueryId(String queryId) {
		// TODO Auto-generated method stub
		boolean check = bankDao.verifyQueryId(queryId);
		if(check) {
					return true;
				} else {
					return false;
				}
	}




	@Override
	public void setQueryStatus(String queryId, String newStatus) {
		// TODO Auto-generated method stub
		bankDao.setQueryStatus(queryId, newStatus);
		

	}






	







	




	






}
