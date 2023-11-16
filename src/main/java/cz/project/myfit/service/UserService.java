package cz.project.myfit.service;

import cz.project.myfit.model.User;
import cz.project.myfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public User getUserByName(String name){
        return (User) userRepository.findUserByName(name).orElse(null);
    }
    public void save(User user) {
        userRepository.save(user);
    }
}
