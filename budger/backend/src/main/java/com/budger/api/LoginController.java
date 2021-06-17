package com.budger.api;

import com.budger.data.dto.AuthenticatedDto;
import com.budger.data.dto.LoginDto;
import com.budger.services.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "/login"
)
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public AuthenticatedDto login(@RequestBody LoginDto loginDto) {
        String login = loginDto.getLogin();
        String password = loginDto.getPassword();
        return loginService.login(login, password);
    }
}
