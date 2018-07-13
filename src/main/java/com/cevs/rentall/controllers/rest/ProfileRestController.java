package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.dto.com.cevs.rentall.dto.validation.ValidEmail;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProfileRestController {

    @Autowired
    IUserService userService;

    @PostMapping("/renter/profile")
    public String updateProfileRenter(@Valid RenterDto renterDto, BindingResult br){
        if(br.hasErrors()){
            if(userService.updateProfile(renterDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }

    @PostMapping("/buyer/profile")
    public String updateProfileBuyer(@Valid BuyerDto buyerDto, BindingResult br){
        if(br.hasErrors()){
            if(userService.updateProfile(buyerDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }
}
