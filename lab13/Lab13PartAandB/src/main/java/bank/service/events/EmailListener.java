package bank.service.events;

import bank.service.scheduling.BankStatementPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailListener {
    Logger logger = LoggerFactory.getLogger(EmailListener.class);

    @EventListener
    public void onEvent(EmailEvent event) {
       logger.info("received event :" + event.getMessage());;
    }
}
