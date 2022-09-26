package com.boot.controller;


import com.boot.entity.Role;
import com.boot.entity.User;
import com.boot.repositories.RoleRepository;
import com.boot.services.UserDetailService;
import com.boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@Controller
public class MainController {

    private final UserDetailService userDetailServiceServices;

    private final UserService userService;

    private final RoleRepository roleRepository;

    public MainController(UserDetailService userServices, UserService userService, RoleRepository roleRepository) {
        this.userDetailServiceServices = userServices;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/home")
    public String homePage(Principal principal, Model model) {
        User user = userDetailServiceServices.findByUsername(principal.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userRole", user.getRoles());
        return "home";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("information", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUserDB(@ModelAttribute("information") User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

}
