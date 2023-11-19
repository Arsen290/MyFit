package cz.project.myfit.controller;

import cz.project.myfit.DTO.ExerciseDTO;
import cz.project.myfit.model.Program;
import cz.project.myfit.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;
    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{userName}/{programName}")
    public String showProgramWithExercises(
            @PathVariable String userName,
            @PathVariable String programName,
            Model model
    ) {
        Program program = exerciseService.getProgramByNameAndUser(userName, programName);

        if (program != null) {
            model.addAttribute("program", program);
            model.addAttribute("exercises", program.getExercises());
            model.addAttribute("newExercise", new ExerciseDTO());
            return "programs/programs_details";
        } else {
            return "redirect:/{userName}";
        }
    }

    @PostMapping("/{userName}/{programName}/add-exercise")
    public String addExerciseToProgram(
            @PathVariable String userName,
            @PathVariable String programName,
            @Valid @ModelAttribute("newExercise") ExerciseDTO exerciseDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "redirect:/{userName}/{programName}";
        }

        exerciseService.addExerciseToProgram(userName, programName, exerciseDTO);
        return "redirect:/{userName}/{programName}";
    }
    @PostMapping("/{userName}/{programName}/delete-exercise/{exerciseId}")
    public String deleteExercise(
            @PathVariable String userName,
            @PathVariable String programName,
            @PathVariable Long exerciseId
    ) {
        exerciseService.deleteExercise(exerciseId);
        return "redirect:/{userName}/{programName}";
    }
}
