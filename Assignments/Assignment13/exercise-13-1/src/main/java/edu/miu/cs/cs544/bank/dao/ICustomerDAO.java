package edu.miu.cs.cs544.bank.dao;

import java.util.Collection;
import edu.miu.cs.cs544.bank.domain.Customer;

public interface ICustomerDAO {
	public void saveCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public Customer loadCustomer(long id);
	public Collection<Customer> getCustomer();
}
