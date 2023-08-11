package com.example.rest_h2_jpa_lombok.mapper;

import com.example.rest_h2_jpa_lombok.domain.Customer;
import com.example.rest_h2_jpa_lombok.model.CustomerDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T17:04:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName( customer.getFirstName() );
        customerDTO.setLastName( customer.getLastName() );
        customerDTO.setUserName( customer.getUserName() );
        customerDTO.setPassword( customer.getPassword() );
        customerDTO.setAge( customer.getAge() );

        return customerDTO;
    }

    @Override
    public Customer customerToCustomerDTO(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setUserName( customerDTO.getUserName() );
        customer.setPassword( customerDTO.getPassword() );
        customer.setFirstName( customerDTO.getFirstName() );
        customer.setLastName( customerDTO.getLastName() );
        customer.setAge( customerDTO.getAge() );

        return customer;
    }
}
