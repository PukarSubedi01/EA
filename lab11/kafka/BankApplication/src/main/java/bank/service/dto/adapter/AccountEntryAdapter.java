package bank.service.dto.adapter;

import bank.domain.AccountEntry;
import bank.service.dto.responseDto.AccountEntryDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountEntryAdapter {
    public static AccountEntryDto convertAccountEntryToAccountEntryDto(AccountEntry accountEntry) {
        AccountEntryDto accountEntryDto = new AccountEntryDto();
        accountEntryDto.setId(accountEntry.getId());
        accountEntryDto.setDate(accountEntry.getDate());
        accountEntryDto.setFromAccountNumber(accountEntry.getFromAccountNumber());
        accountEntryDto.setFromPersonName(accountEntry.getFromPersonName());
        accountEntryDto.setAmount(accountEntry.getAmount());
        accountEntryDto.setDescription(accountEntry.getDescription());
        return accountEntryDto;
    }

    public static AccountEntry convertAccountEntryDtoToAccountEntry(AccountEntryDto accountEntryDto) {
        AccountEntry accountEntry = new AccountEntry();
        accountEntry.setId(accountEntryDto.getId());
        accountEntry.setDate(accountEntryDto.getDate());
        accountEntry.setFromAccountNumber(accountEntryDto.getFromAccountNumber());
        accountEntry.setFromPersonName(accountEntryDto.getFromPersonName());
        accountEntry.setAmount(accountEntryDto.getAmount());
        accountEntry.setDescription(accountEntryDto.getDescription());
        return accountEntry;
    }

    public static Collection<AccountEntryDto> convertAccountEntryCollectionToAccountEntryDtoCollection (Collection<AccountEntry> accountEntries){
        return accountEntries.stream().map(accountEntry -> convertAccountEntryToAccountEntryDto(accountEntry)).collect(Collectors.toList());
    }

    public static Collection<AccountEntry> convertAccountEntryDtoCollectionToAccountEntryCollection (Collection<AccountEntryDto> accountEntryDtos){
        return accountEntryDtos.stream().map(accountEntryDto -> convertAccountEntryDtoToAccountEntry(accountEntryDto)).collect(Collectors.toList());
    }
}
