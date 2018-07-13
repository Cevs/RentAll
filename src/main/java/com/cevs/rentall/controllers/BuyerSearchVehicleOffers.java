package com.cevs.rentall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuyerSearchVehicleOffers {

    @RequestMapping("/buyer/search/vehicles")
    public String searchVehicles(){
        return "buyer-search-vehicles";
    }
}
