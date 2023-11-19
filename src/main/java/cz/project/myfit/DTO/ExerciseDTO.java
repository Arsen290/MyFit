package cz.project.myfit.DTO;

import java.time.DayOfWeek;

public class ExerciseDTO {
    private Long id;
    private String name;
    private int sets;
    private int repetitions;
    private int weight;
    private DayOfWeek dayOfWeek;

    public ExerciseDTO() {
    }

    public ExerciseDTO(Long id, String name, int sets, int repetitions, int weight, DayOfWeek dayOfWeek) {
        this.id = id;
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
        this.weight = weight;
        this.dayOfWeek = dayOfWeek;
    }

    // геттеры и сеттеры для новых полей

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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
