package cz.project.myfit.controller;

import cz.project.myfit.DTO.JwtRequest;
import cz.project.myfit.DTO.ProgramDTO;
import cz.project.myfit.DTO.UserDTO;
import cz.project.myfit.exceptions.AppError;
import cz.project.myfit.model.Program;
import cz.project.myfit.model.User;
import cz.project.myfit.service.ProgramService;
import cz.project.myfit.service.UserService;
import cz.project.myfit.utils.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;


import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;
    private final ProgramService programService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public UserController(UserService userService, ProgramService programService,JwtTokenUtils jwtTokenUtils,AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.programService = programService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("")
    public String redirectToLoginOrUserPage(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login";
        } else {
            // User is authenticated, redirect to user page
            String username = authentication.getName();
            return "redirect:/" + username;
        }
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "users/login";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,Model model, HttpServletRequest request,@RequestBody JwtRequest authenticationRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        if (authentication != null && authentication.isAuthenticated()) {

            model.addAttribute("token", token);

            return "redirect:/" + username;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";
    }

    //All users list
    @GetMapping("/admin")
    public String getUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }
    //Users card
    @GetMapping("/{name}")
    public String getUserByName(@PathVariable("name") String name, Model model) {
        User user = userService.getUserByName(name);

        if (user != null) {
            List<Program> programs = programService.getProgramsByUser(user);
            model.addAttribute("user", user);
            model.addAttribute("programs", programs);
            ProgramDTO newProgram = new ProgramDTO();
            newProgram.setUserId(user.getId());  // Устанавливаем userId в newProgram
            model.addAttribute("newProgram", newProgram);

            return "users/details";
        } else {

            return "redirect:/users";
        }
    }
    //Change implementation with register and login form with token
//    @ResponseBody
//    @PostMapping("/post")
//    public void addUser(@RequestBody UserDTO userDTO) {
//        userService.save(userDTO);
//    }

    //Delete user
    @DeleteMapping("/{name}")
    public String deleteUser(@PathVariable String name, Model model) {
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);

        userService.delete(user.getId());

        return "redirect:/users";
    }
}
