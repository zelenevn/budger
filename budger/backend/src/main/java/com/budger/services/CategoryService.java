package com.budger.services;

import com.budger.data.dto.CategoryDto;
import com.budger.data.entities.Account;
import com.budger.data.entities.Category;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.repositories.AccountRepository;
import com.budger.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public CategoryService(AccountRepository accountRepository, CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> readAll() {
        List<CategoryDto> categoryDtos = new LinkedList<>();
        categoryRepository.findAll().forEach(category -> {
            categoryDtos.add(new CategoryDto(
                    category.getCategoryName()
            ));
        });
        return categoryDtos;
    }
}
