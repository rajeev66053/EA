package edu.miu.cs.cs544.bank.dao;

import java.util.Collection;

import edu.miu.cs.cs544.bank.domain.Account;
import edu.miu.cs.cs544.bank.domain.AccountEntry;

public interface IAccountEntryDAO {
	public void saveAccountEntry(AccountEntry accountEntry);
	public void updateAccountEntry(AccountEntry accountEntry);
	public AccountEntry loadAccountEntry(long id);
	public Collection<AccountEntry> getAccountEntries();
}
