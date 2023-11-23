package cz.project.myfit.DTO;

import jakarta.validation.constraints.NotEmpty;

public class RegistrationUserDTO {
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
}
