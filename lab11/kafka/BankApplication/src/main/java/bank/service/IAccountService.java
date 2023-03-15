package bank.service;

import bank.service.dto.responseDto.AccountDto;

import java.util.Collection;



public interface IAccountService {
    public AccountDto createAccount(long accountNumber, String customerName);
    public AccountDto getAccount(long accountNumber);
    public Collection<AccountDto> getAllAccounts();
    public void deposit (long accountNumber, double amount);
    public void withdraw (long accountNumber, double amount);
    public void depositEuros (long accountNumber, double amount);
    public void withdrawEuros (long accountNumber, double amount);
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
}
