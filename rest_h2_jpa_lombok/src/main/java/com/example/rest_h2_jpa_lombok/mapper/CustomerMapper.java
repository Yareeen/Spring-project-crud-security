package com.example.rest_h2_jpa_lombok.mapper;


import com.example.rest_h2_jpa_lombok.domain.Customer;
import com.example.rest_h2_jpa_lombok.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {


    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerToCustomerDTO(CustomerDTO customerDTO);
}