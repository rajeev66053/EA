package edu.miu.cs.cs544.bank.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.miu.cs.cs544.bank.domain.Customer;

@Repository
public class CustomerDAO implements ICustomerDAO {
	private SessionFactory sf;
	
	// for spring automatically sets SF using DI
	public void setSessionFactory(SessionFactory sf) { 
		this.sf = sf;
	}

	public void saveCustomer(Customer customer) {
		sf.getCurrentSession().persist(customer);
	}

	public void updateCustomer(Customer customer) {
		sf.getCurrentSession().saveOrUpdate(customer);
	}

	public Customer loadCustomer(long id) {
		return (Customer) sf.getCurrentSession().get(Customer.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Customer> getCustomer() {
		return sf.getCurrentSession().createQuery("from Customer").list();
	}

}
