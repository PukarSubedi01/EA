package bank.DTO.adapter;


import bank.DTO.AccountEntryDTO;
import bank.domain.AccountEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountEntryAdapter {
    public static AccountEntry getAccountEntryFromAccountDTO(AccountEntryDTO accountEntryDTO){
        return new AccountEntry(accountEntryDTO.getDate(),
                accountEntryDTO.getAmount(),
                accountEntryDTO.getDescription(),
                accountEntryDTO.getFromAccountNumber(),
                accountEntryDTO.getFromPersonName());

    }

    public static AccountEntryDTO getAccountEntryDTOFromAccount(AccountEntry accountEntry){
        return new AccountEntryDTO(accountEntry.getDate(),
                accountEntry.getAmount(),
                accountEntry.getDescription(),
                accountEntry.getFromAccountNumber(),
                accountEntry.getFromPersonName());
    }
    public static List<AccountEntryDTO> getAccountEntryDTOsFromAccountEntry(List<AccountEntry> accountEntries){
        List<AccountEntryDTO> accountEntryDTOS = new ArrayList<>();
        for (AccountEntry accountEntry:accountEntries){
            accountEntryDTOS.add(getAccountEntryDTOFromAccount(accountEntry));
        }
        return accountEntryDTOS;
    }
}
