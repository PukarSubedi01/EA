package repositories;

import domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select * from Address where city = 'Amsterdam'", nativeQuery = true)
    List<Address> findAddressByCity();
}
