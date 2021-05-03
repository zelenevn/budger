package com.service;

import com.data.dao.GoalRepository;
import com.data.dao.TransactionRepository;
import com.data.entity.Goal;
import com.data.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository repository;

    @Autowired
    public void setRepository(TransactionRepository repository){ this.repository = repository; }

    public Transaction getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    public void update(Integer id, Transaction transaction) {
        Transaction updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setBudget(transaction.getBudget());
            updated.setCategory(transaction.getCategory());
            updated.setCurrency(transaction.getCurrency());
            updated.setTransactionTime(transaction.getTransactionTime());
            updated.setValue(transaction.getValue());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

}
