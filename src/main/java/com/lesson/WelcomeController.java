package com.lesson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    @Value("${com.lesson.java_spring}")
    private String message = "Hello world";

    @RequestMapping("/")
    private String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }
}
