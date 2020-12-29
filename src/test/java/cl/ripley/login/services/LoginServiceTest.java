package cl.ripley.login.services;

import cl.ripley.login.entities.User;
import cl.ripley.login.repositories.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginService loginService;

    @Test
    void returnsTrueWhenCredentialsExistsInDatabase() {
        String username = "Ariel";
        String password = "SuperSecret";
        User user = new User(username, password);
        when(loginRepository.findByUsernameAndPassword(username, password)).thenReturn(user);

        boolean result = loginService.validate(username, password);

        assertTrue(result);
    }

    @Test
    void returnsFalseWhenCredentialsDoesNotExistsInDatabase() {
        String username = "Joel";
        String password = "SuperSecret";
        when(loginRepository.findByUsernameAndPassword(username, password)).thenReturn(null);

        boolean result = loginService.validate(username, password);

        assertFalse(result);
    }

    @Test
    void returnsTokenWhenUserExistsInDatabase() {
        String username = "Ariel";
        String password = "SuperSecret";
        String token = "123";
        User user = new User(username, password);
        user.setToken(token);
        when(loginRepository.findByUsername(username)).thenReturn(user);

        String result = loginService.recoverPassword(username);

        assertEquals("123", result);
    }

    @Test
    void returnsNullWhenUserDoesNotExistsInDatabase() {
        String username = "Ariel";
        when(loginRepository.findByUsername(username)).thenReturn(null);

        String result = loginService.recoverPassword(username);

        assertNull(result);
    }

    @Test
    void returnsTrueWhenTokenExistsInDatabase() {
        String token = "123";
        String newPassword = "newSuperSecret";
        String username = "Ariel";
        String password = "SuperSecret";
        User user = new User(username, password);
        when(loginRepository.findByToken(token)).thenReturn(user);
        user.setPassword(newPassword);
        when(loginRepository.save(user)).thenReturn(user);

        boolean result = loginService.updatePassword(token, newPassword);

        assertTrue(result);
    }

    @Test
    void returnsFalseWhenTokenDoesNotExistsInDatabase() {
        String token = "123";
        String newPassword = "newSuperSecret";
        when(loginRepository.findByToken(token)).thenReturn(null);

        boolean result = loginService.updatePassword(token, newPassword);

        assertFalse(result);
    }
}