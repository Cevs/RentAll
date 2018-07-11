package com.cevs.rentall.controllers;

import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    IUserService userService;

    @RequestMapping("/renter/profile")
    public String renterProfile(ModelMap model){
        Renter renter = userService.getRenterProfileInfo();
        model.addAttribute("renter",renter);
        return "renter-profile";
    }

    @RequestMapping("/buyer/profile")
    public String buyerProfile(ModelMap model){
        Buyer buyer = userService.getBuyerProfileInfo();
        model.addAttribute("buyer",buyer);
        return "buyer-profile";
    }

}
