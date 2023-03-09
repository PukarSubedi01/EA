package bank.jms;

import org.springframework.stereotype.Component;

// Added annotation
@Component
public class JMSSender implements IJMSSender{
	
	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message ="+text);
	}

}
