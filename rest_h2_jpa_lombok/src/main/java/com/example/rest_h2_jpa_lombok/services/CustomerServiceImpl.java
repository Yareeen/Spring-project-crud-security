package com.example.rest_h2_jpa_lombok.services;

import com.example.rest_h2_jpa_lombok.domain.Customer;
import com.example.rest_h2_jpa_lombok.mapper.CustomerMapper;
import com.example.rest_h2_jpa_lombok.model.CustomerDTO;
import com.example.rest_h2_jpa_lombok.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer ->{ CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                customerDTO.setCustomerUrl("/customers/" + customer.getId());
                    return customerDTO;
                })
                .sorted(Comparator.comparing(c -> c.getUserName())).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new); //todo implement better exception handling
    }


    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerToCustomerDTO(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/customer/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerToCustomerDTO(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }

            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

            returnDto.setCustomerUrl("/customer/" + id);

            return returnDto;

        }).orElseThrow(RuntimeException::new); //todo implement better exception handling;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
