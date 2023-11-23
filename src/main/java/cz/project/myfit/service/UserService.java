package cz.project.myfit.service;

import cz.project.myfit.DTO.UserDTO;
import cz.project.myfit.enums.UserRole;
import cz.project.myfit.model.Role;
import cz.project.myfit.model.User;
import cz.project.myfit.repository.RoleRepository;
import cz.project.myfit.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Convert the list of User objects to a Stream
        return users.stream()
                // For each User object, apply the toDTO method to convert it to a UserDTO
                .map(User::toDTO)
                // Collect the resulting UserDTO objects into a List
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public User getUserByName(String name){
        return userRepository.findUserByName(name).orElse(null);
    }

    public void save(UserDTO userDTO) {
        try {
            User user = new User(userDTO.getId(), userDTO.getName(),passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail(), userDTO.getRoles());
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle the exception (e.g., log an error or throw a custom exception)
            throw new IllegalStateException("Duplicate name or email");
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // Creating a Spring Security User object for authentication.
    // Setting the username (email), password, and authorities (roles) for the authenticated user.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                // Mapping user roles to GrantedAuthority and collecting the mapped authorities into a list
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                        .collect(Collectors.toList()));
    }

    public void createNewUser(UserDTO userDTO) {
        try {
        // Find the 'ROLE_USER' role using the roleService
        Role userRole = roleService.findRoleByName(UserRole.USER);

        // Set the user's roles to a Set containing only the 'ROLE_USER' role
        userDTO.setRoles(Set.of(userRole));

        // Create a new User object from the UserDTO
        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getRoles());

        // Save the user to the userRepository
        userRepository.save(user);}
        catch (DataIntegrityViolationException e) {
            // Handle the exception (e.g., log an error or throw a custom exception)
            throw new IllegalStateException("Duplicate name or email");
        }
    }
}
