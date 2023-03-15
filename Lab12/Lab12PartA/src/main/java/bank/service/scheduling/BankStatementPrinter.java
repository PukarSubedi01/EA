package bank.service.scheduling;

import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BankStatementPrinter {

    @Autowired
    AccountService accountService;
    @Scheduled(fixedRate = 20000)
    public void printStatement() {
        System.out.println(
                accountService.getAllAccounts()

        );
    }

}
