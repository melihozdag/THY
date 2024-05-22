package com.tk.wallet.controller;

import com.tk.wallet.model.AppUser;
import com.tk.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/viewusers")
    public String viewUsers(Model model) {
        List<AppUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/user_list";
    }

    @GetMapping("/createuser")
    public String createUserForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "user/user_form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute AppUser user, Model model) {
        if (userService.isUserExists(user.getEmail())) {
            model.addAttribute("error", "This email address is already in use.");
            return "user/user_form"; 
        }
    
        userService.saveUser(user);
        return "redirect:/viewusers";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        AppUser user = userService.getUserById(id);
        userService.deleteUser(id);
        model.addAttribute("user", user);
        return "user/user_form";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/viewusers";
    }
}
