package cl.ripley.login.controllers;

import cl.ripley.login.entities.LoginRequest;
import cl.ripley.login.entities.RecoverPasswordRequest;
import cl.ripley.login.entities.RecoverPasswordResponse;
import cl.ripley.login.entities.UpdatePasswordRequest;
import cl.ripley.login.services.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void returnsOKStatusWhenUsernameAndPasswordAreValid() {
        String username = "Ariel";
        String password = "SuperSecret";
        LoginRequest loginRequest = new LoginRequest(username, password);
        when(loginService.validate(username, password)).thenReturn(true);

        ResponseEntity response = loginController.login(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void returnsForbiddenStatusWhenUsernameIsInvalid() {
        String username = "Ariel";
        String password = "WrongPassword";
        LoginRequest loginRequest = new LoginRequest(username, password);
        when(loginService.validate(username, password)).thenReturn(false);

        ResponseEntity response = loginController.login(loginRequest);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    void returnsOKStatusAndTokenWhenUserExist() {
        String username = "Ariel";
        String token = "123";
        RecoverPasswordRequest recoverPasswordRequest = new RecoverPasswordRequest(username);
        when(loginService.recoverPassword(username)).thenReturn(token);

        ResponseEntity<RecoverPasswordResponse> response = loginController.recoverPassword(recoverPasswordRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getToken());
    }

    @Test
    void returnsNotFoundStatusWhenUserDoesNotExist() {
        String username = "Ariel";
        RecoverPasswordRequest recoverPasswordRequest = new RecoverPasswordRequest(username);
        when(loginService.recoverPassword(username)).thenReturn(null);

        ResponseEntity response = loginController.recoverPassword(recoverPasswordRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void returnsOKStatusWhenTokenIsValid() {
        String token = "123";
        String newPassword = "newSuperSecret";
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(token, newPassword);
        when(loginService.updatePassword(token, newPassword)).thenReturn(true);

        ResponseEntity response = loginController.updatePassword(updatePasswordRequest);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void returnsNotFoundStatusWhenTokenIsValid() {
        String token = "123";
        String newPassword = "newSuperSecret";
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(token, newPassword);
        when(loginService.updatePassword(token, newPassword)).thenReturn(false);

        ResponseEntity response = loginController.updatePassword(updatePasswordRequest);

        assertEquals(404, response.getStatusCodeValue());
    }
}