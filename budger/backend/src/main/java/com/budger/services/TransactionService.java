package com.budger.services;

import com.budger.data.dto.TransactionDto;
import com.budger.data.dto.v2.transaction.GetTransactionDto;
import com.budger.data.entities.Account;
import com.budger.data.entities.Category;
import com.budger.data.entities.Transaction;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.exceptions.TransactionDoesNotExists;
import com.budger.repositories.AccountRepository;
import com.budger.repositories.CategoryRepository;
import com.budger.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public void create(String login, float value, Date date, String category) {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            Category categoryObj = categoryRepository.findCategoryByCategoryName(category).get();
            Transaction transaction = new Transaction(value, date, account.getBudget(), categoryObj);
            transactionRepository.save(transaction);
        } else
            throw new AccountDoesNotExistsException("Account does not exists");
    }

    public List<TransactionDto> readAll(String login) {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            List<Transaction> transactions = account.getBudget().getTransactions();
            List<TransactionDto> transactionDtos = new LinkedList<>();
            transactions.forEach(transaction -> {
                transactionDtos.add(new TransactionDto(
                        transaction.getValue(),
                        transaction.getDate(),
                        transaction.getCategory().getCategoryName()));
            });
            return transactionDtos;
        } else {
            throw new AccountDoesNotExistsException("Account does not exists");
        }
    }


    public GetTransactionDto read(Integer id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            GetTransactionDto transactionDto = new GetTransactionDto(
                    transaction.getId(),
                    transaction.getValue(),
                    transaction.getDate(),
                    transaction.getCategory().getCategoryName()
            );
            return transactionDto;
        } else
            throw new TransactionDoesNotExists(String.format("Transaction with id %d does not exists.", id));
    }

    public void update(Integer id, float newValue, Date newDate, String newCategory) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();

        }
    }

    public void delete(Integer id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transactionRepository.delete(transaction);
        } else
            throw new TransactionDoesNotExists(String.format("Transaction with %d id does not exists", id));
    }

    public List<GetTransactionDto> readAllTransactions(String login) {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            List<Transaction> transactions = account.getBudget().getTransactions();
            List<GetTransactionDto> transactionDtos = new LinkedList<>();
            transactions.forEach(transaction -> {
                transactionDtos.add(new GetTransactionDto(
                        transaction.getId(),
                        transaction.getValue(),
                        transaction.getDate(),
                        transaction.getCategory().getCategoryName()));
            });
            return transactionDtos;
        } else {
            throw new AccountDoesNotExistsException("Account does not exists");
        }
    }
 }