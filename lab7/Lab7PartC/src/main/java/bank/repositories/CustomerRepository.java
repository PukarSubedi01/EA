package bank.repositories;


import bank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    default void saveCustomer(Customer customer) {
//        throw new RuntimeException("could not save customer");
        save(customer);
    }

}




