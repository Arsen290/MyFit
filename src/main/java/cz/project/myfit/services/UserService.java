package cz.project.myfit.services;

import cz.project.myfit.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    public List<User> getUsers() {

        return List.of(
                new User(1L, "Bob", "bob@mail.com")
        );
    }
}
