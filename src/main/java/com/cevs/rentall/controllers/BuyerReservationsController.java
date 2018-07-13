package com.cevs.rentall.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuyerReservationsController {
    @RequestMapping("/buyer/reservation")
    public String reservations(){
        return "buyer-reservations";
    }
}
