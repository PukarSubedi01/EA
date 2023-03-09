package bank.DTO.adapter;

import bank.DTO.AccountDTO;
import bank.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO){
        return  new Account(accountDTO.getAccountnumber());
    }

    public static AccountDTO getAccountDTOFromAccount(Account account){
        return  new AccountDTO(account.getAccountnumber());
    }

    public static List<AccountDTO> getAccountDTOList(List<Account> accounts){
        List<AccountDTO> accountDto = new ArrayList<>();
        for (Account acc: accounts) {
            accountDto.add(getAccountDTOFromAccount(acc));
        }
        return accountDto;
    }
}
