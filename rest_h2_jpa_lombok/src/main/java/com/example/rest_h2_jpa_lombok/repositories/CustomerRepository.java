package com.example.rest_h2_jpa_lombok.repositories;

import com.example.rest_h2_jpa_lombok.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer>  findByUserName(String uname); //Metodu otomatik oluşturması için isminin bu şekilde olması gerekiyor.
    //Select * from customers where userName kodunu otomatik olarak JPA sayesinde yazar.

    Boolean existsByUserName(String uname);



}
