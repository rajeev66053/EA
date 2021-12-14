package edu.miu.cs.cs544.bank.dao;

import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import edu.miu.cs.cs544.bank.domain.AccountEntry;

@Repository
public class AccountEntryDAO implements IAccountEntryDAO {
	private SessionFactory sf;
	
	// for spring automatically sets SF using DI
	public void setSessionFactory(SessionFactory sf) { 
		this.sf = sf;
	}
	
	public void saveAccountEntry(AccountEntry accountEntry) {
		sf.getCurrentSession().persist(accountEntry);
	}

	public void updateAccountEntry(AccountEntry accountEntry) {
		sf.getCurrentSession().saveOrUpdate(accountEntry);
	}

	public AccountEntry loadAccountEntry(long id) {
		return (AccountEntry) sf.getCurrentSession().get(AccountEntry.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<AccountEntry> getAccountEntries() {
		return sf.getCurrentSession().createQuery("from AccountEntry").list();
	}

}
