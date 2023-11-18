package cz.project.myfit.DTO;

import java.time.LocalDate;

public class ProgramDTO{

    private Long id;
    private String name;
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
