
package com.cg.ibs.cardmanagement.ui;

import java.util.*;

import com.cg.ibs.cardmanagement.service.BankService;
import com.cg.ibs.cardmanagement.service.BankServiceClassImpl;
import com.cg.ibs.cardmanagement.service.CustomerService;
import com.cg.ibs.cardmanagement.service.CustomerServiceImpl;

import com.cg.ibs.cardmanagement.cardbean.CaseIdBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardBean;
import com.cg.ibs.cardmanagement.cardbean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.cardbean.DebitCardBean;
import com.cg.ibs.cardmanagement.cardbean.DebitCardTransaction;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardManagementUI {

	 enum CardMenu {

		LIST_EXISTING_DEBIT_CARDS, LIST_EXISTING_CREDIT_CARDS, APPLY_NEW_DEBIT_CARD, APPLY_NEW_CREDIT_CARD,
		UPGRADE_EXISTING_DEBIT_CARD, UPGRADE_EXISTING_CREDIT_CARD, RESET_DEBIT_CARD_PIN, RESET_CREDIT_CARD_PIN,
		REPORT_DEBIT_CARD_LOST, REPORT_CREDIT_CARD_LOST, REQUEST_DEBIT_CARD_STATEMENT, REQUEST_CREDIT_CARD_STATEMENT,
		REPORT_DEBITCARD_STATEMENT_MISMATCH, REPORT_CREDITCARD_STATEMENT_MISMATCH, CUSTOMER_LOG_OUT;
	}

	enum BankMenu {
		LIST_QUERIES, REPLY_QUERIES, VIEW_DEBIT_CARD_STATEMENT, VIEW_CREDIT_CARD_STATEMENT, BANK_LOG_OUT;
	}

	public static void main(String args[]) throws Exception {
		BigInteger accountNumber;
		Scanner scan = new Scanner(System.in);
		CardManagementUI obj = new CardManagementUI();
		CustomerService customService = new CustomerServiceImpl();
		BankService bankService = new BankServiceClassImpl();
		while (true) {
			System.out.println("Welcome to card management System");
			System.out.println("Enter 1 to login as a customer");
			System.out.println("Enter 2 to login as a bank admin");
			int userInput = scan.nextInt();
			if (userInput == 1) {
				System.out.println("You are logged in as a customer");
				CardMenu choice = null;
				while (choice != CardMenu.CUSTOMER_LOG_OUT) {
					System.out.println("Menu");
					System.out.println("--------------------");
					System.out.println("Choice");
					System.out.println("--------------------");
					for (CardMenu mmenu : CardMenu.values()) {
						System.out.println(mmenu.ordinal() + "\t" + mmenu);
					}
					System.out.println("Choice");
					int ordinal = scan.nextInt();
					if (ordinal >= 0 && ordinal < 15) {
						choice = CardMenu.values()[ordinal];

						switch (choice) {

						case LIST_EXISTING_DEBIT_CARDS:
							List<DebitCardBean> debitCardBeans = customService.viewAllDebitCards();
							for (DebitCardBean debitCardBean : debitCardBeans) {
								System.out.println("Account Number:" + debitCardBean.getAccountNumber()
										+ "  Debit Card number :" + debitCardBean.getDebitCardNumber()
										+ " Debit Card Status :" + debitCardBean.isDebitCardStatus() + "  Name on Card:"
										+ debitCardBean.getNameOnDebitCard() + "  CVV:" + debitCardBean.getDebitCvvNum()
										+ "  Current Pin:" + debitCardBean.getDebitCurrentPin() + "  Date of Expiry:"
										+ debitCardBean.getDebitDateOfExpiry() + " Unique Customer ID:"
										+ debitCardBean.getUCI() + "  Card Type:" + debitCardBean.getDebitCardType());
							}
							break;

						case LIST_EXISTING_CREDIT_CARDS:

							List<CreditCardBean> creditCardBeans = customService.viewAllCreditCards();
							for (CreditCardBean creditCardBean : creditCardBeans) {
								System.out.println("  Credit Card number :" + creditCardBean.getCreditCardNumber()
										+ " Credit Card Status :" + creditCardBean.isCreditCardStatus()
										+ "  Name on Card:" + creditCardBean.getNameOnCreditCard() + "  CVV:"
										+ creditCardBean.getCreditCvvNum() + "  Current Pin:"
										+ creditCardBean.getCreditCurrentPin() + "  Date of Expiry:"
										+ creditCardBean.getCreditDateOfExpiry() + " Unique Customer ID: "
										+ creditCardBean.getUCI() + "  Card Type:" + creditCardBean.getCreditCardType()
										+ " Credit Score:" + creditCardBean.getCreditScore() + "  Credit Limit: "
										+ creditCardBean.getCreditLimit() + "  Income Tax Return :"
										+ creditCardBean.getIncomeTaxReturn());
							}

							break;

						case APPLY_NEW_DEBIT_CARD:
							System.out.println("You are applying for a new Debit Card");
							System.out.println("Enter Account Number you want to apply debit card for :");
							accountNumber = scan.nextBigInteger();

							customService.applyNewDebitCard(accountNumber);
							break;
						case APPLY_NEW_CREDIT_CARD:

							System.out.println("You are applying for a new Credit Card");

							customService.applyNewCreditCard();

							break;
						case UPGRADE_EXISTING_DEBIT_CARD:
							System.out.println("Enter your Debit Card Number: ");
							BigInteger debitCardNumber;
							int myChoice;
							debitCardNumber = scan.nextBigInteger();
							String type = customService.verifyDebitcardType(debitCardNumber);
							System.out.println("Your debit card is:" + type);

							if (type.equals("Silver")) {
								System.out.println("Choose 1 to upgrade to Gold");
								System.out.println("Choose 2 to upgrade to Platinum");
								myChoice = scan.nextInt();
								customService.requestDebitCardUpgrade(debitCardNumber, myChoice);

							} else if (type.equals("Gold")) {
								System.out.println("Choose 2 to upgrade to Platinum");
								myChoice = scan.nextInt();
								customService.requestDebitCardUpgrade(debitCardNumber, myChoice);

							} else {
								System.out.println("You already have a Platinum Card");
							}

							break;
						case UPGRADE_EXISTING_CREDIT_CARD:
							System.out.println("Enter your Credit Card Number: ");
							BigInteger creditCardNumber;
							int myChoice1;
							creditCardNumber = scan.nextBigInteger();
							String type1 = customService.verifyCreditcardType(creditCardNumber);
							System.out.println("Your credit card is:" + type1);

							if (type1.equals("Silver")) {
								System.out.println("Choose 1 to upgrade to Gold");
								System.out.println("Choose 2 to upgrade to Platinum");
								myChoice1 = scan.nextInt();
								customService.requestCreditCardUpgrade(creditCardNumber, myChoice1);

							} else if (type1.equals("Gold")) {
								System.out.println("Choose 2 to upgrade to Platinum");
								myChoice1 = scan.nextInt();
								customService.requestCreditCardUpgrade(creditCardNumber, myChoice1);

							} else {
								System.out.println("You already have a Platinum Card");
							}

							break;
						case RESET_DEBIT_CARD_PIN:
							System.out.println("Enter your Debit Card Number: ");
							debitCardNumber = scan.nextBigInteger();
							boolean check = customService.verifyDebitCardNumber(debitCardNumber);
							if (check) {
								System.out.println("Enter your existing pin:");
								int pin = scan.nextInt();
								if (customService.verifyDebitPin(pin, debitCardNumber)) {
									System.out.println("Enter new pin");
									int newPin = scan.nextInt();
									customService.resetDebitPin(debitCardNumber, newPin);
								} else {
									System.out.println("Invalid pin entered");
								}

							} else {
								System.out.println("Invalid debit card number");
							}

							break;
						case RESET_CREDIT_CARD_PIN:
							System.out.println("Enter your Credit Card Number: ");
							creditCardNumber = scan.nextBigInteger();
							boolean check1 = customService.verifyCreditCardNumber(creditCardNumber);
							if (check1) {
								System.out.println("Enter your existing pin:");
								int pin = scan.nextInt();
								if (customService.verifyCreditPin(pin, creditCardNumber)) {
									System.out.println("Enter new pin");
									int newPin = scan.nextInt();
									customService.resetCreditPin(creditCardNumber, newPin);
								} else {
									System.out.println("Invalid pin entered");
								}

							} else {
								System.out.println("Invalid credit card number");
							}
							break;
						case REPORT_DEBIT_CARD_LOST:
							System.out.println("Enter your Debit Card Number: ");

							debitCardNumber = scan.nextBigInteger();
							customService.requestDebitCardLost(debitCardNumber);
							break;
						case REPORT_CREDIT_CARD_LOST:
							System.out.println("Enter your Credit Card Number: ");

							creditCardNumber = scan.nextBigInteger();
							customService.requestCreditCardLost(creditCardNumber);
							break;
						case REQUEST_DEBIT_CARD_STATEMENT:
							System.out.println("Enter your Debit Card Number: ");
							  debitCardNumber=scan.nextBigInteger(); 
							
							  
							  System.out.println("enter days : ");
							  int days = scan.nextInt();
							
							  try {
							  List<DebitCardTransaction> debitCardBeanTrns = customService.getDebitTrns(days,debitCardNumber);
							  for (DebitCardTransaction debitCardTrns : debitCardBeanTrns) 
							        System.out.println(debitCardTrns.toString());
							  }
							  
							  catch(Exception e) {
								  System.out.println("invalid date format or debit card does not exist");
							  }
								break;
						case REQUEST_CREDIT_CARD_STATEMENT:
							System.out.println("Enter your Credit Card Number: ");
							  creditCardNumber=scan.nextBigInteger(); 
							 System.out.println("enter days : ");
							  days = scan.nextInt();
							
							  try {
							  List<CreditCardTransaction> creditCardBeanTrans = customService.getCreditTrans(days,creditCardNumber);
							  for (CreditCardTransaction creditCardTrans : creditCardBeanTrans) 
							        System.out.println(creditCardTrans.toString());
							  }
							  
							  catch(Exception e) {
								  System.out.println("invalid date format or credit card does not exist");
							  }
							break;
						case REPORT_DEBITCARD_STATEMENT_MISMATCH:
							
							String transactionId1;
							System.out.println("Enter your transaction id");
							transactionId1=scan.next();
							customService.raiseDebitMismatchTicket(transactionId1);
							break;
						case REPORT_CREDITCARD_STATEMENT_MISMATCH:
							String transactionId;
							System.out.println("Enter your transaction id");
							transactionId=scan.next();
							customService.raiseCreditMismatchTicket(transactionId);
							break;
						case CUSTOMER_LOG_OUT:
							System.out.println("LOGGED OUT");
							break;
						}
					}
				}
			} else {
				if (userInput == 2) {

					System.out.println("You are logged in as a Bank Admin");
					BankMenu cchoice = null;
					while (cchoice != BankMenu.BANK_LOG_OUT) {
						System.out.println("Menu");
						System.out.println("--------------------");
						System.out.println("Choice");
						System.out.println("--------------------");
						for (BankMenu mmenu : BankMenu.values()) {
							System.out.println(mmenu.ordinal() + "\t" + mmenu);
						}
						System.out.println("Choice");
						int ordinal = scan.nextInt();
						if (ordinal >= 0 && ordinal < BankMenu.values().length) {
							cchoice = BankMenu.values()[ordinal];

							switch (cchoice) {

							case LIST_QUERIES:
								List<CaseIdBean> caseBeans = bankService.viewQueries();
								
								for (CaseIdBean caseId : caseBeans) {
									
									System.out.println(caseId.toString());
								}
								break;

							case REPLY_QUERIES:
								System.out.println("Enter query ID ");
		                           String queryId = scan.next();
		                           if(bankService.verifyQueryId(queryId))
		                          {
			                           System.out.println("Enter new Status");
			                             String newStatus = scan.next();
			                            bankService.setQueryStatus(queryId,newStatus);
			
		                          }
								


								break;
							case VIEW_DEBIT_CARD_STATEMENT:
								System.out.println("Enter your Debit Card Number: ");
								  BigInteger debitCardNumber=scan.nextBigInteger(); 
								
								  
								  System.out.println("enter days : ");
								  int days = scan.nextInt();
								
								  try {
								  List<DebitCardTransaction> debitCardBeanTrns = bankService.getDebitTrns(days,debitCardNumber);
								  for (DebitCardTransaction debitCardTrns : debitCardBeanTrns) 
								        System.out.println(debitCardTrns.toString());
								  }
								  
								  catch(Exception e) {
									  System.out.println("invalid date format or debit card does not exist");
								  }
								

								break;
							case VIEW_CREDIT_CARD_STATEMENT:
								System.out.println("Enter your Credit Card Number: ");
								BigInteger creditCardNumber=scan.nextBigInteger(); 
								 System.out.println("enter days : ");
								  days = scan.nextInt();
								
								  try {
								  List<CreditCardTransaction> creditCardBeanTrans = bankService.getCreditTrans(days,creditCardNumber);
								  for (CreditCardTransaction creditCardTrans : creditCardBeanTrans) 
								        System.out.println(creditCardTrans.toString());
								  }
								  
								  catch(Exception e) {
									  System.out.println("invalid date format or credit card does not exist");
								  }

								break;
							case BANK_LOG_OUT:
								System.out.println("LOGGED OUT");
								break;

							}
						}
					}
				} else {
					System.out.println("Invalid Option!!");

				}

			}
		}
	}
}