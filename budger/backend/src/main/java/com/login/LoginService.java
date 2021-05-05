package com.login;

import com.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountService accountService;

    public

}
