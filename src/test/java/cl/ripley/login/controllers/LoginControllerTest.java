package cl.ripley.login.controllers;

import cl.ripley.login.entities.LoginRequest;
import cl.ripley.login.services.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void returnsOKStatusCodeWhenUsernameAndPasswordAreValid() {
        String username = "Ariel";
        String password = "SuperSecret";
        LoginRequest loginRequest = new LoginRequest(username, password);
        when(loginService.validate(username, password)).thenReturn(true);

        HttpStatus response = loginController.login(loginRequest);

        assertEquals(200, response.value());
    }

    @Test
    void returnsForbiddenStatusCodeWhenUsernameIsInvalid() {
        String username = "Ariel";
        String password = "WrongPassword";
        LoginRequest loginRequest = new LoginRequest(username, password);
        when(loginService.validate(username, password)).thenReturn(false);

        HttpStatus response = loginController.login(loginRequest);

        assertEquals(403, response.value());
    }
}