package edu.miu.cs.cs544.exercise;

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
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Appointment.class, 
				                                                        Patient.class, 
				                                                        Payment.class,
				                                                        Doctor.class				                                                        
				                                                        ));
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// a) Create a Bidirectional OneToMany association between Department and Employee using annotations.
			Doctor dt = new Doctor();
			dt.setDoctortype("Eye doctor");
			dt.setFirstname("Frank");
			dt.setLastname("Brown");
			
			Patient pt = new Patient();
			pt.setName("John Doe");
			pt.setStreet("100 Main Street");
			pt.setCity("Boston");
			pt.setZip("23114");
			
			Payment pm = new Payment();
			pm.setAmount(100);
			pm.setPaydate("12/06/08");
			
			Appointment ap = new Appointment();
			ap.setAppdate("15/05/08");
			ap.setDoctor(dt);
			ap.setPatient(pt);
			ap.setPayment(pm);
			
			// save the entities
			session.persist(dt);			
			session.persist(pt);
			//session.persist(pm);
			session.persist(ap);
			
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