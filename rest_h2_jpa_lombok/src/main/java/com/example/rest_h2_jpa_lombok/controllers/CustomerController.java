
package com.example.rest_h2_jpa_lombok.controllers;

import com.example.rest_h2_jpa_lombok.model.CustomerDTO;
import com.example.rest_h2_jpa_lombok.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getallCustomers(){
        return new ResponseEntity<> //Response domain döndürür.
                (customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerByDTO(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.updateCustomerByDTO(id, customerDTO),HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer (@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO),HttpStatus.OK);
    }

    //@PathVariable Değeri urı'den ayıklamak için kullanılır. URL'nin bir değer içerdiği RESTful web hizmeti için en uygun olanıdır.
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerid){
        customerService.deleteCustomerById(customerid);
        return new ResponseEntity<>("Customer delete", HttpStatus.OK); //Silindiği için yazıdan başka bir şey döndürmeyiz.
    }

}
