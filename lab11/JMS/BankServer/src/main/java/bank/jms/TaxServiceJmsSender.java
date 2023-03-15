package bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaxServiceJmsSender implements  IJMSSender {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    public void sendJMSMessage(String text) {
        System.out.println("Sending message ="+text);
        jmsTemplate.convertAndSend("transactionQueue", text);
    }
}
