package cz.project.myfit.service;

import cz.project.myfit.DTO.UserDTO;
import cz.project.myfit.model.User;
import cz.project.myfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::toDTO)
                .collect(Collectors.toList());
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
    public void save(UserDTO userDTO) {
        try {
            User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle the exception (e.g., log an error or throw a custom exception)
            throw new IllegalStateException("Duplicate name or email");
        }
    }
}
