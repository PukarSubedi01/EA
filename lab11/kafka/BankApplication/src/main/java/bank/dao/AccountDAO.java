package bank.dao;

import bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
	Account findByAccountnumber(long accountnumber);
}
