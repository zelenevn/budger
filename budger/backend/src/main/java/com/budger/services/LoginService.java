package com.budger.services;

import com.budger.data.dto.AuthenticatedDto;
import com.budger.data.entities.Account;
import com.budger.data.entities.Role;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.exceptions.InvalidPasswordException;
import com.budger.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final AccountService accountService;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final JwtProvider provider;

    public LoginService(AccountService accountService,
                        RoleService roleService,
                        PasswordEncoder encoder,
                        JwtProvider provider) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.provider = provider;
    }

    public AuthenticatedDto login(String login, String password) {
        Optional<Account> registeredAccountOptional = accountService.readAccountByLogin(login);
        if (registeredAccountOptional.isPresent()) {
            Account registeredAccount = registeredAccountOptional.get();
            if (encoder.matches(password, registeredAccount.getPassword())) {
                List<Role> roles = roleService.readRolesByAccountLogin(login);
                String token = provider.generateToken(login, roles);
                return new AuthenticatedDto(login, token);
            } else
                throw new InvalidPasswordException("Invalid password");
        } else
            throw new AccountDoesNotExistsException(String.format("Login %s does not registered.", login));
    }

}