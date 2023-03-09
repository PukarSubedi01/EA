package bank.DTO.adapter;

import bank.DTO.CustomerDTO;
import bank.domain.Customer;

public class CustomerAdapter {
    public static Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO){
        return new Customer(
                customerDTO.getName()
        );

    }

    public static CustomerDTO getCustomerDTOFromCustomer(Customer customer){
        return  new CustomerDTO(
                customer.getName()
        );
    }
}
