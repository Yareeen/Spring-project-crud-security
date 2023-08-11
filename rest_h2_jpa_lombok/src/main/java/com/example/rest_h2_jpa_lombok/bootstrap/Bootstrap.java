package com.example.rest_h2_jpa_lombok.bootstrap;

import com.example.rest_h2_jpa_lombok.domain.Category;
import com.example.rest_h2_jpa_lombok.domain.Customer;
import com.example.rest_h2_jpa_lombok.domain.Roles;
import com.example.rest_h2_jpa_lombok.repositories.CategoryRepository;
import com.example.rest_h2_jpa_lombok.repositories.CustomerRepository;
import com.example.rest_h2_jpa_lombok.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadRoles();
    }

    private void loadCategories() {
        Category c1 = new Category();
        c1.setName("Ayakkabı");

        Category c2 = new Category();
        c2.setName("Giyim");

        Category c3 = new Category();
        c3.setName("Aksesuar");

        Category c4 = new Category();
        c4.setName("Teknolojik Aletler");

        Category c5 = new Category();
        c5.setName("Çanta");

        //JPA'ya kaydet
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);
        categoryRepository.save(c4);
        categoryRepository.save(c5);

        System.out.println("Total number of categories: " + categoryRepository.count());
    }


    private void loadCustomers(){

       /* Customer cs1 = new Customer((long)2,"yareeencan","123456","Yaren","CAN",23,(long)1);
        Customer cs2 = new Customer((long)3,"seymaakara","123456","Şeyma","Kara",23);

        customerRepository.save(cs1);
        customerRepository.save(cs2);  */
    }


    private void loadRoles(){
        Roles r1 = new Roles((long)1,"Admin");
        Roles r2 = new Roles((long)2,"User");

        roleRepository.save(r1);
        roleRepository.save(r2);
        System.out.println(roleRepository.count());
    }
}
