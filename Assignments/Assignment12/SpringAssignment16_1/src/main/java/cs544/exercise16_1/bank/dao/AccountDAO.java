package cs544.exercise16_1.bank.dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cs544.exercise16_1.bank.domain.Account;
import cs544.exercise16_1.bank.domain.AccountEntry;
import cs544.exercise16_1.bank.domain.Customer;

public class AccountDAO implements IAccountDAO {
	
	Collection<Account> accountlist = new ArrayList<Account>();
	
	@SuppressWarnings("unchecked")
	
	private SessionFactory sessionFactory  = HibernateUtils.getSessionFactory(Arrays.asList(Account.class, Customer.class, AccountEntry.class));

	public void saveAccount(Account account) {
		// System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
//		accountlist.add(account); // add the new
		Session session = sessionFactory.getCurrentSession();
		session.persist(account); // add the new
	}

	public void updateAccount(Account account) {
		// System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
//		Account accountexist = loadAccount(account.getAccountnumber());
//		if (accountexist != null) {
//			accountlist.remove(accountexist); // remove the old
//			accountlist.add(account); // add the new
//		}
		
		Session session = sessionFactory.getCurrentSession();
		session.update(account);

	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
//		for (Account account : accountlist) {
//			if (account.getAccountnumber() == accountnumber) {
//				return account;
//			}
//		}
//		return null;
		
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Account.class, accountnumber);
	}

	public Collection<Account> getAccounts() {
//		return accountlist;
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("select distinct a from Account a  join fetch a.customer left join fetch a.entryList").list();
	}

}
