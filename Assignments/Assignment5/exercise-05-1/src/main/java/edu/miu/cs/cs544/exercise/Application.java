package edu.miu.cs.cs544.exercise;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.miu.cs.cs544.examples.HibernateUtils;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(
				Customer.class,
				Order.class,
				OrderLine.class,
				Product.class,
				DVD.class,
				CD.class,
				Book.class
				));
	}

	public static void main(String[] args) {
		insertData();
	}


	private static void insertData() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer cust1 = new Customer();
			cust1.setFirstname("CustFName1");
			cust1.setLastname("CustLName1");
						
			CD product1 = new CD();
			product1.setName("CD");
			product1.setDescription("Rock");
			product1.setArtist("Famous");

			DVD product2 = new DVD();
			product2.setName("DVD");
			product2.setDescription("Movie");
			product2.setGenre("Action");
			
			Book product3 = new Book();
			product3.setName("Book");
			product3.setDescription("Story Book");
			product3.setTitle("Best book");
			

			//----------------------------------------
			Order o1 = new Order();
			o1.setDate(Date.from(Instant.now()));
			OrderLine ol1 =new OrderLine();
			ol1.setProduct(product1);
			ol1.setQuantity(2);
			o1.addOrderLine(ol1);
			//-----------------------------------
			
			Order o2 = new Order();
			o2.setDate(Date.from(Instant.now()));
			
			OrderLine ol2 =new OrderLine();
			ol2.setProduct(product2);
			ol2.setQuantity(4);
			o2.addOrderLine(ol2);
			
			OrderLine ol3 =new OrderLine();
			ol3.setProduct(product3);
			ol3.setQuantity(7);
			o2.addOrderLine(ol3);
			
			//------------------------------------
			o1 = cust1.addOrder(o1);
			o2 = cust1.addOrder(o2);
			
			session.persist(cust1);
			
			tx.commit();

			List<Customer> customer = session.createQuery("from Customer").list();
			for(Customer e : customer) {
				System.out.println(e.toString());
			}


		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

}