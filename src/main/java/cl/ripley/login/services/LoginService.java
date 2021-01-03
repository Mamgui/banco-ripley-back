package cl.ripley.login.services;

import cl.ripley.login.entities.User;
import cl.ripley.login.repositories.LoginRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean register(String username, String password) {
        loginRepository.save(new User(username, password));
        return true;
    }

    public boolean validate(String username, String password) {
        User user = loginRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }

    public String recoverPassword(String username) {
        User user = loginRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return user.getToken();
    }

    public boolean updatePassword(String token, String newPassword) {
        User user = loginRepository.findByToken(token);
        if (user == null) {
            return false;
        }
        String newToken = UUID.randomUUID().toString();
        user.setToken(newToken);
        user.setPassword(newPassword);
        loginRepository.save(user);
        return true;
    }
}
