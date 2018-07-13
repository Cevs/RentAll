package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.models.VehicleReservation;
import com.cevs.rentall.services.IVehicleReservations;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/renter/reservation")
public class RenterVehicleReservationRestController {

    @Autowired
    IVehicleReservations vhService;

    @GetMapping(path="/all")
    public List<VehicleReservation> getAllRenterVehicleReservations(@RequestParam("data") String searchValue){
        return vhService.getAllReservations(searchValue);
    }

    @GetMapping(path="/all/{status}")
    public List<VehicleReservation> getReservationWithStatus(@PathVariable("status") String status,
                                                             @RequestParam("data") String searchValue){
        return vhService.getAllReservationsWithStatus(status, searchValue);
    }

    @GetMapping(path="/status/{vehicleType}")
    public List<VehicleReservation> getReservationForVehicleType(@PathVariable("vehicleType") String vehicleType,
                                                                 @RequestParam("data") String searchValue){
        return vhService.getAllReservationsForVehicleType(vehicleType, searchValue);
    }

    @GetMapping(path="/{vehicleType}/{status}")
    public List<VehicleReservation> getVehicleReservations(@PathVariable("vehicleType") String vehicleType,
                                                           @PathVariable("status")String status,
                                                           @RequestParam("data") String searchValue){
        return vhService.getReservations(vehicleType,status, searchValue);
    }

    @DeleteMapping(path="/{id}")
    public  String deleteReservation(@PathVariable int id){
        if(vhService.deleteReservation(id)){
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();
    }

    @PutMapping(path="/{id}")
    public String updateReservationStatus(@PathVariable int id, String status){
        if(vhService.updateReservationStatus(id, status)){
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();
    }
}
