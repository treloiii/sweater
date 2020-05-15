package com.trelloiii.sweater.controllers;

import com.trelloiii.sweater.domain.Role;
import com.trelloiii.sweater.domain.User;
import com.trelloiii.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{user}")
    public String userEditForm(Model model, @PathVariable User user){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userEdit(@RequestParam("userId") User user,
                           @RequestParam Map<String,String> form,
                           @RequestParam String username){

        userService.edit(user,username,form);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("email",user.getEmail());
        return "profile";
    }
    @PostMapping("/profile")
    public String profile(Model model,
                          @AuthenticationPrincipal User user,
                          @RequestParam String password,
                          @RequestParam String email){

        userService.updateProfile(user,password,email);
        return "redirect:/user/profile";

    }

}
