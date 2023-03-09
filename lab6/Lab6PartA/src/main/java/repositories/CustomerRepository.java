package repositories;

import domain.Customer;
import domain.CustomerNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> findByCountry(String country);

    @Query("select c.firstname as firstName, c.lastname as lastName from Customer c where c.address.city = 'Amsterdam'")
    List<CustomerNames> findCustomersByAddress();
}
