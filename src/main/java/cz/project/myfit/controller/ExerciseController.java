package cz.project.myfit.controller;

import cz.project.myfit.model.Program;
import cz.project.myfit.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
            return "programs/programs_details";
        } else {
            return "redirect:/{userName}";
        }
    }
}
