package edu.miu.cs.cs544.examples.A;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.miu.cs.cs544.examples.HibernateUtils;

public class Application {
	
	private static final SessionFactory sessionFactory;
	
	static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(
			Arrays.asList(
				Employee.class, Laptop.class
			)
		);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee employee1 = new Employee("Rajeev", "Thapaliya");
			session.persist(employee1);
			
			Laptop laptop1 = new Laptop("Dell", "new", employee1);
			Laptop laptop2 = new Laptop("lenovo", "new", employee1);
			session.persist(laptop1);
			session.persist(laptop2);
			
			session.persist(employee1);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			List<Employee> employeeList = session.createQuery("from Employee").list();
			for (Employee a : employeeList) {
				System.out.println(a);
			}
			
			List<Laptop> list2 = session.createQuery("from Laptop").list();
			for (Laptop a : list2) {
				System.out.println(a);
			}
			
			
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}