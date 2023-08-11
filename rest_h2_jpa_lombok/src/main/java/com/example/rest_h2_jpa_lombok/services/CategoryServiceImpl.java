package com.example.rest_h2_jpa_lombok.services;

import com.example.rest_h2_jpa_lombok.mapper.CategoryMapper;
import com.example.rest_h2_jpa_lombok.model.CategoryDTO;
import com.example.rest_h2_jpa_lombok.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        /*
        List <Category> categories = categoryRepository.findAll();

        for(Category category : categories){

            System.out.println(category.getName());
        }*/
        int count =0;

        return categoryRepository.findAll()
               .stream()/* .forEach(category -> {
                    System.out.println(category);
                    count++;
                })*/
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
}
}
