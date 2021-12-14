package edu.miu.cs.cs544.exercise;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.miu.cs.cs544.examples.HibernateUtils;

public class AppBook {
	private static SessionFactory sessionFactory;

	static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of Book and set values in it
			Book book1 = new Book("Harry Potter", "ISBN1","author1", 2000.00,new Date());
			Book book2 = new Book("STC", "ISBN2","author2", 195000.00,new Date());
			Book book3 = new Book("Enterprise Architecture", "ISBN3","author3", 1950.00,new Date());
			
			// save the Book
			session.persist(book1);
			session.persist(book2);
			session.persist(book3);
			System.out.println(book1);
			System.out.println(book2);
			System.out.println(book3);

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
			// retrieve all books
			Book book = session.get(Book.class,1);

			System.out.println(book);
			book.setTitle("Algorithm");

			System.out.println(book);
			
			
			Book bookToDelete = (Book) session.load(Book.class, 2);
			session.delete(bookToDelete);
			System.out.println("book 2 deleted");	
			
			
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
			// retrieve all books
			List<Book> bookList = session.createQuery("from Book", Book.class).list();
			for (Book b : bookList) {
				System.out.println(b);
			}
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
			// retrieve all books
			List<Book> bookList = session.createQuery("from Book", Book.class).list();
			for (Book b : bookList) {
				System.out.println(b);
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