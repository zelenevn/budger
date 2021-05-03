package com.service;

import com.data.dao.AccountRepository;
import com.data.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public void setRepository(AccountRepository repository){ this.repository = repository; }

    public Account getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Account account) {
        repository.save(account);
    }

    public void update(Integer id, Account account) {
        Account updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setEmail(account.getEmail());
            updated.setPassword(account.getPassword());
            updated.setUserName(account.getUserName());
            updated.setUserRole(account.getUserRole());
            updated.setBudget(account.getBudget());
            updated.setFamilies(account.getFamilies());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }
}
