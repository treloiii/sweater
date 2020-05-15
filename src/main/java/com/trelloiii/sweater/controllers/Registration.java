package com.trelloiii.sweater.controllers;

import com.trelloiii.sweater.domain.Role;
import com.trelloiii.sweater.domain.User;
import com.trelloiii.sweater.repository.UserRepository;
import com.trelloiii.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

import static com.trelloiii.sweater.controllers.UtilsController.getErrors;

@Controller
public class Registration {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration(){
        System.out.println("jopa");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if(user.getPassword()!=null&&!user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwordError","Passwords are different");
        }
        if(bindingResult.hasErrors()){
            getErrors(bindingResult,model);
            return "registration";
        }else {
            if (userService.addUser(user)) {
                model.addAttribute("usernameError", "user exists");
                return "registration";
            }
            return "redirect:/login";
        }
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActive=userService.activateUser(code);
        if(isActive)
            model.addAttribute("message","User activated");
        else
            model.addAttribute("message","invalid code");
        return "login";
    }
}
