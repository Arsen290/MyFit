package cz.project.myfit.DTO;

public class ExerciseDTO {
    private Long id;
    private String name;
    private int sets;
    private int repetitions;

    public ExerciseDTO() {
    }

    public ExerciseDTO(Long id, String name, int sets, int repetitions) {
        this.id = id;
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
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
}
