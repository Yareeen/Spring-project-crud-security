package com.example.rest_h2_jpa_lombok.auth;


import com.example.rest_h2_jpa_lombok.domain.Customer;
import com.example.rest_h2_jpa_lombok.domain.Roles;
import com.example.rest_h2_jpa_lombok.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
//Springin kendi sınıfını implement ettik
public class CustomerDetailsService implements UserDetailsService {


    private CustomerRepository customerRepository; //customerrepository enjekte edilir.


    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
                return new User(customer.getUserName(), customer.getPassword(), mapRolesToAuthorities(customer.getRoles()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());


    }
}
