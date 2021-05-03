package com.service;


import com.data.dao.AccountRepository;
import com.data.dao.BudgetRepository;
import com.data.entity.Account;
import com.data.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private BudgetRepository repository;

    @Autowired
    public void setRepository(BudgetRepository repository){ this.repository = repository; }

    public Budget getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Budget budget) {
        repository.save(budget);
    }

    public void update(Integer id, Budget budget) {
        Budget updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setGoals(budget.getGoals());
            updated.setTransactions(budget.getTransactions());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Budget> findAll() {
        return repository.findAll();
    }
}
