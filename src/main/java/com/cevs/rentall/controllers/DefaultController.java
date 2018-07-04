package com.cevs.rentall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
}
