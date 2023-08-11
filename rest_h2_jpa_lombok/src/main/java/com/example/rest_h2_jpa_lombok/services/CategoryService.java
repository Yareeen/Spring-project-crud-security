package com.example.rest_h2_jpa_lombok.services;

import com.example.rest_h2_jpa_lombok.model.CategoryDTO;

import java.util.List;

public interface CategoryService {


    //Tüm kategorilerin listesini almak için oluşturuldu.
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}