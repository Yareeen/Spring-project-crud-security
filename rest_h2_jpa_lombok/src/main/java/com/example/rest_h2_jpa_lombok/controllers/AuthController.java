package com.example.rest_h2_jpa_lombok.controllers;

import com.example.rest_h2_jpa_lombok.auth.TokenManager;
import com.example.rest_h2_jpa_lombok.domain.Customer;
import com.example.rest_h2_jpa_lombok.domain.Roles;
import com.example.rest_h2_jpa_lombok.model.AuthResponseDTO;
import com.example.rest_h2_jpa_lombok.model.LoginDTO;
import com.example.rest_h2_jpa_lombok.model.RegisterDTO;
import com.example.rest_h2_jpa_lombok.repositories.CustomerRepository;
import com.example.rest_h2_jpa_lombok.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/authenticate")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private TokenManager tokenManager;

    public AuthController(AuthenticationManager authenticationManager, CustomerRepository customerRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenManager = tokenManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenManager.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        if (customerRepository.existsByUserName(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer();
        customer.setUserName(registerDto.getUsername());
        customer.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Roles roles = roleRepository.findByName("User").get();
        customer.setRoles(Collections.singletonList(roles)); //singleton rol listesi

        customerRepository.save(customer);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
