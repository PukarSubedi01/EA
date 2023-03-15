package bank.service.scheduling;

import bank.service.AccountService;
import bank.service.events.TraceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BankStatementPrinter {
    Logger logger = LoggerFactory.getLogger(BankStatementPrinter.class);
    @Autowired
    AccountService accountService;
    @Scheduled(fixedRate = 20000)
    public void printStatement() {
       logger.info(accountService.getAllAccounts().toString());
    }

}
