package edu.miu.cs.cs544.examples.B;

import java.util.Arrays;
import java.util.Date;
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
				Passenger.class, Flight.class
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
			Passenger passenger = new Passenger("Rajeev");
			session.persist(passenger);
			
			Flight flight1 = new Flight(
				"A010101", 
				"Fairfield",
				"Seattle", 
				new Date()
			);
			Flight flight2 = new Flight(
				"A010102", 
				"Seattle",
				"Kathmandu", 
				new Date()
			);
			session.persist(flight1);
			session.persist(flight2);
			
			passenger.addFlight(flight1);
			passenger.addFlight(flight2);
			
			session.save(passenger);

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
			
			List<Passenger> list0 = session.createQuery("from Passenger").list();
			for (Passenger a : list0) {
				System.out.println(a);
			}
			
			List<Flight> list2 = session.createQuery("from Flight").list();
			for (Flight a : list2) {
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