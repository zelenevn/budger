package com.service;

import com.data.dao.GoalRepository;
import com.data.dao.TransactionRepository;
import com.data.dto.TransactionDto;
import com.data.entity.Budget;
import com.data.entity.Goal;
import com.data.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final CategoryService categoryService;


    public Optional<Transaction> getById(Integer id) {
        return repository.findById(id);
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

    public void createTransactionFromDto(Budget budget, TransactionDto transactionDto){

        Transaction transaction = new Transaction();
        transaction.setValue(transactionDto.getValue());
        transaction.setTransactionTime(transactionDto.getTransactionTime());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setCategory(
                categoryService.getByName(transactionDto.getCategory()).get()
        );
        transaction.setBudget(budget);
        save(transaction);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

}
