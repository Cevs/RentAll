package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.models.*;
import com.cevs.rentall.services.IUserService;
import com.cevs.rentall.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchVehiclesOffersRestController {

    @Autowired
    IVehicleService vehicleService;

    @Autowired
    IUserService userService;

    @RequestMapping("/buyer/search/vehicles/offers")
    public List<VehicleRenter> getAllVehicleOffersOfType(@RequestParam("search") String search,
                                                         @RequestParam("type") String type){
       return vehicleService.getAllVehicleRenterOffersOfType(search, type);
    }

    @RequestMapping("/buyer/search/vehicles/offers/car")
    public CarRenter getCarDetail(@RequestParam("id") int id){
        return vehicleService.findCarRenterById(id);
    }

    @RequestMapping("/buyer/search/vehicles/offers/bus")
    public BusRenter getBusDetail(@RequestParam("id") int id){
        return vehicleService.findBusRenterById(id);
    }

    @RequestMapping("/buyer/search/vehicles/offers/truck")
    public TruckRenter getTruckDetail(@RequestParam("id") int id){
        return vehicleService.findTruckRenterById(id);
    }


    @RequestMapping("/buyer/search/vehicles/company-info")
    public RenterInfo getCompanyInfo(@RequestParam("id") int id){
        RenterInfo renterInfo = userService.getRenterInfo(id);
        return renterInfo;
    }
}
