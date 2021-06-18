package com.budger.api.v1;

import com.budger.data.dto.CategoryDto;
import com.budger.data.dto.GetResourceDto;
import com.budger.data.entities.Category;
import com.budger.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(
        path = "/category"
)
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(
            path = "/all"
    )
    public List<CategoryDto> readAll() {
        return categoryService.readAll();
    }

}
