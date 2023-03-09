package repositories;

import domain.Order;
import domain.OrderNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o.ordernr as ordernr  from Order as o where o.status = 'closed'")
    List<OrderNumber> findOrdernumbersByStatus();

    @Query("select o.ordernr as ordernr from Order as o where o.customer.address.city = :city")
    List<OrderNumber> findOrderNumbersFromCustomersByCity(@Param("city") String city);

}
