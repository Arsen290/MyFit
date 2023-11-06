package cz.project.myfit.services;

import cz.project.myfit.models.User;
import cz.project.myfit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void save(User user){
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
            if (userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        boolean userExists = userRepository.existsById(userId);
        if (!userExists){
            throw new IllegalStateException("user with id "+ userId + "does not exist");
        }
        userRepository.deleteById(userId);
    }
}
