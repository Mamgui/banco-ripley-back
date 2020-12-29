package cl.ripley.login.services;

import cl.ripley.login.entities.User;
import cl.ripley.login.repositories.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<User> users = Arrays.asList(new User(username, password));
        when(loginRepository.findByUsernameAndPassword(username, password)).thenReturn(users);

        Boolean result = loginService.validate(username, password);

        assertEquals(true, result);
    }

    @Test
    void returnsFalseWhenCredentialsDoesNotExistsInDatabase() {
        String username = "Joel";
        String password = "SuperSecret";
        List<User> users = new ArrayList<>();
        when(loginRepository.findByUsernameAndPassword(username, password)).thenReturn(users);

        Boolean result = loginService.validate(username, password);

        assertEquals(false, result);
    }
}