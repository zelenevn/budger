package com.budger.repositories;

import com.budger.data.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    public Optional<Category> findCategoryByCategoryName(@Param("categoryName") String categoryName);
}
