package bank.service;


import bank.DTO.AccountDTO;
import bank.DTO.adapter.AccountAdapter;
import bank.DTO.adapter.AccountEntryAdapter;
import bank.DTO.adapter.CustomerAdapter;
import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.jms.IJMSSender;
import bank.logging.ILogger;
import bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ICurrencyConverter currencyConverter;
	@Autowired
	private IJMSSender jmsSender;
	@Autowired
	private ILogger logger;

	public AccountDTO createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		AccountDTO accountDTO = AccountAdapter.getAccountDTOFromAccount(account);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return accountDTO;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountnumber(accountNumber);
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public AccountDTO getAccount(long accountNumber) {
		Account account = accountRepository.findByAccountnumber(accountNumber);
		return AccountAdapter.getAccountDTOFromAccount(account);
	}

	public Collection<AccountDTO> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountDTO> accountDTOS = new ArrayList<>();
		for(Account acc: accounts){
			AccountDTO accountDTO = AccountAdapter.getAccountDTOFromAccount(acc);
			accountDTO.setCustomer(CustomerAdapter.getCustomerDTOFromCustomer(acc.getCustomer()));
			accountDTO.setEntryList(AccountEntryAdapter.getAccountEntryDTOsFromAccountEntry((List<AccountEntry>) acc.getEntryList()));
			accountDTOS.add(accountDTO);
		}
		return accountDTOS;
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountnumber(accountNumber);
		account.withdraw(amount);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountnumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountRepository.save(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountnumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountRepository.findByAccountnumber(fromAccountNumber);
		Account toAccount = accountRepository.findByAccountnumber(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
