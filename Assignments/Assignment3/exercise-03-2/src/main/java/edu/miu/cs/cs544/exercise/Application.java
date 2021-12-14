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
				Department.class,
				Employee.class,
				Office.class,
				Customer.class,
				Reservation.class,
				Book.class,
				Publisher.class,
				Student.class,
				Course.class
				));
	}

	public static void main(String[] args) {
		a();
		b();
		c();
		d();
		e();
		f();
	}


	private static void a() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Department depto1 = new Department();
			depto1.setName("Department1");

			Employee e1 = new Employee();
			e1.setName("Employee1");
			Employee e2 = new Employee();
			e2.setName("Employee2");

			depto1.setEmployees(new ArrayList(Arrays.asList(e1,e1)));
			session.persist(depto1);
			tx.commit();

			List<Department> departments = session.createQuery("from Department").list();
			for(Department dept : departments) {
				System.out.println(dept);
			}



		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void b() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Publisher publisher1 = new Publisher();
			publisher1.setName("Publisher1");

			Book b1 = new Book();
			b1.setIsbn(1234);
			b1.setTitle("Book1");
			b1.setAuthor("Author1");
			b1.setPublisher(publisher1);

			Book b2 = new Book();
			b2.setIsbn(4321);
			b2.setTitle("Book2");
			b2.setAuthor("Author2");
			b2.setPublisher(publisher1);

			session.persist(b1);
			session.persist(b2);
			tx.commit();
			List<Book> books = session.createQuery("from Book").list();
			for(Book book : books) {
				System.out.println(book);
			}



		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void c() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Course c1 = new Course();
			c1.setCoursenumber(123);
			c1.setName("Course1");

			Course c2 = new Course();
			c2.setCoursenumber(321);
			c2.setName("Course2");

			Student s1 = new Student();
			s1.setFirstname("First1");
			s1.setFirstname("Last1");

			Student s2 = new Student();
			s2.setFirstname("First2");
			s2.setFirstname("Last2");


			c1.setStudents(new ArrayList(Arrays.asList(s1,s2)));
			c2.setStudents(new ArrayList(Arrays.asList(s1,s2)));
			session.persist(c1);
			session.persist(c2);
			tx.commit();
			List<Course> courses = session.createQuery("from Course").list();
			for(Course c : courses) {
				System.out.println(c);
			}



		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void d() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Customer c1 = new Customer();
			c1.setName("Customer1");
			
			Reservation r1 = new Reservation();
			r1.setDate(Date.from(Instant.now()));
			
			Reservation r2 = new Reservation();
			r2.setDate(Date.from(Instant.now()));
			
			
			c1.setReservations(new ArrayList(Arrays.asList(r1,r2)));

			session.persist(c1);
			tx.commit();
			List<Customer> customers = session.createQuery("from Customer").list();
			for(Customer c : customers) {
				System.out.println(c);
			}



		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	private static void e() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			
			
			Reservation r1 = session.get(Reservation.class, 1);
			Book b1 = session.get(Book.class, 1234);
			r1.setBook(b1);
			session.update(r1);
			tx.commit();
			List<Customer> customers = session.createQuery("from Customer").list();
			for(Customer c : customers) {
				System.out.println(c.toString());
			}



		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	private static void f() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Employee e1 = session.get(Employee.class, 1);
			Office of1 = new Office();
			of1.setBuilding("Building1");
			
			of1.setEmployees(new ArrayList(Arrays.asList(e1)));
			session.persist(of1);		
		
			
			session.update(e1);
			tx.commit();
			List<Employee> employees = session.createQuery("from Employee").list();
			for(Employee e : employees) {
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