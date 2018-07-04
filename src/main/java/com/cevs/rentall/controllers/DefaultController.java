package com.cevs.rentall.controllers;

import com.cevs.rentall.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DefaultController {

    @GetMapping("/index")
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return "index";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
}
