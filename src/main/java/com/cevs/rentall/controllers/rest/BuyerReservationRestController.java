package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.dao.IVehicleReservationDao;
import com.cevs.rentall.models.VehicleReservation;
import com.cevs.rentall.services.IVehicleReservations;
import com.cevs.rentall.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerReservationRestController {

    @Autowired
    IVehicleReservations vehicleReservations;

    @PostMapping("/buyer/vehicle/reservation")
    public String makeReservation(@RequestParam("vehicleId") int id,
                                  @RequestParam("beginning") String beginningDate,
                                  @RequestParam("end") String endDate){

        if(beginningDate != null && !beginningDate.isEmpty() && endDate != null && !endDate.isEmpty()){
            if(vehicleReservations.reserveVehicle(id, beginningDate, endDate)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }
}
