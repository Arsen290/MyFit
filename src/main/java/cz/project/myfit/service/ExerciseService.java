package cz.project.myfit.service;

import cz.project.myfit.model.Exercise;
import cz.project.myfit.model.Program;
import cz.project.myfit.repository.ExerciseRepository;
import cz.project.myfit.repository.ProgramRepository;
import cz.project.myfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ProgramRepository programRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository,   ProgramRepository programRepository) {
        this.exerciseRepository = exerciseRepository;
        this.programRepository = programRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
    public Program getProgramByNameAndUser(String userName, String programName) {
        return programRepository.findByUser_NameAndName(userName, programName);
    }
}