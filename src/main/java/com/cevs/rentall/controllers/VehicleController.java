package com.cevs.rentall.controllers;

import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class VehicleController {

    @GetMapping("/renter/vehicle")
    public String renterVehicle(){
        return "renter-vehicles";
    }

}
