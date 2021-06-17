package com.budger.services;

import com.budger.data.entities.Account;
import com.budger.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> readAccountByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    public void create(Account account) {
        this.accountRepository.save(account);
    }
}
