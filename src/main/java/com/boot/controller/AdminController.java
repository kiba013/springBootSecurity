package com.boot.controller;


import com.boot.entity.User;
import com.boot.repositories.RoleRepository;
import com.boot.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    // Admin Home Page
    @GetMapping("/admin")
    public String getAllUser(Model model) {
        model.addAttribute("alluser", userService.getAllUser());
        return "admin/listUser";
    }

    // Updating User
    @GetMapping("/admin/edit/{id}")
    public String getForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userInfo", userService.findUserById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/editUser";

    }

    @PostMapping("/admin/edit/{id}")
    public String changeUserInfo(@PathVariable("id") Long id,
                                 @ModelAttribute("userInfo") User user) {
        userService.updateUser(user, id);
        return "redirect:/admin";

    }


    // Creating new User
//    @GetMapping("/admin/registration")
//    public String getRegistrationForm(Model model) {
//        model.addAttribute("userDetail", new User());
//        return "registration";
//    }
//
//    @PostMapping("/admin/registration")
//    public String registration(@ModelAttribute("userDetail") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

    //Delete User
    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
