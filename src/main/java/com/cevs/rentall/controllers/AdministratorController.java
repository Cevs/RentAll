package com.cevs.rentall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministratorController {
    @RequestMapping("/administrator/users")
    public String administratorUsersPanel(){
        return "administrator-user-panel";
    }
}
