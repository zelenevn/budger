package com.budger.services;

import com.budger.data.entities.Account;
import com.budger.data.entities.AccountRole;
import com.budger.data.entities.Role;
import com.budger.data.entities.pk.AccountRoleId;
import com.budger.exceptions.AccountAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private final AccountService accountService;

    private final RoleService roleService;

    private final AccountRoleService accountRoleService;

    private final PasswordEncoder encoder;

    public RegistrationService(AccountService accountService,
                               RoleService roleService,
                               AccountRoleService accountRoleService,
                               PasswordEncoder encoder) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.accountRoleService = accountRoleService;
        this.encoder = encoder;
    }

    public void registration(String login, String email, String password) {
        Optional<Account> accountOptional = accountService.readAccountByLogin(login);
        if (accountOptional.isEmpty()) {
            String encodedPassword = encoder.encode(password);
            Account account = new Account(login, email, encodedPassword);
            this.accountService.create(account);

            account = accountService.readAccountByLogin(login).get();
            Role userRole = roleService.readRoleByTitle("ROLE_USER");
            accountRoleService.create(new AccountRole(
                    new AccountRoleId(account.getId(), userRole.getId()),
                    account,
                    userRole
            ));
        } else
            throw new AccountAlreadyExistsException(String.format("Login %s already registered.", login));
    }
}