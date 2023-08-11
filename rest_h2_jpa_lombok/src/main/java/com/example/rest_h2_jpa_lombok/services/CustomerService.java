package com.example.rest_h2_jpa_lombok.services;

import com.example.rest_h2_jpa_lombok.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    //Tüm kategorilerin listesini almak için oluşturuldu.
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);


    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);

}
