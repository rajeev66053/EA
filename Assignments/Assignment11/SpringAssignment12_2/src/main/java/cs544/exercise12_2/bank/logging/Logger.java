package cs544.exercise12_2.bank.logging;

import org.springframework.stereotype.Component;

public class Logger implements ILogger{

	public void log(String logstring) {
		java.util.logging.Logger.getLogger("BankLogger").info(logstring);		
	}

}
