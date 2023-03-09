package bank.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Account {
	long accountnumber;
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();
	@OneToOne(cascade = CascadeType.PERSIST)
	Customer customer;
	@Id
	@GeneratedValue
	private Long id;


	public Account (long accountnr){
		this.accountnumber = accountnr;
	}

	public Account() {

	}

	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public double getBalance() {
		double balance=0;
		for (AccountEntry entry : entryList) {
			balance+=entry.getAmount();
		}
		return balance;
	}
	public void deposit(double amount){
		AccountEntry entry = new AccountEntry(LocalDate.now(), amount, "deposit", "", "");
		entryList.add(entry);
	}
	public void withdraw(double amount){
		AccountEntry entry = new AccountEntry(LocalDate.now(), -amount, "withdraw", "", "");
		entryList.add(entry);	
	}

	private void addEntry(AccountEntry entry){
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description){
		AccountEntry fromEntry = new AccountEntry(LocalDate.now(), -amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(LocalDate.now(), amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
		entryList.add(fromEntry);	
		toAccount.addEntry(toEntry);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public Long getId() {
		return id;
	}
}
