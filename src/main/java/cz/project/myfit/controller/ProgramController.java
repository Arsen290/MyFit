package cz.project.myfit.controller;

import cz.project.myfit.DTO.ProgramDTO;
import cz.project.myfit.model.Program;
import cz.project.myfit.model.User;
import cz.project.myfit.service.ProgramService;
import cz.project.myfit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProgramController {
    private final UserService userService;
    private final ProgramService programService;

    @Autowired
    public ProgramController(UserService userService, ProgramService programService) {
        this.userService = userService;
        this.programService = programService;
    }

    @PostMapping("/{userName}/add-program")
    public String addProgramToUser(
            @PathVariable String userName,
            @Valid ProgramDTO programDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "redirect:/{userName}";
        }
        programService.addProgramToUser(userName, programDTO);
        return "redirect:/{userName}";
    }
}
