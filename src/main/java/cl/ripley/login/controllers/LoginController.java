package cl.ripley.login.controllers;

import cl.ripley.login.entities.LoginRequest;
import cl.ripley.login.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public HttpStatus login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return loginService.validate(username, password) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
    }
}
