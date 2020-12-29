package cl.ripley.login.services;

import cl.ripley.login.entities.User;
import cl.ripley.login.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public boolean validate(String username, String password) {
        List<User> response = loginRepository.findByUsernameAndPassword(username, password);
        return !response.isEmpty();
    }
}
