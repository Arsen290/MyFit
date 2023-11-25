package cz.project.myfit.service;

import cz.project.myfit.model.Role;
import cz.project.myfit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.project.myfit.enums.UserRole;
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) {
        System.out.println("Values of UserRole enum:");
        for (UserRole role : UserRole.values()) {
            System.out.println(role.name());
        }
        UserRole userRole = UserRole.valueOf(roleName.toUpperCase().trim());
        System.out.println("userRole = " + userRole);
        return roleRepository.findByName(userRole).orElse(null);
    }
}