package bank.service;

import bank.dao.AccountDAO;
import bank.domain.Account;
import bank.domain.Customer;
import bank.exception.NotFoundException;
import bank.service.dto.responseDto.AccountDto;
import bank.service.dto.adapter.AccountAdapter;
import bank.jms.IJMSSender;
import bank.logging.ILogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class AccountService implements IAccountService {
    private AccountDAO accountDAO;
    private ICurrencyConverter currencyConverter;
    private IJMSSender jmsSender;
    private ILogger logger;

    public AccountService(AccountDAO accountDAO, ICurrencyConverter currencyConverter, IJMSSender jmsSender, ILogger logger) {
        this.accountDAO = accountDAO;
        this.currencyConverter = currencyConverter;
        this.jmsSender = jmsSender;
        this.logger = logger;
    }

    private Account getValidAccount(long accountNumber) {
        Account account = accountDAO.findByAccountnumber(accountNumber);
        if (account == null)
            throw new NotFoundException("Account with account number " + accountNumber + " not found");
        return account;
    }

    public AccountDto createAccount(long accountNumber, String customerName) {
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.save(account);
        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
        return AccountAdapter.convertAccountToAccountDto(account);
    }

    public void deposit(long accountNumber, double amount) {
        Account account = getValidAccount(accountNumber);
        account.deposit(amount);
        accountDAO.save(account);
        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    public AccountDto getAccount(long accountNumber) {
        Account account = getValidAccount(accountNumber);
        return AccountAdapter.convertAccountToAccountDto(account);
    }

    public Collection<AccountDto> getAllAccounts() {
        return AccountAdapter.convertAccountCollectionToAccountDtoCollection(accountDAO.findAll());
    }

    public void withdraw(long accountNumber, double amount) {
        Account account = getValidAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.save(account);
        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    public void depositEuros(long accountNumber, double amount) {
        Account account = getValidAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.save(account);
        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    public void withdrawEuros(long accountNumber, double amount) {
        Account account = getValidAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountDAO.save(account);
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account fromAccount = getValidAccount(fromAccountNumber);
        Account toAccount = getValidAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.save(fromAccount);
        accountDAO.save(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
        }
    }
}
