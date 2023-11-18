package cz.project.myfit.controller;

import cz.project.myfit.DTO.UserDTO;
import cz.project.myfit.model.Program;
import cz.project.myfit.model.User;
import cz.project.myfit.service.ProgramService;
import cz.project.myfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;
    private final ProgramService programService;

    @Autowired
    public UserController(UserService userService, ProgramService programService) {
        this.userService = userService;
        this.programService = programService;
    }

    //All users list
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }
    //Users card
    @GetMapping("/{name}")
    public String getUserByName(@PathVariable("name") String name, Model model) {
        User user = userService.getUserByName(name);
        List<Program> programs = programService.getProgramsByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("programs", programs);
        return "users/details";
    }
    //Change implementation with register and login form with token
    @ResponseBody
    @PostMapping("/post")
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    //Delete user
    @DeleteMapping("/{name}")
    public String deleteUser(@PathVariable String name, Model model) {
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);

        userService.delete(user.getId());

        return "redirect:/users";
    }
}
