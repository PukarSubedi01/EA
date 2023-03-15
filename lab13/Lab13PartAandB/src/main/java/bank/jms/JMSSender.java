package bank.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JMSSender implements IJMSSender{

	Logger logger = LoggerFactory.getLogger(JMSSender.class);
	@Autowired
	JmsTemplate jmsTemplate;

	public void sendJMSMessage (String text){
		logger.info("JMSSender: sending JMS message ="+text);
		jmsTemplate.convertAndSend("taxQueue",text);
	}

}
