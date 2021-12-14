package edu.miu.cs.cs544.bank.service;

import java.util.Collection;


import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.cs.cs544.bank.dao.AccountDAO;
import edu.miu.cs.cs544.bank.dao.CustomerDAO;
import edu.miu.cs.cs544.bank.dao.HibernateUtil;
import edu.miu.cs.cs544.bank.dao.IAccountDAO;
import edu.miu.cs.cs544.bank.dao.ICustomerDAO;
import edu.miu.cs.cs544.bank.domain.Account;
import edu.miu.cs.cs544.bank.domain.Customer;
import edu.miu.cs.cs544.bank.jms.IJMSSender;
import edu.miu.cs.cs544.bank.jms.JMSSender;
import edu.miu.cs.cs544.bank.logging.ILogger;
import edu.miu.cs.cs544.bank.logging.Logger;

@Service
public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;
	private ICustomerDAO customerDAO;
	private ICurrencyConverter currencyConverter;
	private IJMSSender jmsSender;
	private ILogger logger;
	private SessionFactory sf;

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setCurrencyConverter(ICurrencyConverter currencyConverter) {
		this.currencyConverter = currencyConverter;
	}

	public void setJmsSender(IJMSSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Account createAccount(long accountNumber, String customerName) {
		Customer customer = new Customer(customerName);
		customerDAO.saveCustomer(customer);
		Account account = new Account(accountNumber, customer);
		accountDAO.saveAccount(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deposit(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly = true)
	public Account getAccount(long accountNumber) {
		return accountDAO.loadAccount(accountNumber);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly = true)
	public Collection<Account> getAllAccounts() {
		Collection<Account> accounts = accountDAO.getAccounts();
		for (Account account : accounts)
			Hibernate.initialize(account.getEntryList());
		return accounts;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void withdraw(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void depositEuros(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
