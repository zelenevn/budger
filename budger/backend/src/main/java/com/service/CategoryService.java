package com.service;

import com.data.dao.BudgetRepository;
import com.data.dao.CategoryRepository;
import com.data.entity.Budget;
import com.data.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    @Autowired
    public void setRepository(CategoryRepository repository){ this.repository = repository; }

    public Category getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Category category) {
        repository.save(category);
    }

    public void update(Integer id, Category category) {
        Category updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setCategoryName(category.getCategoryName());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Category> getByName(String name){

        return repository.findByCategoryName(name);

    }

    public List<Category> findAll() {
        return repository.findAll();
    }

}
