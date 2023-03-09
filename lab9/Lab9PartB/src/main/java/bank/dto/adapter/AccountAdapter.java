package bank.dto.adapter;

import bank.domain.Account;
import bank.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO){
        return new Account(
            accountDTO.getAccountnumber()
        );
    }
    public static AccountDTO getAccountDTOFromAccount(Account account){
        return new AccountDTO(
                account.getAccountnumber()
        );
    }
    public static List<AccountDTO> getAccountDTOsFromAccounts(List<Account> accounts){
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account:accounts){
            accountDTOS.add(getAccountDTOFromAccount(account));
        }
        return accountDTOS;
    }
}
