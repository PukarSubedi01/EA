package bank.service.dto.adapter;

import bank.domain.Account;
import bank.service.dto.responseDto.AccountDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountAdapter {
    public static AccountDto convertAccountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountnumber(account.getAccountnumber());
        accountDto.setCustomer(CustomerAdapter.convertCustomerToCustomerDto(account.getCustomer()));
        accountDto.setEntryList(AccountEntryAdapter.convertAccountEntryCollectionToAccountEntryDtoCollection(account.getEntryList()));
        return accountDto;
    }
    public static Account convertAccountDtoToAccount(AccountDto accountDto){
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountnumber(accountDto.getAccountnumber());
        account.setCustomer(CustomerAdapter.convertCustomerDtoToCustomer(accountDto.getCustomer()));
        account.setEntryList(AccountEntryAdapter.convertAccountEntryDtoCollectionToAccountEntryCollection(accountDto.getEntryList()));
        return account;
    }

    public static Collection<AccountDto> convertAccountCollectionToAccountDtoCollection (Collection<Account> accounts){
        return accounts.stream().map(account -> convertAccountToAccountDto(account)).collect(Collectors.toList());
    }

    public static Collection<Account> convertAccountDtoCollectionToAccountCollection (Collection<AccountDto> accountDtos){
        return accountDtos.stream().map(accountDto -> convertAccountDtoToAccount(accountDto)).collect(Collectors.toList());
    }
}
