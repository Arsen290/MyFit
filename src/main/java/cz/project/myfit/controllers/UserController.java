package cz.project.myfit.controllers;

import cz.project.myfit.dao.UserDAO;
import cz.project.myfit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        List<User> users = userDAO.getUsers();
        model.addAttribute("users", users);
        return "users/index";
    }
    @ResponseBody
    @PostMapping(value = "post")
    public void addUser(@RequestBody User user){
        userDAO.save(user);
    }
//
//    @DeleteMapping(path = "{userId}")
//    public void deleteUser(@PathVariable("userId") Long userId){
//        userDAO.deleteUser(userId);
//    }
//
//    @PutMapping
//    public void updateUser(@PathVariable("userId") Long userId,
//                           @RequestParam(required = false) String name,
//                           @RequestParam(required = false) String email){
//        userDAO.updateUser(userId, name , email);
//    }

}
