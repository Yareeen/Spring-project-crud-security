package com.example.rest_h2_jpa_lombok.mapper;

import com.example.rest_h2_jpa_lombok.model.CategoryDTO;
import com.example.rest_h2_jpa_lombok.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {


    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
