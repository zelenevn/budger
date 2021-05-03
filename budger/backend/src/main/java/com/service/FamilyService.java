package com.service;

import com.data.dao.CategoryRepository;
import com.data.dao.FamilyRepository;
import com.data.entity.Category;
import com.data.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    private FamilyRepository repository;

    @Autowired
    public void setRepository(FamilyRepository repository){ this.repository = repository; }

    public Family getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Family family) {
        repository.save(family);
    }

    public void update(Integer id, Family family) {
        Family updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setAccounts(family.getAccounts());
            updated.setName(family.getName());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Family> findAll() {
        return repository.findAll();
    }
}
