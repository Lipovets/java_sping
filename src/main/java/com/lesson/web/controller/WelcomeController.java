package com.lesson.web.controller;

import com.lesson.user.model.User;
import com.lesson.user.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Value("${com.lesson.java_spring}")
    private String message = "Hello world";

    @RequestMapping("/")
    private String welcome(Map<String, Object> model) {
        User user = customUserDetailsService.getCurrentUser();
        if(user != null) {
            model.put("message",user.getUsername());
        } else {
            model.put("message", this.message);
        }
        return "welcome";
    }


}
