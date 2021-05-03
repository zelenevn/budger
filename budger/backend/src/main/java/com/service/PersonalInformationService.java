package com.service;

import com.data.dao.GoalRepository;
import com.data.dao.PersonalInformationRepository;
import com.data.entity.Goal;
import com.data.entity.PersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInformationService {

    private PersonalInformationRepository repository;

    @Autowired
    public void setRepository(PersonalInformationRepository repository){ this.repository = repository; }

    public PersonalInformation getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(PersonalInformation personalInformation) {
        repository.save(personalInformation);
    }

    public void update(Integer id, PersonalInformation personalInformation) {
        PersonalInformation updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setAccount(personalInformation.getAccount());
            updated.setBirthdayDate(personalInformation.getBirthdayDate());
            updated.setFirstName(personalInformation.getFirstName());
            updated.setLastName(personalInformation.getLastName());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<PersonalInformation> findAll() {
        return repository.findAll();
    }

}
