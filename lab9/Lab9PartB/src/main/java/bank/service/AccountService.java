package bank.service;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.dto.CustomerDTO;
import bank.dto.adapter.AccountAdapter;
import bank.dto.adapter.AccountEntryAdapter;
import bank.dto.adapter.CustomerAdapter;
import bank.jms.IJMSSender;
import bank.logging.ILogger;
import bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


// Added annotation
@Service
@Transactional
public class AccountService implements IAccountService {
	// injection using autowire
	@Autowired
	private ICurrencyConverter currencyConverter;
	@Autowired
	private IJMSSender jmsSender;
	@Autowired
	private ILogger logger;
	@Autowired
	private AccountRepository accountRepository;

	public AccountDTO createAccount(long accountNumber, String customerName) {
		AccountDTO accountDTO = new AccountDTO(accountNumber);
		CustomerDTO customerDTO = new CustomerDTO(customerName);
		accountDTO.setCustomer(customerDTO);
		Account account = AccountAdapter.getAccountFromAccountDTO(accountDTO);
		Customer customer = CustomerAdapter.getCustomerFromCustomerDTO(customerDTO);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return AccountAdapter.getAccountDTOFromAccount(account);
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
		List<AccountDTO> accountDTOS = new ArrayList<>();
		List<Account> accounts = accountRepository.findAll();
		for (Account account:accounts){
			AccountDTO accountDTO = AccountAdapter.getAccountDTOFromAccount(account);
			accountDTO.setEntryList(AccountEntryAdapter.getAccountEntryDTOsFromAccountEntries((List<AccountEntry>) account.getEntryList()));
			accountDTO.setCustomer(CustomerAdapter.getCustomerDTOFromCustomer(account.getCustomer()));
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
