package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.models.*;
import com.cevs.rentall.services.IUserService;
import com.cevs.rentall.services.IVehicleReservations;
import com.cevs.rentall.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class BuyerVehicleReservationRestController {

    @Autowired
    IVehicleReservations vrService;
    @Autowired
    IVehicleService vehicleService;
    @Autowired
    IUserService userService;

    @RequestMapping("/buyer/reservation/all")
    public List<VehicleReservationRenter> getAllReservations(@RequestParam("search") String searchValue,
                                                             @RequestParam("type") String type,
                                                             @RequestParam("status") String status){
        return vrService.getAllBuyerReservations(searchValue, type, status);
    }

    @RequestMapping("/buyer/reservation/car")
    public CarRenter getCarDetail(@RequestParam("id") int id){
        return vehicleService.findCarRenterById(id);
    }

    @RequestMapping("/buyer/reservation/bus")
    public BusRenter getBusDetail(@RequestParam("id") int id){
        return vehicleService.findBusRenterById(id);
    }

    @RequestMapping("/buyer/reservation/truck")
    public TruckRenter getTruckDetail(@RequestParam("id") int id){
        return vehicleService.findTruckRenterById(id);
    }


    @RequestMapping("/buyer/reservation/company-info")
    public RenterInfo getCompanyInfo(@RequestParam("id") int id){
        RenterInfo renterInfo = userService.getRenterInfo(id);
        return renterInfo;
    }

}
