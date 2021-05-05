package com.data.dto;

import com.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDto {

    private String name;

    public CategoryDto(Category category) {
        this.name = category.getCategoryName();
    }
}
