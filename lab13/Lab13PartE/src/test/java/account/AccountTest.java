package account;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AccountTest {
    @Test
    public void testDeposit() {
        Account account = new Account();
        account.deposit(100.0);
        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

   @Test
    public void testWithdraw(){
        Account account = new Account();
        account.withdraw(100.0);
        assertThat(account.getBalance(), closeTo(-100.0, 0.01));

    }
    @Test
    public void testTransfer(){
        Customer customer = new Customer("Ujjwal");
        Customer customer2 = new Customer("Pukar");

        Account account1 = new Account();
        account1.setCustomer(customer);
        account1.deposit(1000);

        Account account2 = new Account();
        account2.setCustomer(customer2);

        account1.transferFunds(account2,250, "University fee");

        assertThat(account1.getBalance(), closeTo(750, 0.01));
        assertThat(account2.getBalance(), closeTo(250,0.01));

    }

}