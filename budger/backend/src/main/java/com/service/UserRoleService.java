package com.service;

import com.data.dao.GoalRepository;
import com.data.dao.UserRoleRepository;
import com.data.entity.Account;
import com.data.entity.Goal;
import com.data.entity.UserRole;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    private UserRoleRepository repository;

    @Autowired
    public void setRepository(UserRoleRepository repository){ this.repository = repository; }

    public UserRole getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(UserRole userRole) {
        repository.save(userRole);
    }

    public void update(Integer id, UserRole userRole) {
        UserRole updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setAccounts(userRole.getAccounts());
            updated.setTitle(userRole.getTitle());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public UserRole getByAccount(Account account){
        return repository.findByAccounts(account);
    }

    public List<UserRole> findAll() {
        return repository.findAll();
    }
}
