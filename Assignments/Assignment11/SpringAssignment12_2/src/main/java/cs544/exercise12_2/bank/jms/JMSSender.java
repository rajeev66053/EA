package cs544.exercise12_2.bank.jms;

import org.springframework.stereotype.Component;

public class JMSSender implements IJMSSender{
	
	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message ="+text);
	}

}
