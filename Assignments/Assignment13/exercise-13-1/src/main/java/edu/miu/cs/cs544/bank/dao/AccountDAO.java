package edu.miu.cs.cs544.bank.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.cs.cs544.bank.domain.Account;

@Repository
public class AccountDAO implements IAccountDAO {
	private SessionFactory sf;// = HibernateUtil.getSessionFactory();
	
	// for spring automatically sets SF using DI
	public void setSessionFactory(SessionFactory sf) { 
		this.sf = sf;
	}

	public void saveAccount(Account account) {
		sf.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {
		sf.getCurrentSession().saveOrUpdate(account);
	}

	public Account loadAccount(long accountnumber) {
		return (Account) sf.getCurrentSession().get(Account.class, accountnumber);
	}

	@SuppressWarnings("unchecked")
	public Collection<Account> getAccounts() {
		return sf.getCurrentSession().createQuery("from Account").list();
	}
}
