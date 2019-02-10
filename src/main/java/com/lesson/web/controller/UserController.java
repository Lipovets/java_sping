package com.lesson.web.controller;

import com.lesson.user.model.User;
import com.lesson.user.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Olexandr
 */
@Controller
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("registration")
    public String addUser(User user, Map<String, Object>  model) {
        User usr = customUserDetailsService.getUser(user.getUsername());
        if(usr != null) {
            model.put("error", "USER HAVE ALREADY EXISTED");
            return "registration";
        } else {
            user.setEnabled(true);
            customUserDetailsService.saveUser(user);
            return "redirect:/login";
        }
    }
}
