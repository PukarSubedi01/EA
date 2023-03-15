package bank.service;

import bank.service.scheduling.BankStatementPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter implements ICurrencyConverter{
    Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);

    public double euroToDollars (double amount){
		logger.info("CurrencyConverter: converting "+amount+" dollars to euros");
        return 1.57 * amount;
    }
    
    public double dollarsToEuros (double amount){
        return 0.637 * amount;
    }
}
