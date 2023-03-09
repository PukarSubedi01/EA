package bank.DTO;

import bank.domain.AccountEntry;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDTO {
    long accountnumber;
    Collection<AccountEntryDTO> entryList = new ArrayList<AccountEntryDTO>();
    CustomerDTO customer;

    public AccountDTO(long accountnumber) {
        this.accountnumber = accountnumber;

    }

    public long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Collection<AccountEntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(Collection<AccountEntryDTO> entryList) {
        this.entryList = entryList;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }
    public double getBalance() {
        double balance=0;
        for (AccountEntryDTO entry : entryList) {
            balance+=entry.getAmount();
        }
        return balance;
    }
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
