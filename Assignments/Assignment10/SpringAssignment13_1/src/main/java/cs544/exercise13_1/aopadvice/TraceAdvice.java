package cs544.exercise13_1.aopadvice;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import cs544.exercise13_1.CustomerService;
import cs544.exercise13_1.EmailSender;
import cs544.exercise13_1.IEmailSender;

@Aspect
public class TraceAdvice {
	
	@After("execution(* cs544.exercise13_1.CustomerService.addCustomer(..))")
	public void traceaftermethod(JoinPoint joinpoint) {
		Object[] args = joinpoint.getArgs();
		String name = (String)args[0];
		String email = (String)args[1];
		CustomerService customerService= (CustomerService)joinpoint.getTarget();
		IEmailSender emailSender= customerService.getEmailSender();
		System.out.println(new Date()+" method= sendMail address="+email
				+" message= Welcome " + name + " as a new customer"
				+" outgoing mail server ="+emailSender.getOutgoingMailServer()
				);
	}

}
