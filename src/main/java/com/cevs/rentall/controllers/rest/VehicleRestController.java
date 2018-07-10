package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.dto.BusDto;
import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.*;
import com.cevs.rentall.services.IVehicleService;
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
    IVehicleService vehicleService;

    @GetMapping(path="/all")
    public List<Vehicle> getAll(@RequestParam("data") String searchValue){
        return vehicleService.getAllVehicles(searchValue);
    }

    @GetMapping(path="/{type}")
    public List<Vehicle> getType(@PathVariable String type, @RequestParam("data") String searchValue){
        return vehicleService.getAllVehiclesOfType(type, searchValue);
    }

    @GetMapping(path="/car/{id}")
    public Car getSpecificCar(@PathVariable int id){
        return vehicleService.findCarById(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteVehicle(@PathVariable int id){
        if(vehicleService.deleteVehicle(id)){
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();
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

    @PutMapping(path="/car/update")
    public String updateCar(@Valid CarDto carDto, BindingResult br){
        if(!br.hasErrors()){
            if(vehicleService.updateCar(carDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }

    @GetMapping(path="/truck/{id}")
    public Truck getSpecificTruck(@PathVariable int id){
        return vehicleService.findTruckById(id);
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

    @PutMapping(path="/truck/update")
    public String updateTruck(@Valid TruckDto truckDto, BindingResult br){
        if(!br.hasErrors()){
            if(vehicleService.updateTruck(truckDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }

    @GetMapping(path="/bus/{id}")
    public Bus getSpecificBus(@PathVariable int id){
        return vehicleService.findBusById(id);
    }

    @PutMapping(path="/bus/update")
    public String updateBus(@Valid BusDto busDto, BindingResult br){
        if(!br.hasErrors()){
            if(vehicleService.updateBus(busDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }

    @PostMapping(path="/bus/new")
    public String addNewBus(@Valid BusDto busDto, BindingResult br){
        if(!br.hasErrors()){
            if(vehicleService.addNewBus(busDto)){
                return Boolean.TRUE.toString();
            }
        }
        return Boolean.FALSE.toString();
    }
}
