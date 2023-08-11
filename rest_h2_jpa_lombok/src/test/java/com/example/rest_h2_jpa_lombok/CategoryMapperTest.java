package com.example.rest_h2_jpa_lombok;

import com.example.rest_h2_jpa_lombok.mapper.CategoryMapper;
import com.example.rest_h2_jpa_lombok.model.CategoryDTO;
import com.example.rest_h2_jpa_lombok.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Kitap";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Category c6 = new Category();
        c6.setName(NAME);
        c6.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(c6);

        //then
        assertEquals(Long.valueOf(ID), c6.getId());
        assertEquals(NAME, c6.getName());
    }

}
