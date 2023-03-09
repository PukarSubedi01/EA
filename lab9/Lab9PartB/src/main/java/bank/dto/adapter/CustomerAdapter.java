package bank.dto.adapter;

import bank.domain.Customer;
import bank.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter {
    public static Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO){
        return new Customer(
                customerDTO.getName()
        );
    }
    public static CustomerDTO getCustomerDTOFromCustomer(Customer customer){
        return new CustomerDTO(
                customer.getName()
        );
    }
    public static List<CustomerDTO> getCustomerDTOsFromCustomers(List<Customer> customers){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer:customers){
            customerDTOS.add(getCustomerDTOFromCustomer(customer));
        }
        return customerDTOS;
    }
}
