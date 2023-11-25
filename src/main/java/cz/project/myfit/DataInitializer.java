package cz.project.myfit;

import cz.project.myfit.enums.UserRole;
import cz.project.myfit.model.Role;
import cz.project.myfit.repository.RoleRepository;
import cz.project.myfit.service.RoleService;
import cz.project.myfit.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final RoleService roleService;

    @Value("${admin.user.name}")
    private String adminName;

    @Value("${admin.user.email}")
    private String adminEmail;

    @Value("${admin.user.password}")
    private String adminPassword;

    @Value("${admin.user.roles}")
    private String adminRoles;

    public DataInitializer(UserService userService, RoleService roleService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }
    private void initializeRoles() {
        if (!roleRepository.findByName(UserRole.ADMIN).isPresent()) {
            Role adminRole = new Role(UserRole.ADMIN);
            roleRepository.save(adminRole);
        }
    }
    @Override
    public void run(String... args) {
        // Initialize roles
        initializeRoles();
        // Create admin user and add it into the database
        userService.createAdminUser(adminName, adminEmail, adminPassword, adminRoles);
    }
}
