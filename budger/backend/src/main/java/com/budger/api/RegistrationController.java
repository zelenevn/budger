package com.budger.api;

import com.budger.data.dto.AccountDto;
import com.budger.data.dto.AuthenticatedDto;
import com.budger.services.LoginService;
import com.budger.services.RegistrationService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(
        path = "/registration"
)
@CrossOrigin("*")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    public RegistrationController(RegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    @PostMapping
    public AuthenticatedDto registration(@RequestBody AccountDto accountDto) {
        String login = accountDto.getLogin();
        String email = accountDto.getEmail();
        String password = accountDto.getPassword();
        registrationService.registration(login, email, password);
        return loginService.login(login, password);
    }
}