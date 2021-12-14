package edu.miu.cs.cs544.examples.C;

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
				School.class, Student.class
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
			School school = new School("MIU");
			session.persist(school);
			
			Student student1 = new Student(
				611944, 
				"Rajeev",
				"Thapaliya"
			);
			Student student2 = new Student(
				933822, 
				"FIRSTNAME",
				"LASTNAME"
			);
			session.persist(student1);
			session.persist(student2);
			
			school.addStudent(student1);
			school.addStudent(student2);

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
			
			List<School> list0 = session.createQuery("from School").list();
			for (School a : list0) {
				System.out.println(a);
			}
			
			List<Student> list2 = session.createQuery("from Student").list();
			for (Student a : list2) {
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