package bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSSender implements IJMSSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendJMSMessage(String text) {
        System.out.println("JMSSender: sending JMS message =" + text);
		jmsTemplate.convertAndSend("taxServiceQueue", text);
    }

}
