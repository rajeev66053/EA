package edu.miu.cs.cs544.bank.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import edu.miu.cs.cs544.bank.domain.Account;
import edu.miu.cs.cs544.bank.domain.AccountEntry;
import edu.miu.cs.cs544.bank.domain.Customer;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Configuration configuration = new Configuration();
    static List<Class> entityClasses = Arrays.asList(Account.class, Customer.class, AccountEntry.class);
    
    static {
    	try {
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost/cs544");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "password");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
            settings.put(Environment.HBM2DDL_AUTO, "create");
            //settings.put(Environment.HBM2DDL_AUTO, "verify");
            
            configuration.setProperties(settings);
            entityClasses.forEach(entityClass -> configuration.addAnnotatedClass(entityClass));
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }   
}
