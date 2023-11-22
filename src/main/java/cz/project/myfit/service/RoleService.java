package cz.project.myfit.service;

import cz.project.myfit.enums.UserRole;
import cz.project.myfit.model.Role;
import cz.project.myfit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(UserRole roleName) {
        return roleRepository.findByName(String.valueOf(roleName));
    }

}