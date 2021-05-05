package com.service;

import com.data.dao.AccountRepository;
import com.data.entity.Account;
import com.registration.token.ConfirmationToken;
import com.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AccountRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)
                ));
    }

    public String signUpUser(Account account) {
        boolean userExists = repository
                .findByEmail(account.getEmail())
                .isPresent();

        if (userExists)
            throw new IllegalStateException("email already taken");

        String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());

        account.setPassword(encodedPassword);

        repository.save(account);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;


    }

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
            updated.setUserName(account.getUsername());
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
