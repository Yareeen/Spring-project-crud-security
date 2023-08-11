package com.example.rest_h2_jpa_lombok.repositories;

import com.example.rest_h2_jpa_lombok.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String s);
}
