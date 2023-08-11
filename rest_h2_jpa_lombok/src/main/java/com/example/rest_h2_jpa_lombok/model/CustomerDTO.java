package com.example.rest_h2_jpa_lombok.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int age;
    private String customerUrl;
}
