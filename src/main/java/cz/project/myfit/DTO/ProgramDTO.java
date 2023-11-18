package cz.project.myfit.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ProgramDTO{

    private Long id;
    @NotEmpty(message = "Program name should not be empty")
    private String name;
    @NotNull(message = "User ID should not be null")
    private Long userId;
    private LocalDate date;

    public ProgramDTO() {
    }

    public ProgramDTO(Long id, String name, Long userId, LocalDate date) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.date = date;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
