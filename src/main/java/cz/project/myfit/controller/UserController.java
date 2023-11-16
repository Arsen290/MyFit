package cz.project.myfit.controller;

import cz.project.myfit.model.User;
import cz.project.myfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{name}")
    public String getUserByName(@PathVariable("name") String name, Model model) {
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        return "users/details";
    }

    @ResponseBody
    @PostMapping("/post")
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }
}
