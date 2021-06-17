package com.budger.services;

import com.budger.data.entities.AccountRole;
import com.budger.repositories.AccountRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleService {

    private final AccountRoleRepository accountRoleRepository;

    public AccountRoleService(AccountRoleRepository accountRoleRepository) {
        this.accountRoleRepository = accountRoleRepository;
    }

    public void create(AccountRole accountRole) {
        accountRoleRepository.save(accountRole);
    }
}
