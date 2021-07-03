package com.spring.security.yu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello hi";
    }

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(defaultValue = "false") final Boolean error, Model model) {
        if (error) {
            model.addAttribute("errorMessage", "아이디나 패스워드가 올바르지 않습니다.");
        }
        return "loginForm";
    }
}
