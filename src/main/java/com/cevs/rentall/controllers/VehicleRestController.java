package com.cevs.rentall.controllers;

import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.User;
import com.cevs.rentall.models.Vehicle;
import com.cevs.rentall.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/renter/vehicle")
public class VehicleRestController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping(path="/all")
    public List<Vehicle> getAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return vehicleService.getAllVehicles(user.getId());
    }


    @PostMapping(path="/car/new")
    public String addNewCar(@Valid CarDto carDto, BindingResult br){
        if(!br.hasErrors()){
            if(vehicleService.addNewCar(carDto)){
                return Boolean.TRUE.toString();
            }
        }
        return  Boolean.FALSE.toString();
    }

    @PostMapping(path="/truck/new")
    public String addNewTruck(@Valid TruckDto truckDto, BindingResult br){
        if(!br.hasErrors()){
            if(vehicleService.addNewTruck(truckDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }
}
