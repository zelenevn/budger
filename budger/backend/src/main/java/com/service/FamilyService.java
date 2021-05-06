package com.service;

import com.data.dao.CategoryRepository;
import com.data.dao.FamilyRepository;
import com.data.dto.FamilyDto;
import com.data.entity.Category;
import com.data.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private FamilyRepository repository;

    @Autowired
    public void setRepository(FamilyRepository repository){ this.repository = repository; }

    public Optional<Family> getById(Integer id) {
        return repository.findById(id);
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

    public Integer createFromDto(FamilyDto familyDto){
        Family family = new Family();
        family.setName(familyDto.getName());
        save(family);
        return family.getId();
    }

    public List<Family> findAll() {
        return repository.findAll();
    }
}
