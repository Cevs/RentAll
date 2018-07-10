package com.cevs.rentall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RenterReservationController {
    @RequestMapping("/renter/reservation")
    public String reservations(){
        return "renter-reservations";
    }
}
