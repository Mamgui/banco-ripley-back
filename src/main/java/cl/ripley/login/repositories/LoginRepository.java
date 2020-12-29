package cl.ripley.login.repositories;

import cl.ripley.login.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<User, Long> {

    List<User> findByUsernameAndPassword(String username, String password);
}
