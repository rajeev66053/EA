package edu.miu.cs.cs544.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
     
	private static final String USERNAME_QUERY = "SELECT cust.emailaddress AS username, cust.password AS pass, 1 AS [enabled]"
			+ "  FROM [`enterprise-architecture`].[customer] cust WHERE e.[EmailAddress] = ?";
	
	private static final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT cust.emailaddress AS username, cust.role AS [role]"
			+ "  FROM [`enterprise-architecture`].[customer] cust  WHERE e.EmailAddress =?";
 
    @Autowired
    private DataSource dataSource;
     
    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
        
        auth.inMemoryAuthentication()
    		.passwordEncoder(encoder)
    		.withUser("rajeevthapaliya@gmail.com")
    		.password(encoder.encode("password"))
    		.roles("Service");
    	
        auth.jdbcAuthentication()        	
        	.passwordEncoder(encoder) 
            .dataSource(dataSource)
            .usersByUsernameQuery(USERNAME_QUERY)
            .authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http		
		.authorizeRequests()
			.antMatchers("/favicon.ico").permitAll()
			.antMatchers("/countries/**").hasAnyRole("Service")
			.antMatchers("/customers/**").hasAnyRole("Service")
			.anyRequest().authenticated()
			.and()
		.formLogin();
    }
    
}

