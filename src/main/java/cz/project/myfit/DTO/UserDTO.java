package cz.project.myfit.DTO;

import cz.project.myfit.model.Role;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class UserDTO {

    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;


    @NotEmpty
    private Set<Role> roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO(Long id, String name,String password, String email,Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}