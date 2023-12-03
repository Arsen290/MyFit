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

        UserRole userRole = UserRole.valueOf(roleName.toUpperCase().trim());
        return roleRepository.findByName(userRole).orElse(null);
    }
}