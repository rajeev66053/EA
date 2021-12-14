package cs544.exercise16_2;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Servlet Filter implementation class OpenSessionInViewFilter
 */
public class OpenSessionInViewFilter implements Filter {
	private SessionFactory sf;
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO implement actual session in view filter code
		
		Transaction tx = null;
		try {
			tx = sf.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			tx.commit();
		}catch(RuntimeException ex) {
			try { 
				ex.printStackTrace(); tx.rollback();
			} catch(RuntimeException rbEx) {
				System.out.println("Could not rollback transaction " + rbEx); rbEx.printStackTrace();
			}
		}
		
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
		sf = HibernateUtils.getSessionFactory(Arrays.asList(Student.class, Course.class));
	}
}
