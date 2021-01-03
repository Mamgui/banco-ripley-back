package cl.ripley.login.controllers;

import cl.ripley.login.entities.LoginRequest;
import cl.ripley.login.entities.RecoverPasswordRequest;
import cl.ripley.login.entities.RecoverPasswordResponse;
import cl.ripley.login.entities.UpdatePasswordRequest;
import cl.ripley.login.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return loginService.register(username, password) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return loginService.validate(username, password) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/recover-password")
    public ResponseEntity recoverPassword(@RequestBody RecoverPasswordRequest recoverPasswordRequest) {
        String username = recoverPasswordRequest.getUsername();
        String token = loginService.recoverPassword(username);
        if (token == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        RecoverPasswordResponse response = new RecoverPasswordResponse(token);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        String token = updatePasswordRequest.getToken();
        String newPassword = updatePasswordRequest.getNewPassword();
        return loginService.updatePassword(token, newPassword) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
