package cz.project.myfit.service;

import cz.project.myfit.DTO.UserDTO;
import cz.project.myfit.enums.UserRole;
import cz.project.myfit.model.Role;
import cz.project.myfit.model.User;
import cz.project.myfit.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserRepository userRepository;
    private final RoleService roleService;

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
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
        log.info("Attempting to load user by username: " + username);
        User user = userRepository.findUserByName(username)
                .orElseThrow(() -> {
                    log.error("User not found for username: " + username);
                    return new UsernameNotFoundException("User not found");
                });

        log.info("User found: " + user.getEmail());

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());

        log.info("User roles: " + authorities);
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                // Mapping user roles to GrantedAuthority and collecting the mapped authorities into a list
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                        .collect(Collectors.toList()));
    }

    public void createNewUser(UserDTO userDTO) {
        try {

        Role userRole = roleService.findRoleByName(UserRole.USER.toString());

        // Set the user's roles to a Set containing only the 'ROLE_USER' role
        userDTO.setRoles(Set.of(userRole));

        // Create a new BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Create a new User object from the UserDTO
        User user = new User(userDTO.getId(), userDTO.getName(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail(), userDTO.getRoles());

        // Save the user to the userRepository
        userRepository.save(user);}
        catch (DataIntegrityViolationException e) {
            // Handle the exception (e.g., log an error or throw a custom exception)
            throw new IllegalStateException("Duplicate name or email");
        }
    }


    // Creating a new admin user
    public void createAdminUser(String name, String email, String password, String roleName) {
        try {
            // Find the role using the roleService
            Role adminRole = roleService.findRoleByName(roleName);

            if (adminRole == null) {
                // Handle the exception (e.g., log an error or throw a custom exception)
                throw new IllegalStateException("Role not found");
            }
            // Create a new BCryptPasswordEncoder
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Create a set to store user roles
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            // Create a new User object from the UserDTO
            User userAdmin = new User(null, name, passwordEncoder.encode(password), email, roles);

            // Save the user to the userRepository
            userRepository.save(userAdmin);
        } catch (DataIntegrityViolationException e) {
            // Handle the exception (e.g., log an error or throw a custom exception)
            throw new IllegalStateException("Duplicate name or email");
        }
    }

//    public String getHashedPassword(String username) {
//        Optional<User> getUserHashedPassword = userRepository.findUserByName(username);
//        User user = getUserHashedPassword.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        // Ваша логика для получения хэшированного пароля из базы данных по имени пользователя
//        // Например, используя userRepository или другие методы доступа к данным
//        return user.getPassword();
//    }

}
